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
E' lasciata all'utente la libertà di decidere quali statistiche effettuare. Le {statistic_type} che si possono passare sono **"time_statistics"** e **"size_statistics"**, ma se si decide di non passare alcuna {statistic_type}, verranno restituite entrambe.
Per effetturare questa chiamata, sarà necessario inserire un body, in linguaggio JSON, così strutturato sul client:
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
Per effetturare questa chiamata, sarà necessario inserire un body, in linguaggio JSON, così strutturato sul client:
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
        "max_size" : 100000
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
