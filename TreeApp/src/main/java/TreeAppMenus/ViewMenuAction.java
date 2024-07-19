package TreeAppMenus;

import TreeApp.TreeMainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
public class ViewMenuAction extends AbstractAction {
    private final TreeMainFrame mainApp;

    public ViewMenuAction(TreeMainFrame app) {
        super("View Tree");
        mainApp = app;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        mainApp.viewTree();
    }
}
