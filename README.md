# 💉 Vaxplan - A simple vaccine campaign planning in JavaFX for local health authorities written with Java and JavaFX

This prototype was developed as a group project for the Software Engineering course in the Computer Science undergraduate degree at Università di Verona.

## 📖 Documentation

The specification according to which this prototype was built can be found in the `doc/Specifica.pdf` file. This is the handout issued by the SWE professor.

The prototype has been documented in `doc/Specifica.pdf`. Note that the `doc` folder also countains LaTeX sources that can be used to compile this document.

Do bear in mind: the specification and documentation PDFs for this project are in Italian since that's the language the course was taught in. However, please note that the code itself (which means the JavaDoc and names of class, fields, methods and such) was written entirely in English language for consistency.

As previously mentioned, all relevant methods and fields have been annotated with JavaDoc in plain english.

## ❓ What does Vaxplan do?

Vaxplan is a simple GUI prototype meant for an Italian region to plan various vaccination campaigns in parallel. Citizens can use this tool to easily book vaccination appointments for vaccine campaigns they are eligible for, while authorized healthcare personnel can add and edit the available vaccine campaigns.


## ✨ Technologies used

* **Java**: This prototype was written in Java 11 and it's been tested on OpenJDK 11 on Linux. However, since both the JVM and JavaFX run on all major desktop operating systems, this prototype should run anywhere.
* **JavaFX (OpenJFX 11)**: (OpenJFX)[https://openjfx.io/] is a toolkit to develop graphical user interfaces in Java. It could be considered the de-facto successor to the old AWT and Java Swing libraries.
* **Jackson Databind**: (jackson-databind)[https://github.com/FasterXML/jackson-databind] was used for JSON (de)serialization. All data is stored permanently on disk on simple, portable JSON files.
* **JUnit5**: JUnit5 was used to write and run Unit Tests for the validation steps.