package hn.unah.lenguajes1900.delivery.delivery.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.lenguajes1900.delivery.delivery.Services.impl.ProductoServiceImpl;
import hn.unah.lenguajes1900.delivery.delivery.entities.Producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
public class ProductoController {

    @Autowired
    private ProductoServiceImpl productoServiceImpl;

    @PostMapping("/Producto/GuardarProducto")
    public Boolean GuardarProducto(@RequestBody Producto producto) {

        return this.productoServiceImpl.CrearProducto(producto);
    }

    @GetMapping("/Producto/ProductoxNegocio")
    public List<Producto>  TraerProductoXnegocio(@RequestParam Long idNegocio) {
        return this.productoServiceImpl.TraerProductosxNegocio(idNegocio);
    }
    
    
}
