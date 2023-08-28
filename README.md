Environment & Instructions to run
- Application is written in plain Java.
- Windows
- Dependencies required
    - Amazon Coretto 20
    - junit-jupiter
- Recommended IDE to use is IntelliJ
- Simply run the "RaffleApplication.java" file. The file is in this location, "src/main/java/sg/gic/com/RaffleApplication.java"
    - On IntelliJ, navigate to the file, right click and select "Run"
- Test classes can be found in the "src/test" folder.
    - To run the tests
        - In IntelliJ, navigate to the "src/test/java/sg/gic/com" folder, right click the folder and select 'Run tests in 'com'.

Assumptions
- 
- The raffle does not refund players (i.e. If a new draw is started before the raffle is run, the draw pot will remain the same)
- The draw must be started before buying of tickets and running of raffle is allowed.
- Typing 'exit' exits the application.

Design
-
- The application is designed with a heavy emphasis on OOP and SOLID principles.
- Classes are kept intentionally small and logically separated. This ensures adherence with Single Responsibility Principle.
- Interfaces and abstract classes are used to abstract common functionality between classes. This provides a more flexible implementation that can be easily adapted to new features. This adheres with the Open Closed Principle.
  - Menu abstract class separates the common functionality of displaying, and allows all menus to inherit the display method to minimise code duplication. This also adheres to Liskov Substitution Principle.
  - Runnable interface abstracts the final computation of the raffle to expose the run and reset methods, which can be implemented by other raffle computations. This ensures adherence with Interface Segregation Principle.
- Classes logically encapsulate only the variables they need, and expose only required functionality to other classes.
- Classes inject the required dependencies via constructor, and overall design makes higher level modules like Raffle depend on lower level modules like TicketFactory and Draw in adherence with Dependency Inversion Principle.

Tests
-
Unit tests are written in JUnit. Throughout the development process, unit tests were written for high level modules like Raffle to ensure that the provided functionality works as expected.                               
The following are emphasized in the testing methodology:
- Class methods and variables function as expected.
- Application functionality works correctly.
- Edge cases are tested.
- Appropriate exceptions are thrown.
