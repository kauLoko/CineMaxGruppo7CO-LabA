package CineMax;

import prog.io.*;
import java.io.*;
import java.util.*;


public class Utenti {
    //Campi
    private String nome;
    private String cognome;
    private String username;
    private int password;
    private String nascita;
    private String domicilio;
    private int ruolo;
    private static final String fileUtenti = "InfoUtenti.txt";

    //Construtore

    public Utenti(String nome, String cognome, String username, int password, String nascita, String domicilio, int ruolo) {
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
    public static void registrareUtente(Scanner scanner) throws IOException {

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Cognome: ");
        String cognome = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        //vedi se la password é un numer
        int password = 0;

        boolean entrataValida = false;

        while (!entrataValida) {
            try {
                System.out.print("Password: ");
                password = scanner.nextInt(); // Prova a leggere un Int
                scanner.nextLine();
                entrataValida = true; // Se leggi, usce dell loop
            } catch (InputMismatchException e) {
                // 2. Errore e non esci dell loop
                System.out.println("Errore:Password solo numeri.");
                scanner.next();
            }
        }
        System.out.print("Nascista: ");
        String nascista = scanner.nextLine();
        System.out.print("Domicilio: ");
        String domicilio = scanner.nextLine();

        String nomeRuolo = "";
        entrataValida = true;
        while(entrataValida) {
            try {
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

        ConsoleOutputManager ou = new ConsoleOutputManager();
        ConsoleInputManager in = new ConsoleInputManager();

        File file = new File("InfoUtenti.txt");
        Scanner scan = new Scanner(file);
        String fileContent = "";

        while (scan.hasNextLine()) {
            fileContent = fileContent.concat(scan.nextLine() +"\n");
        }

        try (FileWriter fw = new FileWriter(fileUtenti);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            // Salvare
            out.println(nome + "," + cognome + "," + username + "," + password + "," + nascista + "," + domicilio + "," + nomeRuolo + "\n" + fileContent);
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

        boolean autenticazione  = false;
        try (Scanner fileScanner = new Scanner(new File(fileUtenti))) {
            while (fileScanner.hasNextLine()) {
                String linha = fileScanner.nextLine();
                String[] dados = linha.split(",");
                String tipoLogin = "";
                if (dados.length == 7 && dados[2].equals(username) && dados[3].equals(password)) {
                    autenticazione  = true;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Non registrato.");
        }

        if (autenticazione ) {
            System.out.println("Login bene-sucedido! Bene-venuto " + username);
        } else {
            System.out.println("Username o password incorrect.");
        }
    }

    public String toString(){
        return "\nIl tuo  è Utenti:" + nome + " " + cognome +"\nUsername: "+ username + "\nPassword: " + String.valueOf(password)
                + "\nNascita: " + nascita + "\nDomicilio: " + domicilio + "\nRole: " + ruolo;
    }

}
