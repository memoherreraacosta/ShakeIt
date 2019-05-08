package mx.itesm.shakeit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditarLocation extends AppCompatActivity {
    private EditText nombre,latitud,longitud;
    private String id;
    private DatabaseReference myRef, myRefC;
    private Location location;
    private List<Cupon> cupones = new ArrayList<>();
    private String[] llavesCupones;
    private String[] infoCupones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_location);

        nombre = findViewById(R.id.nombreL);
        latitud = findViewById(R.id.latitudL);
        longitud = findViewById(R.id.longitudL);
        Intent i = getIntent();
        id = i.getStringExtra("id");
        myRef = FirebaseDatabase.getInstance().getReference().child("Locations/");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                location = dataSnapshot.child(id).getValue(Location.class);
                if(location != null) {
                    nombre.setText(location.getRestaurante());
                    latitud.setText(location.getLatitud()+"");
                    longitud.setText(location.getLongitud()+"");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRefC = FirebaseDatabase.getInstance().getReference().child("Locations/"+id+"/Cupones");
        myRefC.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                llavesCupones = new String[(int) dataSnapshot.getChildrenCount()];
                infoCupones = new String[(int) dataSnapshot.getChildrenCount()];
                for(DataSnapshot dsp : dataSnapshot.getChildren()){
                    Cupon cuponOriginal = dsp.getValue(Cupon.class);
                    llavesCupones[i] = cuponOriginal.getId();
                    infoCupones[i] = cuponOriginal.getInformacion();
                    i++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void eliminarLoc(View v){
        myRef.child(id).removeValue();
        Toast.makeText(this,"Restaurante eliminado",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void editarLoc(View v){
        location.setId(id);
        location.setRestaurante(nombre.getText().toString());
        location.setLatitud(Double.parseDouble(latitud.getText().toString()));
        location.setLongitud(Double.parseDouble(longitud.getText().toString()));

        for(int i = 0; i < llavesCupones.length; i++){
            Cupon cupon = new Cupon();
            cupon.setId(llavesCupones[i]);
            cupon.setInformacion(infoCupones[i]);
            cupones.add(cupon);
        }
        myRef.child(id).setValue(location);
        for(int i = 0; i < llavesCupones.length;i++){
            myRefC.child(llavesCupones[i]).setValue(cupones.get(i));
        }
        this.finish();
    }
}
