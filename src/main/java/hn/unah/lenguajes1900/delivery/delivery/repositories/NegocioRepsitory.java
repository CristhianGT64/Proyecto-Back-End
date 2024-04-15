package hn.unah.lenguajes1900.delivery.delivery.repositories;

import org.springframework.data.repository.CrudRepository;

import hn.unah.lenguajes1900.delivery.delivery.entities.Negocio;
import hn.unah.lenguajes1900.delivery.delivery.entities.Usuarios;

import java.util.List;


public interface NegocioRepsitory extends CrudRepository<Negocio, Long>{

    public List<Negocio> findByNombre(String nombre);

    public Negocio findByUsuarios(Usuarios usuarios);    
}
