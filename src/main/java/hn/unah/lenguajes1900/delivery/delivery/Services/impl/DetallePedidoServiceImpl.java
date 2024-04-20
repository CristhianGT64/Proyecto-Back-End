package hn.unah.lenguajes1900.delivery.delivery.Services.impl;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

import hn.unah.lenguajes1900.delivery.delivery.Services.DetallePedidoService;
import hn.unah.lenguajes1900.delivery.delivery.entities.DetallePedido;
import hn.unah.lenguajes1900.delivery.delivery.entities.Pedido;
import hn.unah.lenguajes1900.delivery.delivery.repositories.DetallePedidoRepositorie;

public class DetallePedidoServiceImpl implements DetallePedidoService{

    @Autowired
    private DetallePedidoRepositorie detallePedidoRepositorie;

    @Override
    public DetallePedido crearDetallePedido(DetallePedido detallePedido) {

        Pedido pedido = detallePedido.getPedido();
        
        LocalTime horaActual= LocalTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
        String horaConFormato = horaActual.format(formato);
        pedido.setFecha(LocalDate.now());
        pedido.setHora(horaConFormato);

        detallePedido.setPedido(pedido);
        return this.detallePedidoRepositorie.save(detallePedido);
    }
    
}
