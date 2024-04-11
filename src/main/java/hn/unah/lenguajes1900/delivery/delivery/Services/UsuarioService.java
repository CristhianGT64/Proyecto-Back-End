package hn.unah.lenguajes1900.delivery.delivery.Services;

import hn.unah.lenguajes1900.delivery.delivery.entities.Usuarios;

public interface UsuarioService {

    public Usuarios iniciarSesion(String correo, String contrasena);
    
}
