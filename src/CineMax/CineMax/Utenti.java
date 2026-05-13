package CineMax;

import java.io.*;
import java.util.Scanner;

public class Utenti {
    //Campi
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private String nascita;
    private String domicilio;
    private String ruolo;
    private static final String fileUtenti = "InfoUtenti.txt";

    //Construtore

    public Utenti(String nome, String cognome, String username, String password, String nascita, String domicilio, String ruolo) 
    {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.nascita = nascita;
        this.domicilio = domicilio;
        this.ruolo = ruolo;
    }

    //Metodi

    //RegistraClienti
    public static void registrareUtente(Scanner scanner) {

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Cognome: ");
        String cognome = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Nascita: ");
        String nascista = scanner.nextLine();
        System.out.print("Domicilio: ");
        String domicilio = scanner.nextLine();
        System.out.print("Ruolo: ");
        String role = scanner.nextLine();

        try (FileWriter fw = new FileWriter(fileUtenti);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            // Salvare
            out.println(nome + "," + cognome + "," + username + "," + password + "," + nascita + "," + domicilio + "," + ruolo);
            System.out.println("Registrato con sucesso!");
        } catch (IOException e) {
            System.out.println("Errore in salvare file: " + e.getMessage());
        }
    }

    //Login
    public static void fareLogin(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        boolean autenticazione = false;
        try (Scanner fileScanner = new Scanner(new File(fileUtenti))) {
            while (fileScanner.hasNextLine()) {
                String riga = fileScanner.nextLine();
                String[] dati = riga.split(",");
                if (dati.length == 7 && dati[2].equals(username) && dati[3].equals(password)) 
                {
                    autenticazione = true;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Non registrato.");
        }

        if (autenticazione) {
            System.out.println("Login bene-sucedido!");
        } else {
            System.out.println("Username o password incorrect.");
        }
    }

    public String toString(){
        return "\nIl tuo  è Utenti:" + nome + " " + cognome +"\nUsername: "+ username + "\nPassword: " + String.valueOf(password)
                + "\nNascita: " + nascita + "\nDomicilio: " + domicilio + "\nRuolo: " + ruolo;
    }

}
