package hn.unah.lenguajes1900.delivery.delivery.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.lenguajes1900.delivery.delivery.Services.NegocioService;
import hn.unah.lenguajes1900.delivery.delivery.entities.Negocio;
import hn.unah.lenguajes1900.delivery.delivery.entities.Roles;
import hn.unah.lenguajes1900.delivery.delivery.entities.Usuarios;
import hn.unah.lenguajes1900.delivery.delivery.repositories.NegocioRepsitory;
import hn.unah.lenguajes1900.delivery.delivery.repositories.RolesRepositories;

@Service
public class NegocioServiceImpl implements NegocioService{

    @Autowired
    private NegocioRepsitory negocioRepsitory;

    @Autowired
    private RolesRepositories rolesRepositories;

    @Override
    public Boolean crearNegocio(Negocio negocio) {
        //Validamos que el negocio no exita
        try{
            if (negocioRepsitory.findByNombre(negocio.getNombre()).isEmpty()) {

                Roles adminRol = this.rolesRepositories.findById(4).get();
                Usuarios usuarioRol = negocio.getUsuarios();
                usuarioRol.setRoles(adminRol);
                negocio.setUsuarios(usuarioRol);

                this.negocioRepsitory.save(negocio);
                return true;
            }
        } catch (Exception e){
            return false;
        }

        return false;
    }
    
}
