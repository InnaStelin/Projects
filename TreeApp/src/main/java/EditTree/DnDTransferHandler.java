package EditTree;

import TreeApp.TreeMainFrame;
import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
public class DnDTransferHandler extends TransferHandler {
    private TreeMainFrame app;
    private DefaultTreeModel tree;
    public DnDTransferHandler(TreeEditPanel tree, TreeMainFrame mainApp)  {
        super();
        this.tree = (DefaultTreeModel) tree.getModel();
        this.app = mainApp;
    }
    @Override
    public int getSourceActions(JComponent c) {
        return TransferHandler.COPY_OR_MOVE;
    }

    protected Transferable createTransferable(JComponent c) {
        if (c instanceof TreeEditPanel) {
            return new DnDTreePath(((TreeEditPanel) c).getSelection());
        } else {
            return null;
        }
    }

    @Override
    public boolean canImport(TransferSupport transfer) {
        // Set this up, so we can see what it is we are dropping onto.
       transfer.setShowDropLocation(true);

        // At the moment, only allow import of DnDNodes
        if (transfer.isDataFlavorSupported(DnDTreePath.DnDTreeList_FLAVOR)) {
            // Fetch the drop path
            TreePath dropPath = ((JTree.DropLocation)transfer.getDropLocation()).getPath();
            if (dropPath == null) {
                return false;
            }
            // Determine whether we can accept the location
            if (dropPath.getLastPathComponent() instanceof DnDNode) {
                // Only allow to drop onto a DnDNode
                try {
                    // Using node-defined checker, see if node will
                    // accept every selected node as a child.
                    DnDNode parent = (DnDNode) dropPath.getLastPathComponent();
                    ArrayList<TreePath> list = ((DnDTreePath)transfer
                                                .getTransferable()
                                                .getTransferData(DnDTreePath.DnDTreeList_FLAVOR)).getNodes();
                    for (int i = 0; i < list.size(); i++) {
                        if (parent.getAddIndex((DnDNode) list.get(i).getLastPathComponent()) < 0) {
                            return false;
                        }
                    }
                    return true;

                } catch (UnsupportedFlavorException exception) {
                    // Don't allow dropping of other data types.
                    exception.printStackTrace();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean importData(TransferHandler.TransferSupport transfer) {
        if (this.canImport(transfer)) {
            try {
                // Fetch the data to transfer
                Transferable t = transfer.getTransferable();
                ArrayList<TreePath> tList = ((DnDTreePath) t.getTransferData(DnDTreePath.DnDTreeList_FLAVOR)).getNodes();

                // Fetch the drop location
                TreePath loc = ((javax.swing.JTree.DropLocation) transfer.getDropLocation()).getPath();

                // Insert the data at this location using
                // insertNodeInto(TreeNode newChild, TreeNode parent, int index)
                for (int i = 0; i < tList.size(); i++) {
                    this.tree.insertNodeInto(
                            (DnDNode) tList.get(i).getLastPathComponent(),
                            (DnDNode) loc.getLastPathComponent(),
                            ((DnDNode) loc.getLastPathComponent()).getAddIndex((DnDNode) tList.get(i).getLastPathComponent()));
                }
                return true;
            } catch (UnsupportedFlavorException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        return false;
    }
    @Override
    protected void exportDone(JComponent c, Transferable t, int action) {

        if (action == TransferHandler.MOVE) {
            // We need to remove imported items from the source
            try {
                // Get back the list of items that were transferred and remove them
                ArrayList<TreePath> list = ((DnDTreePath) t
                        .getTransferData(DnDTreePath.DnDTreeList_FLAVOR)).getNodes();
                for (int i = 0; i < list.size(); i++) {
                    this.tree.removeNodeFromParent((DnDNode) list.get(i).getLastPathComponent());
                }
                app.viewTree();

            } catch (UnsupportedFlavorException exception) {
                exception.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}

