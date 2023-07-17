# SekCore Library

This mod is a library mod used to help speed up dev and provide helpful common functions primarily used across Sekwah's mods.

Fabric support is not currently planned at this time though once more features have been added it may be considered.

## Features
 * Capability @Sync annotations
 * 
## Utilities
Not fully fledged features but just small utilities in the com.sekwah.util package I use between my mods.
Not all are mentioned here but I will mention the main ones
* Color Utils (Color wheel + java.awt.Color to mc colors)
* Player Utils (Easy server -> client velocity + fix inconsistencies when on the ground)

## Planned
Please see the [project page](https://github.com/users/sekwah41/projects/14/views/1) for a up to date list of planned features.


# If you need help
Please contact us on our [discord](https://discord.sekwah.com/).

# Cross Loader
While some of this mod will be entirely abstracted for cross loader use, others will use an interface then have a forge and fabric implementation.

If there is anything you would like to see added please let me know either via discord or an issue on github.

# Mods Using The Library
This list does not include every mod that is using the library, just the ones I know of.
The list of features being used may be out of date too.

If you want to see example usage of the library, check out the mods below or the test projects within this repo.

Examples of mods using this library:
 * [Sekwah41's Naruto Mod](https://github.com/sekwah41/Naruto-Mod) (Forge) Uses:
   * Capability @Sync annotations
   * Additional Utilities
 * [Re:Skin](https://github.com/sekwah41/Re-Skin) (Forge) Uses: 
   * Capability @Sync annotations
 * [Blockbuster](https://github.com/Mineshinima/blockbuster) (Forge) Uses: 
   * TBD - Currently in progress
