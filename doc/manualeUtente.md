========================================================================
                      CINEMAX - MANUALE UTENTE
========================================================================

Università degli Studi dell'Insubria
Corso di Laurea Triennale in Informatica
Laboratorio Interdisciplinare A - A.A. 2025/2026

Gruppo 7 - Sede di Como
Componenti: Molteni Davide (765300), Toppi Davide (765309), 
            Lanza Mattia Antonio (766287), Salmazo Bocatto Kauan (767919)

========================================================================

INDICE
1. Introduzione ed Avvio dell'Applicazione
2. Requisiti di Sistema e Installazione
3. Credenziali di Test e Profili Preconfigurati
4. Funzionalità per Utente Guest (Non Registrato)
5. Funzionalità per il Cliente Registrato
6. Funzionalità per il Proiezionista
7. Funzionalità per il Bigliettaio
8. Data Set di Test e File di Salvataggio
9. Limiti della Soluzione
10. Sitografia e Bibliografia

========================================================================

## 1. INTRODUZIONE ED AVVIO DELL'APPLICAZIONE
L'applicazione CineMax è un software multipiattaforma basato su interfaccia a terminale (TUI) progettato per la gestione operativa di un cinema monosala con capienza fissa a 200 posti[cite: 1]. 

Per avviare l'applicazione, aprire il terminale, posizionarsi nella cartella di installazione ed eseguire il comando:
   java -jar bin\CineMax.jar

All'avvio, l'utente viene accolto da un menù principale che consente di scegliere se effettuare il Login, registrarsi come nuovo Cliente oppure proseguire come utente Guest.

![Menu Principale](01_inizio.png)
*Figura 1: Schermata iniziale e menu principale.*


## 2. REQUISITI DI SISTEMA E INSTALLAZIONE
- Sistema Operativo: Cross-platform (Windows, macOS, Linux).
- Java Development Kit (JDK): Versione 17 o superiore.
- Memoria RAM: Minimo 512 MB.
- Spazio su disco: Minimo 50 MB per il pacchetto dell'applicazione e la cartella data/.

Procedura di Setup:
1. Verificare l'installazione di Java eseguendo da terminale: java -version
2. Estrarre la cartella del progetto verificando la presenza del file bin/CineMax.jar e della sottocartella data/ contenente i dataset iniziali.


## 3. CREDENZIALI DI TEST E PROFILI PRECONFIGURATI
Per testare le varie funzionalità del sistema senza dover registrare nuovi utenti, è possibile utilizzare i seguenti account predefiniti (salvati nel file data/utenti.csv):

### 3.1 Proiezionisti
1) Username: Cennik
   Password: LairaFerma
2) Username: Mikes
   Password: Fifa201

### 3.2 Bigliettai
1) Username: Teox       | Password: PesciRossi
2) Username: Fasa10     | Password: Cane rotondo
3) Username: Mirko89    | Password: Termosifone
4) Username: TiaLan     | Password: Caciocavallo
5) Username: Lucky      | Password: Leprotto

### 3.3 Utenti Registrati (Clienti)
1) Username: giobram23
   Password: giovanni00!


## 4. FUNZIONALITÀ PER UTENTE GUEST (NON REGISTRATO)
- Accesso Rapido: Selezionando l'accesso come Guest, l'utente può inserire il titolo (anche parziale) di un film per consultare immediatamente le relative proiezioni disponibili.
- Ricerca Proiezioni (cercaProiezione()): Permette di filtrare le proiezioni inserendo criteri specifici quali titolo del film, genere, intervallo di date o fascia di costo del biglietto.
- Dettaglio Proiezione (visualizzaProiezione()): Consente di visualizzare titolo, genere, regista, anno, durata, data, ora, prezzo del biglietto e i posti ancora liberi rispetto alla capienza della sala (200 posti).
- Registrazione (registraCliente()): Consente ad un nuovo utente di creare un account Cliente inserendo Nome, Cognome, Username, Password, Data di Nascita (facoltativa) e Luogo di Domicilio.

![Ricerca Guest](02_guest.png)
*Figura 2.1: Ricerca proiezioni con filtro per genere.*

