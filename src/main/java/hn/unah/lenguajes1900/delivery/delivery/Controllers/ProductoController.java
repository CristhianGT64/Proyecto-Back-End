package hn.unah.lenguajes1900.delivery.delivery.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.lenguajes1900.delivery.delivery.Services.impl.ProductoServiceImpl;
import hn.unah.lenguajes1900.delivery.delivery.entities.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class ProductoController {

    @Autowired
    private ProductoServiceImpl productoServiceImpl;

    @PostMapping("/Producto/GuardarProducto")
    public Boolean GuardarProducto(@RequestBody Producto producto) {

        return this.productoServiceImpl.CrearProducto(producto);
    }
    
}
