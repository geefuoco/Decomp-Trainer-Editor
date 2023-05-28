package me.disturbo.ui.party;

import javax.swing.*;
import java.awt.*;
import me.disturbo.ui.extensions.TextFieldLimiter;

public class ValuePanel extends JPanel {

    
    private InnerValuePanel hpIv;
    private InnerValuePanel atkIv;
    private InnerValuePanel defIv;
    private InnerValuePanel spAtkIv;
    private InnerValuePanel spDefIv;
    private InnerValuePanel spdIv;
    private JLabel valueLabel;

    public static int VALUE_PANEL_IV = 0;
    public static int VALUE_PANEL_EV = 1;
    
    public ValuePanel(int value_panel) {
        setLayout(new GridLayout(7, 1));
        setBackground(Color.WHITE);
        if(value_panel == VALUE_PANEL_IV){
            valueLabel = new JLabel("IVs:");
            hpIv = new InnerValuePanel("HP:", new TextFieldLimiter(-1, 32));
            atkIv = new InnerValuePanel("ATK:",  new TextFieldLimiter(-1, 32));
            defIv = new InnerValuePanel("DEF:", new TextFieldLimiter(-1, 32));
            spAtkIv = new InnerValuePanel("SP ATK:", new TextFieldLimiter(-1, 32));
            spDefIv = new InnerValuePanel("SP DEF:", new TextFieldLimiter(-1, 32));
            spdIv = new InnerValuePanel("SPD:", new TextFieldLimiter(-1, 32));
        } else if(value_panel == VALUE_PANEL_EV) {
            valueLabel = new JLabel("EVs:");
            hpIv = new InnerValuePanel("HP:", new TextFieldLimiter(-1, 253));
            atkIv = new InnerValuePanel("ATK:",  new TextFieldLimiter(-1, 253));
            defIv = new InnerValuePanel("DEF:", new TextFieldLimiter(-1, 253));
            spAtkIv = new InnerValuePanel("SP ATK:", new TextFieldLimiter(-1, 253));
            spDefIv = new InnerValuePanel("SP DEF:", new TextFieldLimiter(-1, 253));
            spdIv = new InnerValuePanel("SPD:", new TextFieldLimiter(-1, 253));
        }
        this.add(valueLabel);
        this.add(hpIv);
        this.add(atkIv);
        this.add(defIv);
        this.add(spAtkIv);
        this.add(spDefIv);
        this.add(spdIv);
    }
    
    public String[] getValues() {
        return new String[] {
            hpIv.getValue(),
            atkIv.getValue(),
            defIv.getValue(),
            spAtkIv.getValue(),
            spDefIv.getValue(),
            spdIv.getValue()
        };
    }

    public void setValues(String[] values) {
        if(values == null) {
            values = new String[]{"0", "0", "0", "0", "0", "0"};
        }
        assert values.length == 6;
        hpIv.setValue(values[0]);
        atkIv.setValue(values[1]);
        defIv.setValue(values[2]);
        spAtkIv.setValue(values[3]);
        spDefIv.setValue(values[4]);
        spdIv.setValue(values[5]);
    }


    private class InnerValuePanel extends JPanel {
        private JTextField value;

        public InnerValuePanel(String labelText, TextFieldLimiter limiter){
            this.setLayout(new GridLayout(1, 2));
            JLabel label = new JLabel(labelText);
            label.setHorizontalAlignment(SwingConstants.LEFT);

            this.setBackground(Color.WHITE);
            
            value = new JTextField();
            value.setDocument(limiter);
            value.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(label);
            this.add(value);
        }

        public String getValue(){
            return this.value.getText();
        }

        public void setValue(String value){
            this.value.setText(value);
        }
    }
}
