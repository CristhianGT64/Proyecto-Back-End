package hn.unah.lenguajes1900.delivery.delivery.entities;


// import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrol")
    private Integer idrol;

    private String nombrerol;

    private String descripcionrol;

    // @JsonIgnore
    // @OneToOne(mappedBy = "roles")
    // private Usuarios usuarios;
}
