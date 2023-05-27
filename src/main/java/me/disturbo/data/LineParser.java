package me.disturbo.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public interface LineParser<T>{
    default T parse(File file, T output){
        FileReader fr = null; BufferedReader br = null;
        int count = 0;
        try {
            fr = new FileReader(file); br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                count += 1;
                if(parseLine(output, line)) break;
            }
            fr.close();
        }
        catch(IOException exception) {
            exception.printStackTrace();
            System.out.println("Error occured at line: " + count);
        }
        finally {
            try {
                fr.close(); br.close();
            }
            catch (IOException exception) {
                exception.printStackTrace();
            }

        }
        return output;
    }

    // If the result of this function is true no more lines will be read
    boolean parseLine(T output, String line);
}
