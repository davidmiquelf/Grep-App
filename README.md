# Jarvis Boot-Camp Java Applications
This is a Maven project consisting of several Java Packages made over the course of my Jarvis boot-camp.
## Grep
Simple grep app that emulates a simple grep search. Searches files in the given directory,
and tries to find any lines that match the regular expression. Writes matched lines in
an output file.
### Usage: Grep [regex] [rootPath] [outFile]
 regex: Regular expression  
 rootPath: The path of the directory being searched.  
 outFile: The file where the matched lines are written.
### Possible Improvements
Rather than holding the entire contents of each file in memory, it would be more efficient
to process each line as it is being read. That way only the matched lines have to be stored.  
Better yet, each matched line could be asynchronously written to the output file. That way,
no running time is lost waiting for writes to complete, and total memory usage is minimized.
## JDBC
An exercise in using DAOs and DTOs. For each table in a database there is a DAO and a DTO.
The DAO accesses the database, and converts each row found into a DTO. This design pattern
is especially useful in larger apps where many components could want database access and/or many
databses could be providing similar information.
## Twitter API App
A command line app that can get, post, or delete tweets. Twitter's API is RESTful so the app
simply has to create, send, and recieve HTTP messages.
