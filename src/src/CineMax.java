import prog.io.*;

public class CineMax {
    public void main(String[] args) {
        ConsoleOutputManager out = new ConsoleOutputManager();
        ConsoleInputManager in = new ConsoleInputManager();

        String oi = in.readLine("escolha:");
        out.println(oi);
    }
}
