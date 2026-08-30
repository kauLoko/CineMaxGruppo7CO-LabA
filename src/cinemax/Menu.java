import java.util.*;

public class Menu
{

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
}

