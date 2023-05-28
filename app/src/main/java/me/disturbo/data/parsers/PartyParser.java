package me.disturbo.data.parsers;

import me.disturbo.data.IndexedLineParser;
import me.disturbo.types.Party;
import me.disturbo.types.PartyMember;

import java.util.HashMap;
import java.util.ArrayList;

public class PartyParser implements IndexedLineParser<Party> {

    private final int END_OFFSET = 3;

    protected ArrayList<HashMap<String, String>> parseIntoMap(String name, String rawParty) {
        String partyType;
        if (rawParty.contains("Customized")) {
            partyType = "TrainerMonCustomized";
        } else {
            partyType = rawParty.substring(rawParty.indexOf("TrainerMon"),
                    rawParty.indexOf("Moves") + "Moves".length());
        }
        String partyDeclaration = "staticconststruct" + partyType + name + "[]={{";
        rawParty = rawParty.replaceAll(System.lineSeparator(), "").replaceAll("\\s+", "");
        rawParty = rawParty.substring(partyDeclaration.length(), rawParty.length() - END_OFFSET);

        String[] partyMembers = rawParty.split("},\\{");
        ArrayList<HashMap<String, String>> partyMembersList = new ArrayList<>();
        try {
            for (String member : partyMembers) {
                HashMap<String, String> values = new HashMap<>();
                int commaIndex = member.endsWith(",") ? 1 : 0;
                member = member.substring(1, member.length() - commaIndex);
                String[] memberData = member.split(",\\.");
                for (String field : memberData) {
                    String key = field.substring(0, field.indexOf("="));
                    String value = field.substring(field.indexOf("=") + 1);
                    values.put(key, value);
                }
                partyMembersList.add(values);
            }
        } catch (Exception e) {
            System.out.println("Error when processing party: " + rawParty);
        }
        return partyMembersList;
    }

    @Override
    public Party parseObject(String name, String rawParty) {
        Party party = new Party(name);
        ArrayList<HashMap<String, String>> partyMembersList = parseIntoMap(name, rawParty);
        partyMembersList.forEach(values -> party.add(new PartyMember(values)));
        return party;
    }
}
