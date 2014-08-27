package pairhero.client.view.util;

import javax.swing.*;

public class Icons {

    public static final String PATH = "/pairhero/icons/";
    private static final String EXTENTION = ".png";

    public static ImageIcon anIcon(String imageName) {
        if (imageName.contains(EXTENTION)) {
            return getImageIcon(imageName, "");
        } else {
            return getImageIcon(imageName, EXTENTION);
        }
    }

    private static ImageIcon getImageIcon(String imageName, String extention) {
        return new ImageIcon(Icons.class.getResource(PATH + imageName + extention));
    }
}
