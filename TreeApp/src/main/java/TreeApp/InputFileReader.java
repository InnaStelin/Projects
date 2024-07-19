package TreeApp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;

// Read tree data from .json file
// JSON parser jar file lives in : C:/Program Files/Common Files/json/json-simple-1.1.1.jar

public class InputFileReader implements InputFileReaderUtils {
    public void readDataFile(List<NodeInfo> nodes, String filePath)
                                               throws IOException,
                                                      ParseException {

        JSONParser parser = new JSONParser();
        FileReader treeReader = new FileReader(filePath);
        JSONArray aArray = (JSONArray) parser.parse(treeReader);

        for (Object obj : aArray) {
            JSONObject nodeObj = (JSONObject) obj;

            String parent = (String) nodeObj.get("parent");
            String index = (String) nodeObj.get("index");
            int iIndex = Integer.parseInt(index);
            String nodeId = (String) nodeObj.get("nodeId");
            String depth = (String) nodeObj.get("depth");
            String caption = (String) nodeObj.get("caption");
            String nodeType = (String) nodeObj.get("nodeType");
            String nodeData = (String) nodeObj.get("nodeData");
            String search = (String) nodeObj.get("search");

            nodes.add (new NodeInfo(parent,
                                iIndex,
                                nodeId,
                                depth,
                                caption,
                                nodeType,
                                nodeData,
                                search));
        }
        treeReader.close();
    }
}