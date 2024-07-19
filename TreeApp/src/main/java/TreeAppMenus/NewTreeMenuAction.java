package TreeAppMenus;

import EditTree.TreeEditPanel;
import TreeApp.TreeMainFrame;
import TreeApp.TreeBuilder;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
public class NewTreeMenuAction extends AbstractAction {
    private TreeMainFrame app;
    public NewTreeMenuAction(TreeMainFrame mainApp) {
        super("New Tree");
        app = mainApp;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        newTree();
    }
    private void newTree() {
        TreeBuilder builder = new TreeBuilder();
        DefaultMutableTreeNode newRootNode = builder.newRootNode();
        JTree tree = app.getViewerPanel().getTree();
        TreeModel m = new DefaultTreeModel(newRootNode);
        tree.setModel(m);

        TreePath path = new TreePath(newRootNode.getPath());
        tree.setSelectionPath(path);
        tree.scrollPathToVisible(path);
        tree.updateUI();

        if (app.getEditorPanel() != null) {
            TreeEditPanel editTree = app.getEditorPanel();
            editTree.setModel(m);
            editTree.updateUI();
        } else {
            //Assume that user will want to add new nodes to the tree
            //that only had root node that was just created
            EditTreeMenuAction editMenu = new EditTreeMenuAction(app);
            editMenu.actionPerformed(null);
        }
    }
}
