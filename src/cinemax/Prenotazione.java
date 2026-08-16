import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Prenotazione {

    //Campi

    private static final String filePrenotazioni = "data/prenotazioni.csv";

    private String codicePrenotazione;
    private String nomeCliente;
    private String cognomeCliente;
    private String titoloFilm;
    private String dataProiezione;
    private String orarioProiezione;
    private Double costoUintario;
    private Double costoTotale;
    private int numeroBiglietti;

    //Costruttore
    public Prenotazione(String codicePrenotazione, String nomeCliente, String cognomeCliente, String titoloFilm, String dataProiezione, String orarioProiezione, Double costoUintario, Double costoTotale, int numeroBiglietti) {
        this.codicePrenotazione = codicePrenotazione;
        this.nomeCliente = nomeCliente;
        this.cognomeCliente = cognomeCliente;
        this.titoloFilm = titoloFilm;
        this.dataProiezione = dataProiezione;
        this.orarioProiezione = orarioProiezione;
        this.costoUintario = costoUintario;
        this.costoTotale = costoTotale;
        this.numeroBiglietti = numeroBiglietti;
    }

    //Metodi
    public String getCodicePrenotazione() {return codicePrenotazione;}
    public String getNomeCliente() {return nomeCliente;}
    public String getCognomeCliente() {return cognomeCliente;}
    public String getTitoloFilm() {return titoloFilm;}
    public String getDataProiezione() {return dataProiezione;}
    public String getOrarioProiezione() {return orarioProiezione;}
    public Double getCostoUnitario() {return costoUintario;}
    public Double getCostoTotale() {return costoTotale;}
    public int getNumeroBiglietti() {return numeroBiglietti;}

    public static List<Prenotazione> listaPrenotazioni() {
        List<Prenotazione> listaPrenotazioni = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePrenotazioni))) {
            String riga = br.readLine();
            while ((riga = br.readLine()) != null) {
                if (riga.trim().isEmpty()) {
                    continue; // Salta le righe vuote
                }
                String[] campi = riga.split(",");
                if(campi.length != 9) {
                    continue; // Salta le righe con meno di 9 campi
                }
                try {
                    String codicePrenotazione = campi[0];
                    String nomeCliente = campi[1];
                    String cognomeCliente = campi[2];
                    String titoloFilm = campi[3];
                    String dataProiezione = campi[4];
                    String orarioProiezione = campi[5];
                    Double costoUnitario = Double.parseDouble(campi[6]);
                    Double costoTotale = Double.parseDouble(campi[7]);
                    int numeroBiglietti = Integer.parseInt(campi[8]);
                    Prenotazione prenotazione = new Prenotazione(codicePrenotazione, nomeCliente, cognomeCliente, titoloFilm, dataProiezione, orarioProiezione, costoUnitario, costoTotale, numeroBiglietti);
                    listaPrenotazioni.add(prenotazione);
                } 
                catch (NumberFormatException e) {
                    System.err.println("Errore durante l'analisi della riga: " + riga); //Se c'è un problema nei parseDouble/Int lancia eccezione
                }
            }
        }   
        catch (IOException e) {
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
            Data proiezione: %s
            Orario proiezione: %s
            Costo unitario: %.2f
            Costo totale: %.2f
            Numero biglietti: %d
            """, codicePrenotazione, nomeCliente, cognomeCliente, titoloFilm, dataProiezione, orarioProiezione, costoUintario, costoTotale, numeroBiglietti);
    }
}