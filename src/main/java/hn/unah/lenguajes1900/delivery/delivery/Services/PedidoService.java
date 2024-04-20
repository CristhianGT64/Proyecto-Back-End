package hn.unah.lenguajes1900.delivery.delivery.Services;

import hn.unah.lenguajes1900.delivery.delivery.dtos.InformacionPedido;
import hn.unah.lenguajes1900.delivery.delivery.entities.DetallePedido;
import hn.unah.lenguajes1900.delivery.delivery.entities.Pedido;

public interface PedidoService {
    
    public InformacionPedido TraerPedidoNuevo(Long idRepartidor); //Esta hecho para que el repartidor vea los pedidos que le toca

}
