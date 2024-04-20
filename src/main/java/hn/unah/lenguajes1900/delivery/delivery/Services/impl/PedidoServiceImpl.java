package hn.unah.lenguajes1900.delivery.delivery.Services.impl;

import java.util.ArrayList;
import java.util.List;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.lenguajes1900.delivery.delivery.Services.PedidoService;
import hn.unah.lenguajes1900.delivery.delivery.dtos.InformacionPedido;
import hn.unah.lenguajes1900.delivery.delivery.entities.DetallePedido;
import hn.unah.lenguajes1900.delivery.delivery.entities.Pedido;
import hn.unah.lenguajes1900.delivery.delivery.entities.Producto;
import hn.unah.lenguajes1900.delivery.delivery.entities.Usuarios;
import hn.unah.lenguajes1900.delivery.delivery.repositories.DetallePedidoRepositorie;
import hn.unah.lenguajes1900.delivery.delivery.repositories.PedidoRepositorie;
import hn.unah.lenguajes1900.delivery.delivery.repositories.UsusarioRepositories;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    private PedidoRepositorie pedidoRepositorie;

    @Autowired
    private UsusarioRepositories ususarioRepositories;

    @Autowired
    private DetallePedidoRepositorie detallePedidoRepositorie;

    @Override
    public InformacionPedido TraerPedidoNuevo(Long idRepartidor) {
        Usuarios repartidor = this.ususarioRepositories.findById(idRepartidor).get();

        List<Pedido> listaPedidos = this.pedidoRepositorie.findByRepartidor(repartidor);

        InformacionPedido informacionPedido = new InformacionPedido();

        for (Pedido pedido2 : listaPedidos) {
            if (pedido2.getEstado() == null) {
                //Me trae la informacion de los productos que se lleva el usuario
                List<DetallePedido> detallePedido = this.detallePedidoRepositorie.findByPedido(pedido2);

                //Nuevo objeto de producto para agregarlo despues al dto
                
                Producto producto = new Producto();
                List<Producto> listaProductos = new ArrayList<>() {
                    
                };


                for (DetallePedido detallePedido2 : detallePedido) {
                    producto.setCantidad(detallePedido2.getCantidad());
                    producto.setNombre(detallePedido2.getProducto().getNombre());
                    producto.setImagen(detallePedido2.getProducto().getImagen());
                    producto.setPrecio(detallePedido2.getProducto().getPrecio());
                    producto.setDescripcion(detallePedido2.getProducto().getDescripcion());
                    listaProductos.add(producto);
                }
                informacionPedido.setProducto(listaProductos);
                informacionPedido.setIdPedido(pedido2.getIdpedido());
                informacionPedido.setNombreUsuario(pedido2.getUsuario().getPersonas().getPrimerapellido() + " " + pedido2.getUsuario().getPersonas().getPrimerapellido());
                informacionPedido.setTelefono(pedido2.getUsuario().getTelefono());
                informacionPedido.setLatitud(pedido2.getUsuario().getLatitud());
                informacionPedido.setLongitud(pedido2.getUsuario().getLongitud());
                informacionPedido.setNombreNegocio(pedido2.getNegocio().getNombre());

                return informacionPedido;
            }
        }
        return null;
    }
    
}
