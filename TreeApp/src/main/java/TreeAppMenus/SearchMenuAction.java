package TreeAppMenus;

import EditTree.TreeEditPanel;
import TreeApp.TreeMainFrame;
import TreeApp.NodeInfo;
import TreeApp.TreeViewPanel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

public class SearchMenuAction extends AbstractAction  {
    JEditorPane editNodePane;
    private TreeMainFrame app;
    public SearchMenuAction(TreeMainFrame mainApp) {

        super("Search Tree");
        app = mainApp;
        editNodePane = mainApp.getEditNodePane();

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        createSearchWindow();
    }
    private void createSearchWindow() {
        String result = (String) JOptionPane.showInputDialog(
                editNodePane,
                "Search For:",
                "Search tree",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                ""
        );
        //showInputDialog() will return the string user has entered
        // if the user hits ok; returns null otherwise.
        if(!(result == null)){
            if (app.getEditorPanel() != null) {

                searchTreeEditMode(result);
            } else {
                searchTreeViewMode(result);
            }
        }
    }
    public void searchTreeViewMode(String searchString) {

        TreeViewPanel viewPanel = app.getViewerPanel();
        DefaultMutableTreeNode root =
                (DefaultMutableTreeNode) viewPanel.getTree().getModel().getRoot();
        viewPanel.getTree().clearSelection();

        Enumeration e = root.breadthFirstEnumeration();
        while (e.hasMoreElements()) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();

            Object nodeObj = node.getUserObject();
            NodeInfo currentNode = (NodeInfo) nodeObj;
            String s = currentNode.getCaption();
            if (s.contains(searchString)) {
               TreePath path = new TreePath(node.getPath());
               viewPanel.getTree().addSelectionPath(path);
               viewPanel.getTree().scrollPathToVisible(path);
            }
        }
    }
    public void searchTreeEditMode(String searchString) {
        TreeEditPanel editorPanel = app.getEditorPanel();
        DefaultMutableTreeNode root =
                (DefaultMutableTreeNode) editorPanel.getModel().getRoot();
        editorPanel.clearSelection();

        Enumeration e = root.breadthFirstEnumeration();
        while (e.hasMoreElements()) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();

            Object nodeObj = node.getUserObject();
            NodeInfo currentNode = (NodeInfo) nodeObj;
            String s = currentNode.getCaption();
            if (s.contains(searchString)) {
                TreePath path = new TreePath(node.getPath());
                editorPanel.addSelectionPath(path);
                editorPanel.scrollPathToVisible(path);
            }
        }
    }
}
