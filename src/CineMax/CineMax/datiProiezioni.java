package CineMax;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class datiProiezioni {

    //Campi
    private String titolo;
    private String genere;
    private String regista;
    private int anno;
    private int durata;
    private int etaMin;
    private static final String fileProiezioni = "proiezioni.csv";

    //Construtore
    public datiProiezioni(String titolo, String genere, String regista, int anno, int durata, int etaMin) {
        this.titolo = titolo;
        this.genere = genere;
        this.regista = regista;
        this.anno = anno;
        this.durata = durata;
        this.etaMin = etaMin;
    }

    //Metodi

    //Aggiungi Proiezione
    public static void aggiungiProiezione(Scanner scanner) throws IOException {

        System.out.print("Titolo: ");
        String titolo = scanner.next();
        System.out.print("Genere: ");
        String genere = scanner.next();
        System.out.print("Regista: ");
        String regista = scanner.next();

        int anno = 0;
        boolean entrataValida = false;

        while (!entrataValida) {
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
}
