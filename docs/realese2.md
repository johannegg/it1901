# Oppdateringer 

Vi har utvidet appen med følgende funksjonalitet:
- En bruker kan opprette en profil og logge inn
- En bruker får opp en liste med "Popular reads" og "Pulitzer winners når den befinner seg på startsiden"

## Filhåndtering

Vi har gått over til å bruke JSON til å håndtere fillagring ved hjelp av jackson-biblioteket. Foreløping fungerer  det slik at hver gang en bruker oppretter en profil opprettes det en ny JSON-fil tilhørende brukeren, med info om e-post, brukernavn og passord. Vi tenker at denne filen etter hvert skal utvides til å også inkludere en enkelt bruker sine bokhyller og "reading challenges". I tillegg lagres brukernavnet i en liste i en egen JSON-fil slik at det er enkelt å sjekke om en bruker eksisterer når man forsøker å logge inn.

En fordel ved å opprette en ny fil for hver bruker er at det gir en enkel og klar struktur for dataene. På den annen side har vi reflektert over at det muligens kan være enklere å aksessere brukerinformasjonen om vi lagrer all informaasjon i én fil, og vi vil derfor videre vurdere muligheten for å gå over til en slik løsning i stedet.

I **[json](bookTracker/json)**-packagen ligger det per nå kun én fil som håndterer lesing og skriving til fil for Users-klassen. Planen vår videre er å opprette klasser for serialisering og deserialisering av objekter, samt modul- og persistensklasser.

## Testing

- jacco
- checkstyle og spotbugs

## UI



# Problemer

