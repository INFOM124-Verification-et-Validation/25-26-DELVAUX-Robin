# Specification-based Testing

## 1. Goal, inputs and outputs
- Goal: trouver la prochaine direction de Clyde
- Input domain:map, position de Clyde, position du joueur
- Output domain:la Direction de Clyde

## 2. Explore the program (if needed)

## 3. Identify input and output partitions

### Input partitions

#### Individual inputs
Distance partition (plus importante):
    -   1: Clyde est à moins de 8 cases de Pac-man
    -   2: Clyde est à plus de 8 cases du Pac-man
    -   3: Clyde est à 8 cases du Pac-man
Pac-man pas sur le plateau
Pas-man sur le bord du plateau
Pac-man n'a pas de case
Obstacle direction partition (2eme plus importante):
    -   1: Clyde peut bouger
    -   2: Le chemin de Clyde est libre
    -   3: Clyde est sur Pac-man
    -   4: Plusieurs choix de mouvements équivalents
Not valid maps:
    - Plusieurs Clyde sur la map
    - Plusieurs Pac-man sur la map


#### Combinations of input values
- Distance < 8 et chemin bloqué
- Distance < 8 et chemin libre
- Distance < 8 et plusieurs chemins possibles

- Distance > 8 et chemin bloqué 
- Distance > 8 et chemin libre 
- Distance > 8 et plusieurs chemins possibles

- Distance = 8 et chemin bloqué
- Distance = 8 et chemin libre
- Distance = 8 et plusieurs chemins possibles

### Output partitions
- Empty direction
- Direction vers Pac-man
- Direction pour fuire Pac-man

## 4. Identify boundaries
Not valid cases:
- Plusieurs Clyde sur la map
- Plusieurs Pac-man sur la map
- Clyde est sur Pac-man
- Pac-man n'est pas sur la map
- Pac-man n'a pas de case
Pac-man au bord du plateau
Pac-man traverse les murs

## 5. Select test cases
On fait toutes les 9 combinations trouvées:
Distance < 8: 
- T1: Path free => s'éloigner du Pac-man
- T2: Path blocked => Empty Direction
- T3: Multiple moves => s'éloigner du Pac-man

Tester les cas limites tous seuls