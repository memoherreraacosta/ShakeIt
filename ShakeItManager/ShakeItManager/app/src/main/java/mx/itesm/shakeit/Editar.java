package mx.itesm.shakeit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Editar extends AppCompatActivity {
    int pos;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        Intent i = getIntent();
        pos = i.getIntExtra("pos",0);
        id = i.getStringExtra("id");
    }

    public void goToCupones(View v){
        Intent i = new Intent(this, ListaCupones.class);
        i.putExtra("pos",pos);
        startActivity(i);
    }

    public void addCupon(View v){
        Intent i = new Intent(this, AddCupon.class);
        i.putExtra("id",id);
        startActivity(i);
    }

    public void editarRestaurante(View v){
        Intent i = new Intent(this,EditarLocation.class);
        i.putExtra("id",id);
        startActivity(i);
    }

}
