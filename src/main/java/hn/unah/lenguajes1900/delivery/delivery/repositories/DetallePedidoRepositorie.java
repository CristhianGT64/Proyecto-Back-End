package hn.unah.lenguajes1900.delivery.delivery.repositories;

import org.springframework.data.repository.CrudRepository;

import hn.unah.lenguajes1900.delivery.delivery.entities.DetallePedido;
import hn.unah.lenguajes1900.delivery.delivery.entities.Pedido;

import java.util.List;


public interface DetallePedidoRepositorie extends CrudRepository<DetallePedido, Long>{
    public List<DetallePedido> findByPedido(Pedido pedido);
}
