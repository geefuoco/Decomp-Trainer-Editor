package me.disturbo.data.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

public class PokemonPicTableParserTest {

    private static PokemonPicTableParser parser;

    private final Path setup() {
        try {
            parser = new PokemonPicTableParser();
            Path tempFile = Files.createTempFile(null, null);
            Files.write(tempFile, "SPECIES_SPRITE(DREEPY, gMonFrontPic_Dreepy),".getBytes(StandardCharsets.UTF_8));
            return tempFile;
        } catch (IOException e) {
            System.out.println("Could not create temp file");
            e.printStackTrace();
            return null;
        }

    }

    @Test
    public void testParse() {
        Path path = setup();
        LinkedHashMap<String, String> picTable = new LinkedHashMap<String, String>();

        parser.parse(path.toFile(), picTable);

        assertEquals(
                picTable.get("gMonFrontPic_Dreepy"),
                "DREEPY",
                new StringBuilder()
                        .append("Expected: DREEPY")
                        .append("Actual: " + picTable.get("gMonFrontPic_Dreepy"))
                        .toString());

    }

}
