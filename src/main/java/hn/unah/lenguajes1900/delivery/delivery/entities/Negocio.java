package hn.unah.lenguajes1900.delivery.delivery.entities;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "negocio")
public class Negocio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnegocio")
    private long idnegocio;

    private String nombre;

    private String telefono;

    private String hora_apertura;

    private String hora_cierre;

    private float latitud;

    private float longitud;

    private String descripcion;

    private String imagen;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    private Usuarios usuarios;

    @JsonIgnore
    @OneToMany(mappedBy = "negocio")
    private List<Producto> producto;
}
