package ca.jrvs.apps.grep;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JavaGrepImp implements JavaGrep {
    private String regex;
    private String outFile;
    private String rootPath;

    public JavaGrepImp(String regex, String rootPath, String outFile){
        this.regex = regex;
        this.outFile = outFile;
        this.rootPath = rootPath;

    }


    @Override
    public void process() throws IOException {

        List<File> files = listFiles(rootPath);
        ArrayList<String> outputLines = new ArrayList<>();
        List<String> fileLines;

        for (File file : files){
            fileLines = readLines(file);

            for (String line : fileLines){
                if(containsPattern(line)){
                    outputLines.add(line);
                }
            }
        }
        writeToFile(outputLines);
    }

    @Override
    public List<File> listFiles(String rootDir) {
        File rootFolder = new File(rootDir);
        List<File> fileList = new ArrayList<>();
        File [] files = rootFolder.listFiles();

        if (files == null){
            fileList.add(rootFolder);
            return fileList;
        }

        for (File fileEntry : files) {
            if (fileEntry.isDirectory()) {
                fileList.addAll(listFiles(fileEntry.getAbsolutePath()));
            } else {
                fileList.add(fileEntry);
            }
        }
        return fileList;
    }

    @Override
    public List<String> readLines(File inputFile) throws IOException{
        ArrayList<String> lines = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line;

        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        return lines;

    }

    @Override
    public Boolean containsPattern(String line) {
        return line.matches(this.regex);
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {

        //Check if there is an outfile.
        if (this.outFile == null){
            return;
        }

        BufferedWriter writer = new BufferedWriter (new FileWriter(this.outFile));

        for (String line : lines) {
            writer.write(line);
        }
        writer.close();

    }

    @Override
    public String getRootPath() {
        return this.rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getRegex() {
        return this.regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String getOutFile() {
        return this.outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }

    public static void main(String[] args){

        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: regex rootPath outFile");
        }

        JavaGrepImp javaGrepImp = new JavaGrepImp(args[0], args[1], args[2]);

        try {
            javaGrepImp.process();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
