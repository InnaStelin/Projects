package TreeApp;

import javax.swing.*;
import java.awt.*;

public class TreeAppSettings {
    private static final String appDataDir = "C:/Users/innat/TreeApp/src/main/resources/AppData/";
    public static final String defaultTreeDataFile = appDataDir + "TreeData.json";
    private static final String appImagesDir = "C:/Users/innat/TreeApp/src/main/resources/AppImages/";
    public static String frameIcon = appImagesDir + "tree.png";
    public static String pictureIconFile = appImagesDir + "picture.png";
    public static String videoIconFile = appImagesDir + "video.png";
    public static final String nodeImagesDir = "C:/Users/innat/TreeApp/src/main/resources/";
    public static final String fileScheme  = "file:/";
    public static final String nodeImageFileDescription = "JPG images";
    public static final String nodeImageFileExtension = "jpg";
    public static final int viewDividerLocation = 350;
    public static final int mainMenuHeight = 50;
    public static final int maxTreeDepth = 4;
    public static Color treeSelectionColor;
    public static Font mediumFont;
    public static Color foreColor;
    public void setupDisplay() {
        //Set font color and size for all panels and menus
        Font currentFont = UIManager.getFont("TextField.font");
        mediumFont = new Font(currentFont.getName(), currentFont.getStyle(), currentFont.getSize() + 2);
        foreColor = new Color(13, 57, 115);
        treeSelectionColor = new Color(209, 231, 255);
    }
}


