# BowlingFX
## Version: 1.0.0
Simple bowling game made as semestral project at University of Las Palmas de Gran Canaria (ULPGC)

## Instalation
Download the repository and in root folder execute:
```
mvn clean install
```
In */target* folder execute the generated .jar file:
```
java -jar Bowling-1.0.0.jar
```
Program uses embedded M2 database, initial data can be found and edited in */src/main/resources/data.sql*

## Usage 
###Playing
After start of GUI you can choose which bowling bar to choose, there are implemented two of them:
- Duck bowling bar
- Lucky Strike

After choosing bowling bar you will see the list of lines with currently played games. When line has a game in progress, you can decide to continue in that game.
If game does not have game in progress, you can start a new one. You can decide the name of the game and number of players. Then fill the names of the players.

After starting new game or continuing game in progress, you will se the table of players with frames and scores.
You will see the detail of each frame, score in frame and total score of player. Also you will see the total score of the game.

In the bottom part, you can do the rolling. Choose the number of pins you want to roll or let it be random.

After end of the game, you can see the game in *Leaderboards* and *Top games* tables.

###Leaderboards
On left part of window click the *Leaderboards* button and see the list of players and their score.

###Top games
On left part of window click the *Top games* button and see the list of games. When clicked on the *Statistics* button for each game you can see the list of players and their score in the game.

## Authors
Petr Lukašík

David Bohmann

This project has been created in Java 8 and JavaFX. It is based on spring framework that is used for MVC architecture. 

## Changelog
1.0.0. First stable release