package com.griffinryan.doter.editor;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.*;

public class Explorer extends EditorTool {

	private TreeView<String> treeView;
	private Map<Integer, Item> items;
	private TreeItem<String> rootNode;

	private final Node rootIcon =
			new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("small.png"))));
	private final Image folderIcon =
			new Image(Objects.requireNonNull(getClass().getResourceAsStream("folder.png")));

	public Explorer(Workspace theWorkspace){

		/* TODO: initialize List<Item> items with the
		    File[] from theWorkspace.fileGroup[i].	*/
		this.items = new HashMap<>(30);
		File[] tempFiles = theWorkspace.getFileGroup();

		for(int i = 0; i < tempFiles.length; i++){

			this.items.put(i, )
		}

		if(theWorkspace.isHasRecent() && theWorkspace.getCurrentDirectory().exists()){
			// it has a directory to make an explorer of.
			this.rootNode = new TreeItem<>("MyCompany Human Resources", rootIcon);


		} else {
			// it does not have a directory. maybe show a button to open one.
		}
	}

	public TreeView<String> getTreeView() {
		return treeView;
	}

	public void setTreeView(TreeView<String> treeView) {
		this.treeView = treeView;
	}
}
