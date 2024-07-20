About This Project
	This project builds and displays a tree control populated with data from input text file.
	Attached input file TreeData.json contains data to display human anatomy images and videos 
	organized by human systems i.e skeletal, muscular, nervous, etc.
	Project also supports editing tree by dragging and dropping nodes and modifying node data. 
	New tree can be build manually by adding new nodes to empty tree. Tree cam be searched.
	Each tree node has a type associated with it and  will either display image or play video when 
	it is selected, depending on the node type.
    Each node is positioned in the tree based on its parent and its index
	under the parent.It is assumed root (top parent) node exists before tree nodes are inserted
    and at least one of them is a child of root node.
	Each tree node is build using the following JSON object:
	{
		"parent":"Skeletal",      //Name of parent node
		"index":"1",              //Index under parent 
		"nodeId":"Skeletal.1",    //always parent & index separated by "."
		"depth": "2",             //Depth from root node
		"caption":"Bones And Ligaments", //Caption to be displayed as node's text
		"nodeType":"image",              //Either image or video
		"nodeData":"images/Skeletial_1.png",   //Location of image or video data file
		"search":""                //String to be used when tree is searched
	}
	  
Built With
	Java and Java Swing library.
Usage
    Tree can be modified by selecting Edit Tree command from main menu and dragging and dropping 
	nodes. Node's data can be edited using right-click popup menu in tree view. Tree can also be build manually
	(without using input JSON file) by creating empty tree and adding nodes to it using Edit/Add node menu.
	Tree can be loaded from another file using File menu. Edited changes can also be saved to a new .json file.

	 
