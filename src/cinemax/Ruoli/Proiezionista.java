import java.io.*;
import java.util.*;

public class Proiezionista extends Utente
{
    //Costruttore
    public Proiezionista(String nome, String cognome, String username, String password, String nascita, String domicilio) 
    {
        super(nome, cognome, username, password, nascita, domicilio, Ruolo.proiezionista);
    }

    public static void aggiungiProiezione(Scanner scanner, List<Proiezione> listaProiezioni) 
    {
        // Implementazione del metodo per aggiungere una proiezione
    }

    public static void modificaProiezione(Scanner scanner, List<Proiezione> listaProiezioni) 
    {
        // Implementazione del metodo per modificare una proiezione
        /*
            1.Chiedi titolo data e ora della proiezione da modificare
            2.Apri arrayList del CSV con tutte le proiezioni e controlla che non ci siano prenotazioni
            3.Chiedi quali modifiche apportare
            4.Sovrascrivi il file e salva nel CSV 
        */
    }

    public static void eliminaProiezione(Scanner scanner, List<Proiezione> listaProiezioni) 
    {
        // Implementazione del metodo per eliminare una proiezione
        /*
            1.Chiedi titolo data e ora della proiezione da eliminare
            2.Apri arrayList del CSV con tutte le proiezioni e controlla che non ci siano prenotazioni
            3.Elimina la proiezione e salva modifiche del CSV
        */
    }
}