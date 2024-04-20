package hn.unah.lenguajes1900.delivery.delivery.Services.impl;

import java.util.List;


// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;

import hn.unah.lenguajes1900.delivery.delivery.Services.UsuarioService;
import hn.unah.lenguajes1900.delivery.delivery.dtos.ActualizarDireccionesRepartidores;
import hn.unah.lenguajes1900.delivery.delivery.dtos.Login;
import hn.unah.lenguajes1900.delivery.delivery.dtos.Repartidor;
import hn.unah.lenguajes1900.delivery.delivery.entities.Negocio;
import hn.unah.lenguajes1900.delivery.delivery.entities.Roles;
import hn.unah.lenguajes1900.delivery.delivery.entities.Usuarios;
import hn.unah.lenguajes1900.delivery.delivery.entities.Vehiculo;
import hn.unah.lenguajes1900.delivery.delivery.repositories.NegocioRepsitory;
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

    @Autowired
    private NegocioRepsitory negocioRepsitory;


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
            repartidor.setEstado(1); //Hacemos que el estado siempre sea verdadero

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

    @Override
    public Repartidor CalcualDistancia(Long idNegocio, Long idUsuario) {
       try {
            int radioTierra = 6371;
            
            //Buscamos que negocio queremos saber la ubicacion
            Negocio negocio = this.negocioRepsitory.findById(idNegocio).get();

            //Para poder hacer el filtro de los repartidores
            Roles rol = this.rolesRepositories.findById(2).get();

            //Se traen todos los repartidores de los usuarios
            List<Usuarios> usuarios = this.ususarioRepositories.findByRoles(rol);

            //Calcular cual es el repartidor que esta mas cerca del negocio

            Repartidor repartidorCercano = new Repartidor(); //Creacion de nuevo repartidor que sera el mas cercano
            double distancia = 10000; //Con este valo comprobaremos cual esta mas cerca
            double LatitudNegocio = gradosaRadienes(negocio.getLatitud()); //Pasamos este valor a radianes
            //  double LongitudNegoocio = gradosaRadienes(negocio.getLongitud());

        //  ==========================================================================================================
            //Calular de cual repartidor esta mas cerca de un negocio

            for (Usuarios repartidor : usuarios) { //Recorremos nuestra lista de repartidores
                if (repartidor.getEstado() == 1) {
                    double detltaLatidutd = gradosaRadienes(negocio.getLatitud() - repartidor.getLatitud()); // Diferencia entre latitudes de negocio y repartidos
                    double deltaLongitud = gradosaRadienes(negocio.getLongitud() - repartidor.getLongitud());

                    double latitudRepartidor = gradosaRadienes(repartidor.getLatitud());

                    //Hacemos la formula de haversine
                    double a = Math.pow(Math.sin(detltaLatidutd / 2) , 2) + Math.cos(LatitudNegocio)*Math.cos(latitudRepartidor)*Math.pow(Math.sin(deltaLongitud / 2), 2) ;
                    double c = 2 * Math.atan2(Math.sqrt(a) , Math.sqrt(1 - a));
                    double d = radioTierra * c;

                    //Si la distancia sale negativa entonces la pasamos a postiviva
                    //Esto sucede ya que es un circulo que analizamos
                    if (d < 0) {
                        d = d*(-1);
                    }
                    
                    //Validamos que usuario esta mas cerca del negocio
                    if (distancia > d) {
                        distancia = d;
                        repartidorCercano.setDistanciaNegocioRepartido(distancia);
                        repartidorCercano.setIdRepartidor(repartidor.getIdusuario());
                        repartidorCercano.setNombre(repartidor.getPersonas().getPrimernombre() + " " +  repartidor.getPersonas().getPrimernombre());
                        repartidorCercano.setMarca(repartidor.getVehiculo().getMarca());
                        repartidorCercano.setModelo(repartidor.getVehiculo().getPlaca());
                        repartidorCercano.setPlaca(repartidor.getVehiculo().getPlaca());
                    }
                }
            } /* Final del for Each */

            //Final del calculo
            // ===============================================================================================
            //En base al repatidor que quedo le cambiamos el estado
            repartidorCercano.setEstado(false);

            //Trae al repartidor que esta mas cerca del negocio
            Usuarios repartidorEstado = this.ususarioRepositories.findById(repartidorCercano.getIdRepartidor()).get();
            repartidorEstado.setEstado(0); //Cambiamos el estado del repartidor a null en la BD

            this.ususarioRepositories.save(repartidorEstado); //Actualizamos el estado en la BD

            //Calcular distancia entre negocio y repartidor
            // ==========================================================

            //Traer informacion del usuario
                Usuarios usuarioDestino = this.ususarioRepositories.findById(idUsuario).get();
                    
                    double detltaLatidutd2 = gradosaRadienes(negocio.getLatitud() - usuarioDestino.getLatitud()); // Diferencia entre latitudes de negocio y repartidos
                    double deltaLongitud2 = gradosaRadienes(negocio.getLongitud() - usuarioDestino.getLongitud());

                    double latitudUsuario = gradosaRadienes(usuarioDestino.getLatitud());

                    //Hacemos la formula de haversine
                    double a = Math.pow(Math.sin(detltaLatidutd2 / 2) , 2) + Math.cos(LatitudNegocio)*Math.cos(latitudUsuario)*Math.pow(Math.sin(deltaLongitud2 / 2), 2) ;
                    double c = 2 * Math.atan2(Math.sqrt(a) , Math.sqrt(1 - a));
                    double d = radioTierra * c;

                    //Si la distancia sale negativa entonces la pasamos a postiviva
                    //Esto sucede ya que es un circulo que analizamos
                    if (d < 0) {
                        d = d*(-1);
                    }

                    repartidorCercano.setDistanciaNegocioUsuarui(d);
                
                //Final de calculo de distancia usuario repartidor
                // =============================================================================

                //Suma total del repartidor al negocio y del negocio al usuario

                repartidorCercano.setDistanciaTotal(repartidorCercano.getDistanciaNegocioRepartido() + repartidorCercano.getDistanciaNegocioUsuarui());

            return repartidorCercano; //Regresamos nuestro repartidor mas cercano
       } catch (Exception e) {
         return null;
       }
    }

    public double gradosaRadienes(double grados){
        return (Math.PI / 180) * grados;
    }

    public double RadianesaGrados(double radianes){
        return (180 / Math.PI) * radianes;
    }

    
}
