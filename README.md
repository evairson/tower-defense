




Importer les jar utilisés dans le projet : 

VScode : rajouter à votre fichier settings.json : 
    "java.project.referencedLibraries": [
        "lib/junit-4.13.2.jar",
        "lib/hamcrest-all-1.3.jar",
        "lib/json-20231013.jar"
    ]
Eclipse : Clic droit sur le projet → Properties → Java Build Path → Libraries → Add External JARs.
IntelliJ IDEA : File → Project Structure → Modules → Depedencies -> + → JARs or Directories → Sélectionnez le fichier JAR.

Pour Ecliipse et IntelliJ il faut rajouter les jar junit-4.13.2.jar, hamcrest-all-1.3.jar et json-20231013.jar