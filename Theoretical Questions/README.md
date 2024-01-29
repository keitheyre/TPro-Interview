# Theoretical Questions

In order to proceed with the interviewing process T-Pro asked a couple of theoretical questions that are splitted by two levels.

## First Level

**1. Explain the main concept of lambda functions.**

 A:
 - The main concept of lambda functions are a clear and concise way to represent anonymous function. Meaning they don't have a name, are usually simple and once-off functions with a clear purpose and without the need for boilerplate code which would be required for traditional functions.
 - Here is an example of the difference using the onClickListener as an example
```
// Traditional Method
myButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        // do something
    }
});
```
```
// Lambda Method
myButton.setOnClickListener(v -> {
    // do something
});
```

**2. What's the difference between `UInt16` and `Int32`?**

 A:
 * Type of integers that differ in their size and range
   * UInt16: Unsigned Integer that occupise 16 bits of memory (2 bytes). Can only be a positive number ranging from 0 to 65,535
   * Int32:  Integer that occupies 32 bits of memory (4 bytes). Can be either positive or negative and has a much larger range of -2,147,483,648 to 2,147,483,647

**4. Do you have any experience with functional programming languages?**

 A:
 - Not exactly. I have experience in Python and Dart, both are not be considered pure functional programming languages but both incorperate several features that are conducive with functional programming such as: First-Clas Functions, Higher-Order Functions, Anonymous Functions and immutability support using final and const. 

## Second Level

**1. Do you have experience with C++ (JNI) code in Android projects? If yes describe what you have implemented.**

 A:
 - I do not. But I do understand that (JNI) in Android Applications serves as a bridge that would allow C++ code/ libraries to be run from Java using the JVM (and other languages also).
 - I can see the advantages when it comes to performance criitical code (heavy calculations, graphics, audio analysis etc.), reusing legacy code and accessing specific hardware features that may not be directly accessible via Java. If I was to use this in an Android Application I might use it to optamise a computationally intensive task such as image processing or physics simulations in games. Implemnting this would achieve higher performance compared to implementing the same functionality soley in Java code. Other application would be audio processing or cryptgraphic operations which might not be readily available or as efficient in Java.
