package hn.unah.lenguajes1900.delivery.delivery.Services;

import java.util.List;
import java.util.Optional;

// import org.hibernate.mapping.List;

import hn.unah.lenguajes1900.delivery.delivery.dtos.InformacionPedido;
import hn.unah.lenguajes1900.delivery.delivery.dtos.InformacionReportes;
import hn.unah.lenguajes1900.delivery.delivery.entities.DetallePedido;
import hn.unah.lenguajes1900.delivery.delivery.entities.Pedido;

public interface PedidoService {
    
    public InformacionPedido TraerPedidoNuevo(Long idRepartidor); //Esta hecho para que el repartidor vea los pedidos que le toca

    public Boolean FinalizarPedido(Long idPedido);

    public Long crearPedido(Pedido pedido);

    public List<InformacionReportes> TodoslosPedidos();

    public List<InformacionReportes> PedidosxNegocio(Long idNegocio);

    public InformacionReportes ReporteUnico(Long idPedido);

    public List<InformacionReportes> PedidosxUsuario(Long idUsuario);

    public void cancelarPedido(Long idPedido);

}
