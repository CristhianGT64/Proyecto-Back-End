package hn.unah.lenguajes1900.delivery.delivery.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.lenguajes1900.delivery.delivery.Services.NegocioService;
import hn.unah.lenguajes1900.delivery.delivery.entities.Negocio;
import hn.unah.lenguajes1900.delivery.delivery.entities.Roles;
import hn.unah.lenguajes1900.delivery.delivery.entities.Usuarios;
import hn.unah.lenguajes1900.delivery.delivery.repositories.NegocioRepsitory;
import hn.unah.lenguajes1900.delivery.delivery.repositories.RolesRepositories;
import hn.unah.lenguajes1900.delivery.delivery.repositories.UsusarioRepositories;

@Service
public class NegocioServiceImpl implements NegocioService{

    @Autowired
    private NegocioRepsitory negocioRepsitory;

    @Autowired
    private RolesRepositories rolesRepositories;

    @Autowired
    private UsusarioRepositories ususarioRepositories;

    @Override
    public Boolean crearNegocio(Negocio negocio) {
        //Validamos que el negocio no exita
        try{
            if (this.negocioRepsitory.findByNombre(negocio.getNombre()).isEmpty()) {
                
                Usuarios usuarioRol = negocio.getUsuarios();

                if (this.ususarioRepositories.findByEmail(usuarioRol.getEmail()).isEmpty()) {
                    Roles adminRol = this.rolesRepositories.findById(4).get();
                    usuarioRol.setRoles(adminRol);
                    negocio.setUsuarios(usuarioRol);

                    this.negocioRepsitory.save(negocio);
                    return true;
                }
            }
        } catch (Exception e){
            return false;
        }

        return false;
    }
    
}
