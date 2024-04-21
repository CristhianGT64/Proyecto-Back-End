package hn.unah.lenguajes1900.delivery.delivery.dtos;

// import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import hn.unah.lenguajes1900.delivery.delivery.entities.Producto;
import lombok.Data;

@Data
public class InformacionReportes {
    private Long idPedido; //ya

    private String nombreUsuario; //ya

    private String telefono; //ya

    private float latitud;

    private float longitud;

    private String estado;

    private String nombreRepartidor; //ya

    private String placa; //Ya

    private String Marca; //Ya

    private LocalDate fecha; //Ya

    private String hora; //Ya

    private String nombreNegocio;

   private List<Producto> producto;

   private Float total;

   private String imagenNegocio;
}
