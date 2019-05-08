package mx.itesm.shakeit;

/**
 * Created by CLN-BRA on 26/02/2019.
 */

public class Cupon {
    private String informacion, id;

    public Cupon(){

    }

    public Cupon(String informacion, String id){
        this.informacion = informacion;
        this.id = id;
    }

    public Cupon(String informacion){
        this.informacion = informacion;
    }

    public String getInformacion(){return this.informacion;}


    public String getId(){return this.id;}
}
