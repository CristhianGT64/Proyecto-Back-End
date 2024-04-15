package hn.unah.lenguajes1900.delivery.delivery.Services;

import java.util.List;

import hn.unah.lenguajes1900.delivery.delivery.dtos.Login;
import hn.unah.lenguajes1900.delivery.delivery.entities.Usuarios;

public interface UsuarioService {

    public Usuarios iniciarSesion(Login login);

    public Boolean crearUsusario(Usuarios usuario);

    public List<Usuarios> TraerRepartidores();

    public Boolean crearRepartidor(Usuarios usuario);
    
}
