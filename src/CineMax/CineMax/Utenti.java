package CineMax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Utenti {
    //Campi
    private String nome;
    private String cognome;
    private String username;
    private int password;
    private String nascista;
    private String domicilio;
    private String role;

    //Construtore

    public Utenti(String na,String cg,String user,int ps, String nsct,String dom,String ro){
        nome = na;
        cognome = cg;
        username = user;
        password = ps;
        nascista = nsct;
        domicilio = dom;
        role = ro;
    }

    //Metodi

    public void registraCliente() throws IOException {
        File file = new File("UntentiIInfo2.txt");
        Scanner scan = new Scanner(file);
        String fileContent = "" ;

        while (scan.hasNextLine()) {
            fileContent = fileContent.concat(scan.nextLine() + "\n");
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("UntentiIInfo2.txt"));
        writer.write(fileContent + nome + " " + cognome + " "  + username + " " + password + " " + nascista + " " +domicilio + " " + role);
        writer.close();
    }

    public void usernameLogin(){
        String userLog = username;
    }

    public void passwordLogin(){
        int passLog = password;
    }

    public String toString(){
        return "\nIl tuo  è Utenti:" + nome + " " + cognome +"\nUsername: "+ username + "\nPassword: " + String.valueOf(password)
                + "\nNascita: " + nascista + "\nDomicilio: " + domicilio + "\nRole: " + role;
    }

}
