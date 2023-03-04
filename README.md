# Projet Programmation Orientée Objet : Tétris

Ce repo contient l'ensemble des fichiers nécessaires à la réalisation de votre projet de programmation.

## Objectif et rendu

### Objectif

Implantez un jeu de Tétris en suivant autant que possible les recommandations officielles du jeu pour le game play.

(Si vous ne connaissez pas le jeu tétris, jetez un œil à la page wikipedia et trouvez un jeu en ligne pour comprendre les règles de base)

Par ailleurs, on souhaite que le code soit organisé de façon modulaire, permettant d'implémenter des extensions (telles que différents mode de jeu, joueur dirigé par une intelligence articifielle, etc). 

La logique de l'organisation suivra une architecture de type Model View Controller telle que proposée dans ce sujet. 

### Groupes

Le projet est à réaliser par groupe de 2 ou 3 étudiant-es.

### Rendu

* Vous rendrez votre code sous forme d'un repo gitlab à partir d'un `fork` du repo principal (1 repo par groupe)
* Le code sera documenté en suivant les conventions du la Javadoc
* vous rendez aussi un rapport au format PDF (1 rapport par groupe) à déposer sur ecampus

###  Rapport

Le rapport doit faire entre 2 et 8 pages. Il doit contenir :

* La liste des membres du groupes
* Une introduction du sujet qui décrit rapidement le sujet et indique un résumé de ce qui a été réalisé
* Pour l'ensemble des packages réalisés : 
  * Une description de la structure de classe réalisée (qui implémente quoi, qui hérite de qui, etc)
  * Une description des choix d'implémentation 
  * Avez-vous implantés l'ensemble des méthodes proprosées par les interfaces ?
  * Les tests proposés passent-ils ?
  * Avez-vous modifié ou augmenté les interfaces / classes proposées ?
  * Avez-vous rajouté des tests ?
* Enfin une conclusion :
  * Quelles seraient les prochaines étapes pour améliorer / étendre votre projet
  * Quels aspects du projet vous ont le plus intéressé / plu ?
  * Quels aspects vous ont posé des difficultés ?
  
### Tests

De nombreux tests vous sont proposés pour tous les packages qui ne dépendent pas de l'interface graphique. Si vous implantez plus que le projet de base, vous pouvez (devez !) rajouter des tests (par exemple pour l'AI ou pour des modes de jeu supplémentaire).

## Le Projet

### Les packages

Le projet est organisé en 3 packages principaux (en plus des tests) dans `fr.upsaclay.bibs.tetris`  : `control` `model` et `view`. Les packages `control` et `model` sont par ailleurs organisés en plusieurs sous packages. Vous trouverez dans ces packages de nombreuses interfaces ainsi que quelques classes qui vous sont fournies. Tout le code fourni est documenté avec des explications spécifiques sur les implémetations attendues.

### Le package `model`

C'est le **premier** qu'il faudra réaliser : c'est lui qui contient la structure interne du jeu.

#### `TetrominoShape` et `Tetromino` dans  le package `model.tetromino`

La première étape du projet est de compléter la classe `TetrominoShape` pour que les différentes formes de tetromino possibles renvoient les objets tétromino qui implémentent l'interface proposée "Tetromino".

Concrètement, un tétromino est un tableau à 2 dimensions qui contient des `TetrisCell`. Les tétrominos sont identifiés par leur forme (I, J, L, T, O, S, Z suivant la nomenclature du jeu tétris) et leur "numéro de roation" (nombre de rotations droites à effectuer depuis la forme initiale). Au cours du jeu, les tétrominos ne sont créés QUE par `TetrominoShape`. Concrètement on écrit :

    TetrominoShape.JSHAPE.getTetromino(2)
    
Pour récupérer le Tétromino de forme "J" tourné 2 fois. 

