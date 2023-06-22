# dazn-assignment-hs

App which displays list of image + name of different cosmic bodies by NASA from a raw json file which when tapped opens a details screen about that cosmic body.

## Project overview
- Project follows **MVVM Architecture + Hilt**.
- App code is divided into 2 parts.
    - data : contains Repository & Data classes & hilt module
    - ui : contains Activity, Fragment, ViewModel, Custom Theme & CommonComposables.
- Tests for compose screens are present in androidTest module:- HomeScreenTest.kt & DetailScreenTest.kt

https://github.com/hsinha610/Screenshots/blob/main/Dazn-Assignment-Video.mp4

## Libraries used

    1. Json Parsing 
        - Gson
    2. Dependency Injection
        - Hilt
    3. Image Loading
        - Coil 
    4. Concurrency
        - Coroutines


