package CineMax;

import java.io.*;
import java.util.Scanner;

public class LoginClienti {


        private static final String ARQUIVO_USUARIOS = "UtentiInfo.txt";

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1 - Registrare\n2 - Login");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            if (opcao == 1) {
                registraCliente(scanner);
            } else if (opcao == 2) {
                fazerLogin(scanner);
            }
        }

        // Método para cadastrar usuário e senha no arquivo

        public static void registraCliente(Scanner scanner) {
            System.out.print("Nome de usuário: ");
            String user = scanner.nextLine();
            System.out.print("Senha: ");
            String pass = scanner.nextLine();

            try (FileWriter fw = new FileWriter(ARQUIVO_USUARIOS, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                // Salva no formato: usuario,senha
                out.println(user + "," + pass);
                System.out.println("Usuário cadastrado com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao salvar arquivo: " + e.getMessage());
            }
        }

        // Método para ler o arquivo e verificar o login
        public static void fazerLogin(Scanner scanner) {
            System.out.print("Usuário: ");
            String user = scanner.nextLine();
            System.out.print("Senha: ");
            String pass = scanner.nextLine();

            boolean autenticado = false;
            try (Scanner fileScanner = new Scanner(new File(ARQUIVO_USUARIOS))) {
                while (fileScanner.hasNextLine()) {
                    String linha = fileScanner.nextLine();
                    String[] dados = linha.split(",");
                    if (dados.length == 7 && dados[2].equals(user) && dados[3].equals(pass)) {
                        autenticado = true;
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Nenhum usuário cadastrado ainda.");
            }

            if (autenticado) {
                System.out.println("Login bem-sucedido!");
            } else {
                System.out.println("Usuário ou senha incorretos.");
            }
        }
    }

