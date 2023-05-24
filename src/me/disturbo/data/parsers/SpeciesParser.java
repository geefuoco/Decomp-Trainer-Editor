package me.disturbo.data.parsers;

import me.disturbo.data.LineParser;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class SpeciesParser implements LineParser<LinkedHashMap<String, String>> {
    @Override
    public boolean parseLine(LinkedHashMap<String, String> species, String line) {
        if(line.contains("[SPECIES_")){
            try{
                line = line.replaceAll("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)", "");
                String declaration = line.substring(1, line.indexOf("]"));
                String name = line.substring(line.indexOf("_(\"") + 3, line.indexOf("\")"));
                species.put(declaration, name);
            } catch(Exception e){
                System.out.println("Error when processing line: " + line);
            }
        }
        return false;
    }
}
