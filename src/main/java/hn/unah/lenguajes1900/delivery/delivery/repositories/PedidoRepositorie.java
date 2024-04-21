package hn.unah.lenguajes1900.delivery.delivery.repositories;

import org.springframework.data.repository.CrudRepository;

import hn.unah.lenguajes1900.delivery.delivery.entities.Negocio;
import hn.unah.lenguajes1900.delivery.delivery.entities.Pedido;
import hn.unah.lenguajes1900.delivery.delivery.entities.Usuarios;

import java.util.List;


public interface PedidoRepositorie extends CrudRepository<Pedido, Long>{
    public List<Pedido> findByUsuario(Usuarios usuario);

    public List<Pedido> findByRepartidor(Usuarios repartidor);

    public List<Pedido> findByNegocio(Negocio negocio);
}
