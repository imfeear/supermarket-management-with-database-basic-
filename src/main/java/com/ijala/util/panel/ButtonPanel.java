package com.ijala.util.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import com.ijala.util.ButtonUtil;

public class ButtonPanel extends JPanel {
    private ButtonUtil cancelButton;
    private ButtonUtil actionButton;

    public ButtonPanel(String actionButtonText, ActionListener buttonAction) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.decode("#2B2B2B"));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        cancelButton = new ButtonUtil("Cancelar", e -> closePanel());
        cancelButton.setBackground(Color.decode("#FF0000"));

        actionButton = new ButtonUtil(actionButtonText, buttonAction);

        add(cancelButton);
        add(Box.createHorizontalStrut(60));
        add(actionButton);
    }

    private void closePanel() {
        Container parent = SwingUtilities.getWindowAncestor(this);
        if (parent instanceof JFrame) {
            ((JFrame) parent).dispose();
        } else if (parent instanceof JDialog) {
            ((JDialog) parent).dispose();
        } else {
            Container parentContainer = this.getParent();
            if (parentContainer != null) {
                parentContainer.remove(this);
                parentContainer.revalidate();
                parentContainer.repaint();
            }
        }
    }
}
