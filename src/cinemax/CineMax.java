//package cinemax;
import java.util.*;

public class CineMax {
  public static void main(String[] args) 
  {
    List<Prenotazione> listaPrenotazioni = Prenotazione.listaPrenotazioni();
    List<Proiezione> listaProiezioni = datiProiezioni.listaProiezioni();
    Scanner scanner = new Scanner(System.in);

    boolean esci = false; 
    while(!esci) 
    {
      String scelta = Menu.mostraMenuPrincipale(scanner); //METODO DA FARE
      switch (scelta) 
      {
        case "1":
          Utente utenteLoggato = Menu.eseguiMenuLogin(scanner);
          if(utenteLoggato != null) 
          {
            switch (utenteLoggato.getRuolo()) 
            {
              case cliente:

                break;
              case proiezionista:
                
                break;
              case bigliettaio:

                break;
            }
          }
          break;
        case "2":

          break;
        case "3":

          break;
        case "4":
          
          break;
        default:
          System.out.println("Scelta non valida!");
      } 
    }
    scanner.close();








  }
}
    /*
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
        Utente utenteLoggato = Menu.eseguiMenuLogin(scanner);
        
        if (utenteLoggato != null) 
        {
          int sceltaSottomenu = 0;

          do 
          {
            System.out.println("\n--- MENU CLIENTE (" + utenteLoggato.getUsername() + ") ---");
            System.out.println("1. Inserisci una prenotazione");
            System.out.println("2. Visualizza le tue prenotazioni");
            System.out.println("3. Modifica o cancella le tue prenotazioni");
            System.out.println("0. Logout (Torna al menu principale)");
            System.out.print("Seleziona: ");
            
            sceltaSottomenu = Integer.parseInt(scanner.nextLine());

            switch (sceltaSottomenu) 
            {
              case 1:
                ClienteRegistrato.creaPrenotazione(scanner, null , utenteLoggato, listaPrenotazioni);
                break;
                          
              case 2:
                Utente.visualizzaPrenotazione(scanner, utenteLoggato, null);
                break;
              
              case 0:
                System.out.println("C");
                break;
              
              default:
                System.out.println("Opzione non valida.");
            }
          } 
          while (sceltaSottomenu != 0); // Esce dal sotto-menu quando sceglie 0
        }
        break;
              
      case "2":
        Menu.registrazioneUtente(scanner);
        break;
              
      case "3":
        ClienteGuest.menuGuest();
        break;
        
      case "4":
      {
        System.out.println("Grazie per aver usato CineMax. Arrivederci!");
        System.exit(0);
      }
      
      default:
        System.out.println("Scelta non valida!");
    }

    
  }
  /*  while (true) 
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
  } */