package mx.itesm.shakeit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignActivity extends AppCompatActivity {

    EditText username, pass;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        username = findViewById(R.id.userSign);
        pass = findViewById(R.id.passSign);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
    }

    public void registrarUsuario(View v){
        String user = username.getText().toString().trim();
        String passw  = pass.getText().toString().trim();

        if(TextUtils.isEmpty(user)){
            Toast.makeText(this, "Por favor introduzca un usuario", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(passw)){
            Toast.makeText(this, "Por favor introduzca su contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Realizando registro en línea");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(user, passw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SignActivity.this, "Se ha registrado el usuario", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            Intent i =  new Intent(SignActivity.this, Login.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignActivity.this, "Hubo un error registrando el usuario", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                        // ...
                    }
                });

    }
}
