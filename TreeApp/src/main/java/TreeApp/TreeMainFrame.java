package TreeApp;

import EditTree.TreeEditPanel;
import TreeAppMenus.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.tree.TreeModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class TreeMainFrame implements TreeMainFrameUtils{
    private JFrame mainFrame;
    private TreeViewPanel viewerPanel;  //used in view tree mode
    private TreeEditPanel editorPanel; //used in edit tree mode
    private JEditorPane editNodePane;  //used in edit tree mode
    private JSplitPane editorContainer; //used in edit tree mode
    public JFrame getMainFrame() {
        return mainFrame;
    }
    public void setViewerPanel(TreeViewPanel panel) {
        viewerPanel = panel;
    }
    public TreeViewPanel getViewerPanel() {
        return viewerPanel;
    }
    public void setEditorPanel(TreeEditPanel editorPanel) {
        this.editorPanel = editorPanel;
    }
    public TreeEditPanel getEditorPanel() { return editorPanel; }
    public void setEditNodePane(JEditorPane pane) {
        editNodePane = pane;
    }
    public JEditorPane getEditNodePane() {
        return editNodePane;
    }
    public void setEditorContainer(JSplitPane editorContainer) {
        this.editorContainer = editorContainer;
    }

    public void initViewer() throws IOException, BadLocationException, ParseException {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Couldn't use system look and feel.");
        }
        TreeAppSettings settings = new TreeAppSettings();
        settings.setupDisplay();

        //Create and setup main app frame.
        mainFrame = new JFrame("Tree Viewer");
        mainFrame.setIconImage(ImageIO.read(new File(TreeAppSettings.frameIcon)));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add tree viewer
        viewerPanel = new TreeViewPanel();
        setViewerPanel(viewerPanel);
        mainFrame.add(viewerPanel);

        Color foreColor = TreeAppSettings.foreColor;
        Font mediumFont = TreeAppSettings.mediumFont;
        mainFrame.setForeground(foreColor);
        mainFrame.setFont(mediumFont);

        setupMenus();
        mainFrame.pack();
        mainFrame.setVisible(true);

        mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(mainFrame,
                        "Would you like to save tree data to a file?", "Save tree data?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        saveTreeOnExit();
                        System.exit(0);
                } else {
                    System.exit(0);
                }
            }
        });
    }

    public void setupMenus() {
        Color foreColor = TreeAppSettings.foreColor;
        Font mediumFont = TreeAppSettings.mediumFont;

        JMenuBar menuBar = new JMenuBar();
        UIManager.put("Menu.font", mediumFont);
        UIManager.put("Menu.foreground", foreColor);

        JMenu fileMenu = new JMenu("File");
        //File Open
        JMenuItem openMenuItem = new JMenuItem(new OpenFileMenuAction(this));
        openMenuItem.setFont(mediumFont);
        openMenuItem.setForeground(foreColor);
        fileMenu.add(openMenuItem);
        //File Export
        JMenuItem exportMenuItem = new JMenuItem(new ExportToFileMenuAction(this));
        exportMenuItem.setFont(mediumFont);
        exportMenuItem.setForeground(foreColor);
        fileMenu.add(exportMenuItem);

        menuBar.add(fileMenu);

        JMenu editMenu = new JMenu("Edit");
        //Edit Tree
        JMenuItem editMenuItem = new JMenuItem(new EditTreeMenuAction(this));
        editMenuItem.setFont(mediumFont);
        editMenuItem.setForeground(foreColor);
        editMenu.add(editMenuItem);
        //New Tree
        JMenuItem newMenuItem = new JMenuItem(new NewTreeMenuAction(this));
        newMenuItem.setFont(mediumFont);
        newMenuItem.setForeground(foreColor);
        editMenu.add(newMenuItem);

        menuBar.add(editMenu);

        //View Tree
        JMenu viewMenu = new JMenu("View");
        JMenuItem viewMenuItem = new JMenuItem(new ViewMenuAction(this));
        viewMenuItem.setFont(mediumFont);
        viewMenuItem.setForeground(foreColor);
        viewMenu.add(viewMenuItem);
        menuBar.add(viewMenu);

        //Search Tree
        JMenu searchMenu = new JMenu("Search");
        JMenuItem searchMenuItem = new JMenuItem(new SearchMenuAction(this));
        searchMenuItem.setFont(mediumFont);
        searchMenuItem.setForeground(foreColor);
        searchMenu.add(searchMenuItem);
        menuBar.add(searchMenu);

        mainFrame.setJMenuBar(menuBar);
    }
    public void viewTree() {
        if (editorPanel == null) return;
        TreeModel m = editorPanel.getModel();
        JTree tree = viewerPanel.getTree();
        tree.setModel(m);
        mainFrame.remove(editorContainer);
        viewerPanel.setVisible(true);
    }
    public void  saveTreeOnExit() {
        ExportToFileMenuAction e = new ExportToFileMenuAction(this);
        e.doExportToFile();
    }
}
