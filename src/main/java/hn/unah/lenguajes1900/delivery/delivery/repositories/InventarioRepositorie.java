package hn.unah.lenguajes1900.delivery.delivery.repositories;

import org.springframework.data.repository.CrudRepository;

import hn.unah.lenguajes1900.delivery.delivery.entities.Inventario;

public interface InventarioRepositorie extends CrudRepository<Inventario, Long>{
    
}
