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

public class InformacionCupon extends AppCompatActivity {
    private EditText info;
    private String id,idC;
    DatabaseReference myRef;
    private Cupon cupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_cupon);

        info = findViewById(R.id.infoCupon);
        Intent i = getIntent();
        id = i.getStringExtra("id");
        idC = i.getStringExtra("idC");
        myRef = FirebaseDatabase.getInstance().getReference().child("Locations/" + id + "/Cupones");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cupon = dataSnapshot.child(idC).getValue(Cupon.class);
                if(cupon != null) {
                    info.setText(cupon.getInformacion());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void eliminarCupon(View v){
        myRef.child(idC).removeValue();
        Toast.makeText(this,"Cupón eliminado",Toast.LENGTH_SHORT).show();
        this.finish();
    }

    public void editarCupon(View v){
        cupon.setId(idC);
        cupon.setInformacion(info.getText().toString());
        myRef.child(idC).setValue(cupon);
        Toast.makeText(this,"Cupón editado",Toast.LENGTH_SHORT).show();
        this.finish();
    }
}
