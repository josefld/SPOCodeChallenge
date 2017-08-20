# SPOCodeChallenge

### Downloading & Installation
In order to run the proyect just:
1. go to releases and download the jar file.
2. After downloading the jar just type in a console the following command:
  - ```java -jar [name of the downloaded jar]```

Now you can start sending requests to the application, it's a self contained application ,so you won't need a server, it's already there!

### Usage
Application will run in localhost:8005, and the resource that does the magic is workForceOptimization. The parameters for the request where simplified as the following:
* capacity: an array of int numbers delimited by coma.
* senior: an int number indicating the workfoce of a senior.
* junior: an int number indicating the workfoce of a junior.
- Example URL:
http://localhost:8005/workForceOptimization?capacity=24,32&senior=11&junior=2

It's that easy, you can start optimizing by using a browser or your favorite program to send GET request.:smile:
