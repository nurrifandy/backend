package backend.dkn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.dkn.model.UserAccountModel;
import backend.dkn.model.UserDetailModel;

@Repository
public interface UserDetailDb extends JpaRepository<UserDetailModel, Integer> {
    List<UserDetailModel> findByUserAccount(UserAccountModel userAccount);   
}
