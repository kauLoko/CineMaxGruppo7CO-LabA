import java.io.*;
import java.util.*;

public class Cliente extends Utente {

    private static final String fileProiezioni = "data/proiezioni.csv";
    //Costruttore
    public Cliente(String nome, String cognome, String username, String password, String nascita, String domicilio) 
    {
        super(nome, cognome, username, password, nascita, domicilio, Ruolo.cliente);
    }

    public static void cercaProiezione(Scanner scanner, List<Proiezione> listaProiezioni) {

        //Ottengo i criteri di ricerca dall'utente; imposto valori di default per capire se non vengono impostati dall'utente
        String titolo = null;
        String genere = null;
        String regista = null;
        int anno = -1;
        String dataOrario = null;
        int durata = -1;
        int etàMinima = -1; 
        double costo = -1.0;
        //mettere try con dentro ciclo while con un booleano per forzare una risposta che sia s o n, come boolean continua = true; ed eventualmente gestire le eccezioni


        System.out.print("Vuoi cercare una proiezione per titolo? (s/n): ");
        String risposta = scanner.nextLine();
        if (risposta.equals("s")) {
            System.out.print("Titolo del film: ");
            titolo = scanner.nextLine();
        }
        System.out.print("Vuoi cercare una proiezione per genere? (s/n): ");
        risposta = scanner.nextLine();
        if (risposta.equals("s")) {
            System.out.print("Genere del film: ");
            genere = scanner.nextLine();
        }
        System.out.print("Vuoi cercare una proiezione per regista? (s/n): ");
        risposta = scanner.nextLine();
        if (risposta.equals("s")) {
            System.out.print("Regista del film: ");
            regista = scanner.nextLine();
        }
        System.out.print("Vuoi cercare una proiezione per anno? (s/n): ");
        risposta = scanner.nextLine();
        if (risposta.equals("s")) {
            System.out.print("Anno del film: ");
            anno = scanner.nextInt();
            scanner.nextLine(); // debuffer
        }   
        System.out.print("Vuoi cercare una proiezione per data e orario? (s/n): ");
        risposta = scanner.nextLine();
        if (risposta.equals("s")) {
            System.out.print("Data e orario della proiezione (formato: gg/mm/aaaa hh:mm): ");
            dataOrario = scanner.nextLine();
        }
        System.out.print("Vuoi cercare una proiezione per durata? (s/n): ");
        risposta = scanner.nextLine();
        if (risposta.equals("s")) {
            System.out.print("Durata del film (in minuti): ");
            durata = scanner.nextInt();
            scanner.nextLine(); // debuffer
        }
        System.out.print("Vuoi cercare una proiezione per età minima? (s/n): ");
        risposta = scanner.nextLine();
        if (risposta.equals("s")) {
            System.out.print("Età minima per il film: ");
            etàMinima = scanner.nextInt();
            scanner.nextLine(); // debuffer
        }
        System.out.print("Vuoi cercare una proiezione per costo del biglietto? (s/n): ");
        risposta = scanner.nextLine();
        if (risposta.equals("s")) {
            System.out.print("Costo del biglietto: ");
            costo = scanner.nextDouble();
            scanner.nextLine(); // debuffer
        }
        System.out.print("Filtri impostati. Inizio la ricerca...");

       //prendo la listaProiezioni generata all'avvio del programma e la filtro in base ai criteri impostati dall'utente, poi stampo i risultati della ricerca.
       for(Proiezione proiezione : listaProiezioni) {
            boolean corrisponde = true;
            //controllo vero e proprio. Filtro tutto per esclusione, molto più semplice logicamente e da implementare rispetto al filtrare per inclusione. Uso lazy evaluation per semplicità
            if (titolo != null && !proiezione.getTitolo().toLowerCase().contains(titolo.toLowerCase())) {
                corrisponde = false;
            }
            if(genere != null && !proiezione.getGenere().toLowerCase().contains(genere.toLowerCase())) {
                corrisponde = false;
            }
            if(regista != null && !proiezione.getRegista().toLowerCase().contains(regista.toLowerCase())) {
                corrisponde = false;
            }
            if(anno != -1 && proiezione.getAnno() != anno) {
                corrisponde = false;
            }
            if(dataOrario != null && !proiezione.getDataOrario().toLowerCase().contains(dataOrario.toLowerCase())) {
                corrisponde = false;
            }   
            if(durata != -1 && proiezione.getDurata() != durata) {
                corrisponde = false;
            }
            if(etàMinima != -1 && proiezione.getEtàMinima() != etàMinima) {
                corrisponde = false;
            }
            if(costo != -1.0 && proiezione.getCosto() != costo) {
                corrisponde = false;
            }
            if (corrisponde) {
                // Da aggiungere alle proiezioni da restituire
            }
       } 

    }
}