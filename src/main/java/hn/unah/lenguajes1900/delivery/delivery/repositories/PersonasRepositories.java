package hn.unah.lenguajes1900.delivery.delivery.repositories;

import org.springframework.data.repository.CrudRepository;

import hn.unah.lenguajes1900.delivery.delivery.entities.Personas;

public interface PersonasRepositories extends CrudRepository<Personas, Long>{
    
}
