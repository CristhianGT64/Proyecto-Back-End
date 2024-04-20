package hn.unah.lenguajes1900.delivery.delivery.repositories;

import org.springframework.data.repository.CrudRepository;

import hn.unah.lenguajes1900.delivery.delivery.entities.Pedido;

public interface PedidoRepositorie extends CrudRepository<Pedido, Long>{
    
}
