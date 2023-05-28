package me.disturbo.ui.party;

import me.disturbo.main.MainActivity;
import me.disturbo.main.Utils;
import me.disturbo.ui.extensions.ComboBoxFiltered;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class MovePanel extends JPanel {
    /*
            The MovesPanel class contains spacing and the combo box required for inputting moves
    */

    private final ComboBoxFiltered moveBox;

    public MovePanel(){
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1, 1, 0, 5));

        LinkedList<String> values = new LinkedList<>(MainActivity.moves.values());
        moveBox = new ComboBoxFiltered(values, values.get(0), new MovesFilter());
        moveBox.setPrototypeDisplayValue(
            Utils.getLongestString(values.toArray(new String[0]))
        );
        add(moveBox);
    }

    public final void setSelectedItem(String item){
        moveBox.setSelectedItem(item);
    }

    public final String getSelectedItem(){
        return moveBox.getSelectedItem().toString();
    }
}
