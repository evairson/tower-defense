Pour démarrer le jeu : 

Depuis un éditeur de code : 

- Importer les jar utilisés dans le projet : lib/junit-4.13.2.jar, lib/hamcrest-all-1.3.jar et lib/json-20231013.jar

    - **VScode** : rajouter à votre fichier settings.json :

          "java.project.referencedLibraries": [
          "lib/junit-4.13.2.jar",
          "lib/hamcrest-all-1.3.jar"
          "lib/json-20231013.jar"
          ]

    - **Eclipse** : Clic droit sur le projet → Properties → Java Build Path → Libraries → Add External JARs.

    - **IntelliJ IDEA** : File → Project Structure → Modules → Depedencies -> + → JARs or Directories → Sélectionnez le fichier JAR.

- Executer la main class : App.java

Depuis un terminal sous UNIX :

- Aller dans le dossier Herson-AitAliBraham

- Executer les commandes suivantes : 

      find . -name "*.java" | xargs javac -classpath "lib/*" -d bin
      java -classpath ".:src:bin/:lib/*" jav.App