![Registrazione Cliente](03_registrazione.png)
*Figura 2.2: Form di registrazione per un nuovo Cliente.*


## 5. FUNZIONALITÀ PER IL CLIENTE REGISTRATO
- Autenticazione: Accesso tramite le credenziali impostate in fase di registrazione o l'account cliente predefinito (giobram23).
- Inserimento Prenotazione (creaPrenotazione()): Permette di scegliere una proiezione e richiedere un determinato numero di biglietti. Se i posti residui sono sufficienti, il sistema genera un codice univoco di prenotazione e conferma l'operazione.
- Gestione Prenotazioni (visualizzaPrenotazione()): Mostra l'elenco di tutte le prenotazioni effettuate dal cliente.
- Modifica Prenotazione (modificaPrenotazione()): Consente di variare la data di una prenotazione, a patto che sia la data originale sia quella nuova siano successive alla data odierna.
- Cancellazione Prenotazione (eliminaPrenotazione()): Permette di eliminare una prenotazione effettuata per una proiezione trascorsa rispetto alla data odierna.

![Filtri Prenotazione](04_prenotazione_filtri.png)
*Figura 3.1: Configurazione dei filtri di ricerca da menu Cliente.*

![Conferma Prenotazione](05_prenotazione_conferma.png)
*Figura 3.2: Inserimento prenotazione e generazione del codice univoco.*

![Storico Prenotazioni](06_prenotazione_storico.png)
*Figura 3.3: Visualizzazione dello storico delle prenotazioni dell'utente.*


## 6. FUNZIONALITÀ PER IL PROIEZIONISTA
- Aggiunta Proiezione (aggiungiProiezione()): Permette di inserire un nuovo film specificando titolo, genere, regista, anno, durata, età minima, prezzo e data/ora di programmazione. Il sistema impedisce la creazione di proiezioni sovrapposte nello stesso orario.
- Modifica Proiezione (modificaProiezione()): Permette di variare la data di una proiezione inserita, a patto che non vi siano prenotazioni già effettuate da parte di clienti.
- Eliminazione Proiezione (eliminaPrenotazione()): Permette di cancellare una proiezione dal palinsesto purché non registri alcuna prenotazione attiva.

![Aggiunta Proiezione](07_proiezionista.png)
*Figura 4: Inserimento di una nuova proiezione dal menu Proiezionista.*


## 7. FUNZIONALITÀ PER IL BIGLIETTAIO
- Visualizzazione Giornaliera: All'accesso, la schermata mostra in automatico le prenotazioni registrate per la data odierna.
- Ricerca Prenotazioni (cercaPrenotazione()): Consente di cercare le prenotazioni tramite codice univoco, nome/cognome del cliente, titolo del film o intervallo di date.
- Dettagli Prenotazione (visualizzaPrenotazione()): Mostra i dati identificativi della prenotazione, il nominativo del cliente, la data/ora del film, la quantità di biglietti e l'importo totale.

![Ricerca Bigliettaio](08_bigliettaio.png)
*Figura 5: Ricerca e verifica di una prenotazione dal menu Bigliettaio.*


## 8. DATA SET DI TEST
I dati necessari al corretto funzionamento dell'applicazione sono mantenuti in tre file CSV nella cartella data/:
- data/proiezioni.csv: Elenco completo dei film, costi del biglietto e orari di programmazione.
- data/utenti.csv: Profili salvati (proiezionisti, bigliettai, clienti registrati) con password cifrate via hashing.
- data/prenotazioni.csv: Registro di tutte le prenotazioni effettuate con codice identificativo univoco.


## 9. LIMITI DELLA SOLUZIONE
- L'applicazione gestisce esclusivamente un cinema monosala (capienza fissa 200 posti).
- L'interfaccia è strettamente a riga di comando (TUI) senza interfaccia grafica GUI.
- La persistenza si basa su file CSV locali senza l'impiego di un DBMS relazionale.


## 10. SITOGRAFIA E BIBLIOGRAFIA
- Oracle Java SE Documentation: https://docs.oracle.com/en/java/
- Linee guida per la documentazione di progetto - Prof. Meroni, Università degli Studi dell'Insubria[cite: 1].