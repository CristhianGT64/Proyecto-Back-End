package hn.unah.lenguajes1900.delivery.delivery.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.lenguajes1900.delivery.delivery.Services.impl.FacturaServiceImpl;
import hn.unah.lenguajes1900.delivery.delivery.entities.Factura;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class FacturaControlller {
        @Autowired
        private FacturaServiceImpl facturaServiceImpl;


        //Una vez que el repoartidor entregue el pedido se hace la entrega de la factura y se
        //guarda en la base de datps
        @PostMapping("/Factura/GuardarFactura")
        public void GuardarFactura(@RequestBody Factura factura) {
            this.facturaServiceImpl.GenerarFactura(factura);;
        }
        
}
