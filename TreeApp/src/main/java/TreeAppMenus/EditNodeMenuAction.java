package TreeAppMenus;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import EditTree.EditNodePanel;
import TreeApp.NodeDataFileSelector;
import TreeApp.NodeInfo;
import TreeApp.TreeMainFrame;
import TreeApp.TreeAppSettings;

public class EditNodeMenuAction implements ActionListener {
    private JLabel labelNodeCaption, labelNodeType, labelNodeData;
    private JTextField textNodeCaption, textNodeType, textNodeData;
    private JButton buttonNodeDataFile, buttonOk;
    private String nodeCaption, nodeType, nodeDataFile;
    NodeInfo editedNode;
    private TreeMainFrame app;
    private EditNodePanel frmEditNode;
    public void init(DefaultMutableTreeNode treeNode, TreeMainFrame mainApp)  {

        app = mainApp;

        editedNode = (NodeInfo) treeNode.getUserObject();
        nodeCaption = editedNode.getCaption();
        nodeType = editedNode.getNodeType();
        nodeDataFile = editedNode.getNodeData();

        frmEditNode = new EditNodePanel("Edit Node");
        ImageIcon icon = new ImageIcon(TreeAppSettings.frameIcon, "");
        frmEditNode.setFrameIcon(icon);

        labelNodeCaption = new JLabel("Node Caption:");
        labelNodeCaption.setPreferredSize(new Dimension(80,15));
        textNodeCaption = new JTextField(35);
        textNodeCaption.setText(nodeCaption);
        labelNodeType = new JLabel("Node Type:");
        labelNodeType.setPreferredSize(new Dimension(80,15));
        textNodeType = new JTextField(35);
        textNodeType.setText(nodeType);
        labelNodeData = new JLabel("Node Data:");
        labelNodeData.setPreferredSize(new Dimension(80,15));
        textNodeData = new JTextField(35);
        textNodeData.setText(nodeDataFile);
        buttonNodeDataFile = new JButton("...");

        buttonOk = new JButton("Ok");
        
        buttonNodeDataFile.addActionListener(this);
        buttonOk.addActionListener(this);

        frmEditNode.add(labelNodeCaption);
        frmEditNode.add(textNodeCaption);
        frmEditNode.add(labelNodeType);
        frmEditNode.add(textNodeType);
        frmEditNode.add(labelNodeData);
        frmEditNode.add(textNodeData);
        frmEditNode.add(buttonNodeDataFile);
        frmEditNode.add(buttonOk);

        frmEditNode.setSize(400, 250);
        frmEditNode.setLocation(0, 0);
        frmEditNode.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        frmEditNode.setVisible(true);
        frmEditNode.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JEditorPane editNodePane = app.getEditNodePane();
        editNodePane.add(frmEditNode);
        try {
            frmEditNode.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonNodeDataFile) {
            String nodeDataFile = new NodeDataFileSelector().selectFile(TreeAppSettings.nodeImagesDir);
            textNodeData.setText(nodeDataFile);

        } else if (e.getSource() == buttonOk) {
            editedNode.setCaption(textNodeCaption.getText());
            editedNode.setNodeType(textNodeType.getText());
            editedNode.setNodeData(textNodeData.getText());
            frmEditNode.dispose();
            app.viewTree();
        }
    }
}


