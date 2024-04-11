package hn.unah.lenguajes1900.delivery.delivery.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "personas")
public class Personas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpersona")
    private Long idpersona;

    private String primernombre;

    private String segundonombre;

    private String primerapellido;

    private String segundoapellido;
    
    @JsonIgnore
    @OneToOne(mappedBy = "personas")
    private Usuarios usuarios;
}
