package hn.unah.lenguajes1900.delivery.delivery.Services;

import java.util.List;

import hn.unah.lenguajes1900.delivery.delivery.dtos.ActualizarDireccionesRepartidores;
import hn.unah.lenguajes1900.delivery.delivery.dtos.Login;
import hn.unah.lenguajes1900.delivery.delivery.dtos.Repartidor;
import hn.unah.lenguajes1900.delivery.delivery.entities.Usuarios;

public interface UsuarioService {

    public Usuarios iniciarSesion(Login login);

    public Boolean crearUsusario(Usuarios usuario);

    public List<Usuarios> TraerRepartidores();

    public String ActualizarDireccion(ActualizarDireccionesRepartidores nvoDireccion);

    public Boolean crearRepartidor(Usuarios usuario);

    public Repartidor RepartidorCercanoNegocio(Long idNegocio);
    
}
