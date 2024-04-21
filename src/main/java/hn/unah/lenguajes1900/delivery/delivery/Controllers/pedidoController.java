package hn.unah.lenguajes1900.delivery.delivery.Controllers;

import java.util.List;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hn.unah.lenguajes1900.delivery.delivery.dtos.InformacionReportes;
import hn.unah.lenguajes1900.delivery.delivery.Services.impl.PedidoServiceImpl;
import hn.unah.lenguajes1900.delivery.delivery.Services.impl.UsuarioServiceImpl;
import hn.unah.lenguajes1900.delivery.delivery.dtos.InformacionPedido;
import hn.unah.lenguajes1900.delivery.delivery.dtos.Repartidor;
import hn.unah.lenguajes1900.delivery.delivery.entities.DetallePedido;
import hn.unah.lenguajes1900.delivery.delivery.entities.Pedido;
import hn.unah.lenguajes1900.delivery.delivery.repositories.UsusarioRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api")
public class pedidoController {

    @Autowired
    private PedidoServiceImpl pedidoServiceImpl;
    
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @GetMapping("/Pedido/DistanciaNegocioRepartidor")
    public Repartidor CalcularDistanciaRepartidorNegocio(@RequestParam Long idNegocio, @RequestParam Long idUsuario) {
        return this.usuarioServiceImpl.CalcualDistancia(idNegocio, idUsuario);
    }

    @GetMapping("/Pedido/TraerPedidosRepartidor")
    public InformacionPedido TraerPedidosRepartidor(@RequestParam Long idRepartidor) {
        return this.pedidoServiceImpl.TraerPedidoNuevo(idRepartidor);
    }

    @GetMapping("/Pedido/EntregarPedido")
    public Boolean EntregarPedido(@RequestParam Long idPedido) {
        return this.pedidoServiceImpl.FinalizarPedido(idPedido);
    }
    
    @PostMapping("/Pedido/CrearPedido")
    public Long crearPedido(@RequestBody Pedido pedido){
        return this.pedidoServiceImpl.crearPedido(pedido);
    }

    @GetMapping("/Pedido/ReportesNegocio")
    public List<InformacionReportes> ReportesNegocio(@RequestParam Long idNegocio) {
        return this.pedidoServiceImpl.PedidosxNegocio(idNegocio);
    };
    
    @GetMapping("/Pedido/ReportesPeidosTodos")
    public List<InformacionReportes> ReportesPedidos() {
        return this.pedidoServiceImpl.TodoslosPedidos();
    }
}
