package hn.unah.lenguajes1900.delivery.delivery.Services;

import java.util.List;

import org.springframework.expression.spel.ast.BooleanLiteral;

import hn.unah.lenguajes1900.delivery.delivery.entities.Producto;

public interface ProductoService {
    

    public Boolean CrearProducto(Producto producto);

    public List<Producto> TraerProductosxNegocio(Long idNegocio);

    public Producto BuscarProducto(Long idProducto);

    public Boolean ActualizarProducto(Producto producto);

    public Boolean EliminarProducto(Long idProducto);

}
