package TreeAppMenus;

import TreeApp.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class OpenFileMenuAction extends AbstractAction {
    TreeMainFrame app;
    TreeViewPanel viewPanel;
    public OpenFileMenuAction(TreeMainFrame mainApp) {
        super("Open");
        app = mainApp;
        viewPanel = app.getViewerPanel();
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        try {
            openFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    private void openFile() throws IOException, ParseException {
        String filePath = selectFile("");
        if (!(filePath == null)) {
            TreeBuilder builder = new TreeBuilder();
            DefaultMutableTreeNode rootNode = builder.buildTree(filePath, TreeAppSettings.maxTreeDepth);

            JTree tree = this.viewPanel.getTree();
            TreeModel m = new DefaultTreeModel(rootNode);
            tree.setModel(m);
            this.viewPanel.setVisible(true);

        }
    }
    private String selectFile(String initPath) {
        File selectedFile = new File(initPath);
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(selectedFile);
        FileNameExtensionFilter filter =
                new FileNameExtensionFilter("TXT files", "txt");
        fileChooser.addChoosableFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        return null;
    }
}