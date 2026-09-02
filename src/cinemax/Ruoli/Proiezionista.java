package cinemax.Ruoli;
import cinemax.*;

import java.util.*;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * La classe rappresenta il proiezionista e gestisce l'aggiunta, modifica ed eliminazione di una proiezione
 * @author Toppi Davide, Matricola: 765309, Sede: CO
 * @author Molteni Davide, Matricola: 765300, Sede: CO 
 * @author Lanza Mattia Antonio, Matricola: 766287, Sede: CO 
 * @author Salmazo Bocatto Kauan, Matricola: 767919, Sede: CO 
 */
public class Proiezionista extends Utente
{
    /**
     * Costruttore della classe Proiezionista
     * @param nome Nome inserito in fase di registrazione
     * @param cognome Cognome inserito in fase di registrazione
     * @param username Username scelto dall'utente in fase di registrazione
     * @param password Password cifrata
     * @param nascita Data di nascita inserita in fase di registrazione
     * @param domicilio Domicilio inserito in fase di registrazione
     */
    public Proiezionista(String nome, String cognome, String username, String password, String nascita, String domicilio) 
    {
        super(nome, cognome, username, password, nascita, domicilio, Ruolo.proiezionista);
    }

    /**
     * Crea un oggetto Proiezione con i parametri inseriti dall'utente e lo aggiunge a List<Proiezione> listaProiezioni
     * @param scanner Scanner per ottenere l'input dell'utente 
     * @param listaProiezioni Array list contenente tutte le proiezioni contenute all'interno del file 'proiezioni.csv'
     */
    public static void aggiungiProiezione(Scanner scanner, List<Proiezione> listaProiezioni) 
    {
        // Acquisizione dei parametri della proiezione da aggiungere
        String titolo = "";
        while(titolo.isEmpty()) 
        {
            System.out.print("Inserisci il titolo della proiezione (o inserire 'esci' per annullare): ");
            titolo = scanner.nextLine().trim();
            if(titolo.isEmpty()) 
            {
                System.out.println("\nErrore: la proiezione deve avere un titolo\n");
            }
            else if(titolo.equalsIgnoreCase("esci")) 
            {
                System.out.println("\nOperazione annullata");
                return; //Esco dal metodo senza fare nulla
            }
        }

        String genere = "";
        while(genere.isEmpty()) 
        {
            System.out.print("Inserisci il genere della proiezione (o inserire 'esci' per annullare): ");
            genere = scanner.nextLine().trim();
            if(genere.isEmpty()) 
            {
                System.out.println("\nErrore: la proiezione deve avere un genere\n");
            }
            else if(genere.equalsIgnoreCase("esci")) 
            {
                System.out.println("\nOperazione annullata");
                return; //Esco dal metodo senza fare nulla
            }
        }

        String regista = "";
        while(regista.isEmpty()) 
        {
            System.out.print("Inserisci il regista della proiezione (o inserire 'esci' per annullare): ");
            regista = scanner.nextLine().trim();
            if(regista.isEmpty()) 
            {
                System.out.println("\nErrore: la proiezione deve avere un regista\n");
            }
            else if(regista.equalsIgnoreCase("esci")) 
            {
                System.out.println("\nOperazione annullata");
                return; //Esco dal metodo senza fare nulla
            }
        }

        int anno = 0;
        boolean annoValido = false;
        while (!annoValido)
        {
            System.out.print("Inserisci l'anno di uscita del film (o inserire -1 per annullare): ");
            try 
            {
                anno = Integer.parseInt(scanner.nextLine().trim()); //Legge stringa e converte in int, più comodo e sicuro di avere nextInt e debuffer
                if(anno >= 1895)   //Anno in cui è avvenuta la prima proiezione, per avere un limite ed evitare date impossibili
                {
                    annoValido = true; //Se legge un input valido esce dal ciclo while
                }
                else if(anno == -1) 
                {
                    System.out.println("\nOperazione annullata");
                    return; //Esco dal metodo senza fare nulla
                }
                else 
                {
                    System.out.println("\nErrore: l'anno non può essere precedente al 1895\n");
                }
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("\nErrore: inserisci un numero intero valido\n");
            }
        }

        int durata = 0;
        boolean durataValida = false;
        while (!durataValida) 
        {
            System.out.print("Inserisci la durata del film (in minuti, o -1 per annullare): ");
            try 
            {
                durata = Integer.parseInt(scanner.nextLine().trim());
                if(durata > 0) //Non posso avere durata negativa
                {
                    durataValida = true;
                }
                else if(durata == -1) 
                {
                    System.out.println("\nOperazione annullata");
                    return;
                }
                else 
                {
                    System.out.println("\nErrore: la durata non può essere negativa\n");
                }
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("\nErrore: inserisci un numero intero valido\n");
            }
        }

        int etaMin = 0; 
        boolean etaValida = false;
        while (!etaValida) 
        {
            System.out.print("Inserisci l'età minima per la visione del film (o inserire -1 per annullare): ");
            try 
            {
                etaMin = Integer.parseInt(scanner.nextLine().trim());
                if(etaMin >= 0) //L'età minima deve essere positiva
                {
                    etaValida = true;
                }
                else if(etaMin == -1) 
                {
                    System.out.println("\nOperazione annullata");
                    return;
                }
                else 
                {
                    System.out.println("\nErrore: l'età minima non può essere negativa\n");
                }
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("\nErrore: inserisci un numero intero valido\n");
            }
        }    
        
        double costo = 0.0; 
        boolean costoValido = false;
        while (!costoValido) 
        {
            System.out.print("Inserisci il prezzo di un biglietto (per i decimali usare il punto, es. 8.50, o -1 per annullare): ");
            try 
            {
                costo = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
                if(costo > 0) //Il costo deve essere positivo
                {
                    costoValido= true;
                }
                else if(costo == -1) 
                {
                    System.out.println("\nOperazione annullata");
                    return;
                }
                else 
                {
                    System.out.println("\nErrore: il costo di un biglietto non può essere negativo\n");
                }
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("\nErrore: inserisci un numero valido\n");
            }
        
        }

        // ciclo per richiesta data e orario, da capire se farne due divisi o uno unico
        DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
        DateTimeFormatter oraFormat = DateTimeFormatter.ofPattern("H:mm");
        DateTimeFormatter formatCSV = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dataOra = null;
        boolean dataOraValida = false;

        while(!dataOraValida) 
        {
            LocalDate data = null;
            LocalTime ora = null;

            boolean dataValida = false;
            while(!dataValida) 
            {
                System.out.println("Inserisci la data della proiezione (gg/mm/aaaa) oppure inserire 'esci' per annullare");
                try 
                {
                    String input = scanner.nextLine().trim();
                    if(input.equalsIgnoreCase("esci")) 
                    {
                        System.out.println("\nOperazione annullata");
                        return;
                    }
                    data = LocalDate.parse(input, dataFormat);
                    dataValida = true;
                }
                catch (Exception e) 
                {
                    System.out.println("\nErrore di formato: usare esattamenente gg/mm/aaaa (es. 12/10/2026)\n");
                }
            }

            boolean oraValida = false;
            while(!oraValida) 
            {
                System.out.println("Inserisci l'ora della proiezione (H:mm) oppure inserire 'esci' per annullare");
                try 
                {
                    String input = scanner.nextLine().trim();
                    if(input.equalsIgnoreCase("esci")) 
                    {
                        System.out.println("\nOperazione annullata");
                        return;
                    }
                    ora = LocalTime.parse(input, oraFormat);
                    oraValida = true;
                }
                catch (Exception e) 
                {
                    System.out.println("\nErrore di formato: usare esattamente HH:mm (es. 9:30 oppure 21:30)\n");
                }
            }
            dataOra = LocalDateTime.of(data, ora);
            if(dataOra.isBefore(LocalDateTime.now())) 
            {
                System.out.println("\nImpossibile programmare una proiezione nel passato. Scegliere una data futura\n");
            }
            else 
            {
                //Controllo per la sovrapposizione con altri film, sempre per esclusione perchè è più semplice
                boolean sovrapposizione = false;
                LocalDateTime fineFilm = dataOra.plusMinutes(durata);
                for(Proiezione proiezione: listaProiezioni) 
                {
                    //Ora di inizio della proiezione n-esima
                    LocalDateTime inizioProiezione = LocalDateTime.parse(proiezione.getDataOrario(), formatCSV);
                    //Calcolo ora di fine della proiezione n-esima
                    LocalDateTime fineProiezione = inizioProiezione.plusMinutes(proiezione.getDurata());
                    //Chiedo che l'inizio della proiezione da aggiungere sia prima della fine della proiezione n-esima e inizio della n-esima sia prima della fine della proiezione aggiunta
                    //Uso && perchè due eventi si sovrappongono solo se entrambi iniziano prima che l'altro sia finito, ovvero avvengono in contamporanea
                    if(dataOra.isBefore(fineProiezione) && inizioProiezione.isBefore(fineFilm)) 
                    {
                        sovrapposizione = true;
                        System.out.println("\nErrore: rilevata una sovrapposizione");
                        System.out.println("La sala è già occupata dal film '" + proiezione.getTitolo() + "'");
                        System.out.println("dalle " + inizioProiezione.format(DateTimeFormatter.ofPattern("HH:mm")) + " alle " + fineProiezione.format(DateTimeFormatter.ofPattern("HH:mm")));
                        System.out.println("Scegli un altro orario");
                        break; //Non mi serve controllare gli altri film se è già stata rilevata una sovrapposizione
                    }
                }
                if(!sovrapposizione) 
                {
                    //Se non ci sono sovrapposizioni allora la data viene confermata
                    dataOraValida = true;
                }
            }
        }
        String dataOrarioStringa = dataOra.format(formatCSV); //Converto l'oggetto LocalDateTime in stringa perchè richiesto dal costruttore di Proiezione
        //Finito di acquisire i parametri, credo nuovo oggetto e faccio add alla lista
        Proiezione nuovaProiezione = new Proiezione(titolo, genere, regista, anno, durata, etaMin, dataOrarioStringa, costo);
        listaProiezioni.add(nuovaProiezione);
        System.out.println("\nProiezione aggiunta con successo!");
    }

