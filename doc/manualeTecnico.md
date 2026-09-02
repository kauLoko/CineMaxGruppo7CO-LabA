========================================================================

&#x20;                     CINEMAX - MANUALE TECNICO

========================================================================



Università degli Studi dell'Insubria

Corso di Laurea Triennale in Informatica

Laboratorio Interdisciplinare A - A.A. 2025/2026



Gruppo 7 - Sede di Como

Componenti: Molteni Davide (765300), Toppi Davide (765309),

&#x20;           Lanza Mattia Antonio (766287), Salmazo Bocatto Kauan (767919)



========================================================================



INDICE

1\. Architettura Software e Struttura Modulare

2\. Modello dei Dati e Gerarchia delle Classi

3\. Gestione dei Dati e Persistenza su File (CSV)

4\. Strutture Dati e Algoritmi Integrati

5\. Gestione Errori, Validazione Input e Sicurezza

6\. JavaDoc e Documentazione del Codice

7\. Limiti Tecnici e Sviluppi Futuri

8\. Sitografia e Bibliografia



========================================================================



\## 1. ARCHITETTURA SOFTWARE E STRUTTURA MODULARE



Il progetto è sviluppato in linguaggio Java (versione 17+) ed è strutturato all'interno del package principale `cinemax`\[cite: 7]. Il punto d'ingresso dell'applicazione è gestito dal metodo `main` situato nella classe principale `CineMax` (`cinemax.CineMax`). 



L'architettura adotta una variante del pattern \*\*Model-View-Controller (MVC)\*\* semplificata per interfacce a riga di comando (TUI):



\- \*\*Model (Modelli Domain-Driven):\*\* Rappresentato dalle entità principali del sistema (`Film`, `Proiezione`, `Prenotazione`, `Utente` e relative sottoclassi).

\- \*\*View / Controller (Interfaccia TUI):\*\* Gestisce l'I/O da terminale mediante la classe `Scanner`, gestendo i menu dinamici, l'acquisizione dei comandi dell'utente e il reindirizzamento alle funzioni del dominio.

\- \*\*Data Access Layer:\*\* Classi e gestori I/O dedicati alla lettura/scrittura dei dati su file CSV memorizzati nella directory `data/`\[cite: 7].





\## 2. MODELLO DEI DATI E GERARCHIA DELLE CLASSI



Il sistema si basa su una strutturazione fortemente orientata agli oggetti (OOP)\[cite: 7]:



\### 2.1 Gerarchia Utenti

\- `Utente` (Classe astratta): Definisce i campi comuni (`username`, `passwordHash`, `nome`, `cognome`) e dichiara i metodi astratti per il controllo degli accessi e la gestione delle autorizzazioni.

&#x20; - `Cliente`: Estende `Utente`, aggiungendo dati anagrafici specifici (`dataDiNascita`, `luogoDomicilio`) e i metodi per la gestione delle prenotazioni personali (`creaPrenotazione()`, `modificaPrenotazione()`, `eliminaPrenotazione()`).

&#x20; - `Proiezionista`: Estende `Utente`, implementando le operazioni di gestione del palinsesto (`aggiungiProiezione()`, `modificaProiezione()`, `eliminaProiezione()`).

&#x20; - `Bigliettaio`: Estende `Utente`, fornendo le funzionalità di verifica e ricerca delle prenotazioni giornaliere (`cercaPrenotazione()`, `visualizzaPrenotazione()`).



\### 2.2 Entità di Dominio

\- `Film`: Incapsula i metadati del film (`titolo`, `genere`, `regista`, `anno`, `durataMinuti`, `etaMinima`).

\- `Proiezione`: Combina un oggetto `Film` con i dati di programmazione (`dataOra`, `prezzoBiglietto`, `postiTotali = 200`).

\- `Prenotazione`: Rappresenta un'acquisto/prenotazione confermato (`codiceUnivoco`, `usernameCliente`, `proiezione`, `numeroPosti`, `dataOraPrenotazione`).





\## 3. GESTIONE DEI DATI E PERSISTENZA SU FILE (CSV)



L'applicazione archivia in modo permanente i propri dati tramite file di testo delimitati memorizzati nella cartella `data/`\[cite: 7]:



\- \*\*File Proiezioni (`data/proiezioni.csv`):\*\* Gestisce il catalogo dei film e della programmazione (Titolo, Genere, Regista, Anno, Durata, Età minima, Costo biglietto, Data e Ora)\[cite: 7].

