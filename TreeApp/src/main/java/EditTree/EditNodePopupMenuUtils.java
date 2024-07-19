package EditTree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.IOException;

 interface EditNodePopupMenuUtils {
    JPopupMenu setupMenu();
    void onAddNode(DefaultMutableTreeNode selectedNode, JTree tree) throws IOException;
    void onEditNode(DefaultMutableTreeNode selectedNode) throws IOException;
    void onDeleteNode(DefaultMutableTreeNode selectedNode, JTree tree);
}
