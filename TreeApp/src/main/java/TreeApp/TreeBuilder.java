package TreeApp;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.IOException;
import java.util.*;
import org.json.simple.parser.ParseException;
public class TreeBuilder implements TreeBuilderUtils {
    List<NodeInfo> nodes;
    public DefaultMutableTreeNode buildTree(String filePath, Integer maxDepth)
            throws IOException, ParseException {

        nodes = new ArrayList<>();

        InputFileReader reader = new InputFileReader();
        reader.readDataFile(nodes,filePath);

        NodeInfo root = nodes.stream().filter(n -> n.getNodeId().equalsIgnoreCase(("ROOT")))
                                      .reduce((first, second) -> first).orElse(null);

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(root);

        HashSet<String> parents = new HashSet<>();

        for (int level = 1; level <= maxDepth; level++) {
            //Get a list of unique parent names for each three level
            final String nodeDepth = String.valueOf(level);
            nodes.stream().filter(n -> n.getDepth().equals(nodeDepth))
                    .forEach(n -> parents.add(n.getParent()));

            //Insert children - this assumes top level node is root, and it is already in the tree
            for (String parentName : parents) {
                if (parentName != "") {
                    DefaultMutableTreeNode parentNode = findParent(rootNode, parentName);
                    if (parentNode != null) {
                        addChildren(parentName, parentNode);
                    }
                }
            }
            parents.clear();
        }
        return rootNode;
    }
    public void addChildren(String parent, final DefaultMutableTreeNode parentNode) {
        nodes.stream().filter(n-> n.getParent().equals(parent))
                      .forEach(n -> parentNode.add(new DefaultMutableTreeNode(n)));
    }
    public DefaultMutableTreeNode findParent(DefaultMutableTreeNode top, String parent) {

        Object nodeObj = top.getUserObject();
        NodeInfo currentNode = (NodeInfo) nodeObj;
        if (currentNode.getNodeId().equals(parent)) {
            return top;
        }
        Enumeration e = top.breadthFirstEnumeration();
        while (e.hasMoreElements()) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();

            nodeObj = node.getUserObject();
            currentNode = (NodeInfo) nodeObj;
            if (currentNode.getNodeId().equals(parent)) {
                return node;
            }
        }
        return null;
    }
    public  DefaultMutableTreeNode newRootNode() {

        //NodeInfo fields: String parent, int index, String nodeId, String depth, String caption,
        //        String nodeType, String nodeData, String search
        NodeInfo root = new NodeInfo("",0,"Root", "0",
                                   "Root", "", "","");

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(root);
        return rootNode;
    }
}
