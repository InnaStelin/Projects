package EditTree;

import javax.swing.*;

public class EditNodePanel extends JInternalFrame {
    public EditNodePanel(String name) {

       super(name,
            true,    //resizable
            true,           //closable
            true,           //maximizable
            true);          //iconable

            setLocation(0, 0);
    }
}
