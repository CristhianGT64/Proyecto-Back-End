package hn.unah.lenguajes1900.delivery.delivery.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.lenguajes1900.delivery.delivery.Services.FacturaService;
import hn.unah.lenguajes1900.delivery.delivery.entities.Factura;
import hn.unah.lenguajes1900.delivery.delivery.repositories.FacturaRepositorie;

@Service
public class FacturaServiceImpl implements FacturaService{

    @Autowired
    private FacturaRepositorie facturaRepositorie;

    @Override
    public void GenerarFactura(Factura factura) {
        this.facturaRepositorie.save(factura);
    }
    
}
