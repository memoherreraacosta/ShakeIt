package mx.itesm.shakeit;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private String id, restaurante;
    private double longitud, latitud;
    private List<Cupon> cupones;

    public Location(){
    }

    public Location(String id, String restaurante, double longitud, double latitud){
        this.id = id;
        this.restaurante = restaurante;
        this.longitud = longitud;
        this.latitud = latitud;
        cupones = new ArrayList<Cupon>();
    }

    public String getID(){ return this.id;}
    public String getRestaurante(){ return this.restaurante;}
    public double getLongitud(){ return this.longitud;}
    public double getLatitud(){ return this.latitud;}
    public Cupon getCupon(int i){ return this.cupones.get(i);}
    public List<Cupon> getCupones(){ return this.cupones;}

    public void setCupones(List<Cupon> cupones){ this.cupones = cupones;}
    public void setId(String id){this.id = id;}
    public void setRestaurante(String restaurante){this.restaurante = restaurante;}
    public void setLatitud(Double latitud){this.latitud = latitud;}
    public void setLongitud(Double longitud){this.longitud = longitud;}

    public void addCupon(Cupon cupon){
        cupones.add(cupon);
    }

}
