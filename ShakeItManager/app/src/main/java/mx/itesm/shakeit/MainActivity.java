package mx.itesm.shakeit;

import android.app.Activity;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.tbouron.shakedetector.library.ShakeDetector;

public class MainActivity extends Activity{
    private TextView tvHello;
    private Button bLocations;
    private SensorManager sensorMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHello = findViewById(R.id.tvHello1);
        bLocations = findViewById(R.id.bLocations1);

        Intent i = getIntent();
        tvHello.setText("Hello " + i.getStringExtra("username"));

       /* ShakeDetector.create(this, new ShakeDetector.OnShakeListener() {

            @Override
            public void OnShake() {
                //Toast.makeText(getApplicationContext(), "Lo shakeamos!", Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    public void goToRestaurantesAdd(View v){
        Intent i = new Intent(this, ListaLocations.class);
        startActivity(i);
    }

    //PENDIENTE DE CAMBIAR
    public void changeToAddCupon(View v){
        Intent i = new Intent(this, AddLocation.class);
        startActivity(i);
    }

    public void changeToMap(View v){
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ShakeDetector.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ShakeDetector.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShakeDetector.destroy();
    }
}
