package pairhero.client.view;

import javax.swing.*;
import java.awt.*;

public class PopUpNotification extends JWindow {

    public PopUpNotification(Frame owner, ImageIcon icon) {
        super(owner);
        setBackground(new Color(0, 0, 0, 0));
        setAlwaysOnTop(true);
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(false);
        setContentPane(contentPane);
        IconLabel iconLabel = new IconLabel(icon);
        add(iconLabel);
        pack();

        setLocation(owner.getX() + (int) (owner.getWidth() - iconLabel.getSize().getWidth()) / 2,
                owner.getY() + (int) (owner.getHeight() - iconLabel.getSize().getHeight() - 100));
    }

    private class IconLabel extends JLabel {
        public IconLabel(ImageIcon icon) {
            super();
            setOpaque(false);
            setIcon(icon);
            setBackground(null);
        }
    }
}
