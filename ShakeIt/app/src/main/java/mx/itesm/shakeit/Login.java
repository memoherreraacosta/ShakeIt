
package mx.itesm.shakeit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    private EditText etUsername, etPassword;


    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        //Toast.makeText(this, "Sesión iniciada", Toast.LENGTH_SHORT).show();
    }



    public void changeToMainActivity(View w){
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("username", etUsername.getText().toString());
        startActivity(i);
    }

    public void changeToSign(View w){
        Intent i = new Intent(this, SignActivity.class);
        startActivity(i);
    }

    public void logInUser(View v){
        String email = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login.this, "Sesión iniciada correctamente", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Login.this, MainActivity.class);
                            i.putExtra("username", etUsername.getText().toString());
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Favor de verificar datos.", Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });
    }
}
/*
=======
package mx.itesm.shakeit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    private EditText etUsername, etPassword;


    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        Toast.makeText(this, "Sesión iniciada", Toast.LENGTH_SHORT).show();
    }



    public void changeToMainActivity(View w){
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("username", etUsername.getText().toString());
        startActivity(i);
    }

    public void changeToSign(View w){
        Intent i = new Intent(this, SignActivity.class);
        startActivity(i);
    }

    public void logInUser(View v){
        String email = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login.this, "Sesión iniciada correctamente", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Login.this, MainActivity.class);
                            i.putExtra("username", etUsername.getText().toString());
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Favor de verificar datos.", Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });
    }
}
>>>>>>> 3fc469f6ac37f0b7041872337fb29c986571741a
*/