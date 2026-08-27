\# **MANUALE TECNICO - CINEMAX**



Molteni Davide		765300

Toppi Davide		765309

Lanza Mattia Antonio	766287

Salmazo Bocatto Kauan	767919





\## **1. ARCHITETTURA SOFTWARE E STRUTTURA MODULARE**

Il progetto è sviluppato in linguaggio Java ed è strutturato nel package principale cinemax. Il punto d'ingresso dell'applicazione è gestito dal metodo main situato nella classe CineMax (cinemax.CineMax). La struttura segue un pattern orientato agli oggetti suddiviso nei seguenti ambiti:

\- Modelli Object-Oriented: Classi Film, Proiezione, Prenotazione e la gerarchia di utenti con la classe astratta Utente ed estensioni Cliente, Proiezionista, Bigliettaio.

\- Interfaccia TUI: Gestione dell'input utente tramite terminale per la navigazione dinamica nei menù e per le chiamate ai controllori.



\## **2. GESTIONE DEI DATI E PERSISTENZA SU FILE**

L'applicazione archivia in modo permanente i propri dati tramite file memorizzati nella cartella data:

\- File Proiezioni (data/proiezioni.csv): Strutturato in formato testuale delimitato, gestisce le proiezioni e i dettagli dei film (Titolo, Genere, Regista, Anno, Durata, Età minima, Costo biglietto, Data e Ora).

\- File Utenti (data/utenti.csv): Mantiene le anagrafiche dei clienti e dei ruoli amministrativi. Le password vengono cifrate tramite algoritmo di hashing prima di essere memorizzate su disco. Il file include di default 2 proiezionisti e 5 bigliettai.

\- File Prenotazioni (data/prenotazioni.csv): Memorizza le prenotazioni create, ciascuna associata ad un codice univoco identificativo generato al momento della creazione.



Scelta Progettuale: Si è optato per il formato testuale CSV rispetto alla serializzazione di oggetti per garantire maggiore interoperabilità, scalabilità e facilità di ispezione manuale dei dataset.



\## **3. STRUTTURE DATI E ALGORITMI INTEGRATI**

\- Gestione in Memoria: All'avvio dell'applicazione i file vengono letti e caricati all'interno di collezioni dinamiche (es. List e Map) per consentire ricerche ed elaborazioni in tempo reale.

\- Calcolo Posti Liberi: I posti disponibili per una proiezione vengono calcolati dinamicamente sottraendo la somma dei biglietti prenotati dalla capienza fissa della sala (200 - somma posti prenotati).

\- Ricerca e Filtraggio Multi-criterio: La funzione cercaProiezione() esegue un filtraggio coordinato che valuta simultaneamente la corrispondenza parziale del titolo (ignorando maiuscole/minuscole), genere, intervalli temporali e soglie di prezzo del biglietto.

\- Controllo Sovrapposizioni: All'atto dell'aggiunta di una nuova proiezione da parte del proiezionista, un algoritmo verifica che l'intervallo temporale (\[ora\_inizio, ora\_inizio + durata]) non collida con alcuna proiezione già memorizzata.



\## **4. GESTIONE ERRORI E VALIDAZIONE INPUT**

Il sistema gestisce le eccezioni e le condizioni d'errore prevenendo anomalie di runtime:

\- Validazione Input Utente: Intercettazione dei dati errati immessi da terminale tramite costrutti try-catch dedicati.

\- Controllo Vincoli di Dominio: Blocchi preventivi nell'esecuzione delle operazioni (es. tentativo di modifica di una proiezione con prenotazioni attive o prenotazioni in caso di posti insufficienti).

\- Integrità dei File: Gestione dei casi di assenza o danneggiamento dei file di input con messaggi di errore informativi.







