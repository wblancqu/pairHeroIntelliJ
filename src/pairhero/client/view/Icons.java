package pairhero.client.view;

import javax.swing.*;

public class Icons {

    private static final String PATH = "/pairhero/icons/";

    public static ImageIcon anIcon(String imageName) {
        return new ImageIcon(Icons.class.getResource(PATH + imageName + ".png"));
    }
}
