package backend.dkn.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="user_detail")
public class UserDetailModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tanggal_lahir" ,nullable = false)
    private Date tanggal_lahir;
    
    @NotNull
    @Column(name = "jenis_kelamin", nullable = false)
    private Boolean jenis_kelamin;
    
    @NotNull
    @Size(max = 50)
    @Column(name = "tempat_lahir", nullable = false)
    private String tempat_lahir;

    @NotNull
    @Size(max = 50)
    @Column(name = "alamat", nullable = false)
    private String alamat;

    @NotNull
    @Size(max = 15)
    @Column(name = "telepon", nullable = false)
    private String telepon;

    @NotNull
    @Size(max = 50)
    @Column(name = "email", nullable = false)
    private String email;

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

    public Date getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(Date tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public Boolean getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(Boolean jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
