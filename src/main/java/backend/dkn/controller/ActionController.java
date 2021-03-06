package backend.dkn.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ActionController {
    
    @PostMapping("/account/deposit")
    public String depositSaldo(){
        return "";
    }

    @PostMapping("/account/withdraw")
    public String withdrawSaldo(){
        return "";
    }

    @GetMapping("/accounts")
    public String findAllAccounts(){
        return "";
    }
}
