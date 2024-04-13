package hn.unah.lenguajes1900.delivery.delivery.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.lenguajes1900.delivery.delivery.Services.impl.NegocioServiceImpl;
import hn.unah.lenguajes1900.delivery.delivery.entities.Negocio;

@RestController
@RequestMapping("/api")
public class NegocioController {

    @Autowired
    private NegocioServiceImpl negocioServiceImpl;

    @PostMapping("/negocio/crear")
    public Boolean crearNegocio(@RequestBody Negocio negocio){
        return this.negocioServiceImpl.crearNegocio(negocio);
    }
    
}
