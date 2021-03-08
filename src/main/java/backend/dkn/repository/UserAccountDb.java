package backend.dkn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.dkn.model.UserAccountModel;

@Repository
public interface UserAccountDb extends JpaRepository<UserAccountModel, Integer> {
    List<UserAccountModel> findByUsername(String username);
}
