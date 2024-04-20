package hn.unah.lenguajes1900.delivery.delivery.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.lenguajes1900.delivery.delivery.Services.impl.PedidoServiceImpl;
import hn.unah.lenguajes1900.delivery.delivery.Services.impl.UsuarioServiceImpl;
import hn.unah.lenguajes1900.delivery.delivery.dtos.Repartidor;
import hn.unah.lenguajes1900.delivery.delivery.entities.Pedido;
import hn.unah.lenguajes1900.delivery.delivery.repositories.UsusarioRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
    public Pedido TraerPedidosRepartidor(@RequestParam Long idRepartidor) {
        return this.pedidoServiceImpl.TraerPedidoNuevo(idRepartidor);
    }
    
    
}
