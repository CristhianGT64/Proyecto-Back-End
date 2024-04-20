package hn.unah.lenguajes1900.delivery.delivery.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.lenguajes1900.delivery.delivery.Services.impl.DetallePedidoServiceImpl;
import hn.unah.lenguajes1900.delivery.delivery.entities.DetallePedido;

@RestController
@RequestMapping("/api")
public class DetalleServiceImpl {

    @Autowired
    private DetallePedidoServiceImpl detallePedidoServiceImpl;


    @PostMapping("/detallePedido/crear")
    public DetallePedido crearDetallePedido(@RequestBody DetallePedido detallePedido){
        return this.detallePedidoServiceImpl.crearDetallePedido(detallePedido);
    }

    
}
