import java.util.*;

public class Menu
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("CineMax - Menu");
        // Inizializzazione del menu principale da integrare qui.
    }

    public static Utente eseguiMenuLogin(Scanner scanner) 
    {
        System.out.println("-- LOGIN --");
        // Ottengo username
        System.out.println("Username: ");
        String username = scanner.nextLine().trim();
        // Ottengo password
        System.out.println("Password: ");
        String password = scanner.nextLine().trim();
        // Passo i dati al metodo eseguilogin vero e proprio
        return Utente.eseguiLogin(username, password);
    }

    public static void registrazioneUtente(Scanner scanner) 
    {
        System.out.println("-- REGISTRAZIONE CLIENTE --");
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Cognome: ");
        String cognome = scanner.nextLine().trim();

        System.out.print("Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Password: ");
        String passwordChiara = scanner.nextLine().trim();

        System.out.print("Data di nascita (gg/mm/aaaa): ");
        String nascita = scanner.nextLine().trim();

        System.out.print("Domicilio: ");
        String domicilio = scanner.nextLine().trim();
        // Cifro la password
        String passwordCifrata = Utente.cifrapassword(passwordChiara);
        //Fisso il ruolo a cliente
        Utente.Ruolo ruolo = Utente.Ruolo.cliente;
        //Creo l'oggetto vero e proprio e lo salvo nel CSV
        Utente nuovoUtente = new Utente(nome, cognome, username, passwordCifrata, nascita, domicilio, ruolo);
        nuovoUtente.salvaSuFile(); 

        System.out.println("Registrazione avvenuta con successo!");
    }
}

