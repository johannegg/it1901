## Oppdateringer 
Prosjektets fundament har blitt ordnet og klargjort. Dette innebærer:

- å sette opp prosjektet slik at det kan bygges og kjøres av maven
- fikse den generelle mappestrukturen samt legge til nødvendige readme filer
- en enkel og grunnleggende versjon av å legge til bøker i en bokhylle ble laget. Brukere har på denne måten
mulighet til å skrive inn boktittel slik at den kan lagres i bokhylla  

## Testing

For å konfigurere jacoco startet vi med å implementere en plugin i hver av de tre pom-filene. Etter dette satt vi fast en liten stund, før vi fant ut at vi kunne laste ned Live Server og Code Gutters. Da vi tilslutt forsto dette, ble det enkelt å få oversikt over hvilke deler av koden som manglet tester. 


## Problemer
- trenger å utvikle søkemotorikken slik at når man søker opp tittel, så kommer det forslag 
- har problemer med å bruke relativ sti, så får kun kjørt maven om vi bruker absolutt sti. Absolutt filsti inneholder brukernavn, og dette varierer fra bruker til bruker, alstå er det viktig at vi finner ut hvordan vi kan få appen til å kjøre med realtiv sti  
