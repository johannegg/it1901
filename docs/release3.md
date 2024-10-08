# Release 3 

## Oppdateringer 
Siden forrige innlevering har vi har utvidet appen med følgende funksjonalitet:
- En bruker kan trykke på en bok som vises under "Top 50 Popular Reads" og "Pulitzer winners 2022 and 2023" på "HOME PAGE" og få opp et pop-up-vindu med boktittel, forfatter og beskrivelse av boken. I tillegg kan brukeren trykke på add, og boken vil da legges til i brukeren sin bokhylle (shelf) - **brukerhistorie 3, 4** 
- Hvis brukeren skriver inn boktittelen på en bok som ligger i "library.json", skal skal det dukke opp en drop-down-meny, der boktittelen står. Klikker brukeren på denne, skal man også få opp et pop-up-vindu med bokdetalljer og mulighet til å legge boken til i bokhyllen. - **brukerhistorie 4**
- Hvis brukeren trykker på "SHELF" i menyen skal brukeren få opp et sett med bilder av bøker, der hver bok representerer en bok som brukeren har lagt til i sin bokhylle, ved hjelp av bokens tittel og forfatter. - **brukerhistorie 6**
- Brukeren skal i tillegg klare å trykke på bøkene i bokhyllen sin. Forså å få opp en pop up med informasjon om hva boka handler om, hvem forfatteren er, antall siden og tittelen på boka. I denne pop upen skal det også være mulighet for å fjerne den spesifikke boka som er trykket på. Bildet av boka som representerer den spesifikke boka vil forsvinne, og boka vil forsvinne fra brukeren sin bokhylle. **brukerhistorie 5**
- Hvis brukeren trykker på "PROFILE" i menyen skal det dukke opp brukerens brukernavn og passord, og brukeren skal få mulighet til å endre passord. Trykker brukeren på "Log out"-knappen vil den bli logget ut og sendt til login-siden. - **brukerhistorie 7, 8, 9**

I tillegg til å utvide appen med funksjonaliteten over har vi opprettet restserver, fikset litt på filhåndteringllogikken og fikset tester for alle modulene.


## Restserver
Vi har integrert prosjektet med Spring Boot og implementert en restserver-modul. Denne modulen inneholder en package med klasser som er ansvarlige for å håndtere de forskjellige HTTP-forespørslene til og fra serveren. Videre har vi utviklet en RemoteDataAccess-klasse i ui-modulen som inneholder metoder for å utføre HTTP-kall. 

## Filhåndtering
Vi har fikset slik at vi lagrer objekter til og fra json ved hjelp av serializers og deserializers. 

## Testing

### Testdekningsgrad
Alle modulene i prosjektet har fått opprettet et sett med tilhørende testklasser, som tester at klassene i modulene fungerer slik de skal. Vi var i dialog med studassen vår om hvorvidt det var viktig å få høy testscore på missed branches, og ble fortalt at det ikke var så viktig. Derfor har vi valgt å ikke legge så mye vekt på akkurat det.

### core
core-testene tester de to packagene: core og json. Ifølge rapporten fra jacoco ligger total testdekningsgrad på 99%, hvilket vi anser som bra nok.

![corejacoco](../bookTracker/images/corejacoco.png)
    

### ui
ui-testene er ment å dekke alle funksjoner og handlinger i appen. Total jacoco-testdekningsgrad for ui ligger på 74%. Grunnen til at den ikke er høyere er at vi ikke tester remoteDataAccess() i det hele tatt, ettersom restserver ikke skal trenge å kjøre for å kunne kjøre testene. Siden restserver ikke skal trenge å kjøre under testene har vi laget en egen directDataAccess-klasse, dedikert til testing, som skal returnere det samme som remoteDataAccess, uten å benytte seg av restAPI-et. Vi opplever dessverre ui-testene som tidvis ustabile. Mer om dette under "Kommentarer til prosjektet" i [README](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2323/gr2323/-/blob/master/bookTracker/README.md).

![uijacoco1](../bookTracker/images/uijacoco1.png)<br />
![uijacoco2](../bookTracker/images/uijacoco2.png)

### restserver:

I restserveren er det én test klasse, denne heter BookTrackerApplicationTest. Klassen er ment å sjekke alle GET, PUT, POST og DELETE metodene i restserveren. Altså tester den metodene i BookTrackerController direkte, og metodene fra service klassene UsersService og LibraryService indirekte. Vi tenker at den total testdekningsgraden på 93% er god nok, da den sikrer at de mest sentrale delene av API-et blir testet grundig.

![restserverJacoco1](../bookTracker/images/restserverJacoco1.png)<br />
![restserverJacoco2](../bookTracker/images/restserverJacoco2.png)
