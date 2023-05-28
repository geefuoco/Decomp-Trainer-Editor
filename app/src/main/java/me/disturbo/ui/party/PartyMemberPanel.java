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

public class PartyMemberPanel extends JPanel{
    /*
            The PartyMemberPanel class contains Swing objects that allow for editing the fields of a PartyMember object
    */

    private final MainFrame frame;

    private JTextField level;
    private ComboBoxFiltered species;
    private ComboBoxFiltered heldItem;
    private MovesPanel movesPanel;
    private ValuePanel ivPanel;
    private ValuePanel evPanel;
    private PartyMemberPicPanel picPanel;

    public PartyMemberPanel(MainFrame frame){
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

        add(general);
        add(section);
    }

    private final void createPicPanel(JPanel panel) {
        picPanel = new PartyMemberPicPanel();
        panel.add(picPanel);
    }


    private final void createIv(JPanel panel){
        ivPanel = new ValuePanel(ValuePanel.VALUE_PANEL_IV);
        panel.add(ivPanel);
    }

    private final void createEv(JPanel panel){
        evPanel = new ValuePanel(ValuePanel.VALUE_PANEL_EV);
        panel.add(evPanel);
    }

    private final void createLevel(JPanel panel){
        JPanel levelPanel = new JPanel();
        levelPanel.setBackground(Color.WHITE);
        levelPanel.setLayout(new GridLayout(2, 1, 4, 4));

        JLabel levelLabel = new JLabel("Level: ");
        levelLabel.setHorizontalAlignment(JLabel.LEFT);
        levelPanel.add(levelLabel);

        level = new JTextField();
        level.setDocument(new TextFieldLimiter(-1, 255));
        levelPanel.add(level);

        panel.add(levelPanel);

    }

    private final void createSpecies(JPanel panel){
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
            new AlphanumericUnderscoreFilter()
        );

        JTextField field = (JTextField) species.getEditor().getEditorComponent();
        field.getDocument()
             .addDocumentListener(new SimplifiedDocumentListener(){
            @Override
            public void change(DocumentEvent event){
                // Repaint the frame to update the displayed species in the party member list
                frame.repaint();
            }
        });

        speciesPanel.add(species);
        panel.add(speciesPanel);

    }

    private final void createHeldItem(JPanel panel){
        JPanel heldItemPanel = new JPanel();
        heldItemPanel.setBackground(Color.WHITE);
        heldItemPanel.setLayout(new GridLayout(2, 1, 4, 4));

        JLabel itemLabel = new JLabel("Held Item: ");
        itemLabel.setHorizontalAlignment(JLabel.LEFT);
        heldItemPanel.add(itemLabel);

        heldItem = new ComboBoxFiltered(
            MainActivity.items, 
            MainActivity.items.get(0), 
            new AlphanumericUnderscoreFilter()
        );
        heldItem.setPrototypeDisplayValue(
            Utils.getLongestString(MainActivity.items.toArray(new String[0]))
        );

        heldItemPanel.add(heldItem);
        panel.add(heldItemPanel);

    }

    private final void createMoves(JPanel panel){
        movesPanel = new MovesPanel();
        panel.add(movesPanel);
    }
    

    public final String getSpecies(){
        return ((JTextField) species.getEditor().getEditorComponent()).getText();
    }

    public final void loadPartyMemberData(PartyMember member){
        if (member.species != null){
            String pokemonName = member.species.substring(member.species.indexOf("_") + 1);
            picPanel.setImage(MainActivity.pokemonPicPaths.get(pokemonName));
        }
        ivPanel.setValues(member.ivs);
        evPanel.setValues(member.evs);
        level.setText(member.level);
        species.setSelectedItem(member.species);
        heldItem.setSelectedItem(member.heldItem != null ? member.heldItem : MainActivity.items.get(0));
        int movesSize = member.moves != null ? member.moves.length : 0;
        for(int i= 0; i< MainActivity.MOVES_MAX; i++){
            if(i < movesSize) {
                movesPanel.setMove(i, MainActivity.moves.get(member.moves[i]));
            }
            else {
                movesPanel.setMove(i, MainActivity.moves.values().toArray(new String[0])[0]);
            }
        }
    }


    public final void savePartyMemberData(PartyMember member){
        member.ivs = ivPanel.getValues();
        member.evs = evPanel.getValues();//ensure evs dont go over 510 --252 max on one stat
        member.level = level.getText();
        member.species = species.getSelectedItem().toString();
        member.heldItem = heldItem.getSelectedItem().toString();
        LinkedList<String> keys = new LinkedList<>(MainActivity.moves.keySet());
        LinkedList<String> values = new LinkedList<>(MainActivity.moves.values());
        String[] moves = new String[4];
        for(int i = 0; i < MainActivity.MOVES_MAX; i++){
            int indexOfMove = values.indexOf(movesPanel.getMove(i));
            moves[i] = keys.get(indexOfMove);
        }
        member.moves = moves;
    }
}
