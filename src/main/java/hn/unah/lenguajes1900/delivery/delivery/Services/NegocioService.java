package hn.unah.lenguajes1900.delivery.delivery.Services;

import java.util.List;

import hn.unah.lenguajes1900.delivery.delivery.entities.Negocio;

public interface NegocioService {

    public Boolean crearNegocio(Negocio negocio);

    public List<Negocio> traerNegocios();
}
