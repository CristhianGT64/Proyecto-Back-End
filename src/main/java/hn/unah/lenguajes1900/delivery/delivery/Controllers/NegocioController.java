package hn.unah.lenguajes1900.delivery.delivery.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.lenguajes1900.delivery.delivery.Services.impl.NegocioServiceImpl;
import hn.unah.lenguajes1900.delivery.delivery.entities.Negocio;
import hn.unah.lenguajes1900.delivery.delivery.entities.Usuarios;

import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
public class NegocioController {

    @Autowired
    private NegocioServiceImpl negocioServiceImpl;

    @PostMapping("/negocio/crear")
    public Boolean crearNegocio(@RequestBody Negocio negocio){
        return this.negocioServiceImpl.crearNegocio(negocio);
    }

    @GetMapping("/negocio/TodosNegocios")
    public List<Negocio> TraerNegocios() {
        return this.negocioServiceImpl.traerNegocios();
    }

    @GetMapping("/negocio/TraerNegocio")
    public Negocio traerNegocioporAdministradorNegocio(@RequestParam Long idUsuario) {
        return  this.negocioServiceImpl.BuscarNegocioAdministrador(idUsuario);
    }

    @GetMapping("/negocio/buscarPorId")
    public Negocio bucarPorId(@RequestParam Long idNegocio){
        return this.negocioServiceImpl.buscarPorId(idNegocio);
    }
    
}
