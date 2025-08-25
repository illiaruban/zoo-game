# Zoo Manager (WIP)

Manage a virtual zoo as a **player** or **admin**: buy and sell animals, build domains (habitats), purchase food, and tune game data. This is a Java console project currently under active development.

> **Status:** 🚧 Work in progress — core Player actions are partially implemented. Upcoming: Unit Tests, MySQL persistence.

---

## Table of Contents

* [Features](#features)
* [Gameplay Loop](#gameplay-loop)
* [Tech Stack](#tech-stack)
* [Project Structure](#project-structure)
* [API Reference](#api-reference)
* [Getting Started](#getting-started)

  * [Prerequisites](#prerequisites)
  * [Build & Run (Gradle)](#build--run-gradle)
  * [Build & Run (Maven)](#build--run-maven)
* [Usage](#usage)
* [Roadmap](#roadmap)
* [Contributing](#contributing)
* [License](#license)

---

## Features

* 🎮 **Player mode**

  * Buy / sell animals
  * Buy / sell **domains** (habitats) required for animals to live
  * Buy **food packs** and keep animals fed
* 🧩 **Animal types**: 4 (Mammal, Bird, Insect, Reptile), each with **unique behaviors**
* 🛠️ **Admin mode**

  * Create custom animals
  * Edit animal attributes
  * Change domain prices
* 🧪 **Planned**: unit testing, saving/loading via **MySQL**

## Gameplay Loop

1. Start the game and choose **Player** or **Admin** mode.
2. As **Player**: acquire domains, buy animals compatible with those domains, manage food inventory, and balance income vs. upkeep.
3. As **Admin**: tweak prices and parameters, or add new animals to the catalog.

## Tech Stack

* **Language:** Java (>= 17 recommended)
* **Build:** Gradle or Maven
* **CLI:** Console-based interface
* **Planned persistence:** MySQL
* **Testing:** JUnit (planned)

## Project Structure

```
zoogame/
├─ animals/
│  ├─ Animal.java           # abstract base class
│  ├─ AnimalType.java       # enum of MAMMAL, BIRD, INSECT, REPTILE
│  ├─ Bird.java             # subclass with feather bonus/penalty logic
│  ├─ Insect.java           # subclass with starvation → death
│  ├─ Mammal.java           # subclass with aggression mechanic
│  ├─ Reptile.java          # subclass with cannibalism mechanic
│  └─ SizeClass.java        # enum for TINY/SMALL/MEDIUM/LARGE/etc.
│
├─ domains/
│  ├─ Domain.java           # base habitat class
│  ├─ InsectDomain.java     # specialized domain for insects
│  └─ ReptileDomain.java    # specialized domain for reptiles
│
├─ exceptions/              # custom exceptions (validation, gameplay)
│
├─ factories/
│  └─ AnimalFactory.java    # factory to create animals from user input
│
├─ users/
│  ├─ Admin.java            # admin menu: CRUD for animals/domains
│  ├─ Player.java           # player menu: buy/sell/feed
│  ├─ AnimalFoodPack.java   # food items to feed animals
│  ├─ InputReader.java      # utility for validated console input
│  ├─ Shop.java             # catalog of available animals/domains
│  └─ Zoo.java              # container of domains/animals
│
└─ Main.java                # program entry point
```

---

## API Reference

### `zoogame.animals.Animal` (abstract)

Base class for all animals. Encapsulates pricing, habitat constraints, feeding schedule and income logic.

* **Subclasses:** `Mammal`, `Bird`, `Insect`, `Reptile`
* **Key fields:** `name`, `price`, `animalType`, `sizeClass`, `maxAmountInDomain`, `timesToFeedPerDay`, `fedPerDay`, `fullIncome`
* **Key methods:**

  * `feed(String timeOfDay)` — increment feeding counter
  * `getIncome()` — abstract; overridden with type-specific rules
  * `endDay()` — reset feeding counter

### `zoogame.users.Admin`

Provides console-based admin menu for managing the shop.

* **Features:** add/delete animals, edit domain prices, update animal info, print lists.
* **Uses:** `InputReader` for validation, `AnimalFactory` for creating animals.
* **Planned:** `loadData()` / `updateData()` hooks for persistence.

### `zoogame.users.Player`

Handles the player’s role:

* Buy/sell animals and domains
* Buy food packs and feed animals
* Track money, income, upkeep
* (Planned) integration with Zoo state and MySQL persistence

### `zoogame.users.Shop`

Central registry of animals/domains available for purchase. Supports printing, adding, and removing.

### `zoogame.users.Zoo`

Represents the player’s zoo — manages owned domains and their animals.

### `zoogame.domains.Domain`

Represents a habitat for animals of a single type/species. Handles pricing, size, and listing of residents.

* **Fields:** `nameOfDomain`, `animals`, `sizeClass`, `price`
* **Constructors:** default (empty), with price+size, copy constructor (deep copies animals)
* **Key methods:**

  * `addAnimal(Animal)` / `takeAnimal(Animal)` — manage residents
  * `getTypeOfDomain()` — returns the domain’s animal type (or null if empty)
  * `closeDay()` — sum daily income and reset feeding counters
  * `getListUnfedAnimals(String)` — count unfed animals for morning/day/evening
  * `toString(String command)` — formatted view for zoo/shop listings

Specialized subclasses (`InsectDomain`, `ReptileDomain`) prefix their type in the `toString` output.---

### `zoogame.users.AnimalFoodPack`

Represents food items that players can purchase to feed their animals.

### `zoogame.factories.AnimalFactory`

Factory pattern for creating new `Animal` instances based on type and validated parameters.

### `zoogame.users.InputReader`

Centralized console input utility with validation methods:

* `readName`, `readPrice`, `readIncome`, `readPositiveInteger`, `readAnimalType`, `readSizeClass`

### `Main`

Entry point of the game. Presents initial menu for selecting **Player** or **Admin** mode.

---

## Getting Started

### Prerequisites

* Java **17+** installed (`java -version`)
* A terminal/command prompt

> **Note:** Player actions are under development; some menu items may be placeholders.

## Roadmap

* [ ] Implement remaining Player actions
* [ ] Add robust input validation & error handling
* [ ] Persist data to **MySQL** (JDBC / JPA — TBD)
* [ ] Domain & Animal compatibility rules
* [ ] Economic balance (prices, income, upkeep)
* [ ] **JUnit** test suite & CI
* [ ] Save/Load game states
* [ ] Documentation: per-class API reference

## Contributing

Pull requests are welcome. Please describe changes clearly and add tests where applicable.

## License

MIT — feel free to use and modify. Add your `LICENSE` file if missing.



