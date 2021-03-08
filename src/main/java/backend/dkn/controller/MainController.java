package backend.dkn.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;

import backend.dkn.model.UserAccountModel;
import backend.dkn.model.UserDetailModel;
import backend.dkn.payload.request.LoginRequest;
import backend.dkn.payload.request.SignUpRequest;
import backend.dkn.payload.response.JwtResponse;
import backend.dkn.payload.response.MessageResponse;
import backend.dkn.security.jwt.JwtUtils;
import backend.dkn.security.services.UserDetailsImpl;
import backend.dkn.repository.UserDetailDb;
import backend.dkn.repository.login.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin(origins = "*")
@RestController
public class MainController {
    @Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserDetailDb userDetailDb;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		

		UserAccountModel users = userRepository.findByUsername(userDetails.getUsername()).orElse(null);

		UserDetailModel user = null;
		if (userDetailDb.findByUserAccount(users).size() != 0){
			user = userDetailDb.findByUserAccount(users).get(0);
		}
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 user
                                                 ));
	}

	@PutMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already tacken!"));
		}

		// Create new user's account
		UserAccountModel user = new UserAccountModel(signUpRequest.getUsername(), 
							 encoder.encode(signUpRequest.getPassword()));
							 
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
