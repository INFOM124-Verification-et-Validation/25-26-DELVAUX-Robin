# Specification-based Testing

## 1. Goal, inputs and outputs
- Goal: trouver la case d'Inky pour son prochain coup
- Input domain: les case de Inky, de Blinky et de Pac-Man, la direction du regard de Pac-Man et le plateau
- Output domain: ne pas bouger, les 4 cases autour de Inky

## 2. Explore the program (if needed)

## 3. Identify input and output partitions

### Input partitions

#### Individual inputs

- Blinky is, roughly, between Inky and Pac-Man
- Pac-Man, is, roughly, between Inky and Blinky

### Output partitions
- Empty direction
- A Direction

## 4. Identify boundaries
Not valid cases:
- Multiple Inky on the map
- Multiple Blinky on the map
- Multiple Pac-man on the map
- Inky is on Pac-man
- Blinky is on Pac-man
- Inky does not have a square partition
- Blinky does not have a square partition
- Pac-man does not have a square partition



## 5. Select test cases

- Inky behind Blinky and Pac-Man
- Pac-Man between Inky and Blinky

Boundaries:
- T10: Multiple Inky on the map
- T10: Multiple Blinky on the map
- T11: Multiple Pac-man on the map
- T12: Inky is on Pac-man
- ...