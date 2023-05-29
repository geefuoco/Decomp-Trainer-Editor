package me.disturbo.data.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

public class NatureParserTest {

    private static NatureParser parser = new NatureParser();

    private final Path setup() {
        try {
            Path tempFile = Files.createTempFile(null, null);
            String rawNatures = new StringBuilder()
                    .append("define NATURE_HARDY    0")
                    .append(System.lineSeparator())
                    .append("define NATURE_LONELY   1")
                    .append(System.lineSeparator())
                    .append("define NATURE_BRAVE    2")
                    .append(System.lineSeparator())
                    .append("define NATURE_ADAMANT  3")
                    .append(System.lineSeparator())
                    .append("define NATURE_NAUGHTY  4")
                    .append(System.lineSeparator())
                    .append("define NATURE_BOLD     5")
                    .append(System.lineSeparator())
                    .append("define NATURE_DOCILE   6")
                    .append(System.lineSeparator())
                    .append("define NATURE_RELAXED  7")
                    .append(System.lineSeparator())
                    .append("define NATURE_IMPISH   8")
                    .append(System.lineSeparator())
                    .append("define NATURE_LAX      9")
                    .append(System.lineSeparator())
                    .append("define NATURE_TIMID    10")
                    .append(System.lineSeparator())
                    .append("define NATURE_HASTY    11")
                    .append(System.lineSeparator())
                    .append("define NATURE_SERIOUS  12")
                    .append(System.lineSeparator())
                    .append("define NATURE_JOLLY    13")
                    .append(System.lineSeparator())
                    .append("define NATURE_NAIVE    14")
                    .append(System.lineSeparator())
                    .append("define NATURE_MODEST   15")
                    .append(System.lineSeparator())
                    .append("define NATURE_MILD     16")
                    .append(System.lineSeparator())
                    .append("define NATURE_QUIET    17")
                    .append(System.lineSeparator())
                    .append("define NATURE_BASHFUL  18")
                    .append(System.lineSeparator())
                    .append("define NATURE_RASH     19")
                    .append(System.lineSeparator())
                    .append("define NATURE_CALM     20")
                    .append(System.lineSeparator())
                    .append("define NATURE_GENTLE   21")
                    .append(System.lineSeparator())
                    .append("define NATURE_SASSY    22")
                    .append(System.lineSeparator())
                    .append("define NATURE_CAREFUL  23")
                    .append(System.lineSeparator())
                    .append("define NATURE_QUIRKY   24")
                    .append(System.lineSeparator())
                    .append("define NUM_NATURES     25")
                    .toString();
            Files.write(tempFile, rawNatures.getBytes(StandardCharsets.UTF_8));
            return tempFile;
        } catch (IOException e) {
            System.out.println("Could not create temp file");
            e.printStackTrace();
            return null;
        }

    }
    @Test
    public void runTest() {
        Path tempFile = setup();

        LinkedList<String> natures = new LinkedList<>();

        parser.parse(tempFile.toFile(), natures);

        assertEquals(25, natures.size());

        assertEquals("NATURE_HARDY",natures.get(0));
        assertEquals("NATURE_TIMID", natures.get(10));

    }
}
