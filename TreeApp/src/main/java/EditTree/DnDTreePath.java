package EditTree;

import javax.swing.tree.TreePath;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.Serializable;
import java.util.ArrayList;

public class DnDTreePath implements Transferable, Serializable {

    private ArrayList<TreePath> nodes;

     //Data flavor that allows a DnDTreePath to be extracted from a transferable
    public static final DataFlavor DnDTreeList_FLAVOR =
                  new DataFlavor(DnDTreePath.class,"Drag and drop list");

     // List of flavors this DnDTreePath can be retrieved as.
    // Currently, only DnDTreeList_FLAVOR is supported
    public static final DataFlavor[] flavors = { DnDTreePath.DnDTreeList_FLAVOR };

    @Override
    public DataFlavor[] getTransferDataFlavors()
    {
        return DnDTreePath.flavors;
    }

     // Nodes to transfer
    public DnDTreePath(ArrayList<TreePath> nodes)
    {
        this.nodes = nodes;
    }
    public ArrayList<TreePath> getNodes()
    {
        return this.nodes;
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
        if (this.isDataFlavorSupported(flavor)) {
            return this;
        }
        else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
    @Override
    public boolean isDataFlavorSupported(DataFlavor fl) {

        DataFlavor[] flavors = this.getTransferDataFlavors();
        for (int i = 0; i < flavors.length; i++) {
            if (flavors[i].equals(fl))  {
                return true;
            }
        }
        return false;
    }
}

