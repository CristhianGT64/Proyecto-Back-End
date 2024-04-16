package hn.unah.lenguajes1900.delivery.delivery.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import hn.unah.lenguajes1900.delivery.delivery.entities.Categoria;

public interface CategoriaRepositorie extends CrudRepository<Categoria, Long>{
    
    public List<Categoria> findByNombre(String nombre);
}
