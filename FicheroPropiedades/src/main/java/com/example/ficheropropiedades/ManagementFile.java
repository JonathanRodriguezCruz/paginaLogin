package com.example.ficheropropiedades;

import java.io.*;
import java.util.ArrayList;
import com.password4j.BcryptFunction;

/**
 * Clase ManagementFile
 *
 * Clase que lee el fichero requerido y encripta las conrtraseñas de los usuarios
 */
public class ManagementFile {
    // Lee el fichero de usuario.properties
    public ArrayList<User> readProperties(){
        ArrayList<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/usuario.properties"))){
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] userList = line.split(" ");
                User user = new User(userList[0],userList[1]);
                users.add(user);
            }
        } catch (IOException  e) {
            System.out.println(e.getMessage());
        }

        return users;
    }

    // Escribe en el fichero de usuario.properties
    public void writeProperties(User users){
        File file = new File("src/main/resources/usuario.properties");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))){
            bw.write(users.toString());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Encripta una contraseña utilizando el algoritmo Bcrypt
    public String hashPassword(String password) {
        var bcrypt = BcryptFunction.getInstance(12);

        return bcrypt.hash(password).getResult();
    }

    // Verifica que la contraseña encriptada con la que le pasa el usuario
    public boolean verifyPassword(int j,String password,ArrayList<User> users) {
        String[] passwordsList = new String[users.size()];
        for (int i = 0; i < users.size(); i++) {
            passwordsList[i] = users.get(i).getPassword();
        }

        var bcrypt = BcryptFunction.getInstance(12);
        boolean valid = bcrypt.check(password, passwordsList[j]);

        if (valid == true) {
            return true;
        }
        return false;
    }
}
