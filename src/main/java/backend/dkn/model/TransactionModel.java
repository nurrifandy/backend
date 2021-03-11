package backend.dkn.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "transaksi")
public class TransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max=50)
    @Column(name = "jenis", nullable = false)
    private String jenis;

    @NotNull
    @DateTimeFormat(iso = ISO.DATE)
    @Column(name="tanggal_transaksi", nullable = false)
    private Date tanggalTransaksi;

    @NotNull
    @Column(name = "nominal", nullable = false)
    private Integer nominal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tabungan", referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SavingsAccountModel savings;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public Date getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(Date tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public SavingsAccountModel getSavings() {
        return savings;
    }

    public void setSavings(SavingsAccountModel savings) {
        this.savings = savings;
    }
}
