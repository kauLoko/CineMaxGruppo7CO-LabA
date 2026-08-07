
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

    private static final String fileUtenti = "datiUtenti.csv";

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

    public static Utente registrazioneUtente(Scanner scanner) 
    {
        System.out.print("Nome: ");
        String nome = scanner.next();

        System.out.print("Cognome: ");
        String cognome = scanner.next();

        System.out.print("Username: ");
        String username = scanner.next();

        System.out.print("Password: ");
        String password = scanner.next();

        scanner.nextLine();

        System.out.print("Data di nascita: ");
        String nascita = scanner.nextLine();

        System.out.print("Domicilio: ");
        String domicilio = scanner.nextLine();

        
        Ruolo ruolo = null;

        while(ruolo == null)
        {   
            System.out.println("\nSeleziona ruolo:");
            System.out.println("1. Cliente\n2. Proiezionista\n3. Bigliettaio");
            int scelta = scanner.nextInt();

            switch(scelta) 
            {
                case 1:
                    ruolo = Ruolo.cliente;
                    break;
                case 2:
                    ruolo = Ruolo.proiezionista;
                    break;
                case 3:
                    ruolo = Ruolo.bigliettaio;
                    break;
                default:
                    System.out.print("Ruolo non valido, scegliere uno di quelli già esistenti");
                    break;
            }
        }

        Utente nuovoUtente = new Utente(nome, cognome, username, password, nascita, domicilio, ruolo);

        nuovoUtente.salvaSuFile();

        return nuovoUtente;
    }


    public void salvaSuFile()
    {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileUtenti, true)))) {
            pw.println(nome + "," + cognome + "," + username + "," + password + "," + nascita + "," + domicilio + "," + ruolo);
            System.out.println(">> Utente registrato e salvato con successo!");
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio su file: " + e.getMessage());
        }
    }

    @Override
    public String toString() 
    {
        return String.format("""
            
            --- SCHEDA UTENTE ---
            Nome: %s %s
            Username: %s
            Password: %s
            Nascita: %s
            Domicilio: %s
            Ruolo: %s""",
            nome, cognome, username, password, nascita, domicilio, ruolo);
    }
}

       /*  File file = new File("datiUtenti.csv");
        Scanner scan = new Scanner(file);
        String fileContent = "";

        while (scan.hasNextLine()) {
            fileContent = fileContent.concat(scan.nextLine() +"\n");
        }

        try (FileWriter fw = new FileWriter(fileUtenti);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pwOut = new PrintWriter(bw)) {

            // Salvare
            pwOut.println(nome + "," + cognome + "," + username + "," + password + "," + nascita + "," + domicilio + "," + ruolo + "\n" + fileContent);
            System.out.println("Registrato con sucesso!");
        } catch (IOException e) {
            System.out.println("Errore in salvare file: " + e.getMessage());
        }
    }

    public String toString(){
        return "\nIl tuo  è Utenti:" + nome + " " + cognome +"\nUsername: "+ username + "\nPassword: " + String.valueOf(password)
                + "\nNascita: " + nascita + "\nDomicilio: " + domicilio + "\nRole: " + ruolo;
    }

} */
