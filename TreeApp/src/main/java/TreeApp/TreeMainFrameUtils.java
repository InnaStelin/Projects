package TreeApp;

import EditTree.TreeEditPanel;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.io.IOException;

interface TreeMainFrameUtils {
     JFrame getMainFrame();
     void setViewerPanel(TreeViewPanel panel);
     TreeViewPanel getViewerPanel();
     void setEditorPanel(TreeEditPanel editorPanel);
     TreeEditPanel getEditorPanel() ;
     void setEditNodePane(JEditorPane pane);
     JEditorPane getEditNodePane();
     void setEditorContainer(JSplitPane editorContainer);
     void initViewer() throws IOException, BadLocationException, ParseException;
     void setupMenus();
}
