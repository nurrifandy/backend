package backend.dkn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.dkn.model.TransactionModel;

@Repository
public interface TransactionDb extends JpaRepository<TransactionModel, Integer> {

}
