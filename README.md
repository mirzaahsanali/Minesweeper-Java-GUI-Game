# Minesweeper Game (Java Swing)

A classic Minesweeper game built in Java using Swing. This project demonstrates object-oriented programming concepts and provides a simple, interactive GUI for playing Minesweeper at different difficulty levels.

## Features

- **Intuitive GUI:** Custom background and styled buttons.
- **Difficulty Selection:** Easy, Medium, and Hard modes.
- **Pause/Stop Menu:** Resume, restart, or return to main menu.
- **Mine Reveal & Flagging:** Left-click to reveal, right-click to flag.
- **Win/Lose Detection:** Automatic game end on win or mine click.

## Folder Structure

```
src/
    Game/
        App.java
        createDB.java
        minesweeper.java
        StopMenu.java
        minesweeper.jpg
        StopMenu.java
README.md
```

## Getting Started

### Prerequisites

- Java JDK 8 or higher (I used JDK 24)
- Visual Studio Code (recommended) or any Java IDE

### Build & Run

```sh
javac -d bin src/Game/*.java
java -cp bin Game.App
```
Or simply use Extension pack for Java extensions in VS Code and run App.java with `Run Java` button in top right dropdown Run Button

## How to Play

- Launch the app and select **Play** or **Level** for difficulty.
- Left-click to reveal tiles, right-click to flag mines.
- Use the in-game menu to pause, restart, or exit.

## OOP Concepts Used

- **Abstraction:** Abstract class for game interface.
- **Inheritance & Polymorphism:** Game logic extends and overrides base methods.

## License

MIT License.

---

For questions or issues, open a GitHub issue.