
# HackZone – Plateforme d’apprentissage de la cybersécurité

HackZone est une application web éducative inspirée de Root-Me. Elle permet aux étudiants de pratiquer le hacking éthique dans un environnement sécurisé, à travers des challenges interactifs, des niveaux progressifs et un système complet de gamification.

## 1. Objectifs du projet

- Proposer une plateforme ludique pour apprendre la cybersécurité.  
- Permettre la pratique d’attaques éthiques dans un contexte contrôlé.  
- Offrir une expérience pédagogique comparable aux CTF (Capture The Flag).  
- Accompagner la progression des utilisateurs grâce aux niveaux, points, badges et classements.

## 2. Fonctionnalités principales

### 2.1. Authentification et gestion des comptes
- Création de compte  
- Connexion sécurisée  
- Authentification via JWT (JSON Web Token)  
- Protection des pages et opérations sensibles  

### 2.2. Profil utilisateur
Le profil regroupe les informations suivantes :  
- Nom d’utilisateur  
- Email  
- Niveau (Débutant, Intermédiaire, Avancé)  
- Points cumulés  
- Classement  
- Badges obtenus  
- Historique des challenges réussis  

### 2.3. Système de niveaux
Le niveau est choisi à l’inscription :  
- Débutant  
- Intermédiaire  
- Avancé  

Le niveau défini :  
- La difficulté des challenges affichés  
- Le nombre de points obtenus après une réussite  

### 2.4. Challenges de cybersécurité

Les types d’attaques proposés incluent :  
- Injection SQL  
- XSS / CSRF  
- Brute Force  
- OSINT / Reconnaissance  
- Analyse réseau  
- Sécurité système  

Chaque challenge suit un déroulement structuré :

#### Étape 1 : Sélection de l’attaque  
L’utilisateur choisit la catégorie du challenge selon son niveau.

#### Étape 2 : Page d’instructions  
La page contient :  
- Description du challenge  
- Objectifs  
- Un champ pour saisir une commande ou un code  
- Un bouton « Démarrer »

#### Étape 3 : Simulation de l’attaque  
L’utilisateur accède à une application vulnérable simulée afin d'identifier une faille et d’obtenir un code de validation.

#### Étape 4 : Validation du challenge  
En entrant le code correct, l’utilisateur :  
- Valide le challenge  
- Gagne des points  
- Progresse dans le classement  
- Avance vers l’obtention d’un badge  

---

## 3. Gamification

### 3.1. Points  
Points attribués selon la difficulté et la réussite des challenges.

### 3.2. Badges  
Un badge est attribué lorsqu’une catégorie complète de challenges est terminée.  
Les badges ont une valeur supérieure aux niveaux et apparaissent en priorité dans le profil.

### 3.3. Classement  
Classement global mis à jour selon :  
- Points cumulés  
- Badges obtenus  
- Progression dans les challenges  

---

## 4. Architecture et workflow

### 4.1. Technologies utilisées

**Backend :**  
- Java 17  
- Spring Boot  
- Spring MVC  
- Spring Data JPA  
- Spring Security + JWT  
- MySQL  

**Frontend :**  
- HTML5  
- CSS3 (thème sombre)  
- Thymeleaf  

### 4.2. Structure du projet
src/
├── main/java/com.uphf.HackZone/
│    ├── Controller/
│    ├── Entity/
│    ├── Security/
│    ├── Repository/
│    └── Service/
│
└── main/resources/
├── templates/
└── application.properties


### 4.3. Fonctionnement de l’authentification JWT

1. L’utilisateur saisit son email et son mot de passe.  
2. Le serveur vérifie les identifiants.  
3. Si les informations sont correctes, un token JWT signé est généré.  
4. Le token est stocké côté client.  
5. Les pages protégées exigent un token valide.  
6. Spring Security vérifie le token avant chaque accès sensible.  

---

## 5. État actuel du projet

Fonctionnalités implémentées :  
- Page de connexion  
- Authentification JWT  
- Gestion des utilisateurs  
- Page d’accueil  
- Architecture backend  

Fonctionnalités en cours :  
- Pages des challenges  
- Validation des codes  
- Profil utilisateur dynamique  
- Classement et badges  

---

## 6. Améliorations prévues

- Ajout de nouvelles catégories d’attaques  
- Tableau de bord utilisateur avancé  
- Mode compétitif entre utilisateurs  
- Interface administrateur  
- API REST externe  
- Passage vers un frontend moderne (React, Angular, Next.js)  

---

## 7. Auteur

Projet développé par  
**Senthooran Thayaparan** 
**et**
**Ibrahim Kamisokko**
