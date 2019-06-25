## Java Grep App
Simple grep app that emulates a simple grep search. Searches files in the given directory,
and tries to find any lines that match the regular expression. Writes matched lines in
an output file.
### Usage: regex rootPath outFile
 regex: Regular expression  
 rootPath: The path of the directory being searched.  
 outFile: The file where the matched lines are written.
### Possible Improvemenets
Rather than holding the entire contents of each file in memory, it would be more efficient
to process each line as it is being read. That way only the matched lines have to be stored.  
Better yet, each matched line could be written directly to the output file while the next line
is being processed. Since the jvm can handle disk reads/writes in parallel to the main thread,
this would allow for optimal running time and memory usage.
