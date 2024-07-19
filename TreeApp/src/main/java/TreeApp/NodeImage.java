package TreeApp;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.IOException;
public class NodeImage implements NodeImageUtils {
    public static void showNodeImage(JEditorPane htmlPane, String leafData)  throws
            IOException, BadLocationException {

        HTMLEditorKit kit = new HTMLEditorKit();
        htmlPane.setEditorKit(kit);
        HTMLDocument doc = (HTMLDocument) htmlPane.getDocument();
        Element[] roots = doc.getRootElements();
        Element body = null;
        for (int i = 0; i < roots[0].getElementCount(); i++) {
            Element element = roots[0].getElement(i);
            if (element.getAttributes().getAttribute(StyleConstants.NameAttribute) == HTML.Tag.BODY) {
                body = element;
                break;
            }
        }

       String leafDataFormatted = leafData.replace("\b", "/b")
                                    .replace("\f", "/f")
                                    .replace("\n", "/n")
                                    .replace("\r", "/r")
                                    .replace("\t", "/t")
                                    .replace("\\", "/");

        //One step replacement does not work for  '\r' (as in '\resources' directory in file path)
        //  .replace function thinks it is special character (carriage return) and does not replace it
        //leafDataFormatted = leafData.replace("\\", "/");

        String imageSourceFile = TreeAppSettings.fileScheme + leafDataFormatted;
        doc.insertAfterStart(body, "<img src=" + imageSourceFile + ">");
    }
}


