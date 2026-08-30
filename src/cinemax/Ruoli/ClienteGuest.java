// package cinemax.Ruoli;

import java.util.*;

public class ClienteGuest 
{
    public static ClienteGuest menuGuest() 
    {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("\n--- OPZIONI GUEST ---");
        System.out.println("1. Cerca Proiezioni");
        System.out.println("2. Visualizza Dettaglio Proiezione");
        System.out.println("3. Registrazione come cliente");
        System.out.println("4. Esci");
        System.out.print("Scelta: ");

        String scelta = scanner.nextLine().trim();

        switch (scelta) 
        {
            case "1":
                ClienteRegistrato.cercaProiezione(scanner, null);
                break;
            
            /*case "2":
                visualizzaDettProiezione
                break; */
            
            case "3":
                Utente.registrazioneUtente(scanner);
                break;
            
            case "4":
            {
                System.out.println("Grazie per aver usato CineMax. Arrivederci!");
                System.exit(0);
            }
    
            default:
                System.out.println("Scelta non valida!");
            }

        return null;
    }  
    
}
