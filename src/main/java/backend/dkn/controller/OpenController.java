package backend.dkn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.dkn.model.SavingsAccountModel;
import backend.dkn.model.UserAccountModel;
import backend.dkn.model.UserDetailModel;
import backend.dkn.repository.UserAccountDb;

@Controller
public class OpenController {
    @Autowired
    private UserAccountDb userAccountDb;

    @GetMapping("/")
    public String daftarPenggunaTerdaftar(Model model){
        // get all users
        List<UserAccountModel> users = userAccountDb.findAll();
        model.addAttribute("users", users);
        return "dashboard-pengguna";
    }

    @GetMapping("/profil/{username}")
    public String viewProfile(@PathVariable String username, Model model){
        UserAccountModel user = userAccountDb.findByUsername(username).get(0);
        UserDetailModel profil = user.getUserDetail();
        Boolean nullValue = false;
        if (profil == null){
            nullValue = true;
        }
        model.addAttribute("nullValue", nullValue);
        model.addAttribute("profil", profil);
        return "view-profil";
    }

    @GetMapping("/tabungan/{username}")
    public String listTabungan(@PathVariable String username, Model model){
        UserAccountModel user = userAccountDb.findByUsername(username).get(0);
        List<SavingsAccountModel> savings = user.getSavings();
        Boolean nullValue = false;
        if (savings == null){
            nullValue = true;
        }
        model.addAttribute("nullValue", nullValue);
        model.addAttribute("savings", savings);
        return "view-tabungan";
    }
}
