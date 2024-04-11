package hn.unah.lenguajes1900.delivery.delivery.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.lenguajes1900.delivery.delivery.Services.UsuarioService;
import hn.unah.lenguajes1900.delivery.delivery.dtos.Login;
import hn.unah.lenguajes1900.delivery.delivery.entities.Usuarios;
import hn.unah.lenguajes1900.delivery.delivery.repositories.UsusarioRepositories;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsusarioRepositories ususarioRepositories;

    // Inicio de sesion
    //Verifica primero si el corro existe y si existe valida la contrasena
    //Si no encunetra nada retorna null
    @Override
    public Usuarios iniciarSesion(Login login) {
        if (null != this.ususarioRepositories.findByEmail(login.getCorreo())) {
            try {
                Usuarios usuarioActual = (Usuarios) this.ususarioRepositories.findByEmail(login.getCorreo()).get(0);
                if (usuarioActual.getContrasena().equals(login.getContrasena())) {
                    return usuarioActual;
                }
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    
}
