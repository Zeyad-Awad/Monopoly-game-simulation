# Monopoly Game Simulation

## Overview
This project is a sophisticated simulation of the classic board game **Monopoly**, designed to analyze the performance of various player strategies through extensive simulation trials. The core developed in Java uses Object-Oriented Design (OOD) principles, while the data analysis component utilizes Python to generate insights from the simulation results.

The project successfully confirmed findings from established literature regarding the probability of non-ending games in Monopoly, specifically validating the 12% non-ending game probability in 2-player scenarios as discussed in [ESTIMATING THE PROBABILITY THAT THE GAME OF MONOPOLY NEVER ENDS](https://www.informs-sim.org/wsc09papers/036.pdf).

## Contributers
1. **Zeyad Ahmed**
2. **Hashem Bader**

## Game Description
The simulation models a standard Monopoly game where 2 to 8 players compete to become the wealthiest player while bankrupting opponents. 
- **Start**: Players begin with $1500.
- **Goal**: Be the last player remaining with money.
- **Theme**: Locations have been customized with a Middle-Eastern theme.

## Strategies Implemented
The simulation compares several distinct player strategies:

1.  **Greedy**: A highly aggressive strategy that buys properties 70% of the time and builds houses 90% of the time.
2.  **Stingy**: A conservative strategy that rarely invests, buying properties only 20% of the time and building houses 10% of the time.
3.  **Default**: A balanced approach, buying properties 50% of the time and building 30% of the time.
4.  **Station Guy**: Prioritizes buying Stations (90% chance) over other properties (40% chance).
5.  **Utility Guy**: Prioritizes buying Utilities (90% chance) over other properties (40% chance).

## Key Findings
Our extensive simulations revealed several interesting insights:
-   **Greedy vs. Stingy**: The Greedy strategy consistently outperforms Stingy in 2-player games, winning approximately 58% of matches.
-   **Specialists**: "Station Guy" significantly outperforms "Utility Guy" in head-to-head match-ups (approx. 59% win rate).
-   **5-Player Chaos**: In a full 5-player game involving all strategies, the **Greedy** strategy emerged as the dominant winner, while the conservative **Stingy** strategy surprisingly accumulated the most wealth on average despite having the lowest win rate.
-   **Literature Confirmation**: Our simulation validated the academic finding that approximately 12% of 2-player Monopoly games using default strategies will practically never end.

For detailed charts, statistics, and analysis, please refer to the `REPORT.md` and the `dataAnalysis.ipynb` notebook.

## Project Structure
-   `src/`: Java source code for the game engine and simulation.
-   `test/`: JUnit 5 tests for the codebase.
-   `dataAnalysis.ipynb`: Python notebook for analyzing simulation results.
-   `data_unused/`: Contains generated CSV data from simulations.

## How to Run

### Prerequisites
-   **Java 17**
-   **Python 3** (for data analysis)
-   **Jupyter Notebook** (optional, to view/run the analysis notebook)

### Running the Simulation
The project is built using Gradle. You can run the tests to verify the simulation logic:

```bash
./gradlew test
```

To run the main simulation experiment (SimulationExperiment class):
```bash
./gradlew run
```
*Note: Ensure the main class in `build.gradle` is set to `SimulationExperiment` if `run` task is configured, or run via your IDE.*

### Running Data Analysis
The simulation exports results to CSV files. To analyze these results:
1.  Ensure you have the required Python libraries installed (pandas, matplotlib, etc.).
2.  Open `dataAnalysis.ipynb` in Jupyter Notebook or VS Code.
3.  Run the cells to generate statistics and visualizations.
