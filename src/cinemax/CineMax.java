import java.io.*;
import java.util.*;

public static void main(String[] args) 
{
  Scanner scanner = new Scanner(System.in);

  System.out.println("=================================================");
  System.out.println("=          BENVENUTO NEL SISTEMA CINEMAX        =");
  System.out.println("=================================================");

  System.out.println("\n--- MENU PRINCIPALE ---");
  System.out.println("1. Login");
  System.out.println("2. Registrati come Cliente");
  System.out.println("3. Prosegui come Ospite (Guest)");
  System.out.println("4. Esci");
  System.out.print("Scelta: ");
  
  String scelta = scanner.nextLine().trim();
  
  switch (scelta) 
  {
    case "1":
      //eseguiLogin();
            
    case "2":
      //registraCliente();
            
    case "3":
      //menuGuest();
            
    case "4":
    {
      System.out.println("Grazie per aver usato CineMax. Arrivederci!");
      System.exit(0);
    }
    
    default:
      System.out.println("Scelta non valida!");
  }

  while (true) 
  {
    if (utenteCorrente == null) //se non c'è utente loggato 
    {
      mostraMenuPrincipale();
    } 
    else 
    {
      switch (utenteCorrente.getRuolo()) 
      {
        case CLIENTE:
          mostraMenuCliente();
                    
        case PROIEZIONISTA: 
          mostraMenuProiezionista();
        
        case BIGLIETTAIO: 
          mostraMenuBigliettaio();
                
      }
    }
  }
}

