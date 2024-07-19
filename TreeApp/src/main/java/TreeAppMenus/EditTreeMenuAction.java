package TreeAppMenus;

import EditTree.TreeEditPanel;
import TreeApp.TreeMainFrame;
import TreeApp.CustomTreeCellRenderer;
import TreeApp.TreeAppSettings;
import TreeApp.TreeViewPanel;
import EditTree.DnDNode;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EditTreeMenuAction extends AbstractAction {

    private final TreeMainFrame app;
    private final JFrame mainFrame;
    private final TreeViewPanel viewPanel;

    public EditTreeMenuAction(TreeMainFrame app) {
        super("Edit Tree");
        this.app = app;
        mainFrame = app.getMainFrame();
        viewPanel = app.getViewerPanel();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        editTree();
    }
    private void editTree() {
        JTree model = viewPanel.getTree();
        viewPanel.setVisible(false);

        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getModel().getRoot();
        DnDNode rootNode = cloneNode(root);

        TreeEditPanel editTree = new TreeEditPanel(rootNode, app);
        editTree.setFont(TreeAppSettings.mediumFont);
        editTree.setCellRenderer(new CustomTreeCellRenderer());
        editTree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        //Add tree to scroll pane
        JScrollPane editTreeScrollPane = new JScrollPane(editTree);
        JEditorPane editNodePane = new JEditorPane();
        JScrollPane editNodeScrollPane = new JScrollPane(editNodePane);

        //Add the scroll panes to a split pane.
        JSplitPane editorContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        editorContainer.setTopComponent(editTreeScrollPane);
        editorContainer.setBottomComponent(editNodeScrollPane);

        Dimension minimumSize = new Dimension(350, 50);
        editTreeScrollPane.setMinimumSize(minimumSize);
        editorContainer.setDividerLocation(350);
        editorContainer.setPreferredSize(new Dimension(1000, 700));

        mainFrame.add(editorContainer);
        mainFrame.pack();
        mainFrame.setVisible(true);

        app.setEditorPanel(editTree);
        app.setEditNodePane(editNodePane);
        app.setEditorContainer(editorContainer);
    }
    public DnDNode cloneNode(DefaultMutableTreeNode node) {
        DnDNode newNode = new DnDNode();
        newNode.setUserObject(node.getUserObject());

        for(int iChildren=node.getChildCount(), i=0; i < iChildren; i++) {
            newNode.add(cloneNode((DefaultMutableTreeNode) node.getChildAt(i)));
        }
        return newNode;
    }
}

