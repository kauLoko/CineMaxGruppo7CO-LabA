
public class Film 
{
    //Campi
    private String titolo;
    private String genere;
    private String regista;
    private int anno;
    private int durataMinuti;
    private int etaMinima;

    //Costruttore
    public Film(String titolo, String genere, String regista, int anno, int durataMinuti, int etaMinima) 
    {
        this.titolo = titolo;
        this.genere = genere;
        this.regista = regista;
        this.anno = anno;
        this.durataMinuti = durataMinuti;
        this.etaMinima = etaMinima;
    }

    //Metodi
    public String getTitolo() 
    { 
        return titolo; 
    }

    public String getGenere() 
    { return genere; 

    }
    
    public String getRegista() 
    { 
        return regista; 
    }

    public int getAnno() 
    { 
        return anno; 
    }

    public int getDurataMinuti() 
    { 
        return durataMinuti; 
    }

    public int getEtaMinima() 
    { 
        return etaMinima; 
    }
}