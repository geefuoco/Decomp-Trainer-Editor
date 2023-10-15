package me.disturbo.types;

import java.util.LinkedList;

public class Party extends LinkedList<PartyMember> {
    public String name;

    public Party(String name) {
        this.name = name;
    }

    public static final String extractPartyName(String name) {
        try {
            return name.substring(name.indexOf("(") + 1, name.length() - 1);
        } catch (Exception e) {
            System.out.println("Error when trying to extract party name: " + name);
            System.out.println("WARNING: Trying to parse with old format.");
            // If this still doesn't work, there is a error in the pokeemerald code and we
            // should terminate here
            return name.split("=")[1].replace("}", "");
        }
    }

    final String buildPartyName() {
        return "TRAINER_PARTY" + "(" + this.name + ")";
    }

    public final String getPartyType() {
        // Party type has been changed to only 'TrainerMon'
        return "TrainerMon";
    }

    public final String getPartyFlags() {
        return "F_TRAINER_PARTY_EVERYTHING_CUSTOMIZED";
    }

    public final String buildPartyStruct() {
        StringBuilder struct = new StringBuilder()
                .append("static const struct ")
                .append(this.getPartyType())
                .append(" ")
                .append(this.name)
                .append("[] = {")
                .append(System.lineSeparator());
        for (int i = 0; i < this.size(); i++) {
            struct.append(this.get(i).buildMemberStruct());
            if (i < this.size() - 1) {
                struct.append(", ");
            }
            struct.append(System.lineSeparator());
        }
        struct
                .append("};")
                .append(System.lineSeparator())
                .append(System.lineSeparator());
        return struct.toString();
    }
}
