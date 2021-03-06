package backend.dkn.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_account", uniqueConstraints = {
    @UniqueConstraint(columnNames = "username")
})
public class UserAccountModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    @Size(max = 255)
    private String password;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user_account")
    private UserDetailModel userDetail;

    public UserAccountModel(){

    }

    public UserAccountModel(String username, String password){
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDetailModel getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetailModel userDetail) {
        this.userDetail = userDetail;
    }

}