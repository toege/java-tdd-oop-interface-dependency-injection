# Object-oriented Programming - Dependency Injection (interfaces)

## Learning Objectives
- Use interfaces to improve maintainability of an application
- Use interfaces to reduce concrete dependency of classes
- Explain that interfaces are a blueprint for concrete implementations

## Set up instructions
- Fork this repository and clone the forked version to your machine
- Open the root directory of the project in IntelliJ

## Introduction

Let's take a look at one of the most important aspects of object-oriented programming: *Interfaces*.

To do this, we'll go back to a previous exercise. Consider the `Scrabble` challenge. One implementation is provided below, spend some time reading through it and understanding how it works:

```java
public class Alphabet {
    public Map<Character, Integer> getLetterScores() {
        return new HashMap<>(){{
            put('a', 1);
            put('b', 3);
            put('c', 3);
            // etc...
        }};
    }
}

public class Scrabble {
    Map<Character, Integer> letterScores;
    
    public Scrabble(Alphabet alphabet) {
        this.letterScores = alphabet.getLetterScores();
    }
    
    public int score(String word) {
        int total = 0;

        for (char ch : word.toCharArray()) {
            int score = this.letterScores.get(ch);
            total += score;
        }

        return total;
    }
}
```

An `Alphabet` class is injected into the `Scrabble` class. The Scrabble classes uses the Alphabet class to get the numeric point value for a single character. This works fine for the English alphabet, but what if we wanted our Scrabble class to go international? As it is right now, we would have to keep adding every type of letter into a single Alphabet class. That would be difficult to maintain.

Instead of injecting a concrete class, we can inject something known as an *interface*. An interface is a blueprint for classes, it's like a contract. It essentially provides a promise that the classes that implement the interface **will** contain certain methods that return specific values.

Here's a simple example using a Computer and a PowerSupply:

```java
interface PowerSupply {
    int getWattage();
}
```

We can inject that interface into a Computer class as normal:

```java
class Computer {
    PowerSupply psu;
    
    public Computer(PowerSupply psu) {
        this.psu = psu;
    }
    
    public String turnOn() {
        if (this.psu.getWattage() < 100) {
            return "PSU not powerful enough";
        }
        
        return "Turned on";
    }
}
```

Now we can decide exactly what type of power supply we want to inject into our computer class!

```java
class CorsairPowerSupply implements PowerSupply {
    int getWattage() {
        return 520;
    }
}

class KnockOffPowerSupply implements PowerSupply {
    int getWattage() {
        return 32;
    }
}

class Program {
    public static void main() {
        PowerSupply psu1 = new KnockOffPowerSupply();
        Computer myFirstPc = new Computer(psu1);

        myFirstPc.turnOn(); // outputs "PSU not powerful enough"

        PowerSupply psu2 = new CorsairPowerSupply();
        Computer mySecondPc = new Computer(psu2);

        mySecondPc.turnOn(); // outputs "Turned on"
    }
}
```

What we've essentially built is one blueprint (the PowerSupply interface) that defines the methods that **will** be available on any class that implements it, and two concrete classes that implement the interface. Both have the same method signature and return type, but they return different values.

The computer class shouldn't care about the exact type of the power supply, all it should care about is that the `getWattage` method exists and returns an int.

Let's revisit the Scrabble solution. If we want to provide multiple types of alphabets to our program, we'd create an interface that promises to return a specific data type:

```java
interface Alphabet {
    Map<Character, Integer> getLetterScores();
}
```

The scrabble class itself doesn't need to change at all! Now we can easily provide different alphabets:

```java
public class EnglishAlphabet implements Alphabet {
    public Map<Character, Integer> getLetterScores() {
        return new HashMap<>(){{
            put('a', 1);
            put('b', 3);
            put('c', 3);
            // etc...
        }};
    }
}

public class GreekAlphabet implements Alphabet {
    public Map<Character, Integer> getLetterScores() {
        return new HashMap<>(){{
            put('α', 1);
            put('β', 3);
            put('γ', 3);
            // etc...
        }};
    }
}

public class Scrabble {
    Map<Character, Integer> letterScores;
    
    public Scrabble(Alphabet alphabet) {
        this.letterScores = alphabet.getLetterScores();
    }
    
    public int score(String word) {
        int total = 0;

        for (char ch : word.toCharArray()) {
            int score = this.letterScores.get(ch);
            total += score;
        }

        return total;
    }
}

public class Program {
    public static void main() {
        Alphabet english = new EnglishAlphabet();
        Alphabet greek = new GreekAlphabet();
        
        Scrabble englishScrabble = new Scrabble(english);
        englishScrabble.score("hello world");
        
        Scrabble greekScrabble = new Scrabble(greek);
        greekScrabble.score("γεια σου κόσμε");
    }
}
```

## Exercise



## Test Output

![](./assets/run-a-test.PNG)

When you run a test, it's either going to pass or fail. When it fails, you'll be presented with a big red stream of text. This is called a stack trace and, though intimidating, does contain some useful information.

One of the core skills of a developer is debugging stack traces like this. The stack trace details in which classes & files the failure happened, and gives you a line number at the end. Most of the lines in the stack trace are irrelevant most of the time, you want to try and identify the files that you're actually working with.

In the sample screenshot below, we've tried to complete the first step of the exercise but provided an invalid value. Then we run the test associated with it and we see a big red stack trace, a test failure.

At the top, we see `expected: <32> but was: <33>`. This means the test expected the value to be 32, but the value the student provided was 33. We can see this in the code snippets at the top of the screenshot.

In the stack trace itself, we see this line: `at app//com.booleanuk.core.ExerciseTest.shouldBeAged32(ExerciseTest.java:20)`. This is helpful! This tells us the exact line in the ExerciseTest.java file (line 20) where the failure happened, as well as the method name (shouldBeAged32), helping us to identify where the issue began. This is the kind of thing you need to look for; a relevant file name, method name, class name and line number to give you a good starting point for debugging.

![](./assets/test-failure.PNG)
