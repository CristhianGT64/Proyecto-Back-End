package hn.unah.lenguajes1900.delivery.delivery.repositories;

import org.springframework.data.repository.CrudRepository;

import hn.unah.lenguajes1900.delivery.delivery.entities.Usuarios;
import java.util.List;


public interface UsusarioRepositories extends CrudRepository<Usuarios, Long>{
    public List<Usuarios> findByEmail(String email);
}
