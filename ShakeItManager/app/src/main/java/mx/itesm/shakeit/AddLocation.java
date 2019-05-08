package mx.itesm.shakeit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddLocation extends AppCompatActivity {

    private EditText rest, promo;
    private TextView lat, lng;
    private FirebaseDatabase database;
    double latitudita, longitudita;
    DatabaseReference myRef;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        rest = findViewById(R.id.restaurantAdd);
        promo = findViewById(R.id.promoAdd);
        lat = findViewById(R.id.lati);
        lng = findViewById(R.id.lngi);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Locations");

        Intent i = getIntent();
        this.latitudita =  i.getDoubleExtra("Latitud",0.0);
        this.longitudita =  i.getDoubleExtra("Longitud",0.0);
        lat.setText("Latitud: " +latitudita );
        lng.setText("Longitud: " + longitudita );
    }

    public void agregarRestaurante(View v){
        String restaurant = rest.getText().toString();
        String id = myRef.push().getKey();

        Location location = new Location(id, restaurant, longitudita, latitudita);
        myRef.child(id).setValue(location);
        Toast.makeText(this,"Restaurante agregado", Toast.LENGTH_LONG).show();
    }

    public void changeToAddCupon(View v){
        Intent i = new Intent(this, AddCupon.class);
        startActivity(i);
    }
}

