package TreeAppMenus;

import EditTree.EditNodePanel;
import TreeApp.NodeDataFileSelector;
import TreeApp.NodeInfo;
import TreeApp.TreeMainFrame;
import TreeApp.TreeAppSettings;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;

public class AddNodeMenuAction implements ActionListener {
        private JLabel labelNodeCaption, labelNodeType, labelNodeData, labelNodeSearch;
        private JTextField txtNodeCaption, txtNodeType, txtNodeData, txtNodeSearch;
        private JButton buttonNodeDataFile, buttonOk;
        private JTree editedTree;
        private DefaultMutableTreeNode parentNode;
        private EditNodePanel frmAddNode;
        private TreeMainFrame app;
        public void init(DefaultMutableTreeNode selectedNode, JTree tree, TreeMainFrame mainApp)
                throws PropertyVetoException {

            editedTree = tree;
            parentNode = selectedNode;
            app = mainApp;

            frmAddNode = new EditNodePanel("Add Node");
            ImageIcon icon = new ImageIcon(TreeAppSettings.frameIcon, "");
            frmAddNode.setFrameIcon(icon);

            labelNodeCaption = new JLabel("Node Caption:");
            labelNodeCaption.setPreferredSize(new Dimension(80,15));
            txtNodeCaption = new JTextField(35);
            txtNodeCaption.setText("");
            labelNodeType = new JLabel("Node Type:");
            labelNodeType.setPreferredSize(new Dimension(80,15));
            txtNodeType = new JTextField(35);
            txtNodeType.setText("");
            labelNodeData = new JLabel("Node Data:");
            labelNodeData.setPreferredSize(new Dimension(80,15));
            txtNodeData = new JTextField(35);
            txtNodeData.setText("");
            buttonNodeDataFile = new JButton("...");
            buttonNodeDataFile.addActionListener(this);

            labelNodeSearch = new JLabel("Search Expression:");
            labelNodeSearch.setPreferredSize(new Dimension(100,15));
            txtNodeSearch = new JTextField(40);
            txtNodeSearch.setText("");

            buttonOk = new JButton("Ok");
            buttonOk.addActionListener(this);

            frmAddNode.add(labelNodeCaption);
            frmAddNode.add(txtNodeCaption);
            frmAddNode.add(labelNodeType);
            frmAddNode.add(txtNodeType);
            frmAddNode.add(labelNodeData);
            frmAddNode.add(txtNodeData);
            frmAddNode.add(buttonNodeDataFile);
            frmAddNode.add(labelNodeSearch);
            frmAddNode.add(txtNodeSearch);
            frmAddNode.add(buttonOk);

            frmAddNode.setSize(500, 200);
            frmAddNode.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
            frmAddNode.setVisible(true);
            frmAddNode.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frmAddNode.setLocation(0, 0);
            JEditorPane editNodePane = app.getEditNodePane();

            editNodePane.add(frmAddNode);
            try {
                frmAddNode.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {}
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonNodeDataFile) {
                String nodeDataFile = new NodeDataFileSelector().selectFile(TreeAppSettings.nodeImagesDir);
                txtNodeData.setText(nodeDataFile);

            } else if (e.getSource() == buttonOk) {
                NodeInfo parentNodeInfo = (NodeInfo) parentNode.getUserObject();
                int index = parentNode.getChildCount() + 1;

                Integer depthParent = Integer.parseInt(parentNodeInfo.getDepth());
                String depth = String.valueOf(depthParent + 1);

                String caption = txtNodeCaption.getText();
                String nodeType = txtNodeType.getText();
                String nodeData = txtNodeData.getText();
                String search = txtNodeSearch.getText();

                String parentId = parentNodeInfo.getNodeId();
                String nodeId;
                if (parentId == "Root") {
                     nodeId = caption;
                } else {
                    nodeId = parentId + "." + index;
                }
                NodeInfo newNodeInfo = new NodeInfo(parentId, index, nodeId, depth,
                                                caption, nodeType, nodeData, search);
                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newNodeInfo);
                parentNode.add(newNode);

                DefaultTreeModel model = (DefaultTreeModel)editedTree.getModel();
                model.nodeChanged(parentNode);
                TreePath path = new TreePath(newNode.getPath());
                editedTree.setSelectionPath(path);
                editedTree.scrollPathToVisible(path);
                editedTree.updateUI();
                frmAddNode.dispose();
                app.viewTree();
            }
        }
}