**Implantation suggérée :** calculer les différentes rotations possibles du tableau dans le constructeur de `TetrominoShape` et créer une fois pour toute l'ensemble des tétrominos d'une forme donnée. Vous pouvez créer une classe `TetrominoImpl` qui implémente l'interface `Tetromino` en lui donnant une visibilité de package ou même comme classe interne de `TetrominoShape`.

Vous pouvez laisser les méthodes relatives aux `wallKicks` pour plus tard, on les expliquera dans la section sur les rotations sur la grille.

Des tests pour l'ensemble des méthodes de `TetrominoShape` et `Tetromino` sont disponibles dans le package `test`

#### `TetrominoProvider`  dans le package `model.tetromino`

Cette interface sert à "fournir" les tétrominos au reste du jeu. Son implantation n'est pas nécessaires pour les autres sous-packages du model et peut donc se faire en parallèle de la suite.

Lisez la documentation et les tests fournis. On vous demande deux implantations de l'interface :

* à partir d'une liste fixée
* en tirant les tétrominos au hasard

La première est plus simple à implanter et c'est elle qui est utilisée dans la plupart des tests. Mais la version au hasard est celle qui est nécessaire pour le jeu !

#### Les interfaces `TetrisGrid` et `TetrisGridView` de `model.grid`

Ce sont les deux interfaces qui gèrent la grille de jeu elle-même. On peut voir la grille comme un tableau à deux dimensions de `TetrisCell` qui possèdent en plus un tétromino qui peut se déplacer sur la grille. 

`TetrisGrid` est une sous-interface de `TetrisGridView` : cela signifique vous devez implanter une seule classe qui implémente toutes les méthodes à la fois de `TetrisGridView` et `TetrisGrid`. 

Note : on vous fourni une classe `SynchronizedView` qui implémente `TetrisGridView` : c'est pour une raison d'encapsulation et d'accès aux différentes méthodes. Vous n'avez rien à faire de spécial avec cette classe. Votre implémentation de `TetrisGrid` ne doit surtout pas en hériter !

De nombreux tests sont proposés pour `TetrisGrid` dans le package de test.

#### Rotation des tétrominos et wall kicks

Les tétromninos ne prennent pas toute la place dans leur "boite" : il y a non seulement des cases vides mais même des lignes et colonnes vides. La boite du tétromino peut donc "dépasser" sur les bords de la grille de jeu. Lorsque l'on tourne le tétromino, on peut alors rencontrer un conflit. Ainsi, il est en théorie impossible de tourner le I s'il se trouve complètement contre le bord de la grille. 

Pour palier à ce problème, on recommande d'utiliser des "wall kicks" : c'est un ensemble de mouvements à effectuer après la rotation s'il y a un conflit. Vous trouverez [des explications détaillées ici](https://tetris.wiki/Super_Rotation_System). C'est la raison d'être des méthodes `wallKicksFromRight` et `wallKicksFromLeft` de tetromino. 

On propose des tests spécifiques pour ces deux méthodes ainsi qu'un ensemble complet de tests pour les rotations nécessitant des wall kicks. Cependant, les wall kicks ne sont pas nécessaires au fonctionnement général du jeu (c'est juste que votre jeu sera moins bien). 

#### ScoreComputer dans `model.score`

Cette interface est nécessaire au calcul du score pendant le déroulement du jeu. Elle sera appelée par le "controler" que nous décrivons après. Le principe est le suivant : le controler "enregistre" les différentes actions de jeu dans le `scoreComputer` qui s'occuper de maintenir à jour les différents paramètres tels que le score, le nombre de lignes, le niveau, etc.

