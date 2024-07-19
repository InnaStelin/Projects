package TreeApp;

import org.json.simple.parser.ParseException;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.IOException;

interface TreeBuilderUtils {
    DefaultMutableTreeNode buildTree(String filePath, Integer maxDepth) throws IOException, ParseException;
    void addChildren(String parent, final DefaultMutableTreeNode parentNode);
    DefaultMutableTreeNode findParent(DefaultMutableTreeNode top, String parent);
    DefaultMutableTreeNode newRootNode();
}
