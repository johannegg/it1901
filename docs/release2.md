# Release 2

## Oppdateringer 
Siden forrige innlevering har vi har utvidet appen med følgende funksjonalitet:
- En bruker kan opprette en profil og logge inn **brukerhistorie 1**
- En bruker får opp en liste med "Popular reads" og "Pulitzer winners" når den befinner seg på startsiden **brukerhistorie 2**

Vi har fjernet muligheten for å legge til bøker i en liste, som vi hadde med i forrige innlevering, ettersom vi må endre denne til å passe bedre med strukturen vår, og vi tenker dermed å utvikle denne funksjonaliteten på nytt på et senere tidspunkt. 

## Kjøring av prosjekt
Problemet med at vi ikke klarte å bruke relativ filsti i forrige innlevering er nå løst, og applikasjonen kjører uten plugin-feil når man kjører `mvn javafx:run` i ui-mappen.

## Filhåndtering
Vi har gått over til å bruke JSON til å håndtere fillagring ved hjelp av jackson-biblioteket. Foreløping fungerer  det slik at hver gang en bruker oppretter en profil opprettes det en ny JSON-fil tilhørende brukeren, med info om e-post, brukernavn og passord. Vi tenker at denne filen etter hvert skal utvides til å også inkludere en enkelt bruker sine bokhyller og "reading challenges". I tillegg lagres brukernavnet i en liste i en egen JSON-fil slik at det er enkelt å sjekke om en bruker eksisterer når man forsøker å logge inn.

En fordel ved å opprette en ny fil for hver bruker er at det gir en enkel og klar struktur for dataene. På den annen side har vi reflektert over at det muligens kan være enklere å aksessere brukerinformasjonen om vi lagrer all informaasjon i én fil, og vi vil derfor videre vurdere muligheten for å gå over til en slik løsning i stedet.

I json-packagen ligger det per nå kun én fil som håndterer lesing og skriving til fil for Users-klassen. Planen vår videre er å opprette klasser for serialisering og deserialisering av objekter, samt modul- og persistensklasser.

## Testing 
Vi har laget tester for både core og ui-modulen. Testdekningsgrad testes fremdeles av jacoco. Vi har siden innlevering 1 også implementert spotbugs og checkstyle i testing av appen vår.

For å få spotbugs og checkstyle til å fungere måtte vi implementere tilhørende plugins i den ytterste pom-filen, samt pom-filene i core og ui. Vi brukte samme plugins som i todo-eksempelet, men dette førte med seg noen problemer. Da spotbugs ble kjørt med kommandoene “mvn spotbugs:check” og “mvn spotbugs verify” fikk vi Build Success, men det kom med en “warning” eller en advarsel. Dette er meldingen vi fikk: 

`[WARNING] Parameter 'htmlOutput' is unknown for plugin 'spotbugs-maven-plugin:4.7.1.1:check (default-cli)'`
`[WARNING] Parameter 'htmlOutput' is unknown for plugin 'spotbugs-maven-plugin:4.7.1.1:spotbugs (spotbugs)'`

Det samme skjedde da kommandoene “mvn verify” og “mvn checkstyle:check” ble kjørt. For checkstyle fikk vi denne advarselen: 

`[WARNING] Parameter 'encoding' is unknown for plugin 'maven-checkstyle-plugin:3.2.0:check (default-cli)'`

For å løse dette fjernet vi deler av den ytterste pom-filen, da disse ikke hadde en stor innvirkning på resultatet. Dette ble fjernet: 

Fra spotbugs plugin:
`<htmlOutput>true</htmlOutput>`

Fra checkstyle plugin:
`<encoding>UTF-8</encoding>`

Dette gjør at spotbugs bare gir tilbakemelding for “mvn spotbugs:gui”. Checkstyle gir tilbakemelding som vanlig.

## ui
Vi har siden sist opprettet nye FXML-filer for innlogging, registrering, startsiden og profilsiden. Disse har tilhørende kontrollere i ui-packagen.




