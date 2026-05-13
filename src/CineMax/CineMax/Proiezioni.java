package CineMax;

import prog.io.ConsoleInputManager;
import prog.io.ConsoleOutputManager;

import java.io.*;
import java.util.Scanner;

public class Proiezioni {

    //Campi
    private String titolo;
    private String genere;
    private String regista;
    private int anno;
    private int durata;
    private int etaMin;
    private static final String fileProiezioni = "Proiezione.txt";

    //Construtore
    public Proiezioni(String titolo, String genere, String regista, int anno, int durata, int etaMin) {
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
        String titolo = scanner.nextLine();
        System.out.print("Genere: ");
        String genere = scanner.nextLine();
        System.out.print("Regista: ");
        String regista = scanner.nextLine();
        System.out.print("Anno: ");
        String anno = scanner.nextLine();
        System.out.print("Durata: ");
        String durata = scanner.nextLine();
        System.out.print("Età minima pubblico: ");
        String etaMin = scanner.nextLine();


        ConsoleOutputManager ou = new ConsoleOutputManager();
        ConsoleInputManager in = new ConsoleInputManager();

        File file = new File("Proiezione.txt");
        Scanner scan = new Scanner(file);
        String fileContent = "";

        while (scan.hasNextLine()) {
            fileContent = fileContent.concat(scan.nextLine() +"\n");
        }

        try (FileWriter fw = new FileWriter(fileProiezioni);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            // Salvare
            out.println(titolo + "," + genere + "," + regista + "," + anno + "," + durata + "," + etaMin + "\n" + fileContent);
            System.out.println("Registrato con sucesso!");
        } catch (IOException e) {
            System.out.println("Errore in salvare file: " + e.getMessage());
        }
    }
}
