package me.disturbo.ui.party;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import me.disturbo.main.MainActivity;

public class PartyMemberPicPanel extends JPanel {

    private ImageIcon icon;
    private JLabel label;

    public PartyMemberPicPanel() {
        setLayout(new GridLayout(2, 1, 0, 5));
        setBackground(Color.WHITE);


        icon = new ImageIcon();
        label = new JLabel("Sprite:");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setIcon(icon);
        add(label);
    }

    public final void setImage(String path){
        icon = new ImageIcon(MainActivity.projectDirectory.getPath() +
            File.separator +
            path);

        Image image = icon.getImage();
        image = createImage(new FilteredImageSource(
                image.getSource(),
                new CropImageFilter(0, 0, 64, 64)));

        icon.setImage(image);
        label.setIcon(icon);
    }

}
