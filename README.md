# LeagueOfWarriors

# Game Project - Java Implementation

## Overview
This project is a Java-based game featuring an authentication system, a procedurally generated map, a strategic combat system, and a graphical user interface (GUI). The game is built using Java Swing for the frontend and follows object-oriented programming (OOP) principles, integrating various design patterns to ensure modularity and scalability.



## Setup & Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/david08mihai/league-of-warriors.git
   cd league-of-warriors
2. Open the project in IntelliJ IDEA
3. Add the JSON library:
   - Go to File -> Project Structure -> Libraries
   - Click + -> Java, then select `json-simple-1.1.1.jar` from the project root
   - Apply and close
4. Run the game
      - Open `src/game/ui/GameUI.java`
5. Login
   - Use one of the accounts defined in `src/game/accounts/accounts.json`
   - An easy account would be:
     * `email: test`
     * `password: 1234`

## Features

### 1. Authentication and Account System
- Users authenticate using an email and password.
- Each account can have multiple characters, each with unique abilities, statistics, and levels.
- Character selection is performed through the GUI.

### 2. Procedurally Generated Map Exploration
- The map consists of different types of cells: sanctuaries, enemies, portals, and open spaces.
- Each playthrough generates a new map for a fresh experience.
- Movement is handled through the graphical interface.

### 3. Strategic Combat System
- Battles occur between the player's character and an enemy.
- Players can choose between simple attacks and using special abilities.
- Different character classes (Warrior, Mage, Rogue) have distinct damage formulas.
- Each enemy has elemental resistances (water, fire, earth) and randomized health/mana values.
- Combat mechanics ensure balanced difficulty, with enemies having a chance to evade attacks.
- Winning a battle restores mana, health, and regenerates abilities while increasing experience.

### 4. Regeneration and Progression
- Sanctuaries act as healing points for health and mana recovery.
- Portals allow transitions between different regions, generating a new map.
- A brief waiting time is introduced during map generation to enhance immersion.

## Graphical User Interface (GUI) Implementation
The game's frontend was implemented using Java Swing, focusing on an intuitive layout and smooth interaction with the backend.

### Implementation Details
- The main game window dynamically updates based on user actions without opening multiple windows.
- A dedicated class manages GUI transitions between different game phases (authentication, grid exploration, combat, abilities, and game conclusion).
- Messages and errors are displayed in real-time to ensure a seamless experience.

### Key Design Patterns Used
1. **Event Handling:**
   - ActionListeners manage button clicks and other UI interactions.
   - Separating logic from UI improves maintainability and debugging.

2. **Multithreading:**
   - A separate thread continuously listens for user interactions and processes commands.
   - This ensures smooth UI performance without delays.

3. **Error Management:**
   - A dedicated class handles errors and displays relevant messages in the GUI.
   - Users receive clear feedback when issues occur.

4. **Design Patterns:**
   - **Visitor Pattern:** Simplifies action management, making it easy to extend functionality.
   - **Singleton Pattern:** Ensures a single instance for global game configuration and shared resources.

5. **Window Scaling Optimization:**
   - The interface adapts dynamically to different window sizes, including full-screen mode.
   - This ensures an optimal user experience on various screen resolutions.

## Conclusion
This project applies advanced Java programming concepts, design patterns, and GUI development techniques. The integration of backend and frontend ensures a fully interactive experience, making the game both engaging and structurally well-designed.
