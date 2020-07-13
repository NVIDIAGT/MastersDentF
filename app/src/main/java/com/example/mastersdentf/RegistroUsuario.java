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
    public void registrarUsuario(View view){
        if( Contraseña.getText().toString().equals(ConfirmarContraseña.getText().toString())){
            mAuth.createUserWithEmailAndPassword( Correo.getText().toString(), Contraseña.getText().toString())
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
                                Toast.makeText(getApplicationContext(), "Authentication failed.",Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });
        }else{
            Toast.makeText(this, "Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
        }


    }
}