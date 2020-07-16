package com.example.mastersdentf.models;

public class Usuarios {
    public String Uid;
    public String Nombre;
    public String Apellidos;
    public String CorreoElectronico;
    public String Contraseña;

    public String getUid() {
        return Uid;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public Usuarios() {

    }

    @Override
    public String toString() {
        return Nombre;
    }
}
