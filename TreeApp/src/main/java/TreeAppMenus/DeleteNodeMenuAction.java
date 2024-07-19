package TreeAppMenus;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class DeleteNodeMenuAction {
    public void deleteNode(DefaultMutableTreeNode selectedNode, JTree tree) {

        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent();

        DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
        model.removeNodeFromParent(selectedNode);
        model.nodeChanged(selectedNode);

        TreePath path = new TreePath(parentNode.getPath());
        tree.setSelectionPath(path);
        tree.scrollPathToVisible(path);
    }
}




