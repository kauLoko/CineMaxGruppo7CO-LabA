package CineMax;

import java.io.*;
import java.util.*;

public class LoginClienti 
{
    private static final String ARQUIVO_USUARIOS = "UtentiInfo.txt";

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Registrare\n2 - Login");
        int opcao = scanner.nextInt();
        scanner.nextLine(); //Cancella il buffer

        if (opcao == 1) 
        {
            registraCliente(scanner);
        } 
        else if (opcao == 2) 
        {
            fazerLogin(scanner);
        }
    }

    // Metodo per registrare nome utente e password nel file

    public static void registraCliente(Scanner scanner) 
    {
        System.out.print("Nome utente: ");
        String user = scanner.nextLine();
        System.out.print("Password: ");
        String pass = scanner.nextLine();

        try (FileWriter fw = new FileWriter(ARQUIVO_USUARIOS, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) 
        {
            // Salvato nel formato: nomeutente, password
            out.println(user + "," + pass);
            System.out.println("Utente registrato con successo!");
        } 
        catch (IOException e) 
        {
            System.out.println("Errore durante il salvataggio del file: " + e.getMessage());
        }
    }
    
    // Metodo per leggere il file e verificare l'accesso
    public static void fazerLogin(Scanner scanner) 
    {
        System.out.print("Utente: ");
        String user = scanner.nextLine();
        System.out.print("Password: ");
        String pass = scanner.nextLine();

        boolean autenticazione = false;
        try (Scanner fileScanner = new Scanner(new File(ARQUIVO_USUARIOS))) 
        { 
            while (fileScanner.hasNextLine()) 
            {
                String linea = fileScanner.nextLine();
                String[] dati = linea.split(",");
                if (dati.length == 7 && dati[2].equals(user) && dati[3].equals(pass)) 
                {
                    autenticazione = true;
                    break;
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Ancora nessun utente registrato");
        }

        if (autenticazione) 
        {
            System.out.println("Login effettuato con successo!");
        } 
        else 
        {
            System.out.println("Nome utente o password errati");
        }
    }
}