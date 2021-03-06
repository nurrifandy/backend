package backend.dkn.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class MainController {
    @PostMapping("/signin")
    public String authenticateUser(@Valid @RequestBody String loginRequest){
        return "";
    }

    @PutMapping("/signup")
    public String registerUser(@Valid @RequestBody String signUpRequest){
        return "";
    }
}
