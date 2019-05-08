package mx.itesm.shakeit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCupon extends AppCompatActivity {

    private EditText rest, promo;
    private FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cupon);
        rest = findViewById(R.id.restaurantAdd);
        promo = findViewById(R.id.promoAdd);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Cupones");
    }

    public void agregarCupon(View v){
        String restaurant = rest.getText().toString();
        String id = myRef.push().getKey();

        Cupon cupon = new Cupon(restaurant, id);
        myRef.child(id).setValue(cupon);
        Toast.makeText(this,"Cupón agregado", Toast.LENGTH_LONG).show();
    }
}
