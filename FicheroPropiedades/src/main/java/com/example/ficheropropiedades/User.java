package com.example.ficheropropiedades;

/**
 * Clase User
 *
 * Contiene toda la informacion del usuario
 */
public class User {
    // Atributos
    private String name;
    private String password;

    // Constructores
    public User() {
        this.name = "";
        this.password = "";
    }
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    // Getters & Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return name + " " + password;
    }
}
