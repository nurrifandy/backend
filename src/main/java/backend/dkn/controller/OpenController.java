package backend.dkn.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/dashboard")
public class OpenController {
    
    @GetMapping("/daftar-pengguna")
    public String daftarPenggunaTerdaftar(Model model){
        // get all users

        return "dashboard-pengguna";
    }
}
