package hn.unah.lenguajes1900.delivery.delivery.Services.impl;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.lenguajes1900.delivery.delivery.Services.DetallePedidoService;
import hn.unah.lenguajes1900.delivery.delivery.entities.DetallePedido;
import hn.unah.lenguajes1900.delivery.delivery.repositories.DetallePedidoRepositorie;
import hn.unah.lenguajes1900.delivery.delivery.repositories.PedidoRepositorie;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService{

    @Autowired
    private DetallePedidoRepositorie detallePedidoRepositorie;

    @Autowired
    private PedidoRepositorie pedidoRepositorie;

    @Override
    public DetallePedido crearDetallePedido(DetallePedido detallePedido) {
        return this.detallePedidoRepositorie.save(detallePedido);
    }
    
}
