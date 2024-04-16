package hn.unah.lenguajes1900.delivery.delivery.Services;

import java.util.List;

import hn.unah.lenguajes1900.delivery.delivery.entities.Categoria;

public interface CategoriaSerice {
    
    public List<Categoria> TraerCategorias();

    public Boolean crearCategoria(Categoria categoria);
}
