package com.example.mastersdentf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class IniciarSesion extends AppCompatActivity {
    EditText C1,C2;
    private FirebaseAuth mAuth;
    private EditText correo;
    private EditText contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        //Iniciar sesion
        correo = findViewById(R.id.txtcorreo);
        contraseña = findViewById(R.id.txtcontraseña);
        C1=findViewById(R.id.txtCorreo);
        C2=findViewById(R.id.txtContrasena);
        mAuth = FirebaseAuth.getInstance();
        //Iniciar sesion
        Button irARegistro = findViewById(R.id.btnRegistarse);
        irARegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(IniciarSesion.this, RegistroUsuario.class);
                startActivity(o);
            }
        });
        //TXT Ir a recueprar contraseña
        TextView txtRecuperarContraseña = this.findViewById(R.id.txtRecuperarContraseña);
        txtRecuperarContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(IniciarSesion.this, RecuperarContrasenna.class);
                startActivity(o);
            }
        });
    }
    public boolean validar() {
        boolean retorno = true;
        String C1 = correo.getText().toString();
        String C2 = correo.getText().toString();
        if (C1.isEmpty()) {
            correo.setError("El campo del correo electronico esta vacio");
            retorno = false;
        }
        if (C2.isEmpty()) {
            contraseña.setError("El campo de la contraseña esta vacio");
            retorno = false;
        }
        return retorno;
    }
    //Iniciar sesion
    public void iniciarSesion(View v){
      if(validar()){
          mAuth.signInWithEmailAndPassword(correo.getText().toString(), contraseña.getText().toString())
                  .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                          if (task.isSuccessful()) {
                              // Sign in success, update UI with the signed-in user's information
                              //Log.d(TAG, "signInWithEmail:success");
                              FirebaseUser user = mAuth.getCurrentUser();
                              // updateUI(user);

                              Intent o = new Intent(IniciarSesion.this, MainActivity.class);
                              startActivity(o);
                          } else {
                              // If sign in fails, display a message to the user.
                              //Log.w(TAG, "signInWithEmail:failure", task.getException());
                              Toast.makeText(getApplicationContext(), "Usuario y contraseña incorrectos.",
                                      Toast.LENGTH_SHORT).show();
                              //updateUI(null);
                          }
                          // ...
                      }
                  });
      }
    }
    //fin Iniciar sesion
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    }

