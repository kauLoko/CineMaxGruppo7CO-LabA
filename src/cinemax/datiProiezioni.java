
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
    private static final String fileProiezioni = "data/proiezioni.csv";

    //Construtore
    public datiProiezioni(String titolo, String genere, String regista, int anno, int durata, int etaMin) 
    {
        this.titolo = titolo;
        this.genere = genere;
        this.regista = regista;
        this.anno = anno;
        this.durata = durata;
        this.etaMin = etaMin;
    }

    //Metodi

    //Aggiungi Proiezione
    public static void aggiungiProiezione(Scanner scanner) throws IOException 
    {
        System.out.print("Titolo: ");
        String titolo = scanner.next();
        System.out.print("Genere: ");
        String genere = scanner.next();
        System.out.print("Regista: ");
        String regista = scanner.next();

        int anno = 0;
        boolean entrataValida = false;

        while (!entrataValida)
        {
            try {
                System.out.print("Anno: ");
                anno = scanner.nextInt(); // Prova a leggere un Int
                scanner.nextLine();
                entrataValida = true; // Se leggi, usce dell loop
            } catch (InputMismatchException e) {
                // 2. Errore e non esci dell loop
                System.out.println("Errore:Anno solo numeri.");
                scanner.next();
            }
        }
        int durata = 0;
        boolean entrataValidaD = false;

        while (!entrataValidaD) {
            try {
                System.out.print("Durata(Minuti): ");
                durata = scanner.nextInt(); // Prova a leggere un Int
                scanner.nextLine();
                entrataValidaD = true; // Se leggi, usce dell loop
            } catch (InputMismatchException e) {
                // 2. Errore e non esci dell loop
                System.out.println("Errore:Durata solo numeri.");
                scanner.next();
            }
        }
        int etaMin = 0;
        boolean entrataValidaE = false;

        while (!entrataValidaE) {
            try {
                System.out.print("Età minima pubblico: ");
                etaMin = scanner.nextInt(); // Prova a leggere un Int
                scanner.nextLine();
                entrataValidaE = true; // Se leggi, usce dell loop
            } catch (InputMismatchException e) {
                // 2. Errore e non esci dell loop
                System.out.println("Errore:Età minima solo numeri.");
                scanner.next();
            }
        }
        int sediaQuant = 200;


        File file = new File("Proiezione.txt");
        Scanner scan = new Scanner(file);
        String fileContent = "";

        while (scan.hasNextLine()) {
            fileContent = fileContent.concat(scan.nextLine() +"\n");
        }

        try (FileWriter fw = new FileWriter(fileProiezioni);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {

            // Salvare
            pw.println(titolo + "," + genere + "," + regista + "," + anno + "," + durata + "," + etaMin + "\n" + fileContent);
            System.out.println("Registrato con sucesso!");
        } catch (IOException e) {
            System.out.println("Errore in salvare file: " + e.getMessage());
        }
    }


    public static List<Proiezione> listaProiezioni() {
        List<Proiezione> listaProiezioni = new ArrayList<>(); //Uso la lista perchè l'array ha dimensione fissa definita in fase di creazione, mentre la lista è estendibile
        try (BufferedReader br = new BufferedReader(new FileReader(fileProiezioni))) {
            String riga = br.readLine(); //Salta la prima riga che è di intestazione
            while ((riga = br.readLine()) != null) {
                String[] campi = riga.split(",");
                String dataOrario = campi[0];
                String titolo = campi[1];
                String genere = campi[2];
                String regista = campi[3];
                int anno = Integer.parseInt(campi[4]);
                int durata = Integer.parseInt(campi[5]);
                int etàMinima = Integer.parseInt(campi[6]);
                double costo = Double.parseDouble(campi[7]);
                Proiezione proiezione = new Proiezione(titolo, genere, regista, anno, durata, etàMinima, dataOrario, costo);
                listaProiezioni.add(proiezione);
            }
        }   
        catch (IOException e) {
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
            if(p.getTitoloFilm().equalsIgnoreCase(proiezione.getTitolo()) && (p.getDataProiezione() + " " + p.getOrarioProiezione()).equals(proiezione.getDataOrario())) 
            {
                postiOccupati += p.getNumeroBiglietti();
            }
        }
        return capienzaMassima - postiOccupati;
    }
}