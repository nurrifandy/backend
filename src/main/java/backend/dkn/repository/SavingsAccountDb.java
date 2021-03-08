package backend.dkn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.dkn.model.SavingsAccountModel;
import backend.dkn.model.UserAccountModel;

@Repository
public interface SavingsAccountDb extends JpaRepository<SavingsAccountModel, Integer> {
    List<SavingsAccountModel> findByUser(UserAccountModel user);
}
