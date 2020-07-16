package com.example.mastersdentf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarContrasenna extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText emailAddress;
    EditText C1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasenna);
        emailAddress = findViewById(R.id.txtUsuario);
        mAuth = FirebaseAuth.getInstance();

    }
    public boolean validar() {
        boolean retorno = true;
        String C1 = emailAddress.getText().toString();
        if (C1.isEmpty()) {
            emailAddress.setError("El campo del correo electronico esta vacio");
            retorno = false;
        }
        return retorno;
    }
    public void sendPasswordReset(View v) {
        // [START send_password_reset]
        if (validar()) {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.sendPasswordResetEmail(emailAddress.getText().toString().trim())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Log.d(TAG, "Email sent.");
                                Toast.makeText(getApplicationContext(), "Listo ahora puede .",
                                        Toast.LENGTH_SHORT).show();
                                Intent o = new Intent(RecuperarContrasenna.this, IniciarSesion.class);
                                startActivity(o);
                            }
                        }
                    });
            // [END send_password_reset]
        }
    }
}