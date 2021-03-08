package backend.dkn.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="savingsAccount")
public class SavingsAccountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max=50)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "saldo", nullable = false)
    private Integer saldo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "savings")
    @JsonIgnore
    private List<TransactionModel> transaction;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserAccountModel user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public UserAccountModel getUser() {
        return user;
    }

    public void setUser(UserAccountModel user) {
        this.user = user;
    }

    public List<TransactionModel> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<TransactionModel> transaction) {
        this.transaction = transaction;
    }
}
