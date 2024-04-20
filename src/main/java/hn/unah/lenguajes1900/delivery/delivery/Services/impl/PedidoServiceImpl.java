package hn.unah.lenguajes1900.delivery.delivery.Services.impl;

import java.util.List;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.lenguajes1900.delivery.delivery.Services.PedidoService;
import hn.unah.lenguajes1900.delivery.delivery.entities.DetallePedido;
import hn.unah.lenguajes1900.delivery.delivery.entities.Pedido;
import hn.unah.lenguajes1900.delivery.delivery.entities.Usuarios;
import hn.unah.lenguajes1900.delivery.delivery.repositories.PedidoRepositorie;
import hn.unah.lenguajes1900.delivery.delivery.repositories.UsusarioRepositories;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    private PedidoRepositorie pedidoRepositorie;

    @Autowired
    private UsusarioRepositories ususarioRepositories;

    @Override
    public Pedido TraerPedidoNuevo(Long idRepartidor) {
        Usuarios repartidor = this.ususarioRepositories.findById(idRepartidor).get();

        List<Pedido> listaPedidos = this.pedidoRepositorie.findByUsuario(repartidor);

        for (Pedido pedido2 : listaPedidos) {
            if (pedido2.getEstado() == null) {
                return pedido2;
            }
        }

        return null;


    }
    
}
