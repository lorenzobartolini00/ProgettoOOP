<p align="center">
<img src="Dropbox Analyzer.png" width="30%" height="30%">
</p>



<h1 align="center"> Progetto per il corso di Programmazione Ad Oggetti 2021/2022 - Dropbox Analyzer </h1>

<p align="center">
Abbiamo sviluppato un'applicazione Java che mette a disposizione dell'utente delle API personalizzate, sfruttando le API di DropBox, grazie alle potenzialità di SpringBoot. 
</p>

## **Scaletta dei contenuti** :mag_right:
* [Introduzione](#intro)
* [Installazione](#install)
* [Rotte](#rotte)
* [Chiamate](#call)
* [Eccezioni](#exceptions)
* [Documentazione](#doc)
* [Autori](#autor)

<a name="intro"></a>
## Introduzione :mega:
Dropbox Analyzer fornisce all'utente una serie di funzionalità, che possono essere riassunte come segue. 
E' possibile:
1. effettuare statistiche sulle revisioni di un file presente in una cartella Dropbox, di cui si deve specificare il percorso. Le revisione possono essere filtrate in base al periodo temporale massimo a cui esse possono risalire e alla dimensione minima e massima. Le statistiche sulle revisioni riguardano due ambiti: ogni quanto si effettuano revisioni su quel file e di quanto il file vari di dimensioni.
2. ottenere una lista di revisioni, con i relativi metadati. Quest'ultimi riguardano la data della modifica(lato client e lato server) del file, la sua dimensione dopo che esser stato modificato e l'id univoco associato alla particolare revisione.
3. ottenere una lista di file, con i relativi metadati. Per farlo occorre specificare il percorso e, nel caso in cui l'utente lo desideri, è possibile visualizzare anche i file presenti nelle sottocartelle. La lista di file da visualizzare può essere filtrata in base ad una serie di campi di seguito riportati.
4. ottenere una lista di utenti che hanno accesso ad un determinato file.

* **STATISTICHE** 
  * ***Asolute Size Percentage Increment**:* Incremento percentuale tra la prima e l'ultima revisione visulizzata
  * ***Average Size Increment Per Revision**:* Incremento medio per ogni revisione 
  * ***Hour Per Revision**:* Periodo che in media trascorre tra una revisione e l'altra 
  * ***Avarage Size Percentage Increment Per Revision**:* Incremento percentuale tra una revisione e l'altra
  * ***Number Of Revisions**:* Numero di revisioni visualizzate
  * ***Absolute Size Increment**:* Incremento totale tra la prima e l'ultima revisione visulizzata

* **FILTRI - REVISIONI** 
  * ***Time Filter**:* filtra in base al periodo temporale scelto ("last_hour", "last_day", "last_week")
  * ***Max Size**:* imposta la dimensione(espressa in byte) massima dei file da visualizzare
  * ***Min Size**:* imposta la dimensione(espressa in byte) minima dei file da visualizzare


* **FILTRI - FILES** 
  * ***File Extension**:* filtra in base all'estensione di un file("docx", "png",...)
  * ***Only Downloadable**:* filtra solamente i file scaricabili
  * ***Max Size**:* imposta la dimensione(espressa in byte) massima dei file da visualizzare
  * ***Min Size**:* imposta la dimensione(espressa in byte) minima dei file da visualizzare

<a name="install"></a>
## Installazione :cd:
La nostra applicazione può essere installata direttamente dal Prompt dei Comandi, scrivendo:
```
git clone https://github.com/lorenzobartolini00/ProgettoOOP.git
```

<a name="rotte"></a>
## Rotte :mountain_cableway:

Per poter effetturare la chiamata della rotta desiderata, l'utente deve anteporre il seguente indirizzo a quello della rotta stessa:
```
http://localhost:8080
```
Le rotte messe a disposizione dall'applicazione sono:
N° | Tipo | Rotta | Descrizione
----- | ------------ | -------------------- | ----------------------
[1](#1) | ` GET ` | ` /revision_statistics/{statistic_type} ` | *Restituisce un JSONObject contenente statistiche sulle revisioni, eventualmente filtrate, di un file*
[2](#2) | ` GET ` | ` /list_files ` | *Restituisce i metadati relitivi ai file, eventualmente filtrati, presenti nella cartella Dropbox*
[3](#3) | ` GET ` | ` /list_revisions ` | *Restituisce una lista di tutte le revisioni, eventualmente filtrate, relativa ad un file*
[4](#4) | ` GET ` | ` /list_file_members ` | *Restituisce una lista di utenti che hanno accesso ad un file*

<a name="call"></a>
## Chiamate :telephone_receiver:
Per eseguire con successo la chiamata all'API è necessario specificare tra i parametri, anche il token per accedere al proprio account DropBox:
```
localhost:8080/rotta?token=G4J8eRdP9roAAAAAAAAAAbvWRhutuOx6QkF7rz2VDCjVr5tQMhM3InqV16_tajQB
```
dove al posto di "rotta", l'utente dovrà specificare una di quelle sopra elencate.

<a name=1></a>
### 1. /revision_statistics/{statistic_type}
Questa rotta permette di effettuare statistiche sulle revisioni di un file. 
La lista di revisioni su cui vengono effettuate le statistiche, può essere filtrata per periodo temporale o per dimensione.
La rotta prende come attributo (opzionale) il tipo di statistica da visualizzare.

N° | "statistics_type"| Descrizione
----- | ------------ | -------------------- 
1 | "time" | Statistiche per periodo temporale
2 | "size" | Statistiche per dimensione
3 | "all" | Statistiche sia per tempo che per dimensione

Nel caso in cui l'attributo venga omesso, ovvero venga chiamata la rotta "revision_statistics", vengono visulizzate tutte le statistiche.

Per effetturare questa chiamata, è necessario inserire nel body della richiesta i seguenti dati in formato Json:

I paramentri da inserire in "info" sono:

N° | "info" | Descrizione | Tipo | Required
----- | ------------ | ----------------- | ----- | ---
1 | "path" | Percorso del file | String | SI
2 | "mode" | Modalità scelta del file (Default = Path) | String | NO 
3 | "limit" | Numero massimo di revisioni da visualizzare (MAX=100) | int | NO

I parametri da inserire in "filters" sono:

N° | "filters" | Descrizione | Tipo
----- | ------------ | ----------------- | -----
1 | "max_size" | Dimensione massima delle revisioni | int
2 | "min_size" | Dimensione minima delle revisioni | int 
3 | "time_filter | Filtra per periodo temporale | String




Un esempio di chiamata è il seguente:

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
        "max_size" : 20000,
        "time_filter" : "last_week"
    }
}

```

Il formato restituito è il seguente:

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
Questa rotta permette di effettuare statistiche sui file presenti in una cartella.
La lista di file può essere filtrata per massima e minima dimensione, per estensione e per possibilità di scaricare il file.

Per effetturare questa chiamata, è necessario inserire nel body della richiesta i seguenti dati in formato Json:

I paramentri da inserire in "info" sono:

N° | "info" | Descrizione | Tipo | Required
----- | ------------ | ----------------- | ----- | ---
1 | "path" | Percorso del file | String | SI
2 | "recursive" | Se true analizza anche i file all'interno delle cartelle | Boolean | NO

I paramentri da inserire in "filters" sono:

N° | "filters" | Descrizione | Tipo |
----- | ------------ | ----------------- | -----
1 | "only_downloadable" | Se true, filtra solo i file scaricabili | Boolean
2 | "max_size" | Filtra per massima dimensione | int
3 | "min_size" | Filtra per minima dimensione | int
2 | "file_extensions" | Filtra per estensione dei file | String

Un esempio di chiamata è il seguente:

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

Il formato restituito è il seguente:

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

<a name=3></a>
### 3. /list_revisions
Questa rotta permette di ottenere la lista di revisioni di un file.
La lista di revisioni può essere filtrata per periodo temporale o per dimensione.

Per effetturare questa chiamata, è necessario inserire nel body della richiesta i seguenti dati in formato Json:

I paramentri da inserire in "info" sono:

N° | "info" | Descrizione | Tipo | Required
----- | ------------ | ----------------- | ----- | ---
1 | "path" | Percorso del file | String | SI
2 | "mode" | Modalità scelta del file (Default = Path) | String | NO 
3 | "limit" | Numero massimo di revisioni da visualizzare (MAX=100) | int | NO

I paramentri da inserire in "filters" sono:

N° | "filters" | Descrizione | Tipo 
----- | ------------ | ----------------- | -----
1 | "max_size" | Dimensione massima delle revisioni | int
2 | "min_size" | Dimensione minima delle revisioni | int 
3 | "time_filter | Filtra per periodo temporale | String

Un esempio di chiamata è il seguente:

```json
{
    "info" :
    {
        "path": "/Uni/Generali.docx",
        "mode" : "path",
        "limit": 100
    },
     "filters" :
    {
        "size_filter" : 20000,
        "time_filter" : "last_week"
    }
}
```
Il formato restituito è il seguente:

```json
[
    {
        "lastClientModify": "2021-12-17T18:44:29.000+00:00",
        "lastServerModify": "2021-12-17T18:44:30.000+00:00",
        "revisionId": "5d35bedefdfb189398f81",
        "size": 1055630,
        "isDownloadable": true,
        "lastServerModifyInMilliseconds": 1639766670000,
        "lastClientModifyInMilliseconds": 1639766669000
    },
    {
        "lastClientModify": "2021-12-15T10:57:29.000+00:00",
        "lastServerModify": "2021-12-15T10:57:31.000+00:00",
        "revisionId": "5d32d2c227dfd89398f81",
        "size": 1055536,
        "isDownloadable": true,
        "lastServerModifyInMilliseconds": 1639565851000,
        "lastClientModifyInMilliseconds": 1639565849000
    },
    {
        "lastClientModify": "2021-12-14T17:36:57.000+00:00",
        "lastServerModify": "2021-12-14T17:36:57.000+00:00",
        "revisionId": "5d31ea2cd798b89398f81",
        "size": 602569,
        "isDownloadable": true,
        "lastServerModifyInMilliseconds": 1639503417000,
        "lastClientModifyInMilliseconds": 1639503417000
    },
    {
        "lastClientModify": "2021-12-14T17:36:30.000+00:00",
        "lastServerModify": "2021-12-14T17:36:30.000+00:00",
        "revisionId": "5d31ea13476b389398f81",
        "size": 12163,
        "isDownloadable": true,
        "lastServerModifyInMilliseconds": 1639503390000,
        "lastClientModifyInMilliseconds": 1639503390000
    },
    {
        "lastClientModify": "2021-12-14T17:31:51.000+00:00",
        "lastServerModify": "2021-12-14T17:34:09.000+00:00",
        "revisionId": "5d31e98cc8bba89398f81",
        "size": 12094,
        "isDownloadable": true,
        "lastServerModifyInMilliseconds": 1639503249000,
        "lastClientModifyInMilliseconds": 1639503111000
    }
]
```

<a name=4></a>
### 4. /list_file_members
Questa rotta permette di ottenere la lista di utenti che possono accedere ad un file.

Per effetturare questa chiamata, è necessario inserire nel body della richiesta i seguenti dati in formato Json:

N° | "info" | Descrizione | Tipo | Required
----- | ------------ | ----------------- | ----- | ---
1 | "path" | Percorso del file | String | SI
2 | "include_inherited" | Include  | String | NO 
3 | "limit" | Numero massimo di utenti da visualizzare (MAX=100) | int | NO

Un esempio di chiamata è:

```json
{
    "info" :
    {
        "file": "id:xjKzQLUfUpAAAAAAAAAADA",
        "include_inherited": true,
        "limit": 100
    }
}
```

Il formato restituito sarà:

```json

[
    {
        "accountId": "dbid:AABLrI5m5DeORK35nqXp6oLLxAdeXymeL5g",
        "email": "lori.bartolini06@gmail.com",
        "displayName": "Lorenzo Bartolini"
    },
    {
        "accountId": "dbid:AADnswjyNc8RS6WimHgG00a7YOlsm_JmrrE",
        "email": "kekkolino21@gmail.com",
        "displayName": "Francesco Cecca"
    }
]

```

<a name="exceptions"></a>
## Eccezioni :bangbang:
Oltre alle eccezioni standard, è stata implementata una nuova eccezione:

[BadFormatException](https://github.com/lorenzobartolini00/ProgettoOOP/blob/main/DropboxAnalyzer/src/main/java/it/univpm/DropboxAnalyzer/exceptions/BadFormatException.java) Lanciata nel caso in cui l'utente non inserisca i parametri richiesti o se questi hanno un tipo errato. Il messaggio contenuto nell'eccezione è stato così formattato e parametrizzato:

```
"Invalid data in " + context + ": "+ "'"+ cause +"' "+ type
```

### Esempio 1

Nel caso in cui non sia possibile trovare il campo "info", poichè è assente oppure è stato scritto male, come visualizzato di seguito:
```
{
    "infos" :
    {
        "path": "/Uni/Generali.docx",
        "mode" : "path",
        "limit": 100
    }
}
```
viene sollevata un'eccezione e restituito il seguente messaggio:
```
"Invalid data in body: 'info' is missing"
```

### Esempio 2
Nel caso in cui uno dei dati richiesti, come ad esempio il numero massimo di file da visualizzare "limit", non venga fornito con il tipo corretto, come visualizzato di seguito:

```
{
    "info" :
    {
        "path": "/Uni/Generali.docx",
        "mode" : "path",
        "limit": true
    }
}
```

viene sollevata un'eccezione e restituito il seguente messaggio:
```
"Invalid data in body/info: 'limit' has wrong type"
```

### Esempio 3
Nel caso in cui non sia possibile trovare uno dei parametri obbligatori(contrassegnati con "required"), come ad esempio il percorso del file "path", come visualizzato di seguito:

```
{
    "info" :
    {
        "mode" : 200,
        "limit": 100
    }
}
```
viene sollevata un'eccezione e restituito il seguente messaggio:
```
"Invalid data in body/info: 'path' is missing"
```
ATTENZIONE: Questo non accade se non viene fornito un parametro non obbligatorio, come, ad esempio, il parametro "mode", in quanto i parametri non obbligatori prevedono un valore di default in caso di assenza.

<a name="doc"></a>
## JUnit tests
Per verificare il corretto funzionamento di alcune parti del codice, sono state sviluppate delle classi di test.
### TestRevision
Questa classe permette di eseguire un test unitario per la creazione di un'istanza della classe Revision. Una volta creata e inizializzata con dei parametri arbitrari, viene verificato, tramite il metodo assertEquals, se i parametri inseriti e quelli attesi combacino.

### TestStatistic
Questa classe permette di eseguire tre test unitari per testare i metodi che stanno alla base del calcolo delle statistiche sulle revisioni. 
1. Il primo test verifica il corretto funzionamento del metodo setDefault(), il quale ha il compito di settare i parametri principali per effettuare la chiamata all'API DropBox, da cui vengono estrapolati i dati. Tramite un assertEquals si verifica che l'url impostato dal metodo setDefault() sia corretto.
2. Il secondo test riguarda la vera e propria chiamata all'API, attraverso il metodo rootCall() della classe HTTPSRequest. Tramite un assertTrue, si verifica se il JsonObject ritornato dal metodo contenga tutti i parametri che ci aspetta da una revisione.
3. Il terzo test riguarda la conversione da JsonObject a oggetto Java di tipo Revision effettuata attraverso il metodo getRevisionList() della classe FileServiceImpl, che prende in ingresso direttamente un JsonObject contenente la lista di revisioni. Viene verificato, tramite una serie di assertEquals, che gli attributi della revisione siano stati effettivamente trasferiti all'istanza della classe Revision, andandoli a confrontare con quelli presenti nel Json.

### TestBadFormatException
Questa classe permette di eseguire due test unitari per testare che venga effettivamente sollevata l'eccezione personalizzata BadFormatException nel caso in cui l'utente non inserisca i parametri obbligatori oppure che questi abbiano il formato errato.
1. Il primo test verifica, tramite un assertThrows, che venga sollevata l'eccezione BadFormatException, dopo aver appositamente evitato di inserire il parametro obbligatorio "path" nella lista dei parametri. Il test è superato se il metodo chekFormat(), il quale si occupa di controllare la correttezza dei parametri, solleva l'eccezione richiesta. Inoltre viene controllato, tramite assertEquals che il messaggio di errore sia corretto.
2. Il secondo test è simile al precedente, ma questa volta si inserisce appositamente un parametro con un tipo errato. Il test è superato se si verificano le medesime condizioni del primo test.

## Documentazione :paperclip:
Il codice java è interamente documentato nella [Javadoc](https://github.com/lorenzobartolini00/ProgettoOOP/tree/main/DropboxAnalyzer/doc)

<a name="autor"></a>
## Autori :family:

* **[Lorenzo Bartolini] (https://github.com/lorenzobartolini00)**
* **[Francesco Pio Cecca] (https://github.com/francescocecca)**
