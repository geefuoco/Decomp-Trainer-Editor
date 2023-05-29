package me.disturbo.ui.party;

import me.disturbo.main.MainActivity;
import me.disturbo.main.Utils;
import me.disturbo.types.PartyMember;
import me.disturbo.ui.MainFrame;
import me.disturbo.ui.extensions.AlphanumericUnderscoreFilter;
import me.disturbo.ui.extensions.ComboBoxFiltered;
import me.disturbo.ui.extensions.SimplifiedDocumentListener;
import me.disturbo.ui.extensions.TextFieldLimiter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class PartyMemberPanel extends JPanel {
    /*
     * The PartyMemberPanel class contains Swing objects that allow for editing the
     * fields of a PartyMember object
     */

    private final MainFrame frame;

    private JTextField level;
    private JTextField friendship;
    private ComboBoxFiltered species;
    private ComboBoxFiltered heldItem;
    private ComboBoxFiltered balls;
    private ComboBoxFiltered natures;
    private ComboBoxFiltered abilities;
    private MovesPanel movesPanel;
    private ValuePanel ivPanel;
    private ValuePanel evPanel;
    private PartyMemberPicPanel picPanel;
    private JCheckBox shiny;

    public PartyMemberPanel(MainFrame frame) {
        this.frame = frame;

        setBackground(Color.WHITE);
        setLayout(new GridLayout(4, 1, 10, 10));

        JPanel general = new JPanel();
        general.setBackground(Color.WHITE);
        general.setLayout(new GridLayout(1, 3, 10, 8));

        createPicPanel(general);
        createIv(general);
        createEv(general);

        JPanel section = new JPanel();
        section.setBackground(Color.WHITE);
        section.setLayout(new GridLayout(2, 2));

        createLevel(section);
        createSpecies(section);
        createHeldItem(section);
        createMoves(section);

        JPanel lowerSection = new JPanel();
        lowerSection.setBackground(Color.WHITE);
        lowerSection.setLayout(new GridLayout(3, 2));

        createFriendship(lowerSection);
        createBall(lowerSection);
        createNature(lowerSection);
        createAbilities(lowerSection);
        createShiny(lowerSection);

        add(general);
        add(section);
        add(lowerSection);
    }

    private final void createAbilities(JPanel panel) {
        JPanel abilityPanel = new JPanel();
        abilityPanel.setBackground(Color.WHITE);
        abilityPanel.setLayout(new GridLayout(2, 1, 4, 4));

        JLabel itemLabel = new JLabel("Ability: ");
        itemLabel.setHorizontalAlignment(JLabel.LEFT);
        itemLabel.setBackground(Color.WHITE);
        abilityPanel.add(itemLabel);

        MainActivity.abilities.add(0, "NONE");

        abilities = new ComboBoxFiltered(
                MainActivity.abilities,
                MainActivity.abilities.get(0),
                new AlphanumericUnderscoreFilter());
        abilities.setPrototypeDisplayValue(
                Utils.getLongestString(MainActivity.abilities.toArray(new String[0])));

        abilityPanel.add(abilities);
        panel.add(abilityPanel);
    }

    private final void createPicPanel(JPanel panel) {
        picPanel = new PartyMemberPicPanel();
        panel.add(picPanel);
    }

    private final void createIv(JPanel panel) {
        ivPanel = new ValuePanel(ValuePanel.VALUE_PANEL_IV, true);
        panel.add(ivPanel);
    }

    private final void createEv(JPanel panel) {
        evPanel = new ValuePanel(ValuePanel.VALUE_PANEL_EV, true);
        panel.add(evPanel);
    }

    private final void createLevel(JPanel panel) {
        JPanel levelPanel = new JPanel();
        levelPanel.setBackground(Color.WHITE);
        levelPanel.setLayout(new GridLayout(2, 1, 4, 4));

        JLabel levelLabel = new JLabel("Level: ");
        levelLabel.setHorizontalAlignment(JLabel.LEFT);
        levelPanel.add(levelLabel);

        level = new JTextField();
        level.setDocument(new TextFieldLimiter(-1, 101));
        levelPanel.add(level);

        panel.add(levelPanel);
    }

    private final void createFriendship(JPanel panel) {
        JPanel friendshipPanel = new JPanel();
        friendshipPanel.setBackground(Color.WHITE);
        friendshipPanel.setLayout(new GridLayout(2, 1, 4, 4));

        JLabel friendshipLabel = new JLabel("Friendship: ");
        friendshipLabel.setHorizontalAlignment(JLabel.LEFT);
        friendshipPanel.add(friendshipLabel);

        friendship = new JTextField();
        friendship.setDocument(new TextFieldLimiter(-1, 256));
        friendshipPanel.add(friendship);

        panel.add(friendshipPanel);
    }

    private final void createShiny(JPanel panel) {
        JPanel shinyPanel = new JPanel();
        shinyPanel.setLayout(new GridLayout(2, 1, 4, 4));
        shinyPanel.setBackground(Color.WHITE);
        JLabel shinyLabel = new JLabel("Shiny: ");
        shinyLabel.setHorizontalAlignment(JLabel.LEFT);

        shiny = new JCheckBox();
        shiny.setBackground(Color.WHITE);
        shinyPanel.add(shinyLabel);
        shinyPanel.add(shiny);
        panel.add(shinyPanel);
    }

    private final void createBall(JPanel panel) {
        JPanel ballPanel = new JPanel();
        ballPanel.setBackground(Color.WHITE);
        ballPanel.setLayout(new GridLayout(2, 1, 4, 4));

        JLabel itemLabel = new JLabel("Ball: ");
        itemLabel.setHorizontalAlignment(JLabel.LEFT);
        ballPanel.add(itemLabel);

        LinkedList<String> ballItems = MainActivity.items
                .stream()
                .filter(item -> {
                    // Filtering out BALL items that are not pokeballs
                    return item.startsWith("ITEM") &&
                            !(item.contains("SMOKE") ||
                                    item.contains("ITEM_TM") ||
                                    item.contains("FIRST") ||
                                    item.contains("LAST") ||
                                    item.contains("LIGHT") ||
                                    item.contains("IRON"))
                            &&
                            item.endsWith("_BALL");
                })
                .collect(Collectors.toCollection(LinkedList::new));

        ballItems.add(0, "NONE");

        balls = new ComboBoxFiltered(
                ballItems,
                ballItems.get(0),
                new AlphanumericUnderscoreFilter());
        balls.setPrototypeDisplayValue(
                Utils.getLongestString(ballItems.toArray(new String[0])));

        ballPanel.add(balls);
        panel.add(ballPanel);
    }

    private final void createNature(JPanel panel) {
        JPanel naturePanel = new JPanel();
        naturePanel.setBackground(Color.WHITE);
        naturePanel.setLayout(new GridLayout(2, 1, 4, 4));

        JLabel itemLabel = new JLabel("Nature: ");
        itemLabel.setHorizontalAlignment(JLabel.LEFT);
        itemLabel.setBackground(Color.WHITE);
        naturePanel.add(itemLabel);

        MainActivity.natures.add(0, "NONE");

        natures = new ComboBoxFiltered(
                MainActivity.natures,
                MainActivity.natures.get(0),
                new AlphanumericUnderscoreFilter());
        natures.setPrototypeDisplayValue(
                Utils.getLongestString(MainActivity.natures.toArray(new String[0])));

        naturePanel.add(natures);
        panel.add(naturePanel);
    }

    private final void createSpecies(JPanel panel) {
        JPanel speciesPanel = new JPanel();
        speciesPanel.setBackground(Color.WHITE);
        speciesPanel.setLayout(new GridLayout(2, 1, 4, 4));

        JLabel speciesLabel = new JLabel("Species: ");
        speciesLabel.setHorizontalAlignment(JLabel.LEFT);
        speciesPanel.add(speciesLabel);

        LinkedList<String> keys = new LinkedList<>(MainActivity.species.keySet());
        species = new ComboBoxFiltered(
                keys,
                keys.get(0),
                new AlphanumericUnderscoreFilter());

        JTextField field = (JTextField) species.getEditor().getEditorComponent();
        field.getDocument()
                .addDocumentListener(new SimplifiedDocumentListener() {
                    @Override
                    public void change(DocumentEvent event) {
                        // Repaint the frame to update the displayed species in the party member list
                        if (field.getText() != null) {
                            String pokemonName = field.getText().substring(field.getText().indexOf("_") + 1);
                            picPanel.setImage(MainActivity.pokemonPicPaths.get(pokemonName));
                            ivPanel.updateTotal();
                            evPanel.updateTotal();
                        }
                        frame.repaint();
                    }
                });

        speciesPanel.add(species);
        panel.add(speciesPanel);

    }

    private final void createHeldItem(JPanel panel) {
        JPanel heldItemPanel = new JPanel();
        heldItemPanel.setBackground(Color.WHITE);
        heldItemPanel.setLayout(new GridLayout(2, 1, 4, 4));

        JLabel itemLabel = new JLabel("Held Item: ");
        itemLabel.setHorizontalAlignment(JLabel.LEFT);
        heldItemPanel.add(itemLabel);

        MainActivity.items.add(0, "NONE");

        heldItem = new ComboBoxFiltered(
                MainActivity.items,
                MainActivity.items.get(0),
                new AlphanumericUnderscoreFilter());
        heldItem.setPrototypeDisplayValue(
                Utils.getLongestString(MainActivity.items.toArray(new String[0])));

        heldItemPanel.add(heldItem);
        panel.add(heldItemPanel);

    }

    private final void createMoves(JPanel panel) {
        movesPanel = new MovesPanel();
        panel.add(movesPanel);
    }

    public final String getSpecies() {
        return ((JTextField) species.getEditor().getEditorComponent()).getText();
    }

    public final void loadPartyMemberData(PartyMember member) {
        if (member.getSpecies() != null) {
            String pokemonName = member.getSpecies().substring(member.getSpecies().indexOf("_") + 1);
            picPanel.setImage(MainActivity.pokemonPicPaths.get(pokemonName));
        }
        ivPanel.setValues(member.getIvs());
        evPanel.setValues(member.getEvs());
        level.setText(member.getLevel());
        species.setSelectedItem(member.getSpecies());
        heldItem.setSelectedItem(member.getHeldItem() != null ? member.getHeldItem() : "NONE");
        balls.setSelectedItem(member.getBall() != null ? member.getBall() : "NONE");
        friendship.setText(member.getFriendship());
        natures.setSelectedItem(member.getNature() != null ? member.getNature() : "NONE");
        abilities.setSelectedItem(member.getAbility() != null ? member.getAbility() : "NONE");
        shiny.setSelected(member.getIsShiny() == "TRUE");
        int movesSize = member.getMoves() != null ? member.getMoves().length : 0;
        for (int i = 0; i < MainActivity.MOVES_MAX; i++) {
            if (i < movesSize) {
                movesPanel.setMove(i, MainActivity.moves.get(member.getMoves()[i]));
            } else {
                movesPanel.setMove(i, MainActivity.moves.values().toArray(new String[0])[0]);
            }
        }
    }

    public final void savePartyMemberData(PartyMember member) {
        member.setIvs(ivPanel.getValues());
        member.setEvs(evPanel.getValues());
        member.setLevel(level.getText());
        member.setSpecies(species.getSelectedItem().toString());
        member.setFriendship(friendship.getText());
        member.setHeldItem(heldItem.getSelectedItem().toString());
        member.setIsShiny(shiny.isSelected() ? "TRUE" : "FALSE");
        member.setBall(balls.getSelectedItem().toString());
        member.setNature(natures.getSelectedItem().toString());
        member.setAbility(abilities.getSelectedItem().toString());

        LinkedList<String> keys = new LinkedList<>(MainActivity.moves.keySet());
        LinkedList<String> values = new LinkedList<>(MainActivity.moves.values());
        String[] moves = new String[4];
        int noneMoveCount = 0;
        for (int i = 0; i < MainActivity.MOVES_MAX; i++) {
            int indexOfMove = values.indexOf(movesPanel.getMove(i));
            if (indexOfMove == 0) {
                noneMoveCount++;
            }
            moves[i] = keys.get(indexOfMove);
        }
        if (noneMoveCount != 4) {
            member.setMoves(moves);
        }
    }
}
