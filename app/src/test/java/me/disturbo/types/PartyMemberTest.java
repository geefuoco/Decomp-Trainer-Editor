package me.disturbo.types;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class PartyMemberTest {

    @Test
    public void runTest() {
        HashMap<String, String> values = new HashMap<>();
        values.put("iv", "TRAINER_PARTY_IVS(0, 0, 0, 0, 0, 0)");
        values.put("ev", "TRAINER_PARTY_EVS(0, 0, 0, 0, 0, 0)");
        values.put("lvl", "21");
        values.put("species", "SPECIES_GEODUDE");
        values.put("friendship", "200");
        values.put("ball", "ITEM_MASTER_BALL");
        values.put("gender", "TRAINER_MON_MALE");
        values.put("nature", "TRAINER_PARTY_NATURE(NATURE_HARDY)");
        values.put("isShiny", "TRUE");
        values.put("moves", "{MOVE_GROWL, MOVE_MAGNITUDE, MOVE_TACKLE, MOVE_EXPLOSION}");

        PartyMember member1 = new PartyMember(values);

        assertArrayEquals(member1.ivs, new String[] { "0", "0", "0", "0", "0", "0" });
        assertArrayEquals(member1.evs, new String[] { "0", "0", "0", "0", "0", "0" });
        assertArrayEquals(member1.moves,
                new String[] { "MOVE_GROWL", "MOVE_MAGNITUDE", "MOVE_TACKLE", "MOVE_EXPLOSION" });
        assertEquals(member1.isShiny, "TRUE");
        assertEquals(member1.level, "21");
        assertEquals(member1.species, "SPECIES_GEODUDE");
        assertEquals(member1.nature, "TRAINER_PARTY_NATURE(NATURE_HARDY)");
        assertEquals(member1.nickname, null);
        assertEquals(member1.ability, null);
        assertEquals(member1.friendship, "200");
        assertEquals(member1.gender, "TRAINER_MON_MALE");
        assertEquals(member1.ball, "ITEM_MASTER_BALL");
    }
}
