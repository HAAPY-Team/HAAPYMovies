# Milestone 2 (M2) Readme

### Running Gradle Commands

Navigate to the root of the M2 project

| Command                                | Description                                      | 
| -------------------------------------- | ------------------------------------------------ |
| ```gradle -b <filename> compileJava``` | compiles and builds the project                  |
| ```gradle -b <filename> compileTest``` | compiles the unit tests for the project          | 
| ```gradle -b <filename> javadoc```     | creates the html documentation for the project   |  
| ```gradle -b <filename> clean```       | removes all the files created by this project    |   
| ```gradle -b <filename> jar```         | creates the executable jar file for this project |    

### Running the Jar

Navigate to the folder with the generate Jar file ```M2/build/libs```

Run ```java -jar <filename>```