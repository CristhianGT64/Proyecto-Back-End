package hn.unah.lenguajes1900.delivery.delivery.Services.impl;

import java.io.Serial;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.lenguajes1900.delivery.delivery.Services.ProductoService;
import hn.unah.lenguajes1900.delivery.delivery.entities.Categoria;
import hn.unah.lenguajes1900.delivery.delivery.entities.Negocio;
import hn.unah.lenguajes1900.delivery.delivery.entities.Producto;
import hn.unah.lenguajes1900.delivery.delivery.repositories.CategoriaRepositorie;
import hn.unah.lenguajes1900.delivery.delivery.repositories.NegocioRepsitory;
import hn.unah.lenguajes1900.delivery.delivery.repositories.ProductoRepositorie;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepositorie productoRepositorie;

    @Autowired 
    private NegocioRepsitory negocioRepsitory;

    @Autowired CategoriaRepositorie categoriaRepositorie;

    @Override
    public Boolean CrearProducto(Producto producto) {
        try {
            Negocio negocio = this.negocioRepsitory.findById(producto.getNegocio().getIdnegocio()).get();
            Categoria categoria = this.categoriaRepositorie.findById(producto.getCategoria().getIdcategoria()).get();
            producto.setCategoria(categoria);
            producto.setNegocio(negocio);
            this.productoRepositorie.save(producto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Producto> TraerProductosxNegocio(Long idNegocio) {

        try {
            Negocio negocio = negocioRepsitory.findById(idNegocio).get();
            return this.productoRepositorie.findByNegocio(negocio);
        } catch (Exception e) {
            return null;
        }
    }
    
}
