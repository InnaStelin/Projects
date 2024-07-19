package EditTree;

import TreeApp.TreeMainFrame;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

//
// Drag and drop node call stack:
//
// EditTreeMenuAction::editTree
// TreeEditPanel::TreeEditPanel
// TreeEditPanel::addRightClickListener
// TreeEditPanel::valueChanged
// drop::TransferHandler$DropHandler (javax.swing)
// DnDTransferHandler::importData
// DnDTransferHandler::exportDone
// dragDropEnd::TransferHandler$DragHandler (javax.swing)
//
public class TreeEditPanel extends JTree implements TreeSelectionListener {

    // Contains selected nodes. This list is automatically updated.
    private ArrayList<TreePath> selected;
    private TreeMainFrame app;

     // Constructs a TreeEditPanel from previously viewed tree
    //  by cloning it
    public TreeEditPanel(TreeNode root, TreeMainFrame app) {

        super(root);
        this.app = app;
        // Turn on the JComponent dnd interface
        this.setDragEnabled(true);
        // Setup transfer handler
        this.setTransferHandler(new DnDTransferHandler(this, app));
        // Set tracking of selected nodes
        this.selected = new ArrayList<TreePath>();
        this.addTreeSelectionListener(this);
        this.addRightClickListener();
    }

    //List of selected nodes
    public ArrayList<TreePath> getSelection() {
        return (ArrayList<TreePath>) (this.selected).clone();
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {

        //TreeSelectionEvent e should contain all the nodes whose state changed (selected/non-selected)
        TreePath[] selection = e.getPaths();
        for (int i = 0; i < selection.length; i++) {
            // for each node whose state changed, either add
            // or remove it from the selected nodes array
            if (e.isAddedPath(selection[i])) {
                // node was selected
                this.selected.add(selection[i]);
            } else {
                // node was de-selected
                this.selected.remove(selection[i]);
            }
        }
    }
    //Create EditNodePopupMenu

    public void addRightClickListener() {

        MouseListener mouseListener = new MouseAdapter() {
            private void popupMenuEvent(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                JTree tree = (JTree) e.getSource();
                TreePath path = tree.getPathForLocation(x, y);
                if (path == null)
                    return;

                tree.setSelectionPath(path);

                DefaultMutableTreeNode selectedNode =
                        (DefaultMutableTreeNode)path.getLastPathComponent();
                EditNodePopupMenu menu = new EditNodePopupMenu(app,tree,selectedNode);
                JPopupMenu popup = menu.setupMenu();
                popup.show(tree, x, y);
            }
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) popupMenuEvent(e);
            }
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) popupMenuEvent(e);
            }
        };
        this.addMouseListener(mouseListener);
    }
}