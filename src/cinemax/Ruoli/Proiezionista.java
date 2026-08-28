import java.io.*;
import java.util.*;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Proiezionista extends Utente
{
    //Costruttore
    public Proiezionista(String nome, String cognome, String username, String password, String nascita, String domicilio) 
    {
        super(nome, cognome, username, password, nascita, domicilio, Ruolo.proiezionista);
    }

    public static void aggiungiProiezione(Scanner scanner, List<Proiezione> listaProiezioni) 
    {
        // Acquisizione dei parametri della proiezione da aggiungere
        String titolo = "";
        while(titolo.isEmpty()) 
        {
            System.out.print("Inserisci il titolo della proiezione: ");
            titolo = scanner.nextLine().trim();
            if(titolo.isEmpty()) 
            {
                System.out.println("Errore: la proiezione deve avere un titolo");
            }
        }

        String genere = "";
        while(genere.isEmpty()) 
        {
            System.out.print("Inserisci il genere della proiezione: ");
            genere = scanner.nextLine().trim();
            if(genere.isEmpty()) 
            {
                System.out.println("Errore: la proiezione deve avere un genere");
            }
        }

        String regista = "";
        while(regista.isEmpty()) 
        {
            System.out.print("Inserisci il regista della proiezione: ");
            regista = scanner.nextLine().trim();
            if(regista.isEmpty()) 
            {
                System.out.println("Errore: la proiezione deve avere un regista");
            }
        }

        int anno = 0;
        boolean annoValido = false;
        while (!annoValido)
        {
            System.out.println("Inserisci l'anno di uscita del film: ");
            try 
            {
                anno = Integer.parseInt(scanner.nextLine().trim()); //Legge stringa e converte in int, più comodo e sicuro di avere nextInt e debuffer
                if(anno >= 1895)   //Anno in cui è avvenuta la prima proiezione, per avere un limite ed evitare date impossibili
                {
                    annoValido = true; //Se legge un input valido esce dal ciclo while
                }
                else 
                {
                    System.out.println("Errore: l'anno non può essere precedente al 1895");
                }
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Errore: inserisci un numero intero valido");
            }
        }

        int durata = 0;
        boolean durataValida = false;
        while (!durataValida) 
        {
            System.out.println("Inserisci la durata del film (in minuti): ");
            try 
            {
                durata = Integer.parseInt(scanner.nextLine().trim());
                if(durata > 0) //Non posso avere durata negativa
                {
                    durataValida = true;
                }
                else 
                {
                    System.out.println("Errore: la durata non può essere negativa");
                }
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Errore: inserisci un numero intero valido");
            }
        }

        int etaMin = 0; 
        boolean etaValida = false;
        while (!etaValida) 
        {
            System.out.println("Inserisci l'età minima per la visione del film: ");
            try 
            {
                etaMin = Integer.parseInt(scanner.nextLine().trim());
                if(etaMin >= 0) //L'età minima deve essere positiva
                {
                    etaValida = true;
                }
                else 
                {
                    System.out.println("Errore: l'età minima non può essere negativa");
                }
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Errore: inserisci un numero intero valido");
            }
        }    
        
        double costo = 0.0; 
        boolean costoValido = false;
        while (!costoValido) 
        {
            System.out.println("Inserisci il prezzo di un biglietto (per i decimali usare il punto, es. 8.50): ");
            try 
            {
                costo = Double.parseDouble(scanner.nextLine().trim());
                if(costo > 0) //Il costo deve essere positivo
                {
                    costoValido= true;
                }
                else 
                {
                    System.out.println("Errore: il costo di un biglietto non può essere negativo");
                }
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Errore: inserisci un numero valido");
            }
        
        }

        // ciclo per richiesta data e orario, da capire se farne due divisi o uno unico
        DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter oraFormat = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime dataOra = null;
        boolean dataOraValida = false;

        while(!dataOraValida) 
        {
            LocalDate data = null;
            LocalTime ora = null;

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
                    System.out.println("Errore di formato: usare esattamenente gg/mm/aaaa (es. 12/10/2026)");
                }
            }

            boolean oraValida = false;
            while(!oraValida) 
            {
                System.out.println("Inserisci l'ora della proiezione (HH:mm)");
                try 
                {
                    ora = LocalTime.parse(scanner.nextLine().trim(), oraFormat);
                    oraValida = true;
                }
                catch (Exception e) 
                {
                    System.out.println("Errore di formato: usare esattamente HH:mm (es. 21:30)");
                }
            }
            dataOra = LocalDateTime.of(data, ora);
            if(dataOra.isBefore(LocalDateTime.now())) 
            {
                System.out.println("Impossibile programmare una proiezione nel passato. Scegliere una data futura");
            }
            else 
            {
                dataOraValida = true;
            }
        }
        DateTimeFormatter formatCSV = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dataOrarioStringa = dataOra.format(formatCSV); //Converto l'oggetto LocalDateTime in stringa perchè richiesto dal costruttore di Proiezione
        //Finito di acquisire i parametri, credo nuovo oggetto e faccio add alla lista
        Proiezione nuovaProiezione = new Proiezione(titolo, genere, regista, anno, durata, etaMin, dataOrarioStringa, costo);
        listaProiezioni.add(nuovaProiezione);
        System.out.println("Proiezione aggiunta con successo");
    }

        /* TENIAMOLA PER RECUPERARE POI NEL METODO sovrascritturaCSV() ALLA CHIUSURA DEL PROGRAMMA
        File file = new File("Proiezione.txt");
        Scanner scan = new Scanner(file);
        String fileContent = "";

        while (scan.hasNextLine()) 
        {
            fileContent = fileContent.concat(scan.nextLine() +"\n");
        }

        try (FileWriter fw = new FileWriter(fileProiezioni); BufferedWriter bw = new BufferedWriter(fw); PrintWriter pw = new PrintWriter(bw)) 
        {
            // Salvare
            pw.println(titolo + "," + genere + "," + regista + "," + anno + "," + durata + "," + etaMin + "\n" + fileContent);
            System.out.println("Registrato con sucesso!");
        } 
        catch (IOException e) 
        {
            System.out.println("Errore in salvare file: " + e.getMessage());
        }
        */
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