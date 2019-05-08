package mx.itesm.shakeit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCupon extends AppCompatActivity {

    private EditText promo;
    private String key;

    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cupon);
        this.promo = findViewById(R.id.promoText);

        Intent i = getIntent();
        this.key = i.getStringExtra("id");
        myRef = FirebaseDatabase.getInstance().getReference("Locations/" + this.key + "/Cupones");
    }

    public void agregarCupon(View v){
        String promocion = promo.getText().toString();
        String id = myRef.push().getKey();

        Cupon cupon = new Cupon(promocion, id);
        myRef.child(id).setValue(cupon);
        Toast.makeText(this,"Cupón agregado", Toast.LENGTH_LONG).show();
    }
}
