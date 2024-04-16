package hn.unah.lenguajes1900.delivery.delivery.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.lenguajes1900.delivery.delivery.Services.impl.CategoriaServiceImpl;
import hn.unah.lenguajes1900.delivery.delivery.entities.Categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class CategoriaController {

    @Autowired
    private CategoriaServiceImpl categoriaServiceImpl;

    @GetMapping("/Categoria/TraerCategorias")
    public List<Categoria> TraerCatgeorias() {
        return this.categoriaServiceImpl.TraerCategorias();
    }
    
}
