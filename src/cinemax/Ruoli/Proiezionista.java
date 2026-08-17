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
    }

    public static void eliminaProiezione(Scanner scanner, List<Proiezione> listaProiezioni) 
    {
        // Implementazione del metodo per eliminare una proiezione
    }
}