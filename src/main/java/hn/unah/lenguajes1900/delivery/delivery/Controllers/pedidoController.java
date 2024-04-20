package hn.unah.lenguajes1900.delivery.delivery.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.lenguajes1900.delivery.delivery.Services.impl.UsuarioServiceImpl;
import hn.unah.lenguajes1900.delivery.delivery.dtos.Repartidor;
import hn.unah.lenguajes1900.delivery.delivery.repositories.UsusarioRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class pedidoController {
    
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @GetMapping("/Pedido/DistanciaNegocioRepartidor")
    public Repartidor CalcularDistanciaRepartidorNegocio(@RequestParam Long idNegocio) {
        return this.usuarioServiceImpl.RepartidorCercanoNegocio(idNegocio);
    }
    
}
