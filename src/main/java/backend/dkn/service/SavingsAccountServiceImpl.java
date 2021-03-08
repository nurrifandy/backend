package backend.dkn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.dkn.model.SavingsAccountModel;
import backend.dkn.repository.SavingsAccountDb;

@Service
@Transactional
public class SavingsAccountServiceImpl implements SavingsAccountService{
    @Autowired
    SavingsAccountDb savingsAccountDb;

    @Override
    public SavingsAccountModel createSavingsAccount(SavingsAccountModel savings){
        return savingsAccountDb.save(savings);
    }
}