Le calcul du score dépend du mode de jeu. Les tests se basent sur une version classique comme [décrite ici](https://tetris.wiki/Scoring) sans les T-Spins, les "difficults", ni les "back to back". (Vous pouvez bien sur implantez d'autres modes de jeu et de score ou compléter le mode existant et rajouter des tests).

Note : il est nécessaire d'avoir une classe implantant `ScoreComputer` pour la suite. Cependant, elle n'a pas besoin d'être entièrement fonctionnelle. Un membre du groupe peut s'occuper de faire fonctionner `ScoreComputer` (avec les tests) pendant que d'autres travaillent sur les autres classes (du model ou du controller).

### Le package `control`

Pour commencer à travailler sur ce package, il faut une version (même limitée) des classes du model. Les classes de ce package servent à gérer la configuration et le déroulement du jeu.

#### Les interfaces `GameManager` et `GamePlayer`

Il y a deux interfaces : `GameManager` dans `control.manager` et `GamePlayer` dans `control.player` : leurs rôles sont les suivants :

* `GameManager` s'occupe de la configuration générale du jeu, du lancement des parties, etc.
* `GamePlayer` s'occupe du déroulement d'une partie donnée

On crée un `GameManager`  à partir de la méthode statiue définie dans l'interface. Ensuite, c'est le `GameManager` qui s'occupera de créer et initialiser le `GamePlayer` au moment voulu et en fonction des différents paramètres.

#### Versions Simples et versions Graphiques

On peut créer deux types de `GameManager` : 'SIMPLE' ou 'VISUAL'. La version 'SIMPLE' correspond à un mode de jeu non graphique, elle sert surtout à implémenter toute la partie "logique" des méthodes qui interfacent avec le modèle mais pas avec la fenêtre graphique. C'est ce qui nous servira pour les tests en particulier. 

De la même façon, il y aura deux implémentations de `GamePlayer` : c'est le `GameManager` qui va le créer typiquement en instanciant des classes différentes en fonction du mode simple ou visual.

**Architecture suggérée** : 

* Une classe `AbstractGameManager` dont héritent deux sous classes : `SimpleGameManager` et `VisualGameManager`. Ces classes stockent des instances de `GamePlayer` avec la bonne classe et implémentent les méthodes `createPlayer` et `getPlayer`.
* Une classe `SimpleGamePlayer` qui contient toute la logique du jeu et l'interface avec le modèle et une classe `VisualGamePlayer` qui hérite de `SimpleGamePlayer` avec en sus l'interfaçage graphique.

Des tests sont proposés pour les versions simples de `GameManager` et `GamePlayer`. 

### Le package `view`

Ce package contient les interfaces liées la partie graphique du jeu : celle qui va nous permettre de jouer pour de vrai. L'interface graphique et la gestion des évènements graphiques est forcément dépendante du choix de la bibliothèque graphique (ici Swing). Cependant, on cherche à conserver au maximum la modularité. C'est pour cela que l'on définit plusieurs interfaces :

* `GameViewPanel` est l'interface des fenêtres et différents panneaux du jeux en charge de la gestions des différentes "vues". Elle contient une fonction d’initialisation et 4 fonctions de dessins pour les différentes phases de jeu.
* `GameFrame` qui est une sous-interface de `GameViewPanel` : elle représente la fenêtre principale, l'équivalent côté "view" du `GameManager`. Elle a donc les méthdoes d'affichage de `GamePanel` et des méthodes supplémentaires qui permettent de gérer les besoins du `GameManager` : en particulier pour spécifier les "listener" qui vont répondre aux actions de l'utilisateur.
* `GamePanel` qui est une sous-interface de `GameViewPanel` : elle représente le panneaux qui contiendra le jeu en lui même : à la fois la grille de jeu et les informations auxiliaire comme le score, le niveau, les tétrominos à venir etc. C'est l'équivalent côté "view" du `GamePlayer`. Elle contient différentes méthodes de mise à jour des informations : en particulier, elle reçoit une "vue" du model avec `TetrisGridView`. Elle définit aussi des méthodes de gestions d'évènements : à la fois pour envoyer des évènements au contrôleur (à travers les listener) et pour recevoir des évènements envoyés par le contrôleur. Cela permet à l'interface de "réagir" à certains évnements du jeu (par exemple émettre un son quand une ligne est validée).
* `ManagerComponent` est une interface pour tous les composants d'interactions de type boutons, menus, etc. Elle définit les besoins du contrôleur qui pourra donc gérer les évènements sans s'occuper de savoir si la source vient d'un `JButton` ou d'un `JMenu` . Un `ManagerComponent` contient une `ManagerAction` (une action de configuration de type "start" ou "pause") et la possibilité de récupérer un fichier. 

Ces interfaces sont des **suggestions** : il est possible de créer un jeu entièrement fonctionnel (et très sympa) en les utilisant. Cependant, vous pouvez les étendre ou les modifier en fonction de l’implémentation que vous décidez, tout en cherchant à limiter les incursions du contrôleur dans la logique interne des composants `Swing`. Vous expliquerez vos choix dans le rapport.

**Architecture suggérée**

* une classe `GameFrameImpl` qui hérite de `JFrame` et implémente `GameFrame` : cette classe gère le dessin général de l'application et contient des liens vers l'ensemble des `JPanel` utilisés pour dessiner le jeu ainsi que vers tous les boutons et menus de configuration. Tous les éléments sont créés au départ (à (l'initialisation et dans la méthode `createManagerComponents`) et les différentes fonction de dessin `drawManagementView` `drawGamePlayView` etc. s'occupent simplement de les rendre visible / invisible.

* une classe `GamePanelImpl` qui implémente `GamePanel` : cette classe contient des liens vers les différents éléments de jeu (la grille, le score, le tétromino sauvegardé etc.) : tous ces éléments seront soit des instances des éléments graphiques de Swing (par exemple `JLabel` pour un affichage), soit des classes spécifiques qui héritent des éléments de Swing. Ainsi on pourra créer une classe `GameGrid` qui hérite de `JPanel` et affiche la grille de jeu. La classe `GamePanelImpl` peut soit elle-même hériter de `JPanel`, soit simplement contenir les liens vers les éléments concernés (ça dépend de comment vous dessinez votre jeu !)

* Différentes classes `ManagerButton` `ManagerMenu` etc. qui héritent des éléments `Swing` correspondants et implémentent la classe `ManagerComponent`

**Fonctionnement suggéré**

Vos implémentations des contrôleurs `GameManager` et `GamePlayer` en mode visuelles peuvent directement implémenter les classes `ActionListener` et `KeyListener` et ainsi gérer directement les évènements venant de l'interface graphique. Par exemple, le `GameManager` est un `ActionListener` qui écoute tous les `ManagerComponent`: quand il recoit un évènement, il récupère l'action correspondante et agit en conséquence. 

La boucle de jeu peut être gérée avec un objet `javax.swing.Timer` "écoutée" par le `GamePlayer`.

**Limite de la modularité**

L'architecture et le fonctionnement proposé ne sont pas parfaitement modulaire car les versions graphiques du contrôleur dépendent des Listener Swing. On pourrait modifier les interfaces pour faire en sorte que la `View` garde un pointeur vers le contrôleur et s'occupe d'écouter les évènements et de notifier le contrôleur. Mais on peut aussi préférer que la view soit justement indépendante du contrôleur...

Chercher une parfaite modularité est un peu un "overkill" : l'interfaçage entre le contrôleur et le graphique sera toujours dépendant de la bibliothèque choisie dans une certaine mesure. Et les choix d'architecture sont souvent au moins influencés par le système sous-jacent (ici le fonctionnement de Swing avec les events listener).

### Fonction main et lancement du jeu

La fonction `main` se trouve dans la classe `Tetris` du package `fr.upsaclay.bibs.tetris`. Vous n'en avez pas beaucoup besoin pendant le développement sauf si vous voulez tester différentes choses ou débugguer en plus des tests de JUnit.

Quand tout est fait, elle peut ne contenir qu'une ligne unique pour lancer le jeu :

    SwingUtilities.invokeLater(()-> GameManager.getGameManager(GameType.VISUAL).initialize());
    
Si besoin, vous pouvez l'étoffer (par exemple si vous voulez laisser la possibilité de faire autre chose que le lancement graphique du jeu).






