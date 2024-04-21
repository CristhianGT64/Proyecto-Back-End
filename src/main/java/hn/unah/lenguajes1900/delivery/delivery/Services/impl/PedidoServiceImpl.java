package hn.unah.lenguajes1900.delivery.delivery.Services.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.lenguajes1900.delivery.delivery.Services.PedidoService;
import hn.unah.lenguajes1900.delivery.delivery.dtos.InformacionPedido;
import hn.unah.lenguajes1900.delivery.delivery.dtos.InformacionReportes;
import hn.unah.lenguajes1900.delivery.delivery.entities.DetallePedido;
import hn.unah.lenguajes1900.delivery.delivery.entities.Negocio;
import hn.unah.lenguajes1900.delivery.delivery.entities.Pedido;
import hn.unah.lenguajes1900.delivery.delivery.entities.Producto;
import hn.unah.lenguajes1900.delivery.delivery.entities.Usuarios;
import hn.unah.lenguajes1900.delivery.delivery.repositories.DetallePedidoRepositorie;
import hn.unah.lenguajes1900.delivery.delivery.repositories.NegocioRepsitory;
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

    @Autowired
    private NegocioRepsitory negocioRepsitory;

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
                
                List<Producto> listaProductos = new ArrayList<>() {
                    
                };

                //Recorremos el arreglo de detalles de pedidos para meterlos en una lista de productos
                for (DetallePedido detallePedido2 : detallePedido) {
                    Producto producto = new Producto();
                    producto.setCantidad(detallePedido2.getCantidad());
                    producto.setNombre(detallePedido2.getProducto().getNombre());
                    producto.setImagen(detallePedido2.getProducto().getImagen());
                    producto.setPrecio(detallePedido2.getProducto().getPrecio());
                    producto.setDescripcion(detallePedido2.getProducto().getDescripcion());
                    listaProductos.add(producto);
                }
                informacionPedido.setProducto(listaProductos);
                informacionPedido.setIdPedido(pedido2.getIdpedido());
                informacionPedido.setNombreUsuario(pedido2.getUsuario().getPersonas().getPrimernombre() + " " + pedido2.getUsuario().getPersonas().getPrimerapellido());
                informacionPedido.setTelefono(pedido2.getUsuario().getTelefono());
                informacionPedido.setLatitud(pedido2.getUsuario().getLatitud());
                informacionPedido.setLongitud(pedido2.getUsuario().getLongitud());
                informacionPedido.setNombreNegocio(pedido2.getNegocio().getNombre());

                return informacionPedido;
            }
        }
        return null;
    }

    @Override
    public Boolean FinalizarPedido(Long idPedido) {
        try {
            //Esta api modifica el estado del pedido y la disponibilidad de repartidor
            Pedido pedido = this.pedidoRepositorie.findById(idPedido).get();
            pedido.setEstado("Entregado");
            this.pedidoRepositorie.save(pedido);

            Usuarios repartidor = this.ususarioRepositories.findById(pedido.getRepartidor().getIdusuario()).get();
            repartidor.setEstado(1);
            this.ususarioRepositories.save(repartidor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Long crearPedido(Pedido pedido) {

        //Generamos un nuevo pedid
        LocalTime horaActual= LocalTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
        String horaConFormato = horaActual.format(formato);

        pedido.setFecha(LocalDate.now());
        pedido.setHora(horaConFormato);
        
        Pedido pedido1 = this.pedidoRepositorie.save(pedido);

        return pedido1.getIdpedido();
    }

    @Override
    public List<InformacionReportes> TodoslosPedidos() {
        //Traemos todos los pedidos hechos que etsna en la base de datos
        List<Pedido> listaPedidos = (List<Pedido>) this.pedidoRepositorie.findAll();

        //Hacer una lista donde se guardaran la informacion de los reportes
        List<InformacionReportes> ListaReportes = new ArrayList<>();

        //Iteramos la lista de los pedidos
        for (Pedido pedido2 : listaPedidos) {
                //Me trae la informacion de los productos que se lleva el usuario
                List<DetallePedido> detallePedido = this.detallePedidoRepositorie.findByPedido(pedido2);

                //Lista donde se guardara la lista de productos que tiene detalle pedido
                List<Producto> listaProductos = new ArrayList<>();

                 //Creamos un arreglo para meter los detalles de los productos dentro
                InformacionReportes InformacionReportes = new InformacionReportes();

                //Recorremos el arreglo de detalles de pedidos para meterlos en una lista de productos
                for (DetallePedido detallePedido2 : detallePedido) {
                //Nuevo objeto de producto para agregarlo despues al dto
                    Producto producto = new Producto();
                    producto.setCantidad(detallePedido2.getCantidad());
                    producto.setNombre(detallePedido2.getProducto().getNombre());
                    producto.setImagen(detallePedido2.getProducto().getImagen());
                    producto.setPrecio(detallePedido2.getProducto().getPrecio());
                    producto.setDescripcion(detallePedido2.getProducto().getDescripcion());
                    listaProductos.add(producto);
                }
                //Seteamos todo a informacionReportes para que el arreglo en el fron sea super mas facil
                InformacionReportes.setProducto(listaProductos);
                InformacionReportes.setEstado(pedido2.getEstado());
                InformacionReportes.setIdPedido(pedido2.getIdpedido());
                InformacionReportes.setNombreUsuario(pedido2.getUsuario().getPersonas().getPrimernombre() + " " + pedido2.getUsuario().getPersonas().getPrimerapellido());
                InformacionReportes.setTelefono(pedido2.getUsuario().getTelefono());
                InformacionReportes.setLatitud(pedido2.getUsuario().getLatitud());
                InformacionReportes.setLongitud(pedido2.getUsuario().getLongitud());
                InformacionReportes.setNombreNegocio(pedido2.getNegocio().getNombre());
                InformacionReportes.setTotal(pedido2.getTotal());
                InformacionReportes.setNombreRepartidor(pedido2.getRepartidor().getPersonas().getPrimernombre()+" "+ pedido2.getRepartidor().getPersonas().getPrimerapellido());
                InformacionReportes.setPlaca(pedido2.getRepartidor().getVehiculo().getPlaca());
                InformacionReportes.setMarca(pedido2.getRepartidor().getVehiculo().getMarca()); 
                InformacionReportes.setFecha(pedido2.getFecha()); 
                InformacionReportes.setHora(pedido2.getHora());
                ListaReportes.add(InformacionReportes);
        }
        return ListaReportes;
    }

    @Override
    public List<InformacionReportes> PedidosxNegocio(Long idNegocio) {
        //Retornar reportes de un pedido en especifico
        Negocio negocio = this.negocioRepsitory.findById(idNegocio).get();

        //Traer un listado de los pedidos por reporte
        List<Pedido> listaPedidos = this.pedidoRepositorie.findByNegocio(negocio);

        //Hacer una lista donde se guardaran la informacion de los reportes
        List<InformacionReportes> ListaReportes = new ArrayList<>();

        //Iteramos la lista de los pedidos
        for (Pedido pedido2 : listaPedidos) {
                //Me trae la informacion de los productos que se lleva el usuario
                List<DetallePedido> detallePedido = this.detallePedidoRepositorie.findByPedido(pedido2);

                //Lista donde se guardara la lista de productos que tiene detalle pedido
                List<Producto> listaProductos = new ArrayList<>();

                 //Creamos un arreglo para meter los detalles de los productos dentro
                InformacionReportes InformacionReportes = new InformacionReportes();

                //Recorremos el arreglo de detalles de pedidos para meterlos en una lista de productos
                for (DetallePedido detallePedido2 : detallePedido) {
                //Nuevo objeto de producto para agregarlo despues al dto
                    Producto producto = new Producto();
                    producto.setCantidad(detallePedido2.getCantidad());
                    producto.setNombre(detallePedido2.getProducto().getNombre());
                    producto.setImagen(detallePedido2.getProducto().getImagen());
                    producto.setPrecio(detallePedido2.getProducto().getPrecio());
                    producto.setDescripcion(detallePedido2.getProducto().getDescripcion());
                    listaProductos.add(producto);
                }
                //Seteamos todo a informacionReportes para que el arreglo en el fron sea super mas facil
                InformacionReportes.setProducto(listaProductos);
                InformacionReportes.setEstado(pedido2.getEstado());
                InformacionReportes.setIdPedido(pedido2.getIdpedido());
                InformacionReportes.setNombreUsuario(pedido2.getUsuario().getPersonas().getPrimernombre() + " " + pedido2.getUsuario().getPersonas().getPrimerapellido());
                InformacionReportes.setTelefono(pedido2.getUsuario().getTelefono());
                InformacionReportes.setLatitud(pedido2.getUsuario().getLatitud());
                InformacionReportes.setLongitud(pedido2.getUsuario().getLongitud());
                InformacionReportes.setNombreNegocio(pedido2.getNegocio().getNombre());
                InformacionReportes.setTotal(pedido2.getTotal());
                InformacionReportes.setNombreRepartidor(pedido2.getRepartidor().getPersonas().getPrimernombre()+" "+ pedido2.getRepartidor().getPersonas().getPrimerapellido());
                InformacionReportes.setPlaca(pedido2.getRepartidor().getVehiculo().getPlaca());
                InformacionReportes.setMarca(pedido2.getRepartidor().getVehiculo().getMarca()); 
                InformacionReportes.setFecha(pedido2.getFecha()); 
                InformacionReportes.setHora(pedido2.getHora());
                ListaReportes.add(InformacionReportes);
        }
        return ListaReportes;
    }

    @Override
    public InformacionReportes ReporteUnico(Long idPedido) {
         //Retornar reportes de un pedido en especifico
        //  Negocio negocio = this.negocioRepsitory.findById(idNegocio).get();

         //Traer el pedido que necesitamos
         Pedido pedido = this.pedidoRepositorie.findById(idPedido).get();
 
 

                 //Me trae la informacion de los productos que se lleva el usuario
                 List<DetallePedido> detallePedido = this.detallePedidoRepositorie.findByPedido(pedido);
 
                 //Lista donde se guardara la lista de productos que tiene detalle pedido
                 List<Producto> listaProductos = new ArrayList<>();
 
                  //Creamos un arreglo para meter los detalles de los productos dentro
                 InformacionReportes InformacionReportes = new InformacionReportes();
 
                 //Recorremos el arreglo de detalles de pedidos para meterlos en una lista de productos
                 for (DetallePedido detallePedido2 : detallePedido) {
                 //Nuevo objeto de producto para agregarlo despues al dto
                     Producto producto = new Producto();
                     producto.setCantidad(detallePedido2.getCantidad());
                     producto.setNombre(detallePedido2.getProducto().getNombre());
                     producto.setImagen(detallePedido2.getProducto().getImagen());
                     producto.setPrecio(detallePedido2.getProducto().getPrecio());
                     producto.setDescripcion(detallePedido2.getProducto().getDescripcion());
                     listaProductos.add(producto);
                 }
                 //Seteamos todo a informacionReportes para que el arreglo en el fron sea super mas facil
                 InformacionReportes.setProducto(listaProductos);
                 InformacionReportes.setEstado(pedido.getEstado());
                 InformacionReportes.setIdPedido(pedido.getIdpedido());
                 InformacionReportes.setNombreUsuario(pedido.getUsuario().getPersonas().getPrimernombre() + " " + pedido.getUsuario().getPersonas().getPrimerapellido());
                 InformacionReportes.setTelefono(pedido.getUsuario().getTelefono());
                 InformacionReportes.setLatitud(pedido.getUsuario().getLatitud());
                 InformacionReportes.setLongitud(pedido.getUsuario().getLongitud());
                 InformacionReportes.setNombreNegocio(pedido.getNegocio().getNombre());
                 InformacionReportes.setTotal(pedido.getTotal());
                 InformacionReportes.setNombreRepartidor(pedido.getRepartidor().getPersonas().getPrimernombre()+" "+ pedido.getRepartidor().getPersonas().getPrimerapellido());
                 InformacionReportes.setPlaca(pedido.getRepartidor().getVehiculo().getPlaca());
                 InformacionReportes.setMarca(pedido.getRepartidor().getVehiculo().getMarca()); 
                 InformacionReportes.setFecha(pedido.getFecha()); 
                 InformacionReportes.setHora(pedido.getHora());

         return InformacionReportes;
    }

    @Override
    public List<InformacionReportes> PedidosxUsuario(Long idUsuario) {
        //Retornar reportes de un pedido en especifico
        Usuarios usuario = this.ususarioRepositories.findById(idUsuario).get();

        //Traer un listado de los pedidos por reporte
        List<Pedido> listaPedidos = this.pedidoRepositorie.findByUsuario(usuario);

        //Hacer una lista donde se guardaran la informacion de los reportes
        List<InformacionReportes> ListaReportes = new ArrayList<>();

        //Iteramos la lista de los pedidos
        for (Pedido pedido2 : listaPedidos) {
                //Me trae la informacion de los productos que se lleva el usuario
                List<DetallePedido> detallePedido = this.detallePedidoRepositorie.findByPedido(pedido2);

                //Lista donde se guardara la lista de productos que tiene detalle pedido
                List<Producto> listaProductos = new ArrayList<>();

                 //Creamos un arreglo para meter los detalles de los productos dentro
                InformacionReportes InformacionReportes = new InformacionReportes();

                //Recorremos el arreglo de detalles de pedidos para meterlos en una lista de productos
                for (DetallePedido detallePedido2 : detallePedido) {
                //Nuevo objeto de producto para agregarlo despues al dto
                    Producto producto = new Producto();
                    producto.setCantidad(detallePedido2.getCantidad());
                    producto.setNombre(detallePedido2.getProducto().getNombre());
                    producto.setImagen(detallePedido2.getProducto().getImagen());
                    producto.setPrecio(detallePedido2.getProducto().getPrecio());
                    producto.setDescripcion(detallePedido2.getProducto().getDescripcion());
                    listaProductos.add(producto);
                }
                //Seteamos todo a informacionReportes para que el arreglo en el fron sea super mas facil
                InformacionReportes.setProducto(listaProductos);
                InformacionReportes.setEstado(pedido2.getEstado());
                InformacionReportes.setIdPedido(pedido2.getIdpedido());
                InformacionReportes.setNombreUsuario(pedido2.getUsuario().getPersonas().getPrimernombre() + " " + pedido2.getUsuario().getPersonas().getPrimerapellido());
                InformacionReportes.setTelefono(pedido2.getUsuario().getTelefono());
                InformacionReportes.setLatitud(pedido2.getUsuario().getLatitud());
                InformacionReportes.setLongitud(pedido2.getUsuario().getLongitud());
                InformacionReportes.setNombreNegocio(pedido2.getNegocio().getNombre());
                InformacionReportes.setImagenNegocio(pedido2.getNegocio().getImagen());
                InformacionReportes.setTotal(pedido2.getTotal());
                InformacionReportes.setNombreRepartidor(pedido2.getRepartidor().getPersonas().getPrimernombre()+" "+ pedido2.getRepartidor().getPersonas().getPrimerapellido());
                InformacionReportes.setPlaca(pedido2.getRepartidor().getVehiculo().getPlaca());
                InformacionReportes.setMarca(pedido2.getRepartidor().getVehiculo().getMarca()); 
                InformacionReportes.setFecha(pedido2.getFecha()); 
                InformacionReportes.setHora(pedido2.getHora());
                ListaReportes.add(InformacionReportes);
        }
        return ListaReportes;
    }

    @Override
    public void cancelarPedido(Long idPedido) {
        try {
            
            Pedido pedido = this.pedidoRepositorie.findById(idPedido).get();
            Usuarios repartidor = this.ususarioRepositories.findById(pedido.getRepartidor().getIdusuario()).get();

            //Cambiando el estado del pedido a Cancelado
            pedido.setEstado("Cancelado");
            this.pedidoRepositorie.save(pedido);

            //Canbiando el estado del repartidor a true
            repartidor.setEstado(1);
            this.ususarioRepositories.save(repartidor);

        } catch (Exception e) {

        }
    }

    
    
}
