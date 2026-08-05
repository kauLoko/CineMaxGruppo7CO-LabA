package CineMax;

import java.io.*;
import java.util.*;


public class Utente 
{
    //Campi
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private String nascita;
    private String domicilio;
    private Ruolo ruolo;
    private static final String fileUtenti = "InfoUtenti.txt";

    public enum Ruolo {cliente, proiezionista, bigliettaio};

    //Construtore
    public Utente(String nome, String cognome, String username, String password, String nascita, String domicilio, Ruolo ruolo) 
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
    public static void registrazioneUtente(Scanner scanner) throws IOException {

        System.out.print("Nome: ");
        String nome = scanner.next();
        System.out.print("Cognome: ");
        String cognome = scanner.next();
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
        System.out.print("Nascita: ");
        String nascita = scanner.nextLine();
        System.out.print("Domicilio: ");
        String domicilio = scanner.nextLine();

        System.out.println("Ruolo: 1. Cliente, 2. Proiezionista, 3. Bigliettaio");
        int scelta = scanner.nextInt();

        Utente u = new Utente(nome, cognome, username, password, nascita, domicilio, scelta);


        entrataValida = true;
        while(entrataValida) 
        {
            try 
            {
                System.out.print("Ruolo:(1.Clienti/2.Proiezionista/3.Balconista) SOLO IL NUMERO: ");
                int role = scanner.nextInt();
                if (role == 1) {
                    nomeRuolo = "Clienti";
                    entrataValida = false;
                    scanner.nextLine();
                }
                else if (role == 2) {
                    nomeRuolo = "Proiezionista";
                    entrataValida = false;
                    scanner.nextLine();
                }
                else if (role == 3) {
                    nomeRuolo = "Balconista";
                    entrataValida = false;
                    scanner.nextLine();
                } else {
                    System.out.print("Entrata non Valida\n");
                    scanner.next();
                }
            } catch (InputMismatchException e) {
                System.out.print("Entrata non Valida\n");
                scanner.next();
            }
        }


        File file = new File("InfoUtenti.txt");
        Scanner scan = new Scanner(file);
        String fileContent = "";

        while (scan.hasNextLine()) {
            fileContent = fileContent.concat(scan.nextLine() +"\n");
        }

        try (FileWriter fw = new FileWriter(fileUtenti);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pwOut = new PrintWriter(bw)) {

            // Salvare
            pwOut.println(nome + "," + cognome + "," + username + "," + password + "," + nascista + "," + domicilio + "," + nomeRuolo + "\n" + fileContent);
            System.out.println("Registrato con sucesso!");
        } catch (IOException e) {
            System.out.println("Errore in salvare file: " + e.getMessage());
        }
    }

    public String toString(){
        return "\nIl tuo  è Utenti:" + nome + " " + cognome +"\nUsername: "+ username + "\nPassword: " + String.valueOf(password)
                + "\nNascita: " + nascita + "\nDomicilio: " + domicilio + "\nRole: " + ruolo;
    }

}
