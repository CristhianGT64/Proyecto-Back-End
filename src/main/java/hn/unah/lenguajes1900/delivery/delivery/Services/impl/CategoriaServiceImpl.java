package hn.unah.lenguajes1900.delivery.delivery.Services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.lenguajes1900.delivery.delivery.Services.CategoriaSerice;
import hn.unah.lenguajes1900.delivery.delivery.entities.Categoria;
import hn.unah.lenguajes1900.delivery.delivery.repositories.CategoriaRepositorie;

@Service
public class CategoriaServiceImpl implements CategoriaSerice{

    @Autowired
    private CategoriaRepositorie categoriaRepositorie;

    @Override
    public List<Categoria> TraerCategorias() {
        return (List<Categoria>) this.categoriaRepositorie.findAll();
    }

    @Override
    public Boolean crearCategoria(Categoria categoria) {
        
        if(this.categoriaRepositorie.findByNombre(categoria.getNombre()).isEmpty()){
            
            this.categoriaRepositorie.save(categoria);
            return true;
        }

        return false;
    }
    
}
