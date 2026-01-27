# Zoo Game

### TUI game application about maintaining the zoo ### 

**Documentation Overview**: 

1. [Description](#1-description)
2. [Requirements](#2-requirements)
3. [Future Features](#3-future-features)

## 1. Description

**Zoo Game** lets you become a manager of the zoo starting a small business with limited finances. As the head of the establishment, player can make a decision about buying an animal, a domain for animal to live in and a foodpack for one as well. 

The game is divided into 3 phases - morning, day and evening, during which animals have to be fed and some don't have to. Player can only purchase domains and animals before opening zoo and after closing it, and receives income after every evening stage.

There are 4 types of animals currently - mammals (aggressive and passive), birds, reptiles and insects, each one with unique trait that depend on proper feeding the animal. Such traits mostly depend on the total income of the day but can decrease amount of visitors (aggressive mammals' trait). Animals also have different sizes and can be placed only in domains of the same size with their kins that have some free places left.

**Admin Mode**:

Admin mode lets you interact with the shop that players buy everything from - change prices and characteristics of food packs, domains and animals. It is possible to freely change beetween player and admin mode.

## 2. Requirements ##

- Java JDK 21
- Gradle (wrapper included)
- Any IDE (IntelliJ Idea recommended)


## 3. Future features of the project

The main priority is to add interaction with MySQL database to the project to enable saving progress for player and allow multiple players on the same device.

It also would be convenient to containerize application using Docker.

