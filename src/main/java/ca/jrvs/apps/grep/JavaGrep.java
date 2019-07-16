package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface JavaGrep {

  /**
   * Top Level Search Workflow
   *
   * @throws IOException when reads or writes fail.
   */
  void process() throws IOException;

  /**
   * Get all the files of the given directory.
   *
   * @param rootDir the directory being searched.
   * @return files under rootDir
   */
  List<File> listFiles(String rootDir);


  /**
   * Read a file and return all the lines.
   *
   * @param inputFile the file being read.
   * @return lines
   * @throws IllegalArgumentException if inputFile does not exist.
   */
  List<String> readLines(File inputFile) throws IOException;

  /**
   * Check if line contains the regex pattern.
   *
   * @param line the string being checked.
   * @return true when the line matches the regex.
   */
  Boolean containsPattern(String line);

  /**
   * Write lines to file.
   *
   * @param lines matched
   * @throws IOException if write failed
   */
  void writeToFile(List<String> lines) throws IOException;

  @SuppressWarnings("unused")
  String getRootPath();

  @SuppressWarnings("unused")
  void setRootPath(String rootPath);

  @SuppressWarnings("unused")
  String getRegex();

  @SuppressWarnings("unused")
  void setRegex(String regex);

  @SuppressWarnings("unused")
  String getOutFile();

  @SuppressWarnings("unused")
  void setOutFile(String outfile);
}
