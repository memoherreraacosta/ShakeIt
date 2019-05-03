package mx.itesm.shakeit;

import android.app.Activity;
import android.content.Intent;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.tbouron.shakedetector.library.ShakeDetector;

public class MainActivity extends Activity{
    private TextView tvHello;
    private Button bCupones;
    private SensorManager sensorMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHello = findViewById(R.id.tvHello);
        bCupones = findViewById(R.id.bCupones);

        Intent i = getIntent();
        tvHello.setText("Hello " + i.getStringExtra("username"));

        ShakeDetector.create(this, new ShakeDetector.OnShakeListener() {

            @Override
            public void OnShake() {
                Toast.makeText(getApplicationContext(), "Lo shakeamos!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, CuponListItem.class);
                // Intent i = new Intent(MainActivity.this, ListaCupones.class);
                startActivity(i);
            }
        });

    }

    public void changeToListaCupones(View v){
        Intent i = new Intent(MainActivity.this, CuponListItem.class);
        // Intent i = new Intent(MainActivity.this, ListaCupones.class);
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
