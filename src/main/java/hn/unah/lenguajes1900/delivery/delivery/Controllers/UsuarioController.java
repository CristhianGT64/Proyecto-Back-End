package hn.unah.lenguajes1900.delivery.delivery.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.lenguajes1900.delivery.delivery.Services.impl.UsuarioServiceImpl;
import hn.unah.lenguajes1900.delivery.delivery.dtos.ActualizarDireccionesRepartidores;
import hn.unah.lenguajes1900.delivery.delivery.dtos.Login;
import hn.unah.lenguajes1900.delivery.delivery.entities.Usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// 
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @GetMapping("/Ususario/IniciarSesion")
    public Usuarios getMethodName(Login login) {
        return this.usuarioServiceImpl.iniciarSesion(login);
    }

    @PostMapping("/Ususario/CrearUsuario")
    public Boolean CrearUsuario(@RequestBody Usuarios usuario) {
        return this.usuarioServiceImpl.crearUsusario(usuario);
    }

    @GetMapping("/Usuario/TraerRepartidores")
    public List<Usuarios> TraerRepartidores() {
        return this.usuarioServiceImpl.TraerRepartidores();
    }
    

    @PostMapping("/Usuario/CrearRepartidor")
    public Boolean crearRepartidor(@RequestBody Usuarios repartidor){
        return this.usuarioServiceImpl.crearRepartidor(repartidor);
    }
    
    @PutMapping("Usuario/ActualizarUbicacion")
    public String ActualizarUbicacion(@RequestBody ActualizarDireccionesRepartidores nvoDireccion) {
        
        return this.usuarioServiceImpl.ActualizarDireccion(nvoDireccion);
    }

    @GetMapping("Usuario/EstadoRepartidor")
    public Boolean EstadoRepartidor(@RequestParam Long idRepartidor) {
        return this.usuarioServiceImpl.EstadoRepartidor(idRepartidor);
    }
    
    @GetMapping("/Usuario/buscarPorEmail")
    public Usuarios buscarPorEmail(@RequestParam String email){
        return this.usuarioServiceImpl.buscarPorEmail(email);
    }

    
}
