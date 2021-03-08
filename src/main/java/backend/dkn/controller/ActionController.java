package backend.dkn.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import backend.dkn.model.SavingsAccountModel;
import backend.dkn.model.TransactionModel;
import backend.dkn.model.TransactionRequestModel;
import backend.dkn.model.UserAccountModel;
import backend.dkn.repository.SavingsAccountDb;
import backend.dkn.repository.TransactionDb;
import backend.dkn.repository.UserAccountDb;
import backend.dkn.service.SavingsAccountService;

@CrossOrigin(origins = "*")
@RestController
public class ActionController {
    @Autowired
    private SavingsAccountService savingsAccountService;

    @Autowired
    private UserAccountDb userAccountDb;

    @Autowired
    private SavingsAccountDb savingsAccountDb;

    @Autowired
    private TransactionDb transactionDb;

    @PutMapping("/account")
    public String createSavingsAccount(@Valid @RequestBody SavingsAccountModel savings, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }
        else{
            String username = request.getUserPrincipal().getName();
            UserAccountModel user = userAccountDb.findByUsername(username).get(0);
            if(user != null){
                savings.setUser(user);
                savingsAccountService.createSavingsAccount(savings);
                return "Savings Account Created";
            }
            return "Your Account doesn't exist!";
        }
    }

    @PostMapping("/account/deposit/{namaTabungan}")
    public String depositSaldo(@Valid @RequestBody TransactionRequestModel transaction,
    BindingResult bindingResult,
    HttpServletRequest request,
    @PathVariable (value = "namaTabungan") String namaTabungan){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }
        else{
            String username = request.getUserPrincipal().getName();
            UserAccountModel user = userAccountDb.findByUsername(username).get(0);

            if(user != null){
                List<SavingsAccountModel> savings = savingsAccountDb.findByUser(user);
                if(savings == null){
                    return "You don't have any savings at that time!";
                }

                for(SavingsAccountModel saving : savings){
                    if(namaTabungan.equalsIgnoreCase(saving.getNama())){
                        // update saldo
                        Integer newSaldo = saving.getSaldo() + transaction.getNominal();
                        saving.setSaldo(newSaldo);
                        savingsAccountDb.save(saving);

                        TransactionModel newTransaction = new TransactionModel();
                        newTransaction.setJenis("deposit");
                        newTransaction.setNominal(transaction.getNominal());
                        newTransaction.setTanggalTransaksi(new Date());
                        newTransaction.setSavings(saving);
                        transactionDb.save(newTransaction);
                        // add new transaction
                        return "Deposit Success!";
                    }
                    return "Your Saving Account doesn't exist!";
                }
            }
            return "Your Account doesn't exist!";
        }
    }

    @PostMapping("/account/withdraw/{namaTabungan}")
    public String withdrawSaldo(@Valid @RequestBody TransactionRequestModel transaction,
    BindingResult bindingResult,
    HttpServletRequest request,
    @PathVariable (value = "namaTabungan") String namaTabungan){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }
        else{
            String username = request.getUserPrincipal().getName();
            UserAccountModel user = userAccountDb.findByUsername(username).get(0);

            if(user != null){
                List<SavingsAccountModel> savings = savingsAccountDb.findByUser(user);
                if(savings == null){
                    return "You don't have any savings at that time!";
                }

                for(SavingsAccountModel saving : savings){
                    if(namaTabungan.equalsIgnoreCase(saving.getNama())){
                        // update saldo
                        if(saving.getSaldo()<transaction.getNominal()){
                            return "Your balance is insufficient!";
                        }
                        Integer newSaldo = saving.getSaldo() - transaction.getNominal();
                        saving.setSaldo(newSaldo);
                        savingsAccountDb.save(saving);

                        TransactionModel newTransaction = new TransactionModel();
                        newTransaction.setJenis("withdraw");
                        newTransaction.setNominal(transaction.getNominal());
                        newTransaction.setTanggalTransaksi(new Date());
                        newTransaction.setSavings(saving);
                        transactionDb.save(newTransaction);
                        // add new transaction
                        return "Withdraw Success!";
                    }
                    return "Your Saving Account doesn't exist!";
                }
            }
            return "Your Account doesn't exist!";
        }
    }

    @GetMapping("/accounts")
    public String findAllSavings(){
        return "";
    }
}
