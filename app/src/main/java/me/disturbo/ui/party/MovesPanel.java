package me.disturbo.ui.party;

import me.disturbo.main.MainActivity;

import javax.swing.*;
import java.awt.*;

public class MovesPanel extends JPanel {
    /*
            The MovesPanel class contains MOVES_MAX MovePanels, one for each move of a pokemon
    */

    private MovePanel[] movesPanels = new MovePanel[MainActivity.MOVES_MAX];

    public MovesPanel(){
        setLayout(new GridLayout(5, 1));
        setBackground(Color.WHITE);

        JLabel movesLabel = new JLabel("Moves: ");
        movesLabel.setHorizontalAlignment(JLabel.LEFT);
        add(movesLabel);

        for(int index = 0; index < MainActivity.MOVES_MAX; index++){
            MovePanel movePanel = new MovePanel();
            movesPanels[index] = movePanel;
            add(movePanel);
        }
    }

    public String getMove(int index){
        return movesPanels[index].getSelectedItem();
    }

    public void setMove(int index, String move){
        movesPanels[index].setSelectedItem(move);
    }
}
