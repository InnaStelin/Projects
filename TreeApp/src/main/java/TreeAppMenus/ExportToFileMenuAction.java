package TreeAppMenus;

import TreeApp.NodeInfo;
import TreeApp.TreeMainFrame;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;

public class ExportToFileMenuAction extends AbstractAction {
    private TreeMainFrame app;
    private String filePath;
    public ExportToFileMenuAction(TreeMainFrame mainApp) {
        super("Export");
        app = mainApp;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
            doExportToFile();
    }
    public void doExportToFile() {
        filePath = selectFile("");
        if (!(filePath == null))
            try {
                exportToFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    private void exportToFile() throws IOException {
        FileWriter writer = new FileWriter(filePath, true);
        writer.write("[");
        writer.write(System.getProperty("line.separator"));

        JTree tree = app.getViewerPanel().getTree();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
        Enumeration e = root.breadthFirstEnumeration();
        DefaultMutableTreeNode node = null;

        while (e.hasMoreElements()) {
            if (node != null) {
                writer.write(",");
                writer.write(System.getProperty("line.separator"));
            }
            node = (DefaultMutableTreeNode) e.nextElement();

            Object nodeObj = node.getUserObject();
            NodeInfo currentNode = (NodeInfo) nodeObj;
            writer.write("{");
            writer.write(System.getProperty("line.separator"));
            writer.write("\"parent\":\"" + currentNode.getParent() + "\"" + ",");
            writer.write(System.getProperty("line.separator"));
            writer.write("\"index\":\""+  currentNode.getIndex() + "\"" + ",");
            writer.write(System.getProperty("line.separator"));
            writer.write("\"nodeId\":\""+ currentNode.getNodeId() + "\"" + ",");
            writer.write(System.getProperty("line.separator"));
            writer.write( "\"depth\":\""+ currentNode.getDepth() + "\"" + ",");
            writer.write(System.getProperty("line.separator"));
            writer.write("\"caption\":\""+ currentNode.getCaption() + "\"" + ",");
            writer.write(System.getProperty("line.separator"));
            writer.write("\"nodeType\":\""+ currentNode.getNodeType() + "\"" + ",");
            writer.write(System.getProperty("line.separator"));
            writer.write("\"nodeData\":\""+ currentNode.getNodeData() + "\"" + ",");
            writer.write(System.getProperty("line.separator"));
            writer.write("\"search\":\""+ currentNode.getSearchString() + "\"");
            writer.write(System.getProperty("line.separator"));
            writer.write("}");
        }
        writer.write(System.getProperty("line.separator"));
        writer.write("]");
        writer.write(System.getProperty("line.separator"));
        writer.close();
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