    /**
     * Permette di modificare i dettagli di una Proiezione e aggiorna l'oggetto in List<Proiezione> listaProiezioni se non sono state effettuate prenotazione
     * @param scanner Scanner per ottenere l'input dell'utente 
     * @param listaProiezioni Array list contenente tutte le proiezioni contenute all'interno del file 'proiezioni.csv'
     */
    public static void modificaProiezione(Scanner scanner, List<Proiezione> listaProiezioni) 
    {
        List<Proiezione>  risultatoParziale = new ArrayList<>(); //Per salvare i risultati parziali
        String titolo = "";

        while(titolo.isEmpty() && risultatoParziale.isEmpty()) 
        {
            System.out.println("\nInserire il titolo della proiezione da modificare (oppure 'esci' per annullare): ");
            titolo = scanner.nextLine().trim(); 
            if(titolo.equalsIgnoreCase("esci")) 
            {
                System.out.println("\nOperazione annullata");
                return; //Uso return e non break perchè devo saltare anche le fasi successive, altrimenti stamperebbe '0 proiezioni trovate' a vuoto
            }
            if(titolo.isEmpty()) 
            {
                System.out.println("\nErrore: il titolo della proiezione non può essere vuoto");
                continue; //Uso il continue perchè se è vuoto non ha senso scansionare tutta la lista, riparto direttamente dall'inizio del metodo
            }
            for(Proiezione proiezione: listaProiezioni) 
            {
                if(titolo.equalsIgnoreCase(proiezione.getTitolo().trim())) 
                {
                    risultatoParziale.add(proiezione);
                }
            }
            if(risultatoParziale.isEmpty()) 
            {
                System.out.println("\nNessuna proiezione trovata con il titolo '" + titolo + "'. Riprova");
                titolo = ""; //Reset del titolo per poter tornare ad inizio metodo senza input residui
            }
        }

        System.out.println(risultatoParziale.size() + " proiezioni trovate per '" + titolo + "'");
        for(Proiezione proiezione: risultatoParziale)
        {
            System.out.println("- Data e ora: " + proiezione.getDataOrario());
        }
        //Riutilizzo il codice del metodo precedente per inserire data e ora, la logica è la stessa
        DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
        DateTimeFormatter oraFormat = DateTimeFormatter.ofPattern("H:mm");
        DateTimeFormatter formatCSV = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Proiezione proiezioneModificata = null;
        boolean selezioneValida = false;

        while(!selezioneValida) 
        {
            System.out.println("-- Seleziona la proiezione esatta --");
            LocalDate dataScelta = null;
            LocalTime oraScelta = null;

            //Prendo la data
            boolean dataValida = false;
            while(!dataValida) 
            {
                System.out.println("Seleziona la data esatta (gg/mm/aaaa): ");
                try 
                {
                    dataScelta = LocalDate.parse(scanner.nextLine().trim(), dataFormat);
                    dataValida = true;
                }    
                catch (Exception e) 
                {
                    System.out.println("Errore di formato: usa esattamente gg/mm/aaaa (es. 12/10/2026)");
                }
            }

            //Prendo l'ora 
            boolean oraValida = false;
            while(!oraValida) 
            {
                System.out.println("Seleziona l'ora esatta (H:mm): ");
                try 
                {
                    oraScelta= LocalTime.parse(scanner.nextLine().trim(), oraFormat);
                    oraValida = true;
                }    
                catch (Exception e) 
                {
                    System.out.println("Errore di formato: usa esattamente HH:mm (es. 9:30 oppure 21:30)");
                }
            }

            //Unisco data e ora per poi confrontare con i risultati parziali e trovare la proiezione esatta
            LocalDateTime dataOraScelta = LocalDateTime.of(dataScelta, oraScelta);
            String dataOraStringa = dataOraScelta.format(formatCSV);
            for(Proiezione proiezione: risultatoParziale)
            {
                if(proiezione.getDataOrario().equals(dataOraStringa)) 
                {
                    proiezioneModificata = proiezione;
                    selezioneValida = true;
                    break;
                }
            }
            if(!selezioneValida) 
            {
                System.out.println("Nessuna proiezione corrisponde a questa data e ora");
            }
        }

        //Controllo se tutti i posti sono liberi per capire se ci sono prenotazioni per questa proiezione
        if(proiezioneModificata.getPostiDisponibili() < 200) 
        {
            System.out.println("Errore: impossibile modificare questa proiezione. Dei biglietti sono già stati venduti");
            return; //esci subito dal metodo
        }

        //Modifica della proiezione
        boolean modificando = true;
        while(modificando) 
        {
            //Richiesta parametro da modificare
            System.out.println("Quale parametro vuoi modificare?");
            System.out.println("1. Titolo");
            System.out.println("2. Genere");
            System.out.println("3. Regista");
            System.out.println("4. Anno");
            System.out.println("5. Durata");
            System.out.println("6. Età minima");
            System.out.println("7. Data e orario");
            System.out.println("8. Costo del biglietto");
            System.out.println("0. Salva modifiche");
            System.out.println("Scelta: ");

            //Gestione casi
            String scelta = scanner.nextLine().trim();
            switch (scelta) 
            {
                case "1":
                    System.out.println("Inserisci il nuovo titolo della proiezione");
                    String titoloNuovo = scanner.nextLine().trim();
                    if(!titoloNuovo.isEmpty()) 
                    {
                        proiezioneModificata.setTitolo(titoloNuovo);
                        System.out.println("\nTitolo aggiornato\n");
                    }
                    break;
                case "2":
                    System.out.println("Inserisci il nuovo genere della proiezione");
                    String genereNuovo = scanner.nextLine().trim();
                    if(!genereNuovo.isEmpty()) 
                    {
                        proiezioneModificata.setGenere(genereNuovo);
                        System.out.println("\nGenere aggiornato\n");
                    }
                    break;
                case "3":
                    System.out.println("Inserisci il nuovo regista: ");
                    String registaNuovo = scanner.nextLine().trim();
                    if(!registaNuovo.isEmpty()) 
                    {
                        proiezioneModificata.setRegista(registaNuovo);
                        System.out.println("\nRegista aggiornato\n");
                    }
                    break;
                case "4":
                    System.out.println("Inserisci il nuovo anno di rilascio: ");
                    try 
                    {
                        int annoNuovo = Integer.parseInt(scanner.nextLine().trim());
                        if(annoNuovo > 1895) 
                        {
                            proiezioneModificata.setAnno(annoNuovo);
                            System.out.println("\nAnno aggiornato\n");
                        }
                        else 
                        {
                            System.out.println("\nErrore: il valore inserito deve essere maggiore di 1895\n");
                        }
                    }
                    catch (NumberFormatException e) 
                    {
                        System.out.println("\nErrore: inserisci un numero valido\n");
                    }
                    break;
                case "5":
                    System.out.println("Inserisci la nuova durata della proiezione: ");
                    try 
                    {
                        int durataNuova= Integer.parseInt(scanner.nextLine().trim());
                        if(durataNuova > 0) 
                        {
                            proiezioneModificata.setDurata(durataNuova);
                            System.out.println("\nDurata aggiornata\n");
                        }
                        else 
                        {
                            System.out.println("\nErrore: il valore inserito deve essere positivo\n");
                        }
                    }
                    catch (NumberFormatException e) 
                    {
                        System.out.println("\nErrore: inserisci un numero valido\n");
                    }
                    break;
                case "6":
                    System.out.println("Inserisci la nuova età minima per la visione");
                    try 
                    {
                        int etaMinNuova= Integer.parseInt(scanner.nextLine().trim());
                        if(etaMinNuova >= 0) 
                        {
                            proiezioneModificata.setEtaMin(etaMinNuova);
                            System.out.println("\nEtà minima aggiornata\n");
                        }
                        else 
                        {
                            System.out.println("\nErrore: il valore inserito deve essere positivo\n");
                        }
                    }
                    catch (NumberFormatException e) 
                    {
                        System.out.println("\nErrore: inserisci un numero valido\n");
                    }
                    break;
                case "7":
                    //Riutilizzo il blocco del metodo aggiungiProiezione, la logica di base è la stessa
                    boolean dataOraValida = false;
                    LocalDateTime nuovaDataOra = null;
                    while(!dataOraValida) 
                    {
                        LocalDate data = null;
                        LocalTime ora = null;

                        //Chiedo data
                        boolean dataValida = false;
                        while(!dataValida) 
                        {
                            System.out.println("Inserisci la data della proiezione (gg/mm/aaaa)");
                            try 
                            {
                                data = LocalDate.parse(scanner.nextLine().trim(), dataFormat);
                                dataValida = true;
                            }
                            catch (Exception e) 
                            {
                                System.out.println("\nErrore di formato: usare esattamenente gg/mm/aaaa (es. 12/10/2026)\n");
                            }
                        }
                        //Chiedo ora
                        boolean oraValida = false;
                        while(!oraValida) 
                        {
                            System.out.println("Inserisci l'ora della proiezione (H:mm)");
                            try 
                            {
                                ora = LocalTime.parse(scanner.nextLine().trim(), oraFormat);
                                oraValida = true;
                            }
                            catch (Exception e) 
                            {
                                System.out.println("\nErrore di formato: usare esattamente HH:mm (es. 9:30 oppure 21:30)\n");
                            }
                        }
                        //Unisco
                        nuovaDataOra = LocalDateTime.of(data, ora);
                        if(nuovaDataOra.isBefore(LocalDateTime.now())) 
                        {
                            System.out.println("\nErrore: impossibile riprogrammare una proiezione nel passato. Scegliere una data futura\n");
                        }
                        else 
                        {
                            dataOraValida = true;
                        }
                    }
                    proiezioneModificata.setDataOra(nuovaDataOra.format(formatCSV));
                    System.out.println("\nData e orario aggiornati\n");
                    break;
                case "8":
                    System.out.println("Inserisci il nuovo prezzo di un biglietto per la proiezione");
                    try 
                    {
                        double costoNuovo= Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
                        if(costoNuovo > 0) 
                        {
                            proiezioneModificata.setCosto(costoNuovo);
                            System.out.println("\nPrezzo aggiornato\n");
                        }
                        else 
                        {
                            System.out.println("\nErrore: il valore inserito deve essere positivo\n");
                        }
                    }
                    catch (NumberFormatException e) 
                    {
                        System.out.println("\nErrore: inserisci un numero valido (per i decimali usa il punto, es. '8.50')\n");
                    }
                    break;
                case "0":
                    System.out.println("\nUscita dal menù di modifica");
                    modificando = false;
                    break;           
                default:
                    System.out.println("\nScelta non valida: inserisci un numero da 0 a 8\n");
                    break;
            }
        }
        System.out.println("Modifiche salvate in memoria\n");
    }

