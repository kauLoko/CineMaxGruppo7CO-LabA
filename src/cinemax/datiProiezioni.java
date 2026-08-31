// package cinemax;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class datiProiezioni 
{
    private static final String fileProiezioni = "data/proiezioni.csv";

    //Metodi

    public static List<Proiezione> listaProiezioni() 
    {
        List<Proiezione> listaProiezioni = new ArrayList<>(); //Uso la lista perchè l'array ha dimensione fissa definita in fase di creazione, mentre la lista è estendibile

        try (BufferedReader br = new BufferedReader(new FileReader(fileProiezioni))) 
        {
            String riga = br.readLine(); //Salta la prima riga che è di intestazione

            while ((riga = br.readLine()) != null) 
            {
                if(riga.trim().isEmpty()) 
                    continue;
                String[] campi = riga.replace("\"", "").split(",");
                String dataOrario = campi[0].trim();
                String titolo = campi[1].trim();
                String genere = campi[2].trim(); 
                String regista = campi[3].trim();
                int anno = Integer.parseInt(campi[4].trim());
                int durata = Integer.parseInt(campi[5].trim());
                int etàMinima = Integer.parseInt(campi[6].trim());
                double costo = Double.parseDouble(campi[7].trim());

                Proiezione proiezione = new Proiezione(titolo, genere, regista, anno, durata, etàMinima, dataOrario, costo);
                listaProiezioni.add(proiezione);
            }
        }   
        catch (IOException e) 
        {
            System.err.println("Errore durante la lettura del file: " + e.getMessage());
        }
        return listaProiezioni;
    }
    
    public static int calcoloPostiLiberi(Proiezione proiezione, List<Prenotazione> listaPrenotazioni) 
    {
        int capienzaMassima = 200;
        int postiOccupati = 0;
        for(Prenotazione p: listaPrenotazioni)
        {
            if(p.getTitoloFilm().equalsIgnoreCase(proiezione.getTitolo()) && p.getDataOraPrenotazione().equals(proiezione.getDataOrario())) 
            {
                postiOccupati += p.getNumeroBiglietti();
            }
        }
        return capienzaMassima - postiOccupati;
    }
    public static List<Proiezione> cercaProiezione(Scanner scanner, List<Proiezione> listaProiezioni) 
    {
        //Ottengo i criteri di ricerca dall'utente; imposto valori di default per capire se non vengono impostati dall'utente
        String titolo = null;
        String genere = null;
        String regista = null;
        LocalDate dataInizio = null;
        LocalDate dataFine = null;
        double costoMin = 0.0;
        double costoMax = Double.MAX_VALUE;

        String risposta = "";

        while(true) 
        {
            System.out.print("Vuoi cercare una proiezione per titolo? (s/n): ");
            risposta = scanner.nextLine().trim().toLowerCase();
            if (risposta.equals("s") || risposta.equals("n")) 
            {
                break;
            }
            else 
            {
                System.out.println("Risposta non valida. Inserisci 's' per si o 'n' per no.");
            }
        }
        if(risposta.equals("s")) 
        {
            System.out.print("Titolo del film: ");
            titolo = scanner.nextLine().trim();
        }

        while (true) 
        {
            System.out.print("Vuoi cercare una proiezione per genere? (s/n): ");
            risposta = scanner.nextLine().trim().toLowerCase();

            if (risposta.equals("s") || risposta.equals("n")) 
            {
                break;
            }
            else 
            {
                System.out.println("Risposta non valida. Inserisci 's' per si o 'n' per no.");
            }
        }

        if(risposta.equals("s")) 
        {
            System.out.print("Genere del film: ");
            genere = scanner.nextLine().trim();
        }
    
        while (true) 
        {
            System.out.print("Vuoi cercare una proiezione per regista? (s/n): ");
            risposta = scanner.nextLine().trim().toLowerCase();

            if (risposta.equals("s") || risposta.equals("n")) 
            {
                break;
            }
            else 
            {
                System.out.println("Risposta non valida. Inserisci 's' per si o 'n' per no.");
            }
        }

        if(risposta.equals("s")) 
        {
            System.out.print("Regista del film: ");
            regista = scanner.nextLine().trim();
        }

        while (true) 
        {
            System.out.print("Vuoi cercare una proiezione per data? (s/n): ");
            risposta = scanner.nextLine().trim().toLowerCase();

            if (risposta.equals("s") || risposta.equals("n")) 
            {
                break;
            }
            else 
            {
                System.out.println("Risposta non valida. Inserisci 's' per si o 'n' per no.");
            }
        }

        if (risposta.equals("s"))
        {
            DateTimeFormatter formatInput = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.println("Puoi inserire un intervallo temporale, altrimenti premere invio");
            boolean inizioValido = false;
            while(!inizioValido) 
            {
                System.out.print("Data iniziale (gg/mm/aaaa)");
                String inizio = scanner.nextLine().trim();
                if(inizio.isEmpty()) 
                {
                    inizioValido = true;
                }
                else 
                {
                    try 
                    {
                        dataInizio = LocalDate.parse(inizio, formatInput);
                        inizioValido = true;
                    }
                    catch (Exception e) 
                    {
                        System.out.println("Errore: usa il formato gg/mm/aaaa");
                    }
                }
            }

            boolean fineValido = false;
            while(!fineValido) 
            {
                System.out.print("Data finale (gg/mm/aaaa)");
                String fine = scanner.nextLine().trim();
                if(fine.isEmpty()) 
                {
                    fineValido = true;
                }
                else 
                {
                    try 
                    {
                        dataFine = LocalDate.parse(fine, formatInput);
                        if(dataInizio != null && dataFine.isBefore(dataInizio)) 
                        {
                            System.out.println("Errore: la data finale non può precedere la data iniziale");
                        }
                        else 
                        {
                            fineValido = true;
                        }
                    }
                    catch (Exception e) 
                    {
                        System.out.println("Errore: usa il formato gg/mm/aaaa");
                    }
                }
            }
        }

        while (true) 
        {
            System.out.print("Vuoi cercare una proiezione per costo del biglietto? (s/n): ");
            risposta = scanner.nextLine().trim().toLowerCase();

            if (risposta.equals("s") || risposta.equals("n")) 
            {
                break;
            }
            else 
            {
                System.out.println("Risposta non valida. Inserisci 's' per si o 'n' per no.");
            }
        }

        if(risposta.equals("s")) 
        {
            boolean inputValidoMin = false;

            while(!inputValidoMin) 
            {
                System.out.print("Inserisci il costo minimo (es. 8, oppure 0 per non impostare un prezzo minimo): ");

                try
                {
                    costoMin = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
                    if(costoMin >= 0) 
                    {
                        inputValidoMin = true;
                    }
                    else 
                    {
                        System.out.println("Errore: il costo non può essere negativo");
                    }
                }
                catch (NumberFormatException e) 
                {
                    System.out.println("Input non valido. Inserisci un numero valido (usa il punto per i decimali, es. '8.50').");
                }
            }

            boolean inputValidoMax = false;

            while(!inputValidoMax) 
            {
                System.out.print("Inserisci il costo massimo (es. 15, oppure 1000 per non impostare un prezzo massimo): ");
                try
                {
                    costoMax = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
                    if(costoMax >= costoMin) 
                    {
                        inputValidoMax = true;
                    }
                    else 
                    {
                        System.out.println("Errore: il costo massimo non può essere inferiore al costo minimo (" + costoMin + ").");
                    }
                }
                catch (NumberFormatException e) 
                {
                    System.out.println("Input non valido. Inserisci un numero valido (usa il punto per i decimali, es. '15.00').");
                }
            }
        }

        System.out.print("Filtri impostati. Inizio la ricerca...");
        
        LocalDateTime dataOggi = LocalDateTime.now();

        //Prendo la listaProiezioni generata all'avvio del programma e la filtro in base ai criteri impostati dall'utente, poi stampo i risultati della ricerca.
        List<Proiezione> risultatoRicerca = new ArrayList<>();
    

        for(Proiezione proiezione : listaProiezioni) 
        {
            boolean corrisponde = true;
            // Escludo le proiezioni già avvenute, non avrebbe senso mostrarle
            DateTimeFormatter formatCSV = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            LocalDateTime orarioProiezione = LocalDateTime.parse(proiezione.getDataOrario(), formatCSV);

            if(orarioProiezione.isBefore(dataOggi)) continue; //Salta direttamente al prossimo se già avvenuto
            //Filtro tutto per esclusione, molto più semplice logicamente e da implementare rispetto al filtrare per inclusione.

            if (titolo != null && !proiezione.getTitolo().toLowerCase().contains(titolo.toLowerCase())) 
            {
                corrisponde = false;
            } 

            if(genere != null && !proiezione.getGenere().toLowerCase().contains(genere.toLowerCase())) 
            {
                corrisponde = false;
            }

            if(regista != null && !proiezione.getRegista().toLowerCase().contains(regista.toLowerCase())) 
            {
                corrisponde = false;
            }
            
            if(dataInizio != null || dataFine != null)
            {
                LocalDate dataProiezione = LocalDate.parse(proiezione.getDataOrario().substring(0, 10));

                if(dataInizio != null && dataProiezione.isBefore(dataInizio)) corrisponde = false;

                if(dataFine != null && dataProiezione.isAfter(dataFine)) corrisponde = false;
            }   

            if(proiezione.getCosto() < costoMin || proiezione.getCosto() > costoMax) 
            {
                corrisponde = false;
            }
            if (corrisponde) 
            {
                risultatoRicerca.add(proiezione);
            }
        }

        if (risultatoRicerca.isEmpty()) 
        {
            System.out.println("\nNessuna proiezione trovata con questi filtri.");
        }   
        else 
        {
            Set<String> titoliTrovati = new HashSet<>();
            for (Proiezione p : risultatoRicerca) 
            {
                titoliTrovati.add(p.getTitolo());              
            }        
            System.out.println("Trovate " + risultatoRicerca.size() + " proiezioni distribuite su " + titoliTrovati.size() + " film:");
            for(String t: titoliTrovati) 
            {
                System.out.println("- " + t);
            }
        }
        System.out.println("--------------------------------"); //Per staccare quando verra' chiamato un altro metodo 
        return risultatoRicerca;
    }


    public static void visualizzaProiezione(Scanner scanner, List<Proiezione> risultatoRicerca) 
    {
        System.out.print("Inserisci il titolo della proiezione da visualizzare: ");
        String titolo = scanner.nextLine().trim();
        boolean trovato = false;

        for (Proiezione proiezione : risultatoRicerca) 
        {
            if (proiezione.getTitolo().equalsIgnoreCase(titolo)) 
            {
                System.out.println(proiezione.toString());
                trovato = true;
            }
        }
        if (!trovato) 
        {
            System.out.println("Proiezione non trovata.");
        }
    }
}