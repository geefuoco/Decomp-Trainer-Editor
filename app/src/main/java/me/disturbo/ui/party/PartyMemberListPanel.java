package me.disturbo.ui.party;

import me.disturbo.main.MainActivity;
import me.disturbo.main.Utils;
import me.disturbo.types.PartyMember;
import me.disturbo.ui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class PartyMemberListPanel extends JPanel {
    /*
            The PartyMemberListPanel class contains the list of pokemon in a party and the control buttons:
            - Add: adds a new pokemon at the bottom of the party
            - Move up/down: moves the currently selected pokemon (if there is one) up or down but not further up than the first or the last pokemon in the party
            - Remove: removes the selected pokemon (or the last one in case there is none selected)

            <partyList> is updated when changing trainer, when using one of the control buttons and when inputting into PartyMemberPanel's species field
    */
    
    private JList<PartyMember> partyList;
    private int membersCount = 0;

    private final MainFrame frame;
    private final PartyPanel panel;

    public PartyMemberListPanel(MainFrame frame, PartyPanel panel){
        this.frame = frame;
        this.panel = panel;

        setBackground(Color.WHITE);
        createListPanel();
    }

    private final void createListPanel(){
        JPanel listPanel = new JPanel();
        listPanel.setBackground(Color.WHITE);
        listPanel.setLayout(new GridLayout(4, 1, 0, 5));

        createList(listPanel);
        createAddAndRemoveButtons(listPanel);
        createMoveButtons(listPanel);
        setAlignmentX(SwingConstants.LEFT);
        setAlignmentY(SwingConstants.TOP);
        add(listPanel);
    }

    private final void createList(JPanel pane){
        partyList = new JList<>();
        partyList.setCellRenderer(new SpeciesRenderer(panel));
        partyList.setPrototypeCellValue(new PartyMember(Utils.getLongestString(MainActivity.species.values().toArray(new String[0]))));
        partyList.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        partyList.addListSelectionListener(event -> {
            if(!event.getValueIsAdjusting() && partyList.getSelectedIndex() != -1){
                //If the selected value is not an empty row in the list

                if(partyList.getSelectedValue() != null && partyList.getSelectedValue().species != null) {
                    panel.switchPartyMemberData(partyList.getSelectedValue());
                    frame.repaint();
                }
            }
        });
        pane.add(partyList);
    }

    private final void createAddAndRemoveButtons(JPanel pane){
        JPanel addRemovePanel = new JPanel();
        addRemovePanel.setBackground(Color.WHITE);
        addRemovePanel.setLayout(new GridLayout(1, 2));

        JButton add = new JButton("Add");
        add.setLayout(new GridBagLayout());
        add.addActionListener(event ->{
            if(membersCount < MainActivity.PARTY_MAX) {
                PartyMember member = new PartyMember();
                addToPartyList(member);
                panel.loadPartyMemberData(member);
                partyList.setSelectedIndex(membersCount - 1);
            }
        });

        JButton remove = new JButton("Remove");
        remove.setLayout(new GridBagLayout());
        remove.addActionListener(event ->{
            if(membersCount - 1 > 0){
                int index = partyList.getSelectedIndex();
                // If there is no index selected set the index to the last pokemon
                index = index != -1 ? index : membersCount - 1;
                // If the selected value is not an empty row in the list
                if(!partyList.getModel().getElementAt(index).equals(" ")){
                    removeFromPartyList(index);
                    partyList.setSelectedIndex(index - (index - 1 >= 0 ? index - 1 : 0));
                }
            }
        });
        addRemovePanel.add(add);
        addRemovePanel.add(remove);
        pane.add(addRemovePanel);
    }


    private final void createMoveButtons(JPanel pane){
        JPanel move = new JPanel();
        move.setBackground(Color.WHITE);
        move.setLayout(new GridBagLayout());
        createMoveUpButton(move);
        createMoveDownButton(move);
        pane.add(move);
    }

    private final void createMoveUpButton(JPanel pane){
        JButton moveUp = new JButton("Move up");
        moveUp.addActionListener(event ->{
            int index = partyList.getSelectedIndex();
            // If there is an index selected other than 0 and ...
            if(index > 0 && membersCount > 1){
                moveInPartyList(index, index - 1);
                partyList.setSelectedIndex(index - 1);
            }
        });
        pane.add(moveUp);
        pane.add(Box.createHorizontalStrut(5));
    }

    private final void createMoveDownButton(JPanel pane){
        JButton moveDown = new JButton("Move down");
        moveDown.addActionListener(event ->{
            int index = partyList.getSelectedIndex();
            // If there is an index selected and ...
            if(index > -1 && index < membersCount - 1 && membersCount > 1){
                moveInPartyList(index, index + 1);
                partyList.setSelectedIndex(index + 1);
            }
        });
        pane.add(moveDown);
    }

    public final int getMembersCount(){
        return membersCount;
    }

    // Creates a modifiable version of the combo box's model
    public final DefaultListModel<PartyMember> generateModel(){
        DefaultListModel<PartyMember> model = new DefaultListModel<>();
        ListModel<PartyMember> current = partyList.getModel();
        for(int index = 0; index < current.getSize(); index++){
            model.addElement(current.getElementAt(index));
        }
        return model;
    }

    public final void loadPartyToList(LinkedList<PartyMember> party){
        DefaultListModel<PartyMember> model = generateModel();

        // Clear current party, reset members count and add new members
        model.removeAllElements();
        membersCount = 0;
        for(PartyMember member : party){
            model.addElement(member);
            membersCount++;
        }

        // Fill the party with empty slots until PARTY_MAX is reached
        while(model.getSize() < MainActivity.PARTY_MAX){
            model.addElement(PartyMember.createPartyMemberPlaceholder());
        }
        partyList.setModel(model);
        partyList.setSelectedIndex(0);
    }

    public final void addToPartyList(PartyMember member){
        DefaultListModel<PartyMember> model = generateModel();
        model.set(membersCount, member);
        membersCount++;
        partyList.setModel(model);
    }

    public final void moveInPartyList(int from, int to){
        DefaultListModel<PartyMember> model = generateModel();
        PartyMember member = model.get(from);
        //Base cases
        if(!member.species.equals(" ") && from != to) {
            if(from >= to) from++;
            else to++;
            model.add(to, member);
            model.removeElementAt(from);
            partyList.setModel(model);
        }
    }

    public final void removeFromPartyList(int index){
        DefaultListModel<PartyMember> model = generateModel();
        model.removeElementAt(index);
        model.addElement(new PartyMember(" "));
        membersCount--;
        partyList.setModel(model);
    }
}
