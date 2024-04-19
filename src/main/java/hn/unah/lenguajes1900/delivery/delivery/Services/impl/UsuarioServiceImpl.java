package hn.unah.lenguajes1900.delivery.delivery.Services.impl;

import java.util.List;


// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.lenguajes1900.delivery.delivery.Services.UsuarioService;
import hn.unah.lenguajes1900.delivery.delivery.dtos.ActualizarDireccionesRepartidores;
import hn.unah.lenguajes1900.delivery.delivery.dtos.Login;
import hn.unah.lenguajes1900.delivery.delivery.entities.Roles;
import hn.unah.lenguajes1900.delivery.delivery.entities.Usuarios;
import hn.unah.lenguajes1900.delivery.delivery.entities.Vehiculo;
import hn.unah.lenguajes1900.delivery.delivery.repositories.RolesRepositories;
import hn.unah.lenguajes1900.delivery.delivery.repositories.UsusarioRepositories;
import hn.unah.lenguajes1900.delivery.delivery.repositories.VehiculoRepositorie;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsusarioRepositories ususarioRepositories;

    @Autowired
    private RolesRepositories rolesRepositories;

    @Autowired
    private VehiculoRepositorie vehiculoRepositorie;

    // Inicio de sesion
    //Verifica primero si el corro existe y si existe valida la contrasena
    //Si no encunetra nada retorna null
    @Override
    public Usuarios iniciarSesion(Login login) {
        if (null != this.ususarioRepositories.findByEmail(login.getCorreo())) {
            try {
                Usuarios usuarioActual = (Usuarios) this.ususarioRepositories.findByEmail(login.getCorreo()).get(0);
                if (usuarioActual.getContrasena().equals(login.getContrasena())) {
                    return usuarioActual;
                }
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public Boolean crearUsusario(Usuarios usuario) {
            /* Creamos un nuevo usuario, cuando se crea se define por default que sera
             * un Usuario normal, por eso traemos de la base de datos ese tipo de rol
             * el administrador podra cambiarlo a qu tipo de usuario sera.
             */
            //Validamos que el correo no exista
            if (this.ususarioRepositories.findByEmail(usuario.getEmail()).isEmpty()) {
            Roles rolDefault = this.rolesRepositories.findById(3).get();
            // Roles roles = this.rolesRepositories.findById(3).get();
            usuario.setRoles(rolDefault);
            this.ususarioRepositories.save(usuario);
            return true;
            }
        return false;
    }

    @Override
    public List<Usuarios> TraerRepartidores() {
        Roles repartidores = this.rolesRepositories.findById(2).get();
        return  this.ususarioRepositories.findByRoles(repartidores);
    }

    @Override
    public Boolean crearRepartidor(Usuarios repartidor) {

        if (this.ususarioRepositories.findByEmail(repartidor.getEmail()).isEmpty()) {
            Roles rolDefault = this.rolesRepositories.findById(2).get();

            double latitudDefault= 14.0645;
            double longitudDefault= -87.3345;

            repartidor.setRoles(rolDefault);
            repartidor.setLatitud((float) latitudDefault);
            repartidor.setLongitud((float) longitudDefault);

            Vehiculo vehiculo = repartidor.getVehiculo();
            vehiculo.setUsuario(repartidor);
            this.vehiculoRepositorie.save(vehiculo);
            // this.ususarioRepositories.save(repartidor);
            return true;
            }
        return false;

    }

    @Override
    public String ActualizarDireccion(ActualizarDireccionesRepartidores nvoDireccion) {


        try {
            Usuarios usuario = this.ususarioRepositories.findById(nvoDireccion.getId()).get();
            usuario.setLatitud(nvoDireccion.getLatitud());
            usuario.setLongitud(nvoDireccion.getLongitud());
            this.ususarioRepositories.save(usuario);

            return "Se Guardo la nueva ubicacion";
        } catch (Exception e) {
            return "Error al guardar la nueva ubicacion";
        }
        // return "No se pudo gradar la nueva ubicacion";
    }

    
}
