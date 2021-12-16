# ProgettoOOP
<h1 align="center"> Progetto per il corso di Programmazione Ad Oggetti 2021/2022 - Dropbox Analyser </h1>

<p align="center">
Dropbox analyser permette di effettuare statistiche sulle revisioni dei file di una cartella Dropbox, anche in base a filtraggi per periodo temporale con tempi medi tra le revisioni e numero minimo di revisioni
</p>

## **Scaletta dei contenuti**
* [Installazione](#install)
* [Rotte](#rotte)
* [Autori](#autor)

<a name="install"></a>
## Installazione
La nostra applicazione può essere installata direttamente dal Prompt dei Comandi, scrivendo:
```
git clone https://github.com/lorenzobartolini00/ProgettoOOP.git
```

<a name="rotte"></a>
## Rotte

N° | Tipo | Rotta | Descrizione
----- | ------------ | -------------------- | ----------------------
[1](#1) | ` GET ` | ` /revision_statistics ` | *Restituisce un JSONObject con all'interno statistiche, opportunamente filtrate, sulle revisioni di un file*
[2](#2) | ` GET ` | ` /list_files ` | *Restituisce i metadati relitivi ai file, opportunamente filtrati, presenti nella cartella Dropbox*
[3](#3) | ` GET ` | ` /get_metadata ` | *Restituisce i metadati relativi ad un singolo file*
[4](#4) | ` GET ` | ` /get_list_revisions ` | *Restituisce una lista di tutte le revisioni relativa ad un file*
[5](#5) | ` GET ` | ` /list_file_members ` | *Restituisce una lista di membri di utenti che hanno accesso ad un file*

<a name="autor"></a>

* **[Lorenzo Bartolini] (https://github.com/lorenzobartolini00):**
* **[Francesco Pio Cecca] (https://github.com/francescocecca):**
