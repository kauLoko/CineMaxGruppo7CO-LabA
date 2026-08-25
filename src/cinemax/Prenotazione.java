import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    //Costruttore
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

    //Metodi
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

    public void setDataOraPrenotazione(String nuovaData) 
    {
        this.dataOraPrenotazione = nuovaData;
    }

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
                    String codicePrenotazione = campi[0];
                    String nomeCliente = campi[1];
                    String cognomeCliente = campi[2];
                    String titoloFilm = campi[3];
                    String dataOraProiezione = campi[4];
                    Double costoUnitario = Double.parseDouble(campi[5]);
                    Double costoTotale = Double.parseDouble(campi[6]);
                    int numeroBiglietti = Integer.parseInt(campi[7]);
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
            Costo unitario: %.2f
            Costo totale: %.2f
            Numero biglietti: %d
            """, codicePrenotazione, nomeCliente, cognomeCliente, titoloFilm, dataOraPrenotazione, costoUintario, costoTotale, numeroBiglietti);
    }
}