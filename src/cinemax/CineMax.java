package cinemax;
import java.util.*;
/**
 * Classe contenente il metodo main
 * @author Toppi Davide, Matricola: 765309, Sede: CO
 * @author Molteni Davide, Matricola: 765300, Sede: CO 
 * @author Lanza Mattia Antonio, Matricola: 766287, Sede: CO 
 * @author Salmazo Bocatto Kauan, Matricola: 767919, Sede: CO 
 */
public class CineMax {
  public static void main(String[] args) 
  {
    // Ottengo tutte le proiezioni e prenotazioni dalla memoria di massa
    List<Prenotazione> listaPrenotazioni = Prenotazione.listaPrenotazioni();
    List<Proiezione> listaProiezioni = datiProiezioni.listaProiezioni();
    // Aggiorno i posti
    for (Proiezione p : listaProiezioni) {
        int postiLiberi = datiProiezioni.calcoloPostiLiberi(p, listaPrenotazioni);
        p.setPostiDisponibili(postiLiberi);
    }
    Scanner scanner = new Scanner(System.in);

    boolean esci = false; 
    while(!esci) 
    {
      String scelta = Menu.mostraMenuPrincipale(scanner);
      switch (scelta) 
      {
        case "1":
          Utente utenteLoggato = Menu.menuEseguiLogin(scanner);
          if(utenteLoggato != null) 
          {
            switch (utenteLoggato.getRuolo()) 
            {
              case cliente:
                Menu.menuUtenteLoggato(scanner, utenteLoggato, listaProiezioni, listaPrenotazioni);
                break;
              case proiezionista:
                Menu.menuProiezionista(scanner, utenteLoggato, listaProiezioni, listaPrenotazioni);
                break;
              case bigliettaio:
                Menu.menuBigliettaio(scanner, utenteLoggato, listaProiezioni, listaPrenotazioni);
                break;
            }
          }
          break;
        case "2":
          Menu.menuRegistrazioneUtente(scanner);
          break;
        case "3":
          Menu.menuGuest(scanner, listaProiezioni);
          break;
        case "4":
          System.out.println("\nGrazie per aver usato CineMax, arrivederci!");
          System.out.println("\n--------------------------------\n");
          datiProiezioni.salvaModificheProiezioni(listaProiezioni);
          Prenotazione.salvaModifichePrenotazioni(listaPrenotazioni);
          return; // Esci dal programma
        default:
          System.out.println("\nScelta non valida!\n");
      } 
    }
    scanner.close();
  }
}               