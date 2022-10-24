# Scala Project E5-DSIA
## Spreadsheet / Feuille de calcul
### Djinou Loïc, Gouja Adam, Khalil Adel, Taillandy Valentin

Ce projet consiste en la réalisation d'une feuille de calcul en Scala.
Pour réaliser ce projet, il est donc nécessaire de passer par la réalisation d'un tableur,
des différentes fonctions.

Dans le cadre de ce projet, nous avons décidé de mettre en place des cellules dans un premier
temps, puis de partir de ces cellules pour mettre en place toutes les fonctions et la feuille
de calcul.

Les fichiers présents dans ce dossier sont organisés de la manière suivante :
- src/main/scala/spreadsheat => Dossier contenant l'ensemble des fichiers fonctionnels utilisé 
les uns en lien avec les autres
  - Cell => Fichier de création de classe et de fonctions des cells. C'est à partir des 
  fonctions de ce fichier que les cells et leurs fonctions sont crées.
  - Functions => Fichier comprenant l'ensemble des fonctions calculatoires nécessaires à la
  création du tableur.
  - Main => Fichier de lancement du tableur.
  - Row => Fichier de création de la classe des lignes de cells et des fonctions associées.
  - Spreadsheet =>Fichier de création de Spreadsheet et de l'enesmble des fonctions associées.
- src/test/scala/spreadsheat => Dossier contenant l'ensemble des fichiers de tests afin de
vérifier que les fonctions présentes dans les fichiers cités plus haut sont fonctionnels



