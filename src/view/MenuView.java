package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuView extends JPanel implements BlackJackPanelView {
    private JLabel titleLabel;
    private JButton newGameButton;
    private JButton optionsButton;
    private JButton exitButton;

    public MenuView() {
        initialize();
    }

    @Override
    public String getViewName() {
        return "MENU";
    }

    @Override
    public void initialize() {
        titleLabel = new JLabel();
        optionsButton = new JButton();
        newGameButton = new JButton();
        exitButton = new JButton();


        //---- optionsButton ----
        optionsButton.setText("Opzioni");

        //---- newGameButton ----
        newGameButton.setText("Nuova Partita");

        //---- exitButton ----
        exitButton.setText("Esci");

        //---- Titolo ----
        titleLabel.setText("JBlackJack");
        titleLabel.setFont(new Font("Source Code Pro Black", Font.PLAIN, 48));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(320, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(newGameButton, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(optionsButton, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
                                .addGap(320, 320, 320))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(220, 220, 220)
                                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(220, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                                .addComponent(newGameButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(optionsButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90))
        );
    }

    @Override
    public void updateView() {
        // Nothing to update in menu view
    }

    /**
     * Set action listener for the new game button
     */
    public void setNewGameButtonListener(ActionListener listener) {
        newGameButton.addActionListener(listener);
    }

    public void setOptionsButtonListener(ActionListener listener) {
        optionsButton.addActionListener(listener);
    }

    /**
     * Set action listener for the exit button
     */
    public void setExitButtonListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }
}
