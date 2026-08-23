import java.util.*;
import java.io.*;

public class ClienteGuest 
{
    public static menuGuest() 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nInserisci il titolo del film e premi invio per avviare la ricerca:");
        
        String titolo = scanner.nextLine().trim();

        List<Proiezione> proiezioni = .cercaProiezioni(titolo, null, null, null, null, null);

    

        while (true) {
            System.out.println("\n--- OPZIONI GUEST ---");
            System.out.println("1. Cerca Proiezioni (Filtri Avanzati)");
            System.out.println("2. Visualizza Dettaglio Proiezione");
            System.out.println("0. Torna al Menu Principale");
            System.out.print("Scelta: ");

            String scelta = scanner.nextLine().trim();
        }
    }
}
