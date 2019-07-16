- [1 Twitter API App](#1-twitter-api-app)
  * [1.1 Design and Implementation](#11-design-and-implementation)
  * [1.2 Usage](#12-usage)
    + [1.2.1 Windows](#121-windows)
    + [1.2.2 Linux](#122-linux)
  * [1.3 Possible Improvements](#13-possible-improvements)
- [2. Grep](#2-grep)
  * [2.1 Design and implementation](#21-design-and-implementation)
  * [2.2 Usage](#22-usage)
  * [2.3 Possible Improvements](#23-possible-improvements)
- [3. JDBC](#3-jdbc)

## 1. Twitter API App
A command line app that can get, post, or delete tweets. Twitter's API is RESTful so the app
simply has to create, send, and receive HTTP messages. 

### 1.1. Design and Implementation

The following tasks are decoupled through interfaces:
 - Sending/Receiving HTTP requests (HttpHelper).
 - Building and Parsing Twitter API calls (CrdDao).
 - Using the DAO's methods to conduct simple Twitter tasks (Twitter service).
 
 ![Twitter App Architecture Diagram](https://github.com/davidmiquelf/Java-Applications/blob/master/Twitter_Diagram.png)

The app uses the Spring framework to construct the interface implementations (beans).
### 1.2. Usage 
#### 1.2.1. Windows
 - Start Windows PowerShell.  
 - Navigate to the project folder and edit the windows environment script. Input your Twitter API access tokens/secrets.  
  `notepad windows_env_setup.ps1`
 - Run the script.  
  `.\windows_env_setup.ps1`
 - Compile the maven project -- can skip tests to speed up the process:  
  `mvn package -D"maven.test.skip=true"`
 - Navigate to the same directory as the jar file.
 
From here you can use the app by calling the jar file:  

Post a tweet:  
`java -jar TwitterCLI.jar post [text] `  

Post a tweet with location:  
`java -jar TwitterCLI.jar post [text] [location]`  

Get a tweet by id:  
`java -jar TwitterCLI.jar get [id]`  

Delete a tweet by id:  
`java -jar TwitterCLI.jar delete [id]`  

#### 1.2.2. Linux
Add a file for access tokens:  
`touch ~/twitter_tokens.sh`

Write the following in the file (replacing the placeholders):
```
export consumerKey="CONSUMER_KEY"
export consumerSecret="CONSUMER_SECRET"
export accessToken="ACCESS_TOKEN"
export tokenSecret="TOKEN_SECRET"
```
Navigate to the project directory and package the app with maven:  
`mvn package -D"maven.test.skip=true"`

Post a tweet:  
`twitter_app.sh post [text] `  

Post a tweet with location:  
`twitter_app.sh post [text] [location]`  

Get a tweet by id:  
`twitter_app.sh get [id]`  

Delete a tweet by id:  
`twitter_app.sh delete [id]`

### 1.3. Possible Improvements
Ability to search for tweets by username, keywords, hashtags.. etc.  
Make interactive so that the app doesnt need to boot every time you want to read a tweet.  
Also interactive mode can ask user for access tokens if they are missing or invalid, and
then save them in an appropriate spot.
## 2. Grep
Simple grep app that emulates a simple grep search. Searches files in the given directory,
and tries to find any lines that match the regular expression. Writes matched lines in
an output file.
### 2.1. Design and implementation
Implemented the following interface:

Interface functions:  
 - process()  
 - listFiles(String rootDir)
 - readLines(File inputFile)
 - containsPattern(String line)

The arguements are passed to the JavaGrepImp constructor, then the process function uses the function implementations to run the desired Grep actions.
 
### 2.2. Usage
`Grep [regex] [rootPath] [outFile]`  
 regex: Regular expression  
 rootPath: The path of the directory being searched.  
 outFile: The file where the matched lines are written.
### 2.3. Possible Improvements
Rather than holding the entire contents of each file in memory, it would be more efficient
to process each line as it is being read. That way only the matched lines have to be stored.  
Better yet, each matched line could be asynchronously written to the output file. That way,
no running time is lost waiting for writes to complete, and total memory usage is minimized.
## 3. JDBC
An exercise in using DAOs and DTOs. For each table in a database there is a DAO and a DTO.
The DAO accesses the database, and converts each row found into a DTO. This design pattern
is especially useful in larger apps where many components could want database access and/or many
databses could be providing similar information.

 ![Architecture Diagram](https://github.com/davidmiquelf/Java-Applications/blob/master/JDBC_Diagram.png)

