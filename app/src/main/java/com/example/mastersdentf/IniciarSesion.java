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

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class IniciarSesion extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    EditText C1,C2;
    private FirebaseAuth mAuth;
    private EditText correo;
    private EditText contraseña;

    //goocle
    SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    TextView textView;
    private static final int RC_SIGN_IN = 1;
    //google

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
            public void onClick(View view) {
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

        //Iniciar Cuenta GOOGLE
        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        signInButton=(SignInButton)findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,RC_SIGN_IN);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            gotoProfile();
        }else{
            Toast.makeText(getApplicationContext(),"Sign in cancel",Toast.LENGTH_LONG).show();
        }
    }

    private void gotoProfile(){
        Intent intent=new Intent(IniciarSesion.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    //FIN Cuenta GOOGLE
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
                                String _email = user.getEmail();
                                String _name = user.getDisplayName();
                                ///Aqui se envian parametros
                                Bundle b = new Bundle();
                                b.putString("email", _email);
                                ///End Envio paramentros
                                int a = 1;
                                a++;
                                // updateUI(user);

                                Intent o = new Intent(IniciarSesion.this, MainActivity.class);
                                o.putExtras(b);
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

