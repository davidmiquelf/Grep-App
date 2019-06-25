package ca.jrvs.apps;

import ca.jrvs.apps.grep.JavaGrepImp;

public class Main {

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
