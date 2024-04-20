package hn.unah.lenguajes1900.delivery.delivery.entities;

import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
// import hn.unah.lenguajes1900.delivery.delivery.dtos.Repartidor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPedido")
    private Long idpedido;

    @ManyToOne()
    @JoinColumn(name = "idusuariofinal" , referencedColumnName = "idusuario")
    private Usuarios usuario;

    @ManyToOne()
    @JoinColumn(name = "idrepartidor", referencedColumnName = "idusuario")
    private Usuarios repartidor;

    @ManyToOne()
    @JoinColumn(name = "idnegocio" , referencedColumnName = "idnegocio")
    private Negocio negocio;

    private String estado;

    private Float total;

    private Date fecha;

    private Time hora;

    @JsonIgnore
    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private DetallePedido detallePedido;
}
