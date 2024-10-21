Mini Pokédex

Project Description

Mini Pokédex is an application developed for a family whose kids love playing Pokémon GO. The app allows parents and children to discover a new Pokémon every day, learn interesting facts about them, and save their favorite Pokémon for later.

Current Version: 1.0.0

-------------------------------------------------------------------------------------------------------------------------
Main Features

For Parents

Random Pokémon on Each Visit: Displays a randomly chosen new Pokémon each time the app is opened.
Choose a Pokémon to View: Allows kids to select a Pokémon they are interested in learning more about.
Refresh Random Pokémon: If the displayed Pokémon doesn't interest the kids, parents can refresh to get a new random match.
Bookmarks: Ability to bookmark certain Pokémon to revisit them later.

For Kids

Detailed Pokémon Information:
Image of the Pokémon.
Name of the Pokémon.
Height, weight, experience and other atriburtes of the Pokemon.
Note: Functions for searching Pokémon and tracking evolutions will be added in the next version.
Kid-Friendly, Colorful UI: The app features a bright and child-friendly interface.
Handling Misspellings: The app is designed to be forgiving of spelling mistakes, suggesting relevant Pokémon if the search term yields no results. (To be implemented in the next version)

-------------------------------------------------------------------------------------------------------------------------
Technology Stack

API: Using PokeAPI REST API v2 (https://pokeapi.co/docs/v2) to fetch Pokémon data.
Programming Language: Kotlin.
Framework: Android with Jetpack Compose for building modern, reactive user interfaces.
State Management: ViewModel and StateFlow are used for managing the app's state.

-------------------------------------------------------------------------------------------------------------------------
Dependencies:

Retrofit and OkHttp for network requests.

Glide for image loading and caching.

Jetpack Navigation Compose for navigation between screens.

Roon for database.

Version Control: Git is used for tracking progress and version management.

-------------------------------------------------------------------------------------------------------------------------
How to Run the App

1) Clone the Repository
https://github.com/ArturSmith/pokedex
2) Open the project in Android Studio Arctic Fox or a newer version.
3) Ensure you have the latest version of Android Studio and the necessary Android SDK installed.
4) Sync Dependencies
The project uses Gradle for dependency management.
5) Sync the project with Gradle to download all necessary dependencies.
6) Run the App
7) Connect an Android physical device or start a virtual emulator via AVD Manager.
8) Click the Run button in Android Studio or use the keyboard shortcut Shift + F10.

-------------------------------------------------------------------------------------------------------------------------
Core Features

Home Screen:

Displays a list of collected Pokemons.
Button to go to the screen to choose new Pokemon.
Bookmarks section.
Search section.

Pokémon Detail Page:

Shows the Pokémon's image, name, and basic characteristics.
Ability to add the Pokémon to bookmarks.
Ability to add the Pokemon to collection.
Note: Evolution details and requirements will be added in the next version.

Bookmarks:

A section to access favorited Pokémon quickly.
Ability to unmark and mark the Pokemon.
Ability to open detail page of the Pokemon.

Choose Pokemon Page:

Shows one random Pokemon.
Ability to generate new Pokemon.
Ability to open detail page.
Ability to add Pokemon to bookmarks.
Ability to add the Pokemon to collection.

-------------------------------------------------------------------------------------------------------------------------
Upcoming Features

Pokémon Search Functionality:

Ability to search for Pokémon by name or relevant keywords.
Handling of misspellings and suggestions for similar Pokémon names.
Evolution Tracking

Display possible evolutions of a Pokémon and the requirements for evolution.
These features will be included in the next version of the app.

-------------------------------------------------------------------------------------------------------------------------
Clean Code Principles

The code adheres to clean architecture principles, making it maintainable and extensible.
Code Comments
Comments are included to explain the logic of key components.
README.md

This file provides project information, setup instructions, and an overview of the main features.

-------------------------------------------------------------------------------------------------------------------------
Third-Party Libraries Used:

Retrofit
OkHttp
Glade
Jetpack Compose Navigation
Acomponist


