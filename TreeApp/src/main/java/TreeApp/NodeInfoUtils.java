package TreeApp;

 interface NodeInfoUtils {
     String getParent();

     int getIndex();
     String getNodeId();
     String getCaption();
     String getNodeType();
     String getNodeData();
     String getDepth();
     String getSearchString();
    
     void setCaption(String caption);
     void setNodeData(String nodeData);
     void setNodeType(String nodeType);
     String toString();
}
