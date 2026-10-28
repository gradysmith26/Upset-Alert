# Upset-Alert
Project Goal
Upset Alert is a college basketball notification system that automatically detects when an AP Top 25 NCAA Men's basketball team loses to a lower ranked or unranked opponent.
The goal is to help college basketball fans stay informed about upsets without having to check scores every day. I hope to eventually send push notifications to multiple users.

# Minimum Viable Product
The MVP will:
* Retrieve NCAA men's basketball scores from ESPN's API.
* Retrieve and store the AP Top 25 rankings.
* Identify complete games in which an upset occurs.
* Display detected upsets in the terminal.
* Save upsets to prevent duplicate notifications from occurring.

# Future Plans
I plan to add:
* Push notifications through ntfy so multiple users can receive alerts on their phones.
* Automatic execution through Bash scripts and cron.
* Custom notifications depending on how outlandish the upset is.
* Cloud hosting so the program can run without my computer.

# Tech Stack

* Java - Main programming language for retrieving data and detecting upsets, I/O for storing ranking data and previous upsets to prevent duplicate notifications.
* Bash - Running and automating the program.
* Java HttpClient - Sending HTTP requests.
* Jackson - Parsing JSON responses from ESPN
* ESPN API - Providing college basketball rankings and scores.
* ntfy - Delivering push notifications.
* GitHub - Version control and documentation.
