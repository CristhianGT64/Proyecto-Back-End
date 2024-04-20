package hn.unah.lenguajes1900.delivery.delivery.entities;

import jakarta.persistence.CascadeType;
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
@Table(name = "detallepedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetallepedido")
    private Long iddetallepedido;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idpedido", referencedColumnName = "idpedido")
    private Pedido pedido;

    @OneToOne
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    private Producto producto;

    private Integer cantidad;

    private Float preciounitario;
    
}
