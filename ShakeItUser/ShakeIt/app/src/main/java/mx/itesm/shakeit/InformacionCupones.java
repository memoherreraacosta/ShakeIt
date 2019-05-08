package mx.itesm.shakeit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InformacionCupones extends AppCompatActivity {

    private Button bRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_cupones);

        bRegresar = findViewById(R.id.bRegresar);
    }

    public void changeToListaCupones(View v){
        Intent i = new Intent(this, CuponListItem.class);
        startActivity(i);
    }
}
