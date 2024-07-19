package TreeApp;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.*;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class TreeViewPanel extends JPanel implements TreeSelectionListener {
        public JEditorPane htmlPane;
        private JTree tree;
        private TreeBuilder builder;
        private static String lineStyle = "Horizontal";

        public JTree getTree() { return tree; }
        public TreeViewPanel() throws IOException, ParseException {

            super(new GridLayout(1, 0));

            builder = new TreeBuilder();
            DefaultMutableTreeNode rootNode = builder.buildTree(TreeAppSettings.defaultTreeDataFile,
                                                                TreeAppSettings.maxTreeDepth);
            //Create a tree that allows one selection at a time.
            tree = new JTree(rootNode);
            tree.getSelectionModel().setSelectionMode
                    (TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
            tree.setCellRenderer(new CustomTreeCellRenderer());
            tree.setFont(TreeAppSettings.mediumFont);

            //Track selection changes
            tree.addTreeSelectionListener(this);
            tree.putClientProperty("JTree.lineStyle", lineStyle);

            //Create scroll pane and add the tree to it
            JScrollPane treeView = new JScrollPane(tree);
            //Create the HTML viewing pane (to view nodes images)
            htmlPane = new JEditorPane();
            htmlPane.setEditable(false);
            htmlPane.setVisible(true);
            JScrollPane htmlView = new JScrollPane(htmlPane);

            //Add the scroll panes to a split pane.
            JSplitPane viewContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
            viewContainer.setTopComponent(treeView);
            viewContainer.setBottomComponent(htmlView);

            Dimension minimumSize = new Dimension(TreeAppSettings.viewDividerLocation, TreeAppSettings.mainMenuHeight);
            htmlView.setMinimumSize(minimumSize);
            treeView.setMinimumSize(minimumSize);
            viewContainer.setDividerLocation(350);
            viewContainer.setPreferredSize(new Dimension(1000, 700));
            add(viewContainer);
    }
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        try {
            onNodeSelected(tree, htmlPane);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void onNodeSelected(JTree tree, JEditorPane htmlPane)
                        throws IOException, BadLocationException {

        htmlPane.setText("");
        htmlPane.removeAll();
        DefaultMutableTreeNode treeNode =
                (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (treeNode == null) return;

        NodeInfo node = (NodeInfo) treeNode.getUserObject();
        String leafData = node.getNodeData();
        if (leafData == null) return;

        if (!leafData.isEmpty()) {
            String nodeType = node.getNodeType();
            if (nodeType.equals("image") ) {
                NodeImage.showNodeImage(htmlPane,leafData);

            } else if (nodeType.equals("video")) {
                NodeVideo.showNodeVideo(htmlPane,leafData);
            }
        }
    }

}
