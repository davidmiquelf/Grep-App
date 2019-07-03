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