    /**
     * Permette di eliminare una Proiezione da List<Proiezione> listaProiezioni se non sono state effettuate prenotazione 
     * @param scanner Scanner per ottenere l'input dell'utente 
     * @param listaProiezioni Array list contenente tutte le proiezioni contenute all'interno del file 'proiezioni.csv'
     */
    public static void eliminaProiezione(Scanner scanner, List<Proiezione> listaProiezioni) 
    {
        //La selezione della proiezione da eliminare è uguale alla ricerca della proiezione da modificare del metodo precedente
        List<Proiezione>  risultatoParziale = new ArrayList<>(); //Per salvare i risultati parziali
        String titolo = "";

        while(titolo.isEmpty() && risultatoParziale.isEmpty()) 
        {
            System.out.println("\nInserire il titolo della proiezione da eliminare (oppure 'esci' per annullare): ");
            titolo = scanner.nextLine().trim(); 
            if(titolo.equalsIgnoreCase("esci")) 
            {
                System.out.println("\nOperazione annullata");
                return; //Uso return e non break perchè devo saltare anche le fasi successive, altrimenti stamperebbe '0 proiezioni trovate' a vuoto
            }
            if(titolo.isEmpty()) 
            {
                System.out.println("\nErrore: il titolo della proiezione non può essere vuoto");
                continue; //Uso il continue perchè se è vuoto non ha senso scansionare tutta la lista, riparto direttamente dall'inizio del metodo
            }
            for(Proiezione proiezione: listaProiezioni) 
            {
                if(titolo.equalsIgnoreCase(proiezione.getTitolo().trim())) 
                {
                    risultatoParziale.add(proiezione);
                }
            }
            if(risultatoParziale.isEmpty()) 
            {
                System.out.println("\nNessuna proiezione trovata con il titolo '" + titolo + "'. Riprova");
                titolo = ""; //Reset del titolo per poter tornare ad inizio metodo senza input residui
            }
        }

        System.out.println(risultatoParziale.size() + " proiezioni trovate per '" + titolo + "'");
        for(Proiezione proiezione: risultatoParziale)
        {
            System.out.println("- Data e ora: " + proiezione.getDataOrario());
        }
        //Riutilizzo il codice del metodo precedente per inserire data e ora, la logica è la stessa
        DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
        DateTimeFormatter oraFormat = DateTimeFormatter.ofPattern("H:mm");
        DateTimeFormatter formatCSV = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Proiezione proiezioneDaEliminare = null;
        boolean selezioneValida = false;

        while(!selezioneValida) 
        {
            System.out.println("\n--- Seleziona la proiezione esatta ---");
            LocalDate dataScelta = null;
            LocalTime oraScelta = null;

            //Prendo la data
            boolean dataValida = false;
            while(!dataValida) 
            {
                System.out.println("Seleziona la data esatta (gg/mm/aaaa): ");
                try 
                {
                    dataScelta = LocalDate.parse(scanner.nextLine().trim(), dataFormat);
                    dataValida = true;
                }    
                catch (Exception e) 
                {
                    System.out.println("\nErrore di formato: usa esattamente gg/mm/aaaa (es. 12/10/2026)\n");
                }
            }

            //Prendo l'ora 
            boolean oraValida = false;
            while(!oraValida) 
            {
                System.out.println("Seleziona l'ora esatta (H:mm): ");
                try 
                {
                    oraScelta= LocalTime.parse(scanner.nextLine().trim(), oraFormat);
                    oraValida = true;
                }    
                catch (Exception e) 
                {
                    System.out.println("\nErrore di formato: usa esattamente HH:mm (es. 9:30 oppure 21:30)\n");
                }
            }

            //Unisco data e ora per poi confrontare con i risultati parziali e trovare la proiezione esatta
            LocalDateTime dataOraScelta = LocalDateTime.of(dataScelta, oraScelta);
            String dataOraStringa = dataOraScelta.format(formatCSV);
            for(Proiezione proiezione: risultatoParziale)
            {
                if(proiezione.getDataOrario().equals(dataOraStringa)) 
                {
                    proiezioneDaEliminare = proiezione;
                    selezioneValida = true;
                    break;
                }
            }
            if(!selezioneValida) 
            {
                System.out.println("\nNessuna proiezione corrisponde a questa data e ora\n");
            }
        }

        //Controllo se tutti i posti sono liberi per capire se ci sono prenotazioni per questa proiezione
        if(proiezioneDaEliminare.getPostiDisponibili() < 200) 
        {
            System.out.println("\nErrore: impossibile eliminare questa proiezione. Dei biglietti sono già stati venduti\n");
            return; //esci subito dal metodo
        }
        listaProiezioni.remove(proiezioneDaEliminare);
        System.out.println("\nProiezione rimossa con successo!");
    }
}