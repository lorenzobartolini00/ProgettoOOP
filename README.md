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
* [Chiamate](#call)
* [Formato Restituito](#formato)
* [Documentazione](#doc)
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

Le richieste che l'utente può effettuare tramite Postman devono essere all'indirizzo
```
http://localhost:8080
```
Le rotte implementate sono le seguenti:
N° | Tipo | Rotta | Descrizione
----- | ------------ | -------------------- | ----------------------
[1](#1) | ` GET ` | ` /revision_statistics/{statistic_type} ` | *Restituisce un JSONObject con all'interno statistiche, opportunamente filtrate, sulle revisioni di un file*
[2](#2) | ` GET ` | ` /list_files ` | *Restituisce i metadati relitivi ai file, opportunamente filtrati, presenti nella cartella Dropbox*
[3](#3) | ` GET ` | ` /get_list_revisions ` | *Restituisce una lista di tutte le revisioni relativa ad un file*
[4](#4) | ` GET ` | ` /list_file_members ` | *Restituisce una lista di membri di utenti che hanno accesso ad un file*

<a name="call"></a>
## Chiamate
Per fare una chiamata da client, (es. Postaman) sarà necessario contattare l'indirizzo:
```
localhost:8080/rotta?token=G4J8eRdP9roAAAAAAAAAAbvWRhutuOx6QkF7rz2VDCjVr5tQMhM3InqV16_tajQB
```
dove al posto della rotta, l'utente andrà ad inserire una delle rotte sopra elencate.

<a name=1></a>
### 1. /revision_statistics/{statistic_type}
Questa rotta permette di effettuare statistiche sulle revisioni di un file. 
La lista di revisioni su cui vengono effettuate le statistiche, può essere filtrata per periodo temporale o per dimensione.
La rotta prende come attributo (opzionale) il tipo di statistica da visualizzare.

N° | "statistics_type"| Descrizione
----- | ------------ | -------------------- 
1 | time | Statistiche per periodo temporale
2 | size | Statistiche per dimensione
3 | all | Statistiche sia per tempo che per dimensione

Nel caso in cui l'attributo venga omesso, ovvero venga chiamata la rotta "revision_statistics", vengono visulizzate tutte le statistiche.

Per effetturare questa chiamata, sarà necessario inserire un body, in linguaggio JSON, che contenga:

Come paramentri da inseriere in "info" ci saranno:

N° | "info" | Descrizione | Tipo | Required
----- | ------------ | ----------------- | ----- | ---
1 | "path" | Percorso del file | String | SI
2 | "mode" | Modalità scelta del file (Default = Path) | String | NO 
3 | "limit" | Numero massimo di revisioni da visualizzare (MAX=100) | int | SI

Come parametri da inserire in "filters" ci saranno:

N° | "filters" | Descrizione | Tipo | Required
----- | ------------ | ----------------- | ----- | ---
1 | "size_filter" | Filtra per dimensione | int | NO
2 | "time_filter | Filtra per periodo temporale | String | NO

Un esempio di chiamata è:

```json
{
    "info" :
    {
        "path": "/Uni/Generali.docx",
        "mode" : "path",
        "limit": 10
    },
    "filters" :
    {
        "size_filter" : 20000,
        "time_filter" : "last_week"
    }
}

```

<a name=2></a>
### 2. /list_files
Questa rotta permette di effettuare statistiche sui file presenti in una cartella.
La lista di file può essere filtrata per massima e minima dimensione, per estensione e per possibilità di scaricare il file.

Per effetturare questa chiamata, sarà necessario inserire un body, in linguaggio JSON, che contenga:

Come paramentri da inseriere in "info" ci saranno:

N° | "info" | Descrizione | Tipo | Required
----- | ------------ | ----------------- | ----- | ---
1 | "path" | Percorso del file | String | SI
2 | "recursive" | Se true analizza anche i file all'interno delle cartelle | Boolean | SI

Come parametri da inserire in "filters" ci saranno:

N° | "filters" | Descrizione | Tipo | Required
----- | ------------ | ----------------- | ----- | ---
1 | "only_downloadable" | Se true, filtra solo i file scaricabili | Boolean | NO
2 | "max_size" | Filtra per massima dimensione | int | NO
3 | "min_size" | Filtra per minima dimensione | int | NO
2 | "file_extensions" | Filtra per estensione dei file | String | NO

Un esempio di chiamata è:

```json
{
    "info" :
    {
        "path": "/Uni",
        "recursive": true
    },
    "filters":
    {
        "only_downloadable" :false,
        "max_size" : 100000,
        "min_size" : 190,
        "file_extensions" : "paper"
    }
}
```

<a name="formato"></a>
## Formato Restituito
<a name=1></a>
### 1. /revision_statistics/{statistic_type}
```json
{
    "time_statistics": {
        "hour_per_revision": "14h,38m"
    },
    "size_statistics": {
        "absolute_size_percentage_increment": "8628.54%",
        "average_size_increment_per_revision": "203,8 KiB",
        "average_size_percentage_increment_per_revision": "985.97%",
        "absolute_size_increment": "1019,1 KiB"
    },
    "generic_statistics": {
        "numbers_of_revisions": 5
    }
}
```

<a name=2></a>
### 2. /list_files
```json
[
    {
        "name": "GIT.docx",
        "pathLower": "/uni/oop/git.docx",
        "pathDisplay": "/Uni/OOP/GIT.docx",
        "id": "id:xjKzQLUfUpAAAAAAAAAAIw",
        "size": 18374,
        "isDownloadable": true,
        "extension": "docx"
    },
    {
        "name": "Appunti.paper",
        "pathLower": "/uni/appunti.paper",
        "pathDisplay": "/Uni/Appunti.paper",
        "id": "id:xjKzQLUfUpAAAAAAAAAAFQ",
        "size": 200,
        "isDownloadable": false,
        "extension": "paper"
    }
]
```

<a name="doc"></a>
## Documentazione
Il codice java è interamente documentato nella [Javadoc](link alla javadoc)

<a name="autor"></a>
## Autori

* **[Lorenzo Bartolini] (https://github.com/lorenzobartolini00)**
* **[Francesco Pio Cecca] (https://github.com/francescocecca)**
