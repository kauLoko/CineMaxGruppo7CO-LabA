// package cinemax.Ruoli;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClienteRegistrato extends Utente 
{
    //Costruttore
    public ClienteRegistrato(String nome, String cognome, String username, String password, String nascita, String domicilio) 
    {
        super(nome, cognome, username, password, nascita, domicilio, Ruolo.cliente);
    }

    public static void creaPrenotazione(Scanner scanner, List<Proiezione> risultatoRicerca, Utente utente, List<Prenotazione> listaPrenotazioni) 
    {
        System.out.println("Inserisci data e ora della proiezione da prenotare (formato: gg/mm/aaaa hh:mm): ");
        String dataOra = scanner.nextLine().trim();
        boolean proiezioneTrovata = false;

        for(Proiezione p: risultatoRicerca) 
        {
            if(p.getDataOrario().equals(dataOra)) 
            {
                proiezioneTrovata = true;
                int numeroBiglietti = 0;
                boolean inputValido = false;
                while(!inputValido) 
                { 
                    System.out.println("Quanti biglietti desidera acquistare?");
                    try
                    {
                        numeroBiglietti = Integer.parseInt(scanner.nextLine().trim());
                        if(numeroBiglietti > 0) 
                        {
                            inputValido = true;
                        } 
                        else 
                        {
                            System.out.println("Inserisci un numero maggiore di zero");
                        }
                    } 
                    catch(NumberFormatException e)
                    {
                        System.out.println("Errore: devi inserire un numero intero");
                    }
                }
                if(numeroBiglietti <= p.getPostiDisponibili()) 
                {
                    p.decrementaPosti(numeroBiglietti);

                    //genera codice univoco
                    String codiceUnivoco = generaCodiceUnivoco();
                    
                    //crea oggetto prenotazione e aggiungilo alla lista prenotazioni globale 
                    Prenotazione nuovaPrenotazione = new Prenotazione(codiceUnivoco, utente.getNome(), utente.getCognome(), p.getTitolo(), p.getDataOrario(), p.getCosto(), (p.getCosto() * numeroBiglietti), numeroBiglietti);
                    listaPrenotazioni.add(nuovaPrenotazione); 
                    System.out.println("Prenotazione avvenuta con successo!");
                    System.out.println(nuovaPrenotazione.toString());
                }
                else 
                {
                    System.out.println("Prenotazione non riuscita: posti liberi non sufficienti");
                } break;
            }
        }
        if (!proiezioneTrovata)         
        {
            System.out.println("Nessuna proiezione trovata a questo orario");
        }
    }

    public static void modificaPrenotazione(Scanner scanner, List<Proiezione> listaProiezioni, List<Prenotazione> listaPrenotazioni) 
    {
        Boolean entrataValida = false;
        while(!entrataValida) 
        {
            System.out.println("Inserire il codice della prenotazione da modificare (o 'esci' per annullare): ");
            String codiceUnivoco = scanner.nextLine().trim();

            if(codiceUnivoco.equalsIgnoreCase("esci")) break;

            for(Prenotazione prenotazione: listaPrenotazioni) 
            {
                    if(codiceUnivoco.equals(prenotazione.getCodicePrenotazione())) 
                        {
                            entrataValida = true;
                            DateTimeFormatter formatter =   DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            LocalDateTime dataOggi = LocalDateTime.now();
                            LocalDateTime dataPrenotazione = LocalDateTime.parse(prenotazione.getDataOraPrenotazione(), formatter);
                            if(dataPrenotazione.isAfter(dataOggi)) 
                            {
                                System.out.println("Prenotazione trovata");
                                for(Proiezione proiezione: listaProiezioni) 
                                {
                                    if(proiezione.getTitolo().trim().equalsIgnoreCase(prenotazione.getTitoloFilm().trim())) 
                                    {
                                        LocalDateTime opzioni = LocalDateTime.parse(proiezione.getDataOrario(), formatter); 
                                        if(opzioni.isAfter(dataOggi) && !proiezione.getDataOrario().equals(prenotazione.getDataOraPrenotazione())) 
                                        {
                                            System.out.println("\n" + proiezione.getDataOrario());
                                        }
                                    }
                                }
                                System.out.println("Inserire la data in cui desideri spostare la preontazione (yyyy-MM-dd HH:mm:ss): ");
                                LocalDateTime dataNuova = null;
                                String inputUtente = "";
                                boolean formatoValido = false;
                                while(!formatoValido) 
                                    {
                                    inputUtente = scanner.nextLine().trim();
                                    try 
                                    {
                                        dataNuova = LocalDateTime.parse(inputUtente, formatter);
                                        formatoValido = true;
                                    }
                                    catch (Exception e) 
                                    {
                                        System.out.println("Errore di formato: inserisci la data esattamente come richiesto (yyyy-MM-dd HH:mm:ss)");
                                    }
                                }
                                
                                boolean nuovaTrovata = false;
                                int biglietti = prenotazione.getNumeroBiglietti();
                                for(Proiezione nuovaProiezione: listaProiezioni) 
                                {
                                    if(nuovaProiezione.getTitolo().trim().equalsIgnoreCase(prenotazione.getTitoloFilm().trim()) && LocalDateTime.parse(nuovaProiezione.getDataOrario(), formatter).equals(dataNuova)) 
                                    {
                                        nuovaTrovata = true;
                                        if(nuovaProiezione.getPostiDisponibili() >= biglietti) 
                                        {
                                            nuovaProiezione.decrementaPosti(biglietti);
                                            
                                            for(Proiezione vecchiaProiezione: listaProiezioni) 
                                            {
                                                if(vecchiaProiezione.getTitolo().trim().equalsIgnoreCase(prenotazione.getTitoloFilm().trim()) && vecchiaProiezione.getDataOrario().equals(prenotazione.getDataOraPrenotazione())) 
                                                {
                                                    vecchiaProiezione.setPostiDisponibili(vecchiaProiezione.getPostiDisponibili() + biglietti);
                                                    break;
                                                }
                                            }
                                            prenotazione.setDataOraPrenotazione(inputUtente); //Passo inputUtente perchè nel blocco precedente col formatter ho già controllato e imposto che sia nel formato corretto, così posso direttamente aggiornarlo 
                                            System.out.println("Prenotazione modificata con successo");
                                        }
                                        else 
                                        {
                                            System.out.println("Errore: posti insufficienti nella nuova data");
                                        } break;
                                    }
                                    
                                }
                                if(!nuovaTrovata) 
                                {
                                    System.out.println("La data inserita non valida o non corrispondente alkle opzioni");
                                } break;
                            }    
                            else 
                            {
                                System.out.println("Non puoi modificare una prenotazione passata. Riprova");
                            }
                        }
            }
            if(!entrataValida) 
            {
                System.out.println("Il codice prenotazione inserito non è valido");
            }
        }
    }

    public static void eliminaPrenotazione(Scanner scanner, List<Prenotazione> listaPrenotazioni) 
    {   
        Boolean entrataValida = false;
        while(!entrataValida) {
            System.out.println("Inserire il codice della prenotazione da eliminare (o 'esci' per annullare): ");
            String codiceUnivoco = scanner.nextLine().trim();

            if(codiceUnivoco.equalsIgnoreCase("esci")) break;

            for(Prenotazione p: listaPrenotazioni) 
            {
                    if(codiceUnivoco.equals(p.getCodicePrenotazione())) 
                        {
                            entrataValida = true;
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //MM e HH perchè in minuscolo indicherebbero minuti e formato a 12 ore con AM e PM
                            LocalDateTime dataOggi = LocalDateTime.now();
                            LocalDateTime dataPrenotazione = LocalDateTime.parse(p.getDataOraPrenotazione(), formatter);
                            if(dataPrenotazione.isBefore(dataOggi)) 
                            {
                                listaPrenotazioni.remove(p);
                                System.out.println("Prenotazione rimossa con successo");
                            }
                            else
                            {
                                System.out.println("Impossibile rimuovere la prenotazione: la proiezione deve ancora avvenire");
                            }
                            break;
                        }
            }
            if(!entrataValida) 
            {
                System.out.println("Il codice prenotazione inserito non è valido. Riprova");
            }
        }
    }

    public static String generaCodiceUnivoco() 
    {
        String caratteri = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder codice = new StringBuilder(); //Builder perchè modificabile al contrario delle stringhe normali
        java.util.Random random = new java.util.Random();
        for(int i = 0; i < 8; i++) 
        {
            int indice = random.nextInt(caratteri.length());
            char carattere = caratteri.charAt(indice);
            codice.append(carattere);
        }
        return codice.toString();
    } 
}