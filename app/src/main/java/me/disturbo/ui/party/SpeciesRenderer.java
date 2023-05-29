package me.disturbo.ui.party;

import me.disturbo.main.MainActivity;
import me.disturbo.types.PartyMember;

import javax.swing.*;
import java.awt.*;

public class SpeciesRenderer extends JLabel implements ListCellRenderer<PartyMember>{
    private final PartyPanel panel;

    public SpeciesRenderer(PartyPanel panel){
        this.panel = panel;
    }

    @Override
    public final Component getListCellRendererComponent(JList<? extends PartyMember> list, PartyMember value, int index, boolean isSelected, boolean cellHasFocus) {
        setOpaque(true);

        String display;
        String savedSpecies = MainActivity.species.get(value.getSpecies());

        if(value == panel.getCurrentLoaded()){
            String species = MainActivity.species.get(panel.getSpeciesText());
            if(species != null) display = " " + species;
            else{
                if(savedSpecies != null) display = " " + savedSpecies;
                else display = " " + value.getSpecies();
            }
        }
        else display = savedSpecies != null ? " " + savedSpecies : " " + value.getSpecies();

        setText(display);

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }
}
