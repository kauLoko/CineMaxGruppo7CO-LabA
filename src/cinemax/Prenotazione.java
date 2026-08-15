public class Prenotazione {

    //Campi
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

}