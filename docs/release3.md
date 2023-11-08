## Oppdateringer 
Siden forrige innlevering har vi har utvidet appen med følgende funksjonalitet:
- En bruker kan trykke på en bok som vises under "Top 50 Popular Reads" og "Pulitzer winners 2022 and 2023" på "HOME PAGE" og få opp et pop-up-vindu med boktittel, forfatter og beskrivelse av boken. I tillegg kan brukeren trykke på add, og boken vil da legges til i brukeren sin bokhylle (shelf) - **brukerhistorie 3, 4** 
- Hvis brukeren skriver inn boktittelen på en bok som ligger i "library.json", skal skal det dukke opp en drop-down-meny, der boktittelen står. Klikker brukeren på denne, skal man også få opp et pop-up-vindu med bokdetalljer og mulighet til å legge boken til i bokhyllen. - **brukerhistorie 4**
- Hvis brukeren trykker på "SHELF" i menyen skal brukeren få opp et sett med bilder av bøker, der hver bok representerer en bok som brukeren har lagt til i sin bokhylle, ved hjelp av bokens tittel og forfatter. - **brukerhistorie 6**
- Hvis brukeren trykker på "PROFILE" i menyen skal det dukke opp brukerens brukernavn og passord, og brukeren skal få mulighet til å endre passord. Trykker brukeren på "Log out"-knappen vil den bli logget ut og sendt til login-siden. - **brukerhistorie 7, 8, 9**

I tillegg til å utvide appen med funksjonaliteten over har vi opprettet restserver, fikset litt på filhåndteringllogikken og fikset tester for alle modulene.


## Restserver
Vi har integrert prosjektet med Spring Boot og implementert en restserver-modul. Denne modulen inneholder en package med klasser som er ansvarlige for å håndtere de forskjellige HTTP-forespørslene til og fra serveren. Videre har vi utviklet en RemoteDataAccess-klasse i ui-modulen som inneholder metoder for å utføre HTTP-kall. 

## Filhåndtering
Vi har fikset slik at vi lagrer objekter til og fra json ved hjelp av serializers og deserializers. 

## Testing
Vi har opprettet tester for alle moduelene... Jacoco...

