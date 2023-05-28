package me.disturbo.data.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

class PokemonPicsPathsParserTest {
    private PokemonPicsPathsParser parser; 

    private final Path setup() {
        try {
            LinkedHashMap<String, String> picTable = new LinkedHashMap<String, String>();
            picTable.put("gMonFrontPic_Zacian", "ZACIAN");
            parser = new PokemonPicsPathsParser(picTable);
            Path tempFile = Files.createTempFile(null, null);
            Files.write(tempFile, "const u32 gMonFrontPic_Zacian[] = INCBIN_U32(\"graphics/pokemon/zacian/front.4bpp.lz\");".getBytes(StandardCharsets.UTF_8));
            return tempFile;
        } catch(IOException e){
            System.out.println("Could not create temp file");
            e.printStackTrace();
            return null;
        }

    }

    @Test
    public void testParse(){
        Path file = setup();
        assertNotNull(file, "Could not create the temp file");
        LinkedHashMap<String, String> output = new LinkedHashMap<String, String>();
        parser.parse(file.toFile(), output);

        assertEquals(output.get("ZACIAN"), "graphics/pokemon/zacian/front.png",  new StringBuilder().append("Got: ")
                                                                                                    .append(output.get("ZACIAN"))
                                                                                                    .append(". ")
                                                                                                    .append("Expected: ZACIAN").toString());


    }
}
