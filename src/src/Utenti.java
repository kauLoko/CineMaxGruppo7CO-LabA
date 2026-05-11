import prog.io.*;

import java.io.*;
import java.util.Scanner;

public class Utenti {


    public Utenti() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        ConsoleOutputManager out = new ConsoleOutputManager();
        ConsoleInputManager in = new ConsoleInputManager();

        File file = new File("UntentiIInfo2.txt");
        Scanner scan = new Scanner(file);
        String fileContent = in.readLine();

        while (scan.hasNextLine()) {
            fileContent = fileContent.concat(scan.nextLine() + "\n");
        }
        out.println(fileContent);


        BufferedWriter writer = new BufferedWriter(new FileWriter("UntentiIInfo2.txt"));
        writer.write("\n" + fileContent);
        writer.close();
    }
}


