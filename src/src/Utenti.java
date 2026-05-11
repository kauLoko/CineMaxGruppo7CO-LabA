import prog.io.*;
import prog.utili.*;
import java.io.*;
import java.util.Scanner;

public class Utenti {
    public static void main(String[] args) throws FileNotFoundException {
        ConsoleOutputManager out = new ConsoleOutputManager();

    File file = new File("D:\\IntelliJ\\Projetos\\CineMaxGrupo7CO-LabA\\src\\src\\UntentiIInfo.txt");
    Scanner scan = new Scanner(file);

    while(scan.hasNextLine()){
    out.println(scan.nextLine());}


    }
}

