### **Ustabile ui-tester**

Når vi kjører testene for ui-modulen hender det at de feiler. Errorene vi får varierer hver gang vi kjører testen og av og til kjører testene med suksess.

Vi har sett nøye på alle testene og undersøkt de ulike errorene vi får når testene feiler, men ettersom testene viser ulike errors hver gang vi kjører dem, har det vært vanskelig å bruke dem til å debugge koden. Vi har kommet frem til at  at problemet sannsynligvis henger sammen med at vi må interagere med controller-klassene fra testene for at det skal være initialisert en directDataAccess når controller-klassene initialiseres. Vi har ikke funnet noen løsning på problemet, men om det ikke fungerer å kjøre testene via enten `mvn test` eller `mvn clean install` kan det fungere å kjøre `mvn clean install -DskipTests` før man kjører en av de to førstnevnte kommandoene på nytt. Om dette heller ikke fungerer vil problemet kunne løses ved å simpelthen prøve flere ganger, selv om dette er ikke en ideell løsning.

Vi har lært at det er viktig at ui-testene i minst mulig grad interragerer direkte med andre klasser i prosjeketet, da det kan skape kluss i testene.