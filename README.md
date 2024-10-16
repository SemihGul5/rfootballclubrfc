# FC 25 | RANDOM TEAM

[![Get it on Google Play](https://upload.wikimedia.org/wikipedia/commons/7/78/Google_Play_Store_badge_EN.svg)](https://play.google.com/store/apps/details?id=com.rfootballclubrfc)

## 🚀 Introduction

**FC 25 | RANDOM TEAM** is an exciting app for football game enthusiasts to make their game nights more thrilling. Randomly select teams for popular football games and enjoy a balanced match with friends. The app allows users to pick teams from a variety of leagues or create their custom leagues. Select from high-rated teams (4-5 stars) or limit your choices to the top-tier teams only. Tailor your gaming experience by picking teams from specific leagues or designing your own custom league for more fun!

## 🎯 Features

- **Random Team Selection:** Choose random teams from famous leagues or your own custom leagues.
- **Star Rating Filter:** Easily filter teams by their star ratings (e.g., 4 or 5 stars) for balanced matchups.
- **Custom League Creation:** Create, manage, and play with teams in your own leagues.
- **Popular Leagues Integration:** Explore and select from teams in major leagues worldwide.
- **Smooth UI Animations:** Enjoy a sleek splash screen with fade-in animations to enhance user experience.
- **Simple Interface:** Easy-to-navigate design for seamless team selection and gameplay setup.

## 🛠 Technologies Used

- **Kotlin:** Core language used for app development.
- **Android Jetpack:** Architecture components including ViewModel, LiveData, Room, and Navigation for seamless app structure.
- **MVVM Architecture:** Ensures clean separation between the UI and business logic.
- **Hilt:** Efficient dependency injection framework used to manage app components.
- **Retrofit:** Networking library for handling API requests and managing network connections.
- **Room:** Provides a local database to store app data.
- **Glide:** Image loading and caching for smoother performance.
- **Google Mobile Ads SDK:** Ad integration for monetizing the app with banner and interstitial ads.
- **Material Design:** Implemented across the app to ensure a modern, user-friendly interface.


### 📂 Detailed Structure

- **data:**
  - **datasource:** Classes for handling data from API or local database.
  - **model:** Data models representing the objects like leagues and teams.
  - **repo:** Repository layer abstracts data sources and interacts with ViewModel.
  
- **di (Dependency Injection):**
  - Dependency injection with Hilt for managing application-wide components.

- **ui:**
  - **adapter:** RecyclerView adapters for displaying teams and league lists.
  - **fragment:** Fragments containing the app’s UI components and navigation logic.
  - **viewmodel:** ViewModel for handling business logic and providing data to UI.

- **retrofit:**
  - Handles network requests and API calls.

- **room:**
  - Manages local database using Room ORM.

- **util:**
  - Utility classes and extension functions that help with app logic and UI enhancements.
