package hn.unah.lenguajes1900.delivery.delivery.repositories;

import org.springframework.data.repository.CrudRepository;

import hn.unah.lenguajes1900.delivery.delivery.entities.Categoria;
import hn.unah.lenguajes1900.delivery.delivery.entities.Negocio;
import hn.unah.lenguajes1900.delivery.delivery.entities.Producto;
import java.util.List;


public interface ProductoRepositorie extends CrudRepository<Producto, Long>{
    public List<Producto> findByNegocio(Negocio negocio);

    public List<Producto> findByCategoria(Categoria categoria);
}
