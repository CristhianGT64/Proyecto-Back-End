package hn.unah.lenguajes1900.delivery.delivery.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Facturaid")
    private Long Facturaid;

    private Long idpedido;

    private String nombreusuario;

    private String nombrerapartidor;

    private Date fechaemision;

    private Float totalpagar;

}
