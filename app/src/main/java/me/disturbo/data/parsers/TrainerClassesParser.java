package me.disturbo.data.parsers;

import me.disturbo.data.LineParser;
import me.disturbo.types.TrainerClass;

import java.util.LinkedHashMap;

public class TrainerClassesParser implements LineParser<LinkedHashMap<String, TrainerClass>> {
    @Override
    public boolean parseLine(LinkedHashMap<String, TrainerClass> trainerClasses, String line) {
        if(line.contains("[TRAINER_")){
            try {
                line = line.replaceAll("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)", "");
                String declaration = line.substring(1, line.indexOf("]"));
                String name = line.substring(line.indexOf("_(\""), line.indexOf("\")"));
                trainerClasses.put(declaration, new TrainerClass(name, "0"));
            } catch(Exception e){
                System.out.println("Error when processing line: " + line);
            }
        }
        return false;
    }
}
