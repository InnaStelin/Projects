package EditTree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.io.IOException;
import TreeApp.TreeMainFrame;
import TreeApp.TreeAppSettings;
import TreeAppMenus.AddNodeMenuAction;
import TreeAppMenus.EditNodeMenuAction;
import TreeAppMenus.DeleteNodeMenuAction;

public class EditNodePopupMenu implements EditNodePopupMenuUtils{
    private TreeMainFrame app;
    private DefaultMutableTreeNode selectedNode;
    private JTree tree;
    public EditNodePopupMenu(TreeMainFrame mainApp,
                             JTree editTree,
                             DefaultMutableTreeNode selected) {

        app = mainApp;
        selectedNode = selected;
        tree = editTree;
    }
    public JPopupMenu setupMenu() {

        JPopupMenu popup = new JPopupMenu();
        Font mediumFont = TreeAppSettings.mediumFont;
        Color foreColor = TreeAppSettings.foreColor;

        //Add node
        JMenuItem addNode = new JMenuItem("Add Node");
        addNode.setFont(mediumFont);
        addNode.setForeground(foreColor);
        popup.add(addNode);
        addNode.addActionListener(actionEvent -> {
            try {
                onAddNode(selectedNode, tree);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        //Edit node
        JMenuItem editNode = new JMenuItem("Edit Node");
        editNode.setFont(mediumFont);
        editNode.setForeground(foreColor);

        popup.add(editNode);
        editNode.addActionListener(actionEvent -> {
            try {
                onEditNode(selectedNode);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JMenuItem deleteNode = new JMenuItem("Delete Node");
        deleteNode.setFont(mediumFont);
        deleteNode.setForeground(foreColor);

        popup.add(deleteNode);
        deleteNode.addActionListener(actionEvent -> onDeleteNode(selectedNode, tree));
        return popup;
    }
    public void onAddNode(DefaultMutableTreeNode selectedNode, JTree tree) throws IOException {
        AddNodeMenuAction addNode = new AddNodeMenuAction();
        try {
            addNode.init(selectedNode, tree, app);
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }
    public void onEditNode(DefaultMutableTreeNode selectedNode) throws IOException {
        EditNodeMenuAction editNode = new EditNodeMenuAction();
        editNode.init(selectedNode, app);
    }
    public void onDeleteNode(DefaultMutableTreeNode selectedNode, JTree tree) {
        DeleteNodeMenuAction deleteNode = new DeleteNodeMenuAction();
        deleteNode.deleteNode(selectedNode, tree);
    }
}
