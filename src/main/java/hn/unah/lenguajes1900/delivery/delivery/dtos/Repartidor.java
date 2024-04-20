package hn.unah.lenguajes1900.delivery.delivery.dtos;

import lombok.Data;

@Data
public class Repartidor {
    public Long idRepartidor;

    public String nombre;

    public Boolean Estado;

    public double distanciaNegocioRepartido;

    public double distanciaNegocioUsuarui;

    public double distanciaTotal;

    private String marca;

    private String modelo;

    private String placa;
}
