package view;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a custom button
 */
public class MyButton extends JButton {
    /**
     * Constructor
     * @param text the button text
     */
    public MyButton(String text) {
        super(text);
        this.setBackground(new Color(99, 15, 15));
        this.setForeground(new Color(228, 132, 0));
        this.setOpaque(true);
    }

}
