package backend.dkn.repository.login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.dkn.model.UserAccountModel;

@Repository
public interface UserRepository extends JpaRepository<UserAccountModel, Integer>{
    Optional<UserAccountModel> findByUsername(String username);
    Boolean existsByUsername(String username);
}
