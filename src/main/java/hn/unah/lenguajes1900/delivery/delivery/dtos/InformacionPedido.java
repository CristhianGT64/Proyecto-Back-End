package hn.unah.lenguajes1900.delivery.delivery.dtos;

import java.util.List;

import hn.unah.lenguajes1900.delivery.delivery.entities.Producto;
import lombok.Data;

@Data
public class InformacionPedido {
     private Long idPedido;

     private String nombreUsuario;

     private String telefono;

     private float latitud;

     private float longitud;

     private String nombreNegocio;

    private List<Producto> producto;

}
