package hn.unah.lenguajes1900.delivery.delivery.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.lenguajes1900.delivery.delivery.Services.impl.UsuarioServiceImpl;
import hn.unah.lenguajes1900.delivery.delivery.entities.Usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @GetMapping("/Ususario/IniciarSesion")
    public Usuarios getMethodName(@RequestParam String correo, @RequestParam String contrasena) {
        return this.usuarioServiceImpl.iniciarSesion(correo, contrasena);
    }
    
    
}
