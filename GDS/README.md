GDS
---
#### How to run
1. JRE: `11.0.6` or above 
2. `cd` into folder `GDS`
3. Run command `java -jar build/libs/GDS-1.0-SNAPSHOT.jar`
4. Example input & output:
   ```
   Start application
   Number of people: 3
   Name 1: Alice
   Pay: 30
   Name 2: Bob
   Pay: 25.5
   Name 3: Chan
   Pay: 0
   Chan pays Alice $11.5
   Chan pays Bob $7.0
   Number of transactions: 2
   ```
5. For running test: 
   1. Gradle: `6.1` or above
   2. Linux / MacOS: `./gradlew test`
   3. Windows PowerShell: `.\gradlew test`
