package me.disturbo.data.parsers;

import me.disturbo.data.IndexedLineParser;
import me.disturbo.types.Trainer;

import java.util.HashMap;

public class TrainerParser implements IndexedLineParser<Trainer> {
    @Override
    public Trainer parseObject(String name, String rawTrainer) {
        HashMap<String, String> values = Trainer.templateValues();

        try{
            rawTrainer = rawTrainer.replace("};", "");
            rawTrainer = rawTrainer.replaceAll(System.lineSeparator(), "");
            rawTrainer = rawTrainer.replaceAll("\\s+", "");
            rawTrainer = rawTrainer.substring(("[" + name + "]={.").length(), rawTrainer.length() - 3);

            String[] trainer = rawTrainer.split(",\\.");
            for(String field : trainer){
                String key = field.substring(0, field.indexOf("="));
                String value = field.substring(field.indexOf("=") + 1);
                values.put(key, value);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error when processing raw trainer: " + rawTrainer);
        }
        return new Trainer(name, values);
    }
}
