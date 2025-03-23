# Projet n°21 : Définir une solution fonctionnelle et concevoir l'architecture d'une application

![GitHub contributors](https://img.shields.io/github/contributors/LancelleTimote/Projet-n-21-Definir-une-solution-fonctionnelle-et-concevoir-l-architecture-d-une-application?style=for-the-badge&color=green)
![GitHub language count](https://img.shields.io/github/languages/count/LancelleTimote/Projet-n-21-Definir-une-solution-fonctionnelle-et-concevoir-l-architecture-d-une-application?style=for-the-badge)
![GitHub repo size](https://img.shields.io/github/repo-size/LancelleTimote/Projet-n-21-Definir-une-solution-fonctionnelle-et-concevoir-l-architecture-d-une-application?style=for-the-badge)
![GitHub last commit](https://img.shields.io/github/last-commit/LancelleTimote/Projet-n-21-Definir-une-solution-fonctionnelle-et-concevoir-l-architecture-d-une-application?style=for-the-badge)
![GitHub License](https://img.shields.io/github/license/LancelleTimote/Projet-n-21-Definir-une-solution-fonctionnelle-et-concevoir-l-architecture-d-une-application?style=for-the-badge)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/timote-lancelle-devweb/)

## :mag: Aperçu

![Aperçu du site web](visuel_projet/visuel_projet.png)

## :bookmark_tabs: Sommaire

<ol>
    <li><a href="#sujet">Sujet</a></li>
    <li><a href="#demandes_respecter">Demandes à respecter</a></li>
    <li><a href="#objectifs_projet">Objectifs du projet</a></li>
    <li><a href="#technologies_utilisees">Technologies utilisées</a></li>
    <li><a href="#prerequis">Prérequis</a></li>
    <li><a href="#installation">Installation</a></li>
    <li><a href="#utilisation_siteweb">Utilisation du site web</a></li>
    <li><a href="#auteurs_contributeurs">Auteurs et contributeurs</a></li>
    <li><a href="#licence">Licence</a></li>
</ol>

## :page_facing_up: 1. Sujet <a name = "sujet"></a>

Vous travaillez pour Your Car Your Way en tant que développeur senior !

Your Car Your Way fait des locations de voitures depuis une vingtaine d’années. Initialement en Angleterre, l’entreprise s’est étendue sur le marché européen, puis, depuis l’an dernier, sur le marché américain.

Cette croissance n’avait pas été anticipée du point de vue du système d’information. Selon les pays, les clients utilisent des applications web distinctes (même si le design a été homogénéisé, en réalité ce sont différentes applications).

Vous êtes donc chargé du développement d’une nouvelle application web où tous les clients dans tous les pays pourront gérer toute la procédure de location de voitures.

Une équipe a préalablement rédigé les exigences fonctionnelles dans un document Business Requirements. Pourtant, Leilani, la CEO (Chief Executive Officer), les a examinées et n’a pas été satisfaite à 100 %.

De : Leilani
À : Vous
Objet : Lancement du projet Your Car Your Way App

Bonjour,

Le projet Your Car Your Way App a été lancé. Cette nouvelle application à destination de tous nos clients est le premier jalon d'une nouvelle stratégie de notre entreprise. Une équipe a commencé à travailler sur les besoins fonctionnels, mais j'ai besoin que tu reprennes la main pour la suite.

Cette tâche se divise en trois grandes parties :

La mise à jour du document “Business Requirements”.
La réalisation d’un “Architecture Definition Document” (cahier des charges technique).
La complétion d’un “Compliance Assessment”.
Je t'enverrai prochainement des informations plus détaillées sur ces trois documents.

À la fin du projet, j’aimerais que tu me présentes ton travail lors d’une réunion. Sois prêt à me montrer ce que tu as fait, ainsi qu’à expliquer et justifier tes choix. Pour cela, j’aimerais que tu prépares un support de présentation soigneusement rédigé pour accompagner ta présentation.

Si tu as des questions, n’hésite pas !

À très vite,

Leilani
CEO, Your Car Your Way

Afin de vous assurer de la validité de votre proposition, vous décidez également de développer une preuve de concept (PoC, “Proof of Concept” en anglais) que vous pourrez présenter à Leilani. Votre objectif est de la rassurer sur la faisabilité de la fonctionnalité d’échange avec le service client via un chat en ligne. Vous développerez ce PoC tout en respectant les spécifications techniques que vous avez vous-même établies.

## :memo: 2. Demandes à respecter <a name = "demandes_respecter"></a>

- Faire la partie back-end de l'application web en utilisant Java et Spring ;
- Extraire et mettre en place une base de donnée avec une technologie au choix (H2, MySQL, etc...) ;
- Faire la partie front-end de l'application web en utilisant Angular.js ;
- Utiliser WebSocket pour la partie messagerie en temps réel.

## :checkered_flag: 3. Objectifs du projet <a name = "objectifs_projet"></a>

- Apprendre à utiliser Java et Spring (initialisation d'une application Spring, utilisation de WebSocket, etc...) ;
- Apprendre à utiliser Angular (gestion des composants, système de routing, utilisation de bibliothèques Angular, etc...).

## :computer: 4. Technologies utilisées <a name = "technologies_utilisees"></a>

- Java
- Spring
- SQL
- HTML
- CSS / Sass
- JavaScript / Angular.js
- Git & GitHub

## :exclamation: 5. Prérequis <a name = "prerequis"></a>

- Pour le back un Java en version 17 ou plus ;
- Vous aurez aussi besoin de maven en version 3.9.8.

## :wrench: 6. Installation <a name = "installation"></a>

- Cloner ce repository ;
- Pour installer les différents packages, dans le terminal à partir du dossier front-end, exécuter npm install ;
- Pour installer les différents packages, dans le terminal à partir du dossier back-end, exécuter mvn lean install, ou dans intelliJ faites un build project ;
- Attention pour des soucis de confidentialité il n'y a pas de fichier application.properties, vous en aurez besoin pour démarrer le back-end, je vous met un exemple de application.properties.

## :question: 7. Utilisation du site web <a name = "utilisation_siteweb"></a>

- Dans le terminal à partir du dossier back-end, exécuter BackApplication présent dans src/main/java/com/chatop ;
- Dans le terminal à partir du dossier front-end, exécuter npm start ;
- L'application est disponible a l'adresse suivante : http://localhost:4200/
- L'API s'exécute sur le port 3001 ;
- La base de donnée est disponible dans le dossier back/src/main/resources.
- La base de donnée H2 est disponible à cette adresse : http://localhost:3001/h2-console
- Les identifiants pour la base de donnée H2 sont :
  Username : sa
  Password : password
- Il y a déjà deux comptes clients et services client insérer en base.
- Une fois sur l'application, vous pourrez sélectionner un des deux comptes clients, communiquer avec un des deux services support, et inversement.

## :beers: 8. Auteurs et Contributeurs <a name = "auteurs_contributeurs"></a>

Timoté Lancelle : [GitHub](https://github.com/LancelleTimote) / [LinkedIn](https://www.linkedin.com/in/timote-lancelle-devweb/)

## :page_with_curl: 9. Licence <a name = "licence"></a>

Distribué sous la licence MIT. Voir le fichier [LICENSE](LICENSE) pour plus d'informations.
