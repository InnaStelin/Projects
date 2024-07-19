package EditTree;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.Serializable;

public class DnDNode extends DefaultMutableTreeNode implements
                                                    Transferable,
                                                    Serializable,
                                                    DnDNodeUtils {

    // A DataFlavor provides meta information about data. DataFlavor is typically
    // used to access data on the clipboard, or during a drag and drop operation.
    // Here data flavor is used to get back a DnDNode from data transfer

    private static final DataFlavor DnDNode_FLAVOR =
            new DataFlavor(DnDNode.class,"Drag and drop Node");

    // List of all flavors that this DnDNode can be transferred as
    private static final DataFlavor[] flavors = { DnDNode.DnDNode_FLAVOR };

    @Override
    public DataFlavor[] getTransferDataFlavors()
    {
        return DnDNode.flavors;
    }
    public DnDNode()
    {
        super();
    }

    // Determines if input node can be a child of this node.
    // If node is being dragged/dropped onto itself we cannot proceed with transfer
    public boolean canAddChild(DnDNode node) {
        if (node != null) {
            if (!this.equals(node.getParent())) {
                if ((!this.equals(node))) {
                    return true;
                }
            }
        }
        return false;
    }

     // Gets the index for node insert. New node insert should maintain sorted order.
     // By default, DnDNode adds children at the end.
     // Returns the index to add at, or -1 if node cannot be added
    public int getAddIndex(DnDNode node) {
        if (!this.canAddChild(node))  {
            return -1;
        }
        return this.getChildCount();
    }

    // Checks this node for equality with another node. To be equal, this node
    // and all of its children must be equal. Note that the parent/ancestors do
    // not need to match at all.
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        else if (!(o instanceof DnDNode)) {
            return false;
        }
        else if (!this.equalsNodeOnly((DnDNode) o)) {
            return false;
        }
        else if (this.getChildCount() != ((DnDNode) o).getChildCount()) {
            return false;
        }
        // compare all children
        for (int i = 0; i < this.getChildCount(); i++) {
            if (!this.getChildAt(i).equals(((DnDNode) o).getChildAt(i))) {
                return false;
            }
        }
        return true;
    }

    // Compares if this node is equal to another node. In this method, children
    // are not taken into consideration.
    public boolean equalsNodeOnly(DnDNode node) {
        if (node != null) {
            if (this.getAllowsChildren() == node.getAllowsChildren()) {
                if (this.getUserObject() != null) {
                    if (this.getUserObject().equals(node.getUserObject())) {
                        return true;
                    }
                }
                else  {
                    if (node.getUserObject() == null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException  {
        if (this.isDataFlavorSupported(flavor)) {
            return this;
        }
        else  {
            throw new UnsupportedFlavorException(flavor);
        }
    }
    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        DataFlavor[] df = this.getTransferDataFlavors();
        for (int i = 0; i < df.length; i++) {
            if (df[i].equals(flavor)) {
                return true;
            }
        }
        return false;
    }
}

