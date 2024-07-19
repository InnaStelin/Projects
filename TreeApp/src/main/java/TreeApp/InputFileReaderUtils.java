package TreeApp;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

interface InputFileReaderUtils {
    void readDataFile(List<NodeInfo> nodes, String filePath)
            throws IOException,
            ParseException;
}
