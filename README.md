# ClockSimulation

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)

A Java-based simulation program that models the simultaneous operation of multiple clocks, each with configurable drift rates, over a period of specified time.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Technical Details](#technical-details)
- [Version History](#version-history)
- [Contributing](#contributing)
- [License](#license)
- [Authors](#authors)
- [Acknowledgments](#acknowledgments)

## Overview

This application simulates a collection of clocks running simultaneously for a specified time period, where each clock drifts by a specified amount of time per second. The simulation allows users to observe and compare how different drift rates affect timekeeping accuracy over extended periods.

Originally developed as a final exam project for CS 131 - Data Structures, this program demonstrates object-oriented design principles, inheritance, polymorphism, and time-based calculations in Java.

## Features

- **Multiple Clock Simulation**: Create and manage multiple independent clock instances
- **Configurable Drift Rates**: Each clock can have a custom drift rate (time gained/lost per second)
- **Date & Time Tracking**: Full support for day, month, year, hours, minutes, and seconds
- **Leap Year Support**: Accurate calendar calculations including leap year handling
- **Interactive Menu System**: User-friendly console interface for clock management
- **Clock Operations**:
  - Create custom clocks with user-defined parameters
  - Reset all clocks simultaneously with an initial time or 00:00:00
  - Display synchronized time across all clocks
  - View comprehensive clock information in tabular format
- **Customizable Simulation Period**: Run simulations for any specified time duration

## Installation

1. **Clone the repository**:
   
   ```bash
   git clone https://github.com/salvadance/ClockSimulation.git
   cd ClockSimulation
   ```
1. **Navigate to the source directory**:
   
   ```bash
   cd ClockSimulation
   ```
1. **Compile the Java files preferably to a bin directory**:
   
   ```bash
   javac -d bin src\ClockProgram\*.java
   ```
   ----------------or-------------------------------
   
   ```bash
   javac *.java
   ```
   
1. **Run the application**:
   
    ```bash
    java -cp bin ClockProgram.ClockInterface
    ```
    -----------------or--------------------------
    
    ```bash
    java ClockProgram.ClockInterface
    ```

## Usage

### Starting the Application

Run the main class to start the interactive menu:

If created a bin:
    
```bash
java -cp bin ClockProgram.ClockInterface
```
-----------------or--------------------------

```bash
java ClockInterface
```

### Menu Options

Upon starting, you’ll be presented with the following menu:

1. **Create Clocks**: Define custom clocks with specific drift rates
1. **Reset Clocks**: Reset one or all clocks to their initial state or 00:00:00
1. **Display Clock Information**: View a table of all clock parameters
1. **Show Current Time**: Display synchronized times for all clocks
1. **Quit**: Exit the application

### Creating a Custom Clock

When creating a clock, you’ll be prompted to enter:

- Clock name
- Initial date (day, month, year)
- Initial time (hours, minutes, seconds)
- Drift rate (seconds gained/lost per real second)

### Example Workflow

```
1. Create 3 clocks with different drift rates
   - Clock A: No drift (perfect clock)
   - Clock B: Gains 0.001 seconds per second

2. Run simulation for specified period (e.g., one week, one month)

3. Compare final times to observe drift effects
```

## Project Structure

```
ClockSimulation/
├── src
|   ├── ClockProgram
│       ├── ClockInterface.java      # Main program interface and menu system
│       ├── ClockTime.java           # Time and date representation
│       ├── clockNames.java          # Clock implementations (concrete classes)
│       └── [Other supporting files]
│   
├── LICENSE                          # MIT License
└── README.md                        # This file
```

## Technical Details

### Key Classes

#### `ClockTime.java`

- Manages time representation (hours, minutes, seconds)
- Handles date functionality (day, month, year)
- Provides formatted string output for display
- Implements leap year calculations

#### `clockNames.java`

- Contains concrete clock implementations
- Implements `displayTime()` method with date functionality
- Provides `reset()` and `fullReset()` methods
- Supports custom clock configurations

#### `ClockInterface.java`

- Main entry point for the application
- Implements interactive menu system
- Handles user input validation
- Manages clock collection and operations
- Contains methods:
  - `createClocksMenu()`: Creates custom clock instances
  - `resetMenu()`: Reset functionality for clocks
  - `printClocksInfoTable()`: Displays clock parameters
  - `displayClocksTimes()`: Shows all clocks' times
  - `initializeClockList()`: Initializes clock collection

### Design Patterns

- **Object-Oriented Design**: Utilizes inheritance and polymorphism
- **Encapsulation**: Private fields with public accessor methods
- **Menu-Driven Interface**: User-friendly command-line interaction
- **Data Structures**: ArrayList for managing multiple clock instances

## Version History

### Version 13.25 (Current)

**Changes:**

- Added comprehensive date functionality (day, month, year tracking)
- Updated all constructors and methods to support date operations
- Added JavaDoc comments throughout codebase
- Enhanced documentation for all public methods
- Improved code readability and maintainability
- No logic changes from v12.25

**Release Date:** December 2, 2025

### Version 12.25

- Initial implementation of clock simulation
- Basic time tracking (hours, minutes, seconds)
- Drift rate calculations
- Interactive menu system

**Release Date:** November 20, 2025

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
1. Create a feature branch (`git checkout -b feature/AmazingFeature`)
1. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
1. Push to the branch (`git push origin feature/AmazingFeature`)
1. Open a Pull Request

### Coding Standards

- Follow Java naming conventions
- Include JavaDoc comments for all public methods
- Write clear, descriptive commit messages
- Test thoroughly before submitting

## License

This project is licensed under the MIT License - see the <LICENSE> file for details.

### MIT License Summary

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files, to deal in the Software without restriction, including the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software.

## Authors

- **Shelten Aguilar** - *Developer*
- **Salvador Lugo** - *Developer* - [@salvadance](https://github.com/salvadance)

## Acknowledgments

- **Course**: CS 131 - Data Structures
- **Project Type**: Final Exam Project
- **Exam Completion Date**: December 2, 2025

-----

## FAQ

**Q: How accurate is the drift simulation?**  
A: The simulation uses floating-point arithmetic to model drift over time. While suitable for educational purposes, it may accumulate small rounding errors over extended simulations.

**Q: Can I add more than the default number of clocks?**  
A: Yes, the program supports creating multiple custom clocks limited only by system memory.

**Q: What happens during leap years?**  
A: The program includes leap year detection and correctly handles 
        - February 29th in leap years.
        - Correct number of days until same date next year 
                - From February 29 next years date of March 1 is 366 days
                - If in a leap year and on or before February 28. 
                  (Ex. From January 1 on a leap year to Jan 1 next year is 366 days 
                 - If current year NOT A LEAP YEAR, current month after February, 
                    and next year IS A LEAP YEAR
                   (Ex. From April 1 to April 1 next year on a leap year is 366 days

**Q: Can I save and load clock configurations?**  
A: The current version does not support persistence. This feature may be added in future releases.

-----

## Support

For questions, issues, or suggestions:

- Open an issue on [GitHub Issues](https://github.com/salvadance/ClockSimulation/issues)
- Contact the developers through GitHub

-----

**Made with ☕ and ⏰ for CS 131**
