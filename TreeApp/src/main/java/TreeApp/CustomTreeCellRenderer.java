package TreeApp;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

//Set custom icons and font color for tree nodes
public class CustomTreeCellRenderer extends DefaultTreeCellRenderer {
    private final Icon pictureIcon;
    private final Icon videoIcon;
    private final Color foreColor;
    public CustomTreeCellRenderer() {

        this.pictureIcon = new ImageIcon(TreeAppSettings.pictureIconFile, "icon for picture node");
        this.videoIcon = new ImageIcon(TreeAppSettings.videoIconFile,"icon for video node");

        this.foreColor = TreeAppSettings.foreColor;
        this.setBackgroundSelectionColor(TreeAppSettings.treeSelectionColor);
    }
    @Override
    public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean sel,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        String nodeType = getNodeType(value);
        setIcon(pictureIcon); //default
        setToolTipText("This node displays image.");

        if (nodeType.equals("video")) {
              setIcon(videoIcon);
              setToolTipText("This node displays video.");
        }
        setForeground(foreColor);
        return this;
    }
    protected String getNodeType(Object value) {
        DefaultMutableTreeNode node =
                (DefaultMutableTreeNode)value;
        NodeInfo nodeData =
                (NodeInfo)(node.getUserObject());

        return nodeData.getNodeType();
    }
}

