![sac-a-dos](https://raw.githubusercontent.com/yusa-ai/aav/main/docs/logo.svg?token=APPMWKQ42IOZZXT6OYXZ47LBNPPEU)

# Problème du sac à dos

Ce repo contient mon code source pour la résolution du problème de sac à dos à à l'aide 
de trois algorithmes, ou méthodes :

* [La méthode gloutonne](https://fr.wikipedia.org/wiki/Algorithme_glouton)
* La méthode dynamique donnée par [ce cours](http://user.oc-static.com/pdf/95368-introduction-a-la-programmation-dynamique.pdf)
* [La méthode par procédure de séparation et d'évaluation (PSE)](https://fr.wikipedia.org/wiki/S%C3%A9paration_et_%C3%A9valuation)

Ce programme a été réalisé intégralement en Java dans le cadre d'un projet d'Algorithmique 
Avancée (AAV) en deuxième année de DUT Informatique à l'IUT de Paris, pour l'année 
universitaire 2021 - 2022.

## Synopsis

En algorithmique, le problème du sac à dos est un problème d’optimisation combinatoire. Il modélise une
situation analogue au remplissage d’un sac à dos, ne pouvant supporter plus d’un certain poids, avec tout ou
partie d’un ensemble donné d’objets ayant chacun un poids et une valeur. Les objets mis dans le sac à dos
doivent maximiser la valeur totale, sans dépasser le poids maximum.  
Le problème du sac à dos est l’un des 21 problèmes NP-complets de Richard Karp, exposés dans son article
de 1972. La formulation du problème est fort simple, mais sa résolution est plus complexe. Les algorithmes
existants peuvent résoudre des instances pratiques de taille importante. Cependant, la structure singulière du
problème, et le fait qu’il soit présent en tant que sous-problème d’autres problèmes plus généraux, en font un
sujet de choix pour la recherche

### Présentation du problème

L’énoncé de ce problème fameux est simple :
> Étant donné plusieurs objets possédant chacun un poids et une valeur et étant donné un poids
maximum pour le sac, quels objets faut-il mettre dans le sac de manière à maximiser la valeur totale
sans dépasser le poids maximal autorisé pour le sac ?

Source : [ProjetAAV.pdf](./ProjetAAV.pdf)

## Notes
