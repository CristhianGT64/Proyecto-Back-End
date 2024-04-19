package hn.unah.lenguajes1900.delivery.delivery.Services;

import java.util.List;

import hn.unah.lenguajes1900.delivery.delivery.entities.Negocio;
import hn.unah.lenguajes1900.delivery.delivery.entities.Usuarios;

public interface NegocioService {

    public Boolean crearNegocio(Negocio negocio);

    public List<Negocio> traerNegocios();

    public Negocio BuscarNegocioAdministrador(Long idUsuario);

    public Negocio buscarPorId(Long idNegocio);
}
