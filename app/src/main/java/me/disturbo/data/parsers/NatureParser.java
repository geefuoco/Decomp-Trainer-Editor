package me.disturbo.data.parsers;

import me.disturbo.data.LineParser;

import java.util.LinkedList;

public class NatureParser implements LineParser<LinkedList<String>> {
    @Override
    public boolean parseLine(LinkedList<String> natures, String line) {
        if(line.contains("STATS") || line.contains("EVO")){
            return true;
        }
        if(line.contains("NATURE_")) {
            natures.add(line.split(" ")[1]);
        }
        return false;
    }
}
