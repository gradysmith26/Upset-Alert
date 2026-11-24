# Upset-Alert
Upset Alert is a college basketball notification system that automatically detects when an AP Top 25 NCAA men's basketball team loses to a lower ranked or unranked opponent.

# The Problem
As a college basketball fan, I want to stay informed about major upsets without constantly checking scores throughout the day. Upset Alert solves this problem by retrieving basketball results, identifying unexpected losses, and notifying when an upset occurs.

## How it Works
Upset Alert retrieves college basketball scores and team rankings from ESPN's scoreboard API. Then the application processes the data to determine whether a game is considered an upset or not. If a game is an upset, a message is sent to my iPhone through ntfy and the game ID is stored in order to ensure no duplicate messages are sent.

![Upset Alert System Diagram](docs/System%20Diagram.png)

# Tech Stack
* Java - Main programming language for retrieving data and detecting upsets.
* Java File I/O - Storing previously reported game IDs in a text file to prevent duplicate notifications.
* Maven - Dependency management, building, and testing.
* Java HttpClient - Sending HTTP requests to ESPN and ntfy.
* Jackson - Parsing JSON responses from ESPN.
* ESPN Scoreboard API - Providing college basketball rankings and scores.
* ntfy - Delivering push notifications.
* JUnit 5 - Testing the upset detection algorithm.
* GitHub - Version control and documentation.

# How to Use
Before you start, be sure you've installed:
- Java 21 or newer
- Apache Maven
- Git
- ntfy (mobile app)

### Step 1: Clone the repository
- "git clone https://github.com/gradysmith26/Upset-Alert.git"
- "cd Upset-Alert"
### Step 2: Configure notifications
- Download ntfy and subscribe to a unique topic using the ntfy.sh server.
- In your terminal, set your notification in your program to the topic name you chose in ntfy.
- "export NTFY_TOPIC="your-topic-name"
### Step 3: Run Upset Alert
- "mvn compile exec:java -Dexec.mainClass=Main"

### Example Input and Output
No manual input is required.

Example game data:
- Purdue (Unranked): 80
- Indiana (#3): 79
- Game Status: Final

Output:
Unranked Purdue Boilermakers defeat #3 Indiana Hoosiers 80-79

After the upset is reported, the game ID is saved to prevent duplicate notifications.

### Step 4: Run Tests
- "mvn clean test"

This runs the JUnit tests for upset detection.

This application currently needs to be launched manually.


# Roadmap

### Completed
- [x] Create GitHub repository and organize files
- [x] Set up Java and Maven
- [x] Retrieve basketball data from ESPN's API
- [x] Parse JSON using Jackson
- [x] Implement upset detection logic
- [x] Detect upsets using real data
- [x] Prevent duplicate alerts using stored game IDs
- [x] Implement push notifications using ntfy
- [x] Test notifications on a mobile device
- [x] Create and merge a feature branch for notifications
- [x] Add JUnit tests for upset detection

### Future Improvements
- [ ] Automatically run Upset Alert on a schedule
- [ ] Host the application in the cloud
- [ ] Customize notifications based on magnitude of upset
- [ ] Allow users to subscribe to specific teams or conferences

# Demo

[Watch the Upset Alert Demonstration][https://youtu.be/fcylf4TSoxg]


