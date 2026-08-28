
import java.io.*;
import java.util.*;

public class datiProiezioni 
{
    //Campi
    private String titolo;
    private String genere;
    private String regista;
    private int anno;
    private int durata;
    private int etaMin;
    String dataOrario;
    double costo;
    private static final String fileProiezioni = "data/proiezioni.csv";

    //Construtore
    public datiProiezioni(String titolo, String genere, String regista, int anno, int durata, int etaMin, String dataOrario, double costo) 
    {
        this.titolo = titolo;
        this.genere = genere;
        this.regista = regista;
        this.anno = anno;
        this.durata = durata;
        this.etaMin = etaMin;
        this.dataOrario = dataOrario;
        this.costo = costo;
    }

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
}