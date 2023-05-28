package org.example.dialog;

import java.awt.*;
import javax.swing.*;
import lombok.Getter;

@Getter
public class ChangeProgressDialog extends JDialog {
    private final JTextField textField;
    private boolean confirmed;

    public ChangeProgressDialog(JFrame owner, int progress, int progressMax) {
        super(owner, "Change progress", true);
        this.confirmed = false;
        textField = new JTextField(String.valueOf(progress));
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            try {
                int newProgress = Integer.parseInt(textField.getText());
                if (newProgress <= progressMax) {
                    confirmed = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "You can't enter more than " + progressMax);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "That is not a number");
            }
        });
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Enter progress:"), BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);
        panel.add(okButton, BorderLayout.SOUTH);
        setContentPane(panel);
        pack();
        setLocationRelativeTo(owner);
    }

    public int getNewProgress() {
        return Integer.parseInt(textField.getText());
    }
}
