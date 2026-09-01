========================================================================
&#x20;                      CINEMAX - MANUALE UTENTE
========================================================================

Università degli Studi dell'Insubria
Corso di Laurea Triennale in Informatica
Laboratorio Interdisciplinare A - A.A. 2025/2026

Gruppo 7 - Sede di Como
Componenti: Molteni Davide (765300), Toppi Davide (765309), 
&#x20;      Lanza Mattia Antonio (766287), Salmazo Bocatto Kauan (767919)

========================================================================



> Nota per la redazione del PDF: Inserire le schermate di esempio dell'interfaccia a terminale sotto a ciascuna sezione prima dell'esportazione finale.



\## 1. INTRODUZIONE ED AVVIO DELL'APPLICAZIONE
L'applicazione CineMax è un software multipiattaforma basato su interfaccia a terminale (TUI) progettato per la gestione di un cinema monosala da 200 posti. Per avviare l'applicazione, aprire il terminale, posizionarsi nella cartella di installazione ed eseguire il comando:
&#x20;  java -jar bin\\CineMax.jar
All'avvio, l'utente viene accolto da un menù principale che consente di scegliere se effettuare il Login, registrarsi come nuovo Cliente oppure proseguire come utente Guest.

\## 2. FUNZIONALITÀ PER UTENTE GUEST (NON REGISTRATO)
\- Accesso Rapido: Selezionando l'accesso come Guest, l'utente può inserire il titolo (anche parziale) di un film per consultare immediatamente le relative proiezioni disponibili.
\- Ricerca Proiezioni (cercaProiezione()): Permette di filtrare le proiezioni inserendo criteri specifici quali titolo del film, genere, intervallo di date o fascia di costo del biglietto.
\- Dettaglio Proiezione (visualizzaProiezione()): Consente di visualizzare titolo, genere, regista, anno, durata, data, ora, prezzo del biglietto e i posti ancora liberi rispetto alla capienza della sala.
\- Registrazione (registraCliente()): Consente ad un nuovo utente di creare un account Cliente inserendo Nome, Cognome, Username, Password, Data di Nascita (facoltativa) e Luogo di Domicilio.

\## 3. FUNZIONALITÀ PER IL CLIENTE REGISTRATO
\- Autenticazione: Accesso tramite le credenziali impostate in fase di registrazione.
\- Inserimento Prenotazione (creaPrenotazione()): Permette di scegliere una proiezione e richiedere un determinato numero di biglietti. Se i posti residui sono sufficienti, il sistema genera un codice univoco di prenotazione e conferma l'operazione.
\- Gestione Prenotazioni (visualizzaPrenotazione()): Mostra l'elenco di tutte le prenotazioni effettuate dal cliente.
\- Modifica Prenotazione (modificaPrenotazione()): Consente di variare la data di una prenotazione, a patto che sia la data originale sia quella nuova siano successive alla data odierna.
\- Cancellazione Prenotazione (eliminaPrenotazione()): Permette di eliminare una prenotazione effettuata per una proiezione trascorsa rispetto alla data odierna.

\## 4. FUNZIONALITÀ PER IL PROIEZIONISTA
\- Aggiunta Proiezione (aggiungiProiezione()): Permette di inserire un nuovo film specificando titolo, genere, regista, anno, durata, età minima, prezzo e data/ora di programmazione. Il sistema impedisce la creazione di proiezioni sovrapposte nello stesso orario.
\- Modifica Proiezione (modificaProiezione()): Permette di variare la data di una proiezione inserita, a patto che non vi siano prenotazioni già effettuate da parte di clienti.
\- Eliminazione Proiezione (eliminaProiezione()): Permette di cancellare una proiezione dal palinsesto purché non registri alcuna prenotazione attiva.

\## 5. FUNZIONALITÀ PER IL BIGLIETTAIO
\- Visualizzazione Giornaliera: All'accesso, la schermata mostra in automatico le prenotazioni registrate per la data odierna.
\- Ricerca Prenotazioni (cercaPrenotazione()): Consente di cercare le prenotazioni tramite codice univoco, nome/cognome del cliente, titolo del film o intervallo di date.
\- Dettagli Prenotazione (visualizzaPrenotazione()): Mostra i dati identificativi della prenotazione, il nominativo del cliente, la data/ora del film, la quantità di biglietti e l'importo totale.

