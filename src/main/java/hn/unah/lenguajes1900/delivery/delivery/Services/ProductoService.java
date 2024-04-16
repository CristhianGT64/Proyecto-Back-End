package hn.unah.lenguajes1900.delivery.delivery.Services;

import java.util.List;

import hn.unah.lenguajes1900.delivery.delivery.entities.Producto;

public interface ProductoService {
    

    public Boolean CrearProducto(Producto producto);

    public List<Producto> TraerProductosxNegocio(Float idNegocio);

}
