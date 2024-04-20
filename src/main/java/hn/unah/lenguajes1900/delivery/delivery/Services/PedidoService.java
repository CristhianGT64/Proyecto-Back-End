package hn.unah.lenguajes1900.delivery.delivery.Services;

import hn.unah.lenguajes1900.delivery.delivery.entities.Pedido;

public interface PedidoService {
    
    public Pedido TraerPedidoNuevo(Long idRepartidor); //Esta hecho para que el repartidor vea los pedidos que le toca

}
