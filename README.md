<p align="center">
<img src="DropBox aNALYSER.png" width="30%" height="30%">
</p>



<h1 align="center"> Progetto per il corso di Programmazione Ad Oggetti 2021/2022 - Dropbox Analyser </h1>

<p align="center">
Dropbox analyser permette di effettuare statistiche sulle revisioni dei file di una cartella Dropbox, anche in base a filtraggi per periodo temporale con tempi medi tra le revisioni e numero minimo di revisioni
</p>

## **Scaletta dei contenuti**
* [Introduzione](#intro)
* [Installazione](#install)
* [Rotte](#rotte)
* [Autori](#autor)

<a name="intro"></a>
## Introduzione :mega:
DropboxAnalyser è stata pensata principalmente per effetuare statistiche sulle revisioni effetuate di un file, con la possibilità di utilizzare uno o pià filtri, ma può anche effettuare statistiche rigurdanti più file, analizzandone i metadati.

* **STATISTICHE** 
  * *[Asolute Size Percentage Increment]:* Incremento percentuale tra la prima e l'ultima revisione visulizzata
  * *[Average Size Increment Per Revision]:* Incremento medio per ogni revisione 
  * *[Hour Per Revision]:* Periodo che in media trascorre tra una revisione e l'altra 
  * *[Avarage Size Percentage Increment Per Revision]:* Incremento percentuale tra una revisione e l'altra
  * *[Number Of Revisions]:* Numero di revisioni visulizzate
  * *[Absolute Size Increment]:* Incremento totale tra la prima e l'ultima revisione visulizzata

* **FILTRI - REVISIONI** 
  * *[Time Filter]:* filtra in base al periodo temporale scelto (es. oggi, ultima settimana, ...).
  * *[Size Filter]:* imposta la dimensione massima dei file da visualizzare


* **FILTRI - REVISIONI** 
  * *[Only Type]:* filtra in base all'estensione di un file
  * *[Only Downloadable]:* filtra in base ai file scaricabili

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
## Autori

* **[Lorenzo Bartolini] (https://github.com/lorenzobartolini00)**
* **[Francesco Pio Cecca] (https://github.com/francescocecca)**
