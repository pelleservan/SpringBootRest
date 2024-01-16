# Projet Spring Boot CRUD avec Thymeleaf

Ce projet est une application Spring Boot qui offre une API REST pour gérer les informations sur les équipes et les joueurs. L'application utilise une base de données H2 pour stocker les données et Thymeleaf pour créer une interface utilisateur simple.

## Configuration

- **Java:** Assurez-vous d'avoir Java installé sur votre machine. La version utilisée dans ce projet est Java 19.
- **Port:** L'application est configurée pour utiliser le port 8080 par défaut. Assurez-vous que ce port est disponible ou modifiez-le dans le fichier `application.properties` si nécessaire.

## Exécution du projet

1. Clonez le dépôt : `git clone https://github.com/votre-utilisateur/votre-projet.git`
2. Accédez au répertoire du projet : `cd votre-projet`
3. Exécutez l'application : `./mvnw spring-boot:run`

L'application sera accessible à l'adresse [http://localhost:8080](http://localhost:8080).

## API REST

L'API REST offre les fonctionnalités CRUD pour les entités "Team" et "Player". Voici quelques exemples d'utilisation :

- **Récupérer la liste des équipes :**
  ```bash
  curl http://localhost:8080/api/teams
