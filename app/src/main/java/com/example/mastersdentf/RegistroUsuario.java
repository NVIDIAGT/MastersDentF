package com.example.mastersdentf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegistroUsuario extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText C1,C2,C3,C4,C5;
    //campos de registro
    private EditText Nombre;
    private EditText Apellidos;
    private EditText Correo;
    private EditText Contraseña;
    private EditText ConfirmarContraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        mAuth = FirebaseAuth.getInstance();
        Nombre = findViewById(R.id.txtNombre);
        Apellidos = findViewById(R.id.txtApellidos);
        Correo = findViewById(R.id.txtCorreo);
        Contraseña = findViewById(R.id.txtContrasena);
        ConfirmarContraseña = findViewById(R.id.txtConfirmarContraseña);
        C1=findViewById(R.id.txtNombre);
        C2=findViewById(R.id.txtApellidos);
        C3=findViewById(R.id.txtCorreo);
        C4=findViewById(R.id.txtContrasena);
        C5=findViewById(R.id.txtConfirmarContraseña);

    }
    public boolean validar() {
        boolean retorno = true;
        String C1 = Nombre.getText().toString();
        String C2 = Apellidos.getText().toString();
        String C3 = Correo.getText().toString();
        String C4 = Contraseña.getText().toString();
        String C5 = ConfirmarContraseña.getText().toString();
        if (C1.isEmpty()) {
            Nombre.setError("El campo del correo electronico esta vacio");
            retorno = false;
        }
        if (C2.isEmpty()) {
            Apellidos.setError("El campo del contraseña esta vacio");
            retorno = false;
        }
        if (C3.isEmpty()) {
            Correo.setError("El campo del correo electronico esta vacio");
            retorno = false;
        }
        if (C4.isEmpty()) {
            Contraseña.setError("El campo del correo electronico esta vacio");
            retorno = false;
        }
        if (C5.isEmpty()) {
            ConfirmarContraseña.setError("El campo del correo electronico esta vacio");
            retorno = false;
        }
        return retorno;
    }
   //verificar si inicio sesion
   @Override
   public void onStart() {
       super.onStart();
       // Check if user is signed in (non-null) and update UI accordingly.
       FirebaseUser currentUser = mAuth.getCurrentUser();
       //updateUI(currentUser);
   }
   //registrar Usuario Nuevo
    public void registrarUsuario(View view) {
        if (validar()) {
            if (Contraseña.getText().toString().trim().equals(ConfirmarContraseña.getText().toString().trim())) {
                mAuth.createUserWithEmailAndPassword(Correo.getText().toString().trim(), Contraseña.getText().toString().trim())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    //Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);
                                    Intent o = new Intent(RegistroUsuario.this, IniciarSesion.class);
                                    startActivity(o);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }
    }
}