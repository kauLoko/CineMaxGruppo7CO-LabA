# CineMaxGruppo7CO-LabA
Progetto Menu Gruppo 7 

Molteni Davide		765300
Toppi Davide		765309
Lanza Mattia Antonio	766287
Salmazo Bocatto Kauan	767919 


========================================================================
                      CINEMAX - MANUAL D'INSTALLAZIONE
========================================================================

Progetto del Corso di Laboratorio Interdisciplinare A
Università degli Studi dell'Insubria
Anno Accademico 2025/2026

------------------------------------------------------------------------
1. REQUISITI DI SISTEMA
------------------------------------------------------------------------
- Java Development Kit (JDK) 17 o superiore installato e configurato nelle
  variabili d'ambiente (PATH).
- Terminale / Prompt dei comandi.

------------------------------------------------------------------------
2. STRUTTURA DEL REPOSITORY
------------------------------------------------------------------------
La struttura delle directory del progetto è così organizzata:

  .
  ├── autori.txt           -> Dettagli dei membri del team di progetto
  ├── README.txt           -> Questo file (istruzioni di compilazione ed esecuzione)
  ├── bin/                 -> Contiene l'eseguibile compilato (.jar)
  ├── data/                -> Contiene i dataset persistenti (proiezioni.csv, utenti.csv, ecc.)
  ├── doc/                 -> Contiene i manuali PDF e la JavaDoc generata
  ├── lib/                 -> Librerie esterne utilizzate (se presenti)
  └── src/                 -> Codice sorgente Java (package cinemax)

------------------------------------------------------------------------
3. COMPILAZIONE E CREAZIONE DEL FILE .JAR
------------------------------------------------------------------------
Per compilare il progetto manualmente da riga di comando e generare il file
eseguibile `.jar`:

1. Aprire il terminale e posizionarsi nella cartella radice del progetto:
   cd path/to/repository

2. Compilare i sorgenti memorizzando i file .class nella cartella bin:
   javac -d bin -sourcepath src src/cinemax/*.java

3. Creare il pacchetto eseguibile .jar:
   jar cfe bin/CineMax.jar cinemax.CineMax -C bin .

------------------------------------------------------------------------
4. ESECUZIONE DELL'APPLICAZIONE
------------------------------------------------------------------------
Per avviare l'applicazione CineMax, eseguire il seguente comando dalla
cartella radice del progetto:

   java -jar bin/CineMax.jar

Nota: Assicurarsi che la cartella 'data' si trovi nella stessa directory o al
livello appropriato per consentire al programma la lettura e scrittura dei file.