\- \*\*File Utenti (`data/utenti.csv`):\*\* Mantiene le anagrafiche dei clienti e dei ruoli amministrativi. Include di default 2 proiezionisti (`Cennik`, `Mikes`), 5 bigliettai (`Teox`, `Fasa10`, `Mirko89`, `TiaLan`, `Lucky`) e l'utente cliente (`giobram23`).

\- \*\*File Prenotazioni (`data/prenotazioni.csv`):\*\* Memorizza le prenotazioni create, ciascuna associata ad un codice univoco identificativo generato al momento della conferma.



\*\*Scelta Progettuale:\*\* Si è optato per il formato testuale CSV rispetto alla serializzazione di oggetti nativa Java per garantire maggiore interoperabilità, facilità di ispezione manuale dei dataset e manutenzione\[cite: 7].





\## 4. STRUTTURE DATI E ALGORITMI INTEGRATI



\### 4.1 Gestione in Memoria

All'avvio dell'applicazione i file CSV vengono letti e caricati all'interno di collezioni dinamiche del framework Java Collections (`ArrayList`, `HashMap`) per consentire ricerche ed elaborazioni immediate in RAM\[cite: 7].



\### 4.2 Calcolo Posti Liberi

I posti disponibili per una determinata proiezione vengono calcolati dinamicamente tramite lo stream dei dati di prenotazione:

$$\\text{Posti Liberi} = 200 - \\sum \\text{postiPrenotati}$$



\### 4.3 Ricerca e Filtraggio Multi-criterio (`cercaProiezione()`)

La funzione applica un filtro predicativo combinato che valuta simultaneamente:

\- Corrispondenza parziale del titolo (case-insensitive).

\- Uguaglianza o compatibilità di genere cinematografico.

\- Appartenenza della data ad un intervallo `\[dataInizio, dataFine]`.

\- Rispetto della soglia massima di prezzo del biglietto.



\### 4.4 Controllo Sovrapposizioni

In fase di inserimento di una nuova proiezione, l'algoritmo verifica che il nuovo intervallo temporale $\\text{\[ora\\\_inizio, ora\\\_inizio + durata]}$ non presenti intersezioni con alcuna proiezione già a palinsesto nella sala.





\## 5. GESTIONE ERRORI, VALIDAZIONE INPUT E SICUREZZA



\- \*\*Hashing delle Password:\*\* Le password salvate nel file `data/utenti.csv` non vengono archiviate in chiaro, ma protette tramite algoritmo di hashing per garantire la sicurezza delle credenziali.

\- \*\*Validazione Input Utente:\*\* Gestione delle eccezioni di parsing (`InputMismatchException`, `DateTimeParseException`, `NumberFormatException`) tramite blocchi `try-catch` dedicati per evitare il crash del terminale in caso di dati errati.

\- \*\*Integrità dei File I/O:\*\* Gestione preventiva dei casi di assenza o danneggiamento dei file di input con rigenerazione dei file base o messaggi di errore descrittivi.





\## 6. JAVADOC E DOCUMENTAZIONE DEL CODICE



Il codice sorgente è interamente documentato mediante lo standard JavaDoc\[cite: 7]:

\- Tutte le classi e le interfacce contengono i tag `@author` per la tracciabilità dei moduli e `@version`\[cite: 7].

\- Ogni metodo e costruttore include i tag `@param` per i parametri di input, `@return` per i valori restituiti e `@throws` per la descrizione delle eccezioni sollevate\[cite: 7].

\- I file HTML della documentazione tecnica possono essere rigenerati eseguendo da terminale:

&#x20; `javadoc -d doc -sourcepath src -subpackages cinemax`





\## 7. LIMITI TECNICI E SVILUPPI FUTURI



\- \*\*Monosala:\*\* L'architettura è dimensionata per la gestione di una sola sala cinematografica (200 posti).

\- \*\*Concorrenza:\*\* La gestione della concorrenza sui file CSV è limitata ad una singola istanza attiva dell'applicazione.

\- \*\*Sviluppi Futuri:\*\* Migrazione della persistenza verso un DBMS relazionale (es. PostgreSQL/MySQL) e integrazione di un'interfaccia grafica (GUI) basata su JavaFX.





\## 8. SITOGRAFIA E BIBLIOGRAFIA



\- Oracle Java SE 17 Documentation: https://docs.oracle.com/en/java/javase/17/

\- Oracle JavaDoc Tool Guide: https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html

\- Linee guida di documentazione del corso - Prof. Giovanni Meroni, Università degli Studi dell'Insubria\[cite: 7].

