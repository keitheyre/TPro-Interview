# Code Questions

In order to proceed with the interviewing process, T-Pro asked a couple of code questions that also requires something theoretical to justify the implementation or just to provide some explanation regarding the topic.

## First Level Questions

**5. Compile any Kotlin source using `kotlinc`.**

 A: 
 - First I will make sure I have the Kotlin compiler installed on my machine
 - Now I will make a simple HelloWorld.kt file using Sublime Text Editor
 - The code for this file is as follows:
   ```
   fun main() {
    println("Hello, World!")
   }
   ```
- Then from the Terminal I will run the following commands to build using kotlinc and run using Java on the output .jar file to see the result in the terminal
  ```
  kotlinc HelloWorld.kt -include-runtime -d HelloWorld.jar
  java -jar HelloWorld.jar
  ```
 The resulted output is "Hello, World" being output to the Terminal



**6. Create a function in Kotlin that writes 50 million random floats in a file, the memory usage should be minimum in the entire process.**

 A:
 - First I will do as I did before, create a new file 'RandomFloatGenerator.kt'
 - Then I will add the following code to generate 50 million random floats and write them to a txt file
```
// Import necessary classes
import java.io.BufferedWriter
import java.io.FileWriter
import kotlin.random.Random

// Define a function to write random floats to a file
fun writeRandomFloatsToFile(filename: String, count: Int) {
    // Use a BufferedWriter to write text to a new file
    BufferedWriter(FileWriter(filename)).use { writer ->
        // Repeat 'count' number of times
        repeat(count) {
            // Generate a random float number
            val randomFloat = Random.nextFloat()
            // Write the random float to the file followed by a newline character
            writer.write("$randomFloat\n")
        }
    } // The BufferedWriter is automatically closed here due to the 'use' function
}

// Main function - Entry point of the Kotlin program
fun main() {
    // Call the writeRandomFloatsToFile function to create a file with 50 million random floats
    writeRandomFloatsToFile("random_floats.txt", 50_000_000)
}
```
- Then using the same commands as before, I will generate the .jar file with kotlinc and execute in the terminal
The result is a 513mb .txt file with 50 million random floats

## Second Level Questions

**2. Create a code below that implements the basic concept of dependency injection.**

 A:
 - This example will consist of a Service and a Client class that depends on the Service. Dependency Injection is achieved by passing the Service instance into the Client class instead of the client class create it directly
```
// Service interface
interface NotificationService {
    fun sendNotification(message: String)
}

// Implementation of the NotificationService
class EmailNotificationService : NotificationService {
    override fun sendNotification(message: String) {
        println("Sending: $message")
    }
}

// The client class that depends on a NotificationService
class NotificationClient(private val service: NotificationService) {
    fun doNotify(message: String) {
        service.sendNotification(message)
    }
}

// Demonstrate dependency injection
fun main() {
    // Creating instance of EmailNotificationService
    val emailService = EmailNotificationService()

    // Injecting emailService into NotificationClient
    val client = NotificationClient(emailService)

    // Using client to send a message
    client.doNotify("An simple example of Dependency Injection")
}
```

**3. Implement a simple Model-View-Presenter or Model-View-ViewModel structure below, you can reuse this in the projects below. Also explain why you choose MVVM over MVP and vice versa.**

 A:
 - I have chosen to use MVVM over MVP as I find it works better with the Android Architecture Components ViewModel and LiveData and allows a nice, clean seperation between the UI logic and the UI elements
 - This architecture also encorouges a more data-driven and reactive approach
```
// Model
class UserModel(private val name: String) {
    fun getName(): String {
        return name
    }
}

// ViewModel
class UserViewModel : ViewModel() {
    private val userModel = UserModel("Keith Eyre")
    val userNameLiveData = MutableLiveData<String>()

    fun loadUserName() {
        val name = userModel.getName()
        userNameLiveData.postValue(name) // This will trigger any observes to fire when cahnges are made
    }
}

fun main() {
    // Create an instance of the ViewModel
    val userViewModel = UserViewModel()

    // Observe any changes in the viewModel variables
    userViewModel.userNameLiveData.observe(this, Observer { newNameData ->
        println("Name: $newNameData")
    }

    // Update the name and thus triggering the observer to print the new name
    userViewModel.loadUserName()
}
```

## Questions of the third level

The third level questions are on the corresponding folders, `BrokenApp` and `SimpleApp`, inside this directory.
