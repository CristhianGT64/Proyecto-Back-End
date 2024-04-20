package hn.unah.lenguajes1900.delivery.delivery.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import hn.unah.lenguajes1900.delivery.delivery.Services.DetallePedidoService;
import hn.unah.lenguajes1900.delivery.delivery.entities.DetallePedido;
import hn.unah.lenguajes1900.delivery.delivery.repositories.DetallePedidoRepositorie;

public class DetallePedidoServiceImpl implements DetallePedidoService{

    @Autowired
    private DetallePedidoRepositorie detallePedidoRepositorie;

    @Override
    public DetallePedido crearDetallePedido(DetallePedido detallePedido) {
        return this.detallePedidoRepositorie.save(detallePedido);
    }
    
}
