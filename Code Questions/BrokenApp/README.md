# Broken App

This folder contains code developed by [Pedro Amorim](https://github.com/ppamorim) and it is an Android application which bugs should be fixed as part of this interviewing process.

## Implementation Hints

T-Pro gave the following implementation hints:

* Use the library [Okio](https://square.github.io/okio/) for IO if required;
* For DI on the last project, you can use [Koin](https://insert-koin.io/), [Kodein](https://kodein.org/di/) or [Dagger2](https://dagger.dev/);
* For HTTP, you can use [OkHttp](https://square.github.io/okhttp/) or [retrofit](https://github.com/square/retrofit).

## Prerequisites

A: I had to update some versions (could have been my Android Studio that was the issue)
Once the app was running I then began looking at the code (This took the majority of the time)

Findings:

MainActivity.kt
- The package name appears to be duplicated
- Thread.sleep(50) being used on the Main Thread. Should be run on a background thread
- Null safety issues using the !! forcing unwrapping and can cause NPEs. It's better to use safe calls ?. and handle null cases gracefully
- Unused/ commented code and redundant override (Bad practices)

activity_main.xml
- Use of 'px' rather than 'dp'. dp is better to cater for different screen sizes
- Hard coded text value for button. Not good practice, use value files instead
- Padding along with hard-coded height was obscuring the input text

string.xml
- Hardcoded secret keys... Big no no (removed)

Approach:

- Fix the XML so I can see what is going on
- XML fixed and layout improved
- Restrict the input types into the EditText to numbers
- Center layout components

- Fix the MainActivity code to calculate the 2 numbers
- Create a ViewModel to handle the calculation logic and update a LiveData variable
- Update the Activity to observe the result and update the UI when it changes
- Introduce some simple error handling for empty inputs (error displayed in the result_tv)

## Architecture

A: MVVM

## Libraries Used

A: LiveData, ViewModel

## Backlog

A: N/A

## Improvements

A: Introduce MVVM arch, move constants to value files i.e. Strings, padding (dimens.xml) etc.