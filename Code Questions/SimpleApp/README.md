# Simple App

This folder contains the Android project of the `simple app` required by T-Pro on the second question of the third questions level. The application should show a list of items and make them clickable to reproduce the audio from the URL sent in a JSON file. This JSON file can be obtained via a HTTP GET request to the endpoint https://urvd7g56zh.execute-api.eu-west-2.amazonaws.com/dev. A pause button should also be added.

## Implementation Hints

T-Pro gave the following implementation hints:

* Use the library [Okio](https://square.github.io/okio/) for IO if required;
* For DI on the last project, you can use [Koin](https://insert-koin.io/), [Kodein](https://kodein.org/di/) or [Dagger2](https://dagger.dev/);
* For HTTP, you can use [OkHttp](https://square.github.io/okhttp/) or [retrofit](https://github.com/square/retrofit).

## Prerequisites

A:
- First I needed to create the empty project in Andorid Studio
- Then, create a MainActivity, an Adapter, an Item model class to stop the data, a service to fetch the data and then handle the audio playback. I will try use as little external libraries as possible to keep the app lean and neat
- Then I will need to code the logic to handle playing and pausing the audio files from a url (Idealy I will use the same button for both Pause and Play)

## Architecture

A:
- MVVM architecture will make fetching the data and showing progress bars a bit easier. Also, it will keep as much logic away from the MainActivity as possible to keep it clean

## Libraries Used

A:
- I chose to use the OkHTTP library as I have not used that before and fancied myself a challenge. It was also interesting to learn what other libraries are out there and how they work

## Backlog

- I did try to use Hilt to DI but I was running into a lot of issues and did not want to take up too much time trying to figure this out. I will look into Hilt in my own time and learn it

- I did try to use FastLane to build a Release of the SimpleApp but the terminal kept getting stuck on this command. I tried multiple times and decided to leave it and like above, I will look into this in my own time 
```
keitheyre ~/Development/TPRO-INTERVIEW/Code Questions/SimpleApp [master] $ gem install fastlane
Ignoring ffi-1.15.0 because its extensions are not built. Try: gem pristine ffi --version 1.15.0
``` 


- [X] Create the initial project setup;
- [X] Setup the UI and navigation required;
- [X] Add the required data classes/entities;
- [X] Implement the OkHTTP service;
- [X] Fetch and display the according data on the UI;
- [ ] Integrate dependency injection with Hilt;
- [X] Manage the audio playback;
- [ ] Implement the fastlane script to build the project;

## Improvements

A:
Some improvements I would like to make given some extra time would be:
- Once the audio file is played, download that file to the device for faster playback the second time
- If AWS has active listeners for the endpoint, I would add a listen to refresh the data in real-time if the endpoint data was modified to keep the app in sync with the server
- Update the UI and make it a bit nicer


## Author

Keith Eyre

## Time to complete

* Theoretical Questions: 15 mins approx.
* Code Questions: 15 mins approx.
* Broken App: 2+ hours
  * I encountered a lot of errors that I had to research and understand before I could apply a fix. Mainly surrounding the
  ```
  MainActivity : AppCompatActivity()
  ```
  I was struggling to find why changing this from
  ```
  MainActivity : Activity()
  ```
  was causing the onClicks not to register and also the ViewModel initialisation was being ignored.
* Simple App: 4+ hours
  * I began strong with this as I knew what I had to do. I had to leave the house in the middle of this task to bring my wife to an appointment.
  * Once I returned I added another 1 hour to handle the uadio playback and then refactored some of the files to be clean and also added comments all over the place to make it clear what I was attempting to do
  




