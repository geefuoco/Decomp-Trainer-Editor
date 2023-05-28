package me.disturbo.data.parsers;

import me.disturbo.data.LineParser;

import java.util.LinkedHashMap;

public class PokemonPicTableParser implements LineParser<LinkedHashMap<String, String>> {
    @Override
    public boolean parseLine(LinkedHashMap<String, String> picAssociations, String line) {
        if(line.contains("SPECIES_SPRITE")){
            try{
                line = line.replaceAll("\\s+", "");
                String[] association = line.substring("SPECIES_SPRITE(".length(), line.length() - 2).split(",");
                picAssociations.put(association[1], association[0]);
            } catch(Exception e){
                System.out.println("Error when processing line: " + line);
            }
        }
        return false;
    }
}
