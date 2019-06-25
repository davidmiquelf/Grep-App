package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface JavaGrep {

    /**
     * Top Level Search Workflow
     * @throws IOException
     */
    void process() throws IOException;

    /**
     * Get all the files of the given directory.
     * @param rootDir
     * @return files under rootDir
     */
    List<File> listFiles(String rootDir);


    /**
     * Read a file and return all the lines.
     *
     * @param inputFile
     * @return lines
     * @throws IllegalArgumentException if inputFile does not exist.
     */
    List<String> readLines(File inputFile) throws IOException;

    /**
     * Check if line contains the regex pattern.
     * @param line
     * @return
     */
    Boolean containsPattern(String line);

    /**
     * Write lines to file.
     *
     * @param lines matched
     * @throws IOException if write failed
     */
    void writeToFile(List<String> lines) throws IOException;

    String getRootPath();

    void setRootPath(String rootPath);

    String getRegex();

    void setRegex(String regex);

    String getOutFile();

    void setOutFile(String outfile);
}
