# Decomp Trainer Editor+
DTE allows you to modify every trainer's properties (and there are around 850 of them), as well as their Pokémon parties dynamically. What does this mean? You can add new items, Pokémon or moves to your repo and the tool shouldn't have any problem loading them, as long as you keep the few things that the tool uses as a base for loading everything. This will be further explained later.

This tool has been modified to support pokeemerald-expansion tested on [this](https://github.com/rh-hideout/pokeemerald-expansion/tree/0ac203f2e50de8ae24d8e8e603b60b469f8be007) commit.


# How to compile / run
Java is needed to run the program. Download from [release](https://github.com/geefuoco/Decomp-Trainer-Editor/releases/tag/v1.0.0)

## Windows
Run the .bat file in `/bin`. JAVA_HOME Variable needs to be set in order to run it.

## Linux
Run the app file in `/bin`



## Functionalities

***NOTE***
Currently Z-Moves are not supported in the program


![Trainer list](https://i.imgur.com/mg6ORE4.png)

Trainer searching: Shown trainers are filtered from text in the field below the list

![General trainer editing](https://i.imgur.com/EAP3Hfe.png)![enter image description here](https://i.imgur.com/bJQDbaP.png)

Trainer editing:
 - Names: Named displayed ingame (no internal name editing as of yet)
 - Trainer class: Changes the class text shown ingame and victory money gains
 - Toggle double battles
 - Items
 - Trainer pic: Changes the image displayed ingame
 - Gender
 - Music
 - AI:  Changes the trainer's behaviour. Includes some not targeted towards trainers.

Party editing:
 - Addition and deletion of party members: Up to a maximum of `PARTY_MAX` and a minimum of 1
 - Party member order
 - General IV count
 - Level
 - Species
 - Held item
 - Moves: If one of the Pokémon in the party has custom moves, it is required that the rest have them too or otherwise their moveset will be empty
 - Nature
 - Pokeball
 - Shiny
 - Ability: Only abilities that the pokemon can have are valid. (i.e. Setting intimidate on pikachu wont work)
 - Friendship
