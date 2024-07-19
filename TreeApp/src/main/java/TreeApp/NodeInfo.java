package TreeApp;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;;
import java.io.Serializable;
public class NodeInfo implements Transferable, Serializable, NodeInfoUtils  {
    private String parent;
    private int index;
    private String nodeId;
    private String depth;
    private String caption;
    private String nodeType;
    private String  nodeData;
    private String  search;
    private static final DataFlavor DnDNode_FLAVOR = new DataFlavor(
                                NodeInfo.class,
             "Drag and drop Node");

    // Data flavor is used to get back a NodeInfo from data transfer
    private static final DataFlavor[] flavors = { NodeInfo.DnDNode_FLAVOR };


    public DataFlavor[] getTransferDataFlavors() {
        return NodeInfo.flavors;
    }

    public NodeInfo(String parent, int index, String nodeId, String depth, String caption,
                    String nodeType, String nodeData, String search) {
        this.parent = parent;
        this.index = index;
        this.nodeId = nodeId;
        this.depth = depth;
        this.caption = caption;
        this.nodeType = nodeType;
        this.nodeData = nodeData;
        this.search = search;
    }
    public String getParent() { return parent;}

    public int getIndex() { return index;}
    public String getNodeId() { return nodeId;}
    public String getCaption() { return caption;}

    public String getNodeType() { return nodeType;}
    public String getNodeData() { return nodeData; }
    public String getDepth() { return depth; }
    public String getSearchString() { return search; }
    public void setCaption(String caption) {
        this.caption = caption;
    }
    public void setNodeData(String nodeData) {
        this.nodeData = nodeData;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String toString() {
        return caption;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {

        DataFlavor[] flavors = this.getTransferDataFlavors();
        for (int i = 0; i < flavors.length; i++) {
            if (flavors[i].equals(flavor)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public Object getTransferData(DataFlavor flavor)  {
        if (this.isDataFlavorSupported(flavor)) {
            return this;
        }
        return null;
    }
}
