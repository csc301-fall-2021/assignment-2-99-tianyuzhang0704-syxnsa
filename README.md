# CSC301 A2 TEAM99




## Pair Programming Process


### General Description

Throughout our development process, we take the driver and the navigator's role alternatively. It can be shown in our commit history (see the overview of the commit example below).

### Challenges Solved Together

* When we were deploying our Spring-boot Maven project onto Heroku server, from time to time build process was successful locally but failed on Heroku. Those problems include higher JDK version, newer plugin version, problems with the .jar file created in the building process, etc. Both of us searched online and tried different ways of upgrading or downgrading dependencies or plugins. Debugging for deploying is tough, as there can be numerous ways things went wrong, and we have to check them one by one. Therefore, having one person trying the code and another one looking for other solutions might be more efficient.

  

  Jobs: Tianyu Zhang (driver), Xinkai Jiang (navigator)

  

* Converting between csv files and JSON is complicated in Java. We parse both of them into String (for reading csv files, we used CSVReader class to read the csv file into Strings and ```org.json.CDL``` to convert JSONArray to String in csv format). We first did research online together. When implementing, the navigator helped to watch if there's problem with the code and search online for solutions if problems were encountered.

  

  Jobs: Xinkai Jiang (driver), Tianyu Zhang (navigator)

  

* Searching for key values in JSON file in Java. First we used only string methods to find the corresponding values, but it is too complicated. When the driver is writing the code, the navigator found it is easier to use the ```getString()``` method on Document objects. Knowing this makes our codes much more simpler and easier to write. With the help of the navigator, the problem is solved more efficiently.

  

  Jobs: Tianyu Zhang (driver), Xinkai Jiang (navigator)

  

### Roles We Took (Individually as drivers)

| Team Member  | Role                                                         |
| ------------ | ------------------------------------------------------------ |
| Tianyu Zhang | Deployment, Servlet classes, Test classes, database          |
| Xinkai Jiang | Setup Java Spring Boot project, converting between csv files and JSON, Servlet classes, Test classes, database |



## Reflections and Evaluation


### Reflection on The Process

Overall, the process went well. Both of us had opportunities to write the code as the driver and assistenting the driver as the navigator. Compared to the previous experiences of working separately, when we have to pulse coding and search online if problems occur, in pair programming we have a helper on our side to help us solve problems together. Moreover, when someone is watching you programming, it makes the coding part more efficient as well.

### Evaluation

#### Like

* Two of us can face the challenges together, so individual team member won't be so stressed.
* More communications between two team mates. We know better how each other thinks in each process.
* When trying to explain our codes to team mates some times can help us solve problem quicker.

#### Dislike

* When there are problems neither of us know how to solve, the process kind of stuck. It feels better when both of us can test and working on our own computers.
* It is better to have the two team members meeting face to facea, so that it is more convenient to give comments and communicate. However, sometimes it can be hard for the two people to find a time works for both. Also, since the daily routine for both of us don't quite much (Usually one of us is sleeping while the other is awake), and we have to be present in one place, it is sometimes less efficient than working directly at each one's home.
* We not only need to be familiar with our own codes as what we do when tasks are clearly divided, but also need to know clearly about the partner's codes, so that we know how to modify his or her code, which can be time consuming.



## Program Design


### API Design

We decided to use two APIs. One is https://csc301a2team99.herokuapp.com/upload for 

### Backend Design



## Tests


### Unit Test

We used JUnit to run unit tests. Since it is not a typical OOP program, for this part we mostly checked the methods (e.g. database methods, methods for converting between csv and JSON, and other helper methods). Jacoco is used to report the overall coverage of the unit test. On test branch, detailed report can be found on ```test``` branch in.

### Integration Test





## API Documentation


### API Documentation Link

Documentations can be found on: https://documenter.getpostman.com/view/16194739/UVC3kTUw

Request can be sent from Postman by clicking on "Run in Postman" button on the top right corner on the website. Note that https://csc301a2team99.herokuapp.com/query is requested using POST method with raw JSON body while https://csc301a2team99.herokuapp.com/upload is requested using POST method with form data. All the details and examples can be found in the documentation.