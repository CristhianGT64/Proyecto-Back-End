package hn.unah.lenguajes1900.delivery.delivery.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data   
@Table(name = "usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Long idusuario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idpersona", referencedColumnName = "idpersona")
    private Personas personas;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idrol", referencedColumnName = "idrol")
    private Roles roles;

    private String email;

    private String contrasena;

    private String  telefono;

    private Float latitud;

    private Float longitud;
}
