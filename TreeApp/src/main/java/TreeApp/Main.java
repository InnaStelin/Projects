package TreeApp;


import javax.swing.text.BadLocationException;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class Main {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                TreeMainFrame fr = new TreeMainFrame();
                fr.initViewer();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (BadLocationException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
