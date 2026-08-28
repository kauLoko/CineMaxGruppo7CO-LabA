public class Proiezione 
{
    //Campi
    private String titolo;
    private String genere;
    private String regista;
    private int anno;
    private int durata;
    private int etaMinima;
    private String dataOrario;
    private double costo;
    private int postiDisponibili; 
    
    //Costruttore
    public Proiezione(String titolo, String genere, String regista, int anno, int durata, int etaMinima, String dataOrario, double costo) 
    {
        this.titolo = titolo;
        this.genere = genere;
        this.regista = regista;
        this.anno = anno;
        this.durata = durata;
        this.etaMinima = etaMinima;
        this.dataOrario = dataOrario;
        this.costo = costo;
        this.postiDisponibili = 200;
    }

    //Metodi getter
    public String getTitolo() 
    {
        return titolo;
    }

    public String getGenere() 
    {
        return genere;
    }

    public String getRegista() 
    {
        return regista;
    }

    public int getAnno() 
    {
        return anno;
    }
    
    public int getDurata() 
    {
        return durata;
    }

    public int getEtaMinima() 
    {
        return etaMinima;
    }

    public String getDataOrario() 
    {
        return dataOrario;
    }

    public double getCosto() 
    {
        return costo;
    }

    public int getPostiDisponibili() 
    {
        return postiDisponibili;
    }

    //Metodi setter
    public void setTitolo(String titoloNuovo) 
    {
        this.titolo = titoloNuovo;
    }

    public void setGenere(String genereNuovo) 
    {
        this.genere = genereNuovo;
    }

    public void setRegista(String registaNuovo) 
    {
        this.regista = registaNuovo;
    }

    public void setAnno(int annoNuovo)
    {
        this.anno = annoNuovo;
    }

    public void setDurata(int durataNuova)
    {
        this.durata = durataNuova;
    }

    public void setEtaMin(int etaMinNuova) 
    {
        this.etaMinima = etaMinNuova;
    }

    public void setDataOra(String dataOraNuova)
    {
        this.dataOrario = dataOraNuova;
    }

    public void setCosto(double costoNuovo)
    {
        this.costo = costoNuovo;
    }

    public void setPostiDisponibili(int postiDisponibili) 
    {
        this.postiDisponibili = postiDisponibili;
    }

    public void decrementaPosti(int bigliettiVenduti) 
    {
        this.postiDisponibili -= bigliettiVenduti;
    }

    @Override
    public String toString() 
    {
        return String.format("""
            
            --- SCHEDA PROIEZIONE ---
            Titolo: %s
            Genere: %s
            Regista: %s
            Anno: %d
            Durata: %d minuti
            Età Minima: %d anni
            Data e Ora: %s
            Costo: %.2f €
            Posti Disponibili: %d""",
            titolo, genere, regista, anno, durata, etaMinima, dataOrario, costo, postiDisponibili);
    }
}