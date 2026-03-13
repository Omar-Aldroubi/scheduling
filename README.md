# Scheduling Project - Java Fil Rouge

Ce projet consiste en la conception et le développement d'un moteur de planification de tâches intégrant des contraintes temporelles, des algorithmes de tri topologique et des générateurs d'emplois du temps. 

Réalisé dans le cadre du module de Programmation Orientée Objet (Fil Rouge), le projet couvre les parties 1 à 7 ainsi que l'exercice optionnel 8 sur les contraintes composites.

---

##  Fonctionnalités du Projet
* **Gestion d'Activités :** Création et manipulation de tâches avec durée.
* **Contraintes Temporelles :** Implémentation de contraintes de précédence, de contiguïté (`MeetConstraint`) et de délais (`Gap`).
* **Tri Topologique :** Algorithmes de tri "Brute Force" et en temps linéaire pour l'ordonnancement des tâches.
* **Solveurs :** Vérification de la validité d'un emploi du temps et générateur aléatoire de plannings.
* ** Exercice Optionnel (Partie 8) :** Implémentation de contraintes composites (`NegationConstraint`, `DisjunctionConstraint`).

---

##  Organisation des Packages

Le projet est structuré selon une architecture modulaire pour assurer la clarté et la réutilisabilité du code :

| Package | Description |
| :--- | :--- |
| `scheduling.activities` | Définition des activités de base. |
| `scheduling.constraints` | Gestion des contraintes unaires, binaires et composites. |
| `scheduling.factoredconstraints` | Version factorisée via l'héritage. |
| `scheduling.solvers` | Algorithmes de tri topologique, vérificateur et scheduler aléatoire. |
| `scheduling.factoredtopologicalsort` | Factorisation des algorithmes de tri. |

---

##  Compilation et Exécution

### Prérequis
* Java 11 ou supérieur.
* La bibliothèque `schedulingtests.jar` doit être présente dans un dossier `lib/`.

### Compilation
Depuis le dossier `src/`, compilez l'ensemble du projet vers un dossier `build/` :
```bash
javac -d ../build -cp .:../lib/schedulingtests.jar $(find . -name "*.java")
