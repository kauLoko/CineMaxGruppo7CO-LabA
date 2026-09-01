package cinemax;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Prenotazione rappresenta una prenotazione per un film in un cinema. 
 * Contiene informazioni sul cliente, il film, la data e l'ora della proiezione, il costo unitario e totale, e il numero di biglietti prenotati. 
 * La classe fornisce metodi per leggere e salvare le prenotazioni da un file CSV, generare codici univoci per le prenotazioni e visualizzare le informazioni della prenotazione.
 * @author Toppi Davide, Matricola: 765309, Sede: CO
 * @author Molteni Davide, Matricola: 765300, Sede: CO 
 * @author Lanza Mattia Antonio, Matricola: 766287, Sede: CO 
 * @author Salmazo Bocatto Kauan, Matricola: 767919, Sede: CO 
 */
public class Prenotazione 
{
    //Campi
    private static final String filePrenotazioni = "data/prenotazioni.csv";
    private String codicePrenotazione;
    private String nomeCliente;
    private String cognomeCliente;
    private String titoloFilm;
    private String dataOraPrenotazione;
    private Double costoUintario;
    private Double costoTotale;
    private int numeroBiglietti;

    /**
     * Costruttore della classe Prenotazione.
     * @param codicePrenotazione Il codice univoco della prenotazione.
     * @param nomeCliente Il nome del cliente.
     * @param cognomeCliente Il cognome del cliente.
     * @param titoloFilm Il titolo del film prenotato.
     * @param dataOraPrenotazione La data e l'ora della prenotazione.
     * @param costoUintario Il costo unitario di un biglietto.
     * @param costoTotale Il costo totale della prenotazione.
     * @param numeroBiglietti Il numero di biglietti prenotati.
     */
    public Prenotazione(String codicePrenotazione, String nomeCliente, String cognomeCliente, String titoloFilm, String dataOraPrenotazione, Double costoUintario, Double costoTotale, int numeroBiglietti) 
    {
        this.codicePrenotazione = codicePrenotazione;
        this.nomeCliente = nomeCliente;
        this.cognomeCliente = cognomeCliente;
        this.titoloFilm = titoloFilm;
        this.dataOraPrenotazione = dataOraPrenotazione;
        this.costoUintario = costoUintario;
        this.costoTotale = costoTotale;
        this.numeroBiglietti = numeroBiglietti;
    }

    //Metodi getter
    public String getCodicePrenotazione() 
    {
        return codicePrenotazione;
    }

    public String getNomeCliente() 
    {
        return nomeCliente;
    }

    public String getCognomeCliente() 
    {
        return cognomeCliente;
    }

    public String getTitoloFilm() 
    {
        return titoloFilm;
    }

    public String getDataOraPrenotazione() 
    {
        return dataOraPrenotazione;
    }

    public Double getCostoUnitario() 
    {
        return costoUintario;
    }

    public Double getCostoTotale() 
    {
        return costoTotale;
    }

    public int getNumeroBiglietti() 
    {
        return numeroBiglietti;
    }

    //Metodi setter
    public void setDataOraPrenotazione(String nuovaData) 
    {
        this.dataOraPrenotazione = nuovaData;
    }

    /**
     * Legge il file CSV delle prenotazioni e restituisce una lista di oggetti Prenotazione.
     * @return Una lista di oggetti Prenotazione letti dal file CSV.
     */
    public static List<Prenotazione> listaPrenotazioni() 
    {
        List<Prenotazione> listaPrenotazioni = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePrenotazioni))) 
        {
            String riga = br.readLine();

            while ((riga = br.readLine()) != null) 
            {
                if (riga.trim().isEmpty()) 
                {
                    continue; // Salta le righe vuote
                }

                String[] campi = riga.split(",");

                if(campi.length != 8) 
                {
                    continue; // Salta le righe con meno di 8 campi
                }

                try 
                {
                    String codicePrenotazione = campi[0].trim();
                    String nomeCliente = campi[1].trim();
                    String cognomeCliente = campi[2].trim();
                    String titoloFilm = campi[3].trim();
                    String dataOraProiezione = campi[4].trim();
                    Double costoUnitario = Double.parseDouble(campi[5].trim());
                    Double costoTotale = Double.parseDouble(campi[6].trim());
                    int numeroBiglietti = Integer.parseInt(campi[7].trim());
                    Prenotazione prenotazione = new Prenotazione(codicePrenotazione, nomeCliente, cognomeCliente, titoloFilm, dataOraProiezione, costoUnitario, costoTotale, numeroBiglietti);
                    listaPrenotazioni.add(prenotazione);
                } 
                catch (NumberFormatException e) 
                {
                    System.err.println("Errore durante l'analisi della riga: " + riga); //Se c'è un problema nei parseDouble/Int lancia eccezione
                }
            }
        }   
        catch (IOException e) 
        {
            System.err.println("Errore durante la lettura del file: " + e.getMessage());
        }
        
        return listaPrenotazioni;
    }

    /**
     * Salva le modifiche apportate alla lista delle prenotazioni nel file CSV.
     * @param lista La lista delle prenotazioni da salvare.
     */
    public static void salvaModifichePrenotazioni(List<Prenotazione> lista) 
    {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePrenotazioni, false))) { // false = sovrascrivi
        pw.println("codicePrenotazione, nomeCliente, cognomeCliente, titoloFilm, dataOraProiezione, costoUintario, costoTotale, numeroBiglietti"); // Intestazione
        for (Prenotazione p : lista) {
            pw.println(p.getCodicePrenotazione() + ", " + p.getNomeCliente() + ", " + p.getCognomeCliente() + ", " + p.getTitoloFilm() + ", " + p.getDataOraPrenotazione() + ", " + p.getCostoUnitario() + ", " + p.getCostoTotale() + ", " + p.getNumeroBiglietti());
        }
        } catch (IOException e) {
            System.err.println("Errore salvataggio: " + e.getMessage());
        }
    }
    
    /**
     * Genera un codice univoco per la prenotazione composto da 8 caratteri alfanumerici casuali.
     * @return Il codice univoco generato.
     */
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

    @Override
    public String toString() 
    {
        return String.format("""
            
            --- PRENOTAZIONE ---
            Codice prenotazione: %s
            Nome cliente: %s
            Cognome cliente: %s
            Titolo film: %s
            Data e ora proiezione: %s
            Costo unitario: %.2f EUR
            Costo totale: %.2f EUR
            Numero biglietti: %d
            """, codicePrenotazione, nomeCliente, cognomeCliente, titoloFilm, dataOraPrenotazione, costoUintario, costoTotale, numeroBiglietti);
    }
}