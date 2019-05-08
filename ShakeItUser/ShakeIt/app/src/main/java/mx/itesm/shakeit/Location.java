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
    public String getCuponID(int i){return this.cupones.get(i).getId();}
    public String getRestaurante(){ return this.restaurante;}
    public double getLongitud(){ return this.longitud;}
    public double getLatitud(){ return this.latitud;}
    public Cupon getCupon(int i){ return this.cupones.get(i);}
    public List<Cupon> getCupones(){ return this.cupones;}

    public void addCupon(Cupon cupon){
        cupones.add(cupon);
    }

}
