# BookTracker

Booktracker er en Goodreads-inspirert applikasjon. Applikasjonen fungerer som et medium hvor brukere kan holde oversikt over bøker de har lest eller ønsker å lese. Brukere skal for eksempel ha muligheten til å lage personlige lesemål, lage ulike mapper og søke etter bøker.

## Applikasjons innhold

BookTracker er en JavaFX-applikasjon som skal gi brukere muligheten til å ha en digital oversikt over bøker de har lest eller ønsker å lese. <br />
Ved første møte med applikasjonen må brukerensørge for å registrere seg som en ny bruker, altså en `User`. Brukeren må da oppgi informasjon om ønsket brukernavn, mail og passord. Serveren vil deretter lagre informasjonen slik at brukeren kan logge seg inn ved bruk av brukernavn og passord. Passordet og brukernavnet vil da tilhøre den bestemte brukeren, og kan brukes ved videre innlogging.<br />
Det første brukeren vil møte på etter innlogging er startsiden, Home Page. På startsiden kan han/hun se en oversikt over de mest populære bøkene og Pulitzer vinnerne fra 2022 og 2023. Brukeren kan i tillegg søke etter bestemte bøker og legge de til i sin bokhylle, hvis de eksisterer. Muligheten for å legge til bøkene som allerede vises på startsiden, i sin egen bokhylle, er også der. <br />
I `Shelf` kan man finne bokhyllene som inneholder de tilhørende bøkene til brukeren. Brukerinformasjonen om den spesifikke brukeren hentes og lagres i serveren.  Allerede lagt til bøker i bokhyllen vil vises i `Shelf`, samt bøker som legges til fortløpende. I `Shelf` har man også muligheten til å fjerne bøker, bokhyllen i restserveren vil oppdateres deretter. For hver gang en bok legges til vil brukeren få et varsel, mens når en bok fjernes fra bokhyllen vil antall bøker endres deretter. <br />
Det finnes også en brukerprofil side, `Profile`, som innholder brukernavn og emailen til brukeren som er logget inn. Muligheten til å bytte passord er også her. Ved passordbytte vil serveren lagre det nye passordet og forkaste det gamle. 

**Innholdet i denne readme-filen:**
Endelig brukergrensesnitt 
Kommentarer til prosjektet

Selve oversikten over koden og kodens oppbygging, er i **[readme](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2323/gr2323/-/blob/master/readme.md)**. <br />

## Endelig brukergrensesnitt 

## Kommentarer til prosjektet
Vi har laget flere brukerhistorier som dekker ønsket bruksområde til applikasjonen. Brukerhistoriene innholder funksjoner som legges til i applikasjonen. Disse historiene finner man i **[brukerhistorier](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2323/gr2323/-/blob/master/brukerhistorier.md)**





