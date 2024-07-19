package TreeApp;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class NodeDataFileSelector implements NodeDataFileSelectorUtils {
    public String selectFile(String initPath) {
        File selectedFile= new File (initPath);
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(selectedFile);
        FileNameExtensionFilter filter =
                new FileNameExtensionFilter(TreeAppSettings.nodeImageFileDescription,
                        TreeAppSettings.nodeImageFileExtension);
        fileChooser.addChoosableFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
        }
        return selectedFile.getAbsolutePath();
    }
}
