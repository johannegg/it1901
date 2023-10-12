[open in Eclipse Che](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2323/gr2323?new)

# BookTracker - Oversikt og Struktur
Prosjektet er konfigurert med **maven** som byggesystem. Prosjektet har i hovedssak de to modulene core og ui. Brukergrensesnittet befinner seg i modulen ui. Prosjektet benytter javafx version 17.0.8 og Apache Maven 3.9.4.

Kodeprosjektet er plassert i mappen **[bookTracker](bookTracker)**. Denne mappen inneholder også en **[readme-fil](bookTracker/README.md)** som fungerer som en mer forklarende fil om hvordan selve applikasjonen er bygget opp og fungerer, samt brukerhistorier som beskriver litt av prosjektfremgangen. 

## Bygging og kjøring av prosjekt 
Til bygging og kjøring av prosjektet benyttes maven. For å kunne kjøre prosjektet må man først kjøre `mvn clean install` i bookTracker-mappen for å rense, bygge og installere prosjektet lokalt. Etter dette kan man gå inn i ui-mappen og kjøre `mvn javafx:run` for å kjøre applikasjonen og `mvn test` for å for å kjøre testene.

## Organisering 
## core 
core-moduelen er delt inn i to packages:
- **[core](bookTracker/core/src/main/java/core/)** 
- **[json](bookTracker/core/src/main/java/json/)** (ikke helt i mål her ennå - se punkt om filhåndtering i realese2.md)

**[core](bookTracker/core)** inneholder alle klasser for logikk til applikasjonen vår. I vårt prosjekt skal en bruker kunne registrere en profil for så å kunne søke etter, legge til og rangere bøker. Klassene danner til sammen logikk som håndterer dette.

## ui
**[ui](bookTracker/ui)** inneholder én pacage, **[ui](bookTracker/ui/src/main/java/ui)**. Denne inneholder alle kontrollerne til brukergrensesnitett i applikasjonen vår. Vi har i prosjektet benyttet JavaFX og FXML. Hver scene i appen vår har en tilhørende FXML-fil med en tilhørende kontroller. FXML-filene er plassert i en egen resources-mappe i ui-modulen.


## testdekningsgrad 
### jacoco
Jacoco er et verktøy som sjekker prosjektets testdekningsgrad.  gir detaljert innsikt i hvilken del av koden som testes i løpet av kjøretiden. Dette kan hjelpe med å identifisere områder av koden som mangler testdekning, slik at kvaliteten på koden kan forbedres. 

For å få mest mulig ut av Jacoco i Visual Studio Code, har det i dette prosjektet blitt brukt to extensions; Live Server og Coverage Gutters. Liver Server gjør at man kan åpne opp en detaljert oversikt i nettleseren, mens Code Gutters gjør at man kan se hvilke deler som er testet, direkte i koden. 

### spotbugs 
Spotbugs er et statisk analyse verktøy som tester koden for vanlige feil. Den identifiserer potensielle problemer, bugs og svake punkter i koden. For så å gi innsikt og tilbakemelding om hvordan kodekvaliteten og sikkerheten til softwaren kan forbedres. Spotbugs sørger for at koden er sikker, forutsigbar og lesbar. 

For å kjøre spotbugs kan man bruke kommandoene “mvn verify”, “mvn spotbugs:check” og “mvn spotbugs:gui”, sistnevnte er den kommandoen som gir  tilbakemelding. Tilbakemeldingen vil komme i et spotbugs vindu.

### checkstyle 
Checkstyle er et verktøy som bevarer kodekvaliteten og sørger for at en bestemt kodestandard blir holdt. Hovedoppgaven til verktøyet er å sjekke overfladiske og stilmessige egenskaper til koden som tekst.  Den sørger for at koden holder seg konsistent slik at den er enklere å lese og forstå. Checkstylen i prosjektet er konfigurert med standard “Google Java Style ”. Filen bookTracker\config\checkstyle\eclipse-java-google-style.xml er den som definerer reglene og retningslinjene på formatet på prosjektet  vårt ( koden vår). 

For å kjøre checkstyle kan man kjøre kommandoene “mvn verify” og  “mvn checkstyle:check”


## maven
byggesystemet vårt har tillegg for: 
- oppsett av java (**maven-compiler-plugin**)
- testing (**maven-surefire-plugin**)
- kjøring av javafx (**javafx-maven-plugin**)
- testdekningsgrad med jacoco (**jacoco-maven-plugin**)
- sjekking av kodekvalitet med checkstyle (**maven-checkstyle-plugin**) og spotbugs (**spotbugs-maven-plugin**)