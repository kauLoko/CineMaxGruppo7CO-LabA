import java.io.*;
import java.util.*;

public class Bigliettaio extends Utente
{
    //Costruttore
    public Bigliettaio(String nome, String cognome, String username, String password, String nascita, String domicilio) 
    {
        super(nome, cognome, username, password, nascita, domicilio, Ruolo.bigliettaio);
    }

    public static void cercaPrenotazione(Scanner scanner) {
        // Implementazione del metodo per cercare una prenotazione
        //Aprire CSV trascrivere tutto come con le proiezioni ad inizio file e poi accedere alla lista per tutti i confronti e ricerche
    }

    public static void visualizzaPrenotazione(Scanner scanner) {
        // Implementazione del metodo per modificare una prenotazione
    }
}

