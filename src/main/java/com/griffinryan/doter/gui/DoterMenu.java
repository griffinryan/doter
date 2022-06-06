package com.griffinryan.doter.gui;

import com.griffinryan.doter.DoterApplication;
import com.griffinryan.doter.editor.CodeEditor;
import com.griffinryan.doter.editor.Workspace;
import eu.mihosoft.monacofx.MonacoFX;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.checkerframework.checker.units.qual.C;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoterMenu {

	public Menu fileMenu;
	public Menu editMenu;
	public FileChooser fileChooser;
	public DirectoryChooser directoryChooser;

	public MenuItem newFile;
	public MenuItem newProject;
	public MenuItem openFile;
	public MenuItem openProject;
	public MenuItem saveFile;
	public MenuItem openSettings;
	public MenuItem closeProgram;
	public MenuBar menuBar;

	private Workspace workspace;
	private CodeEditor editor;

	String fileExtensionName;
	String TEMPEXTENSION = "java";

	public DoterMenu(Stage stage, Workspace workspace){
		this.fileChooser = new FileChooser();
		this.setFileExtensionName(TEMPEXTENSION);
		this.setWorkspace(workspace);
		this.editor = new CodeEditor();

		createMenuItems();

		this.newFile.setOnAction(e -> openWindow("New File", stage));
		this.newProject.setOnAction(e -> openWindow("New Project", stage));
		this.openFile.setOnAction(e -> openWindow("Open File", stage));
		this.openProject.setOnAction(e -> openWindow("Open Project", stage));
		this.openSettings.setOnAction(e -> openWindow("Open Settings", stage));
		this.saveFile.setOnAction(e -> openWindow("Save", stage));
		this.closeProgram.setOnAction(e -> System.exit(0));

		menuBar = new MenuBar();
		menuBar.setUseSystemMenuBar(true);
		menuBar.getMenus().addAll(fileMenu, editMenu);
	}

	public void openWindow(String type, Stage stage){
		this.fileChooser = new FileChooser();
		this.directoryChooser = new DirectoryChooser();
		MonacoFX tempMonaco = this.editor.getMonaco();

		switch (type) {
			case "Open Settings" -> System.out.println("Haha, there are no settings!");
			case "New File" -> {
				this.fileChooser.setInitialFileName(workspace.getFileExtension());
				this.fileChooser.setTitle("Create New File..."); // set operation title.
				this.fileChooser.showSaveDialog(stage);    // save operation.
			}
			case "Open File" -> {
				this.fileChooser.setTitle("Open File..."); // set operation title.
				this.fileChooser.showOpenDialog(stage);    // save operation.
			}
			case "Save" -> {
				this.fileChooser.setInitialFileName(workspace.getFileExtension());
				this.fileChooser.setTitle("Save..."); // set operation title.

				File file = this.fileChooser.showSaveDialog(stage);
				String parsed = parseDocument(this.editor);
				saveTextToFile(parsed, file);
			}
			case "Open Project" -> {
				this.directoryChooser.setTitle("Open Project...");
				this.directoryChooser.showDialog(stage);
			}
			case "New Project" -> {
				this.directoryChooser.setTitle("Create New Project...");
				this.directoryChooser.showDialog(stage);
			}
		}
	}

	public String parseDocument(CodeEditor editor){
		return editor.getMonaco().getEditor().getDocument().getText();
	}

	private void saveTextToFile(String parsed, File file){
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			writer.println(parsed);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Logger.getLogger(DoterMenu.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void setFileExtensionName(String s){
		this.fileExtensionName = " ." + s; // set to .java
		this.fileChooser.setInitialFileName("App" + fileExtensionName);
	}

	public void createMenuItems(){
		this.fileMenu = new Menu("File"); // TO-DO: Add second args
		this.editMenu = new Menu("Edit"); // for image.
		this.newFile = new MenuItem("New File...");
		this.newProject = new MenuItem("New Project...");
		this.openFile = new MenuItem("Open File...");
		this.openProject = new MenuItem("Open Project...");
		this.saveFile = new MenuItem("Save...");
		this.openSettings = new MenuItem("Settings...");
		this.closeProgram = new MenuItem("Quit...");


		this.fileMenu.getItems().add(this.newFile);
		this.fileMenu.getItems().add(this.newProject);
		this.fileMenu.getItems().add(new SeparatorMenuItem());
		this.fileMenu.getItems().add(this.openFile);
		this.fileMenu.getItems().add(this.openProject);
		this.fileMenu.getItems().add(new SeparatorMenuItem());
		this.fileMenu.getItems().add(this.saveFile);
		this.fileMenu.getItems().add(new SeparatorMenuItem());
		this.fileMenu.getItems().add(this.openSettings);
		this.fileMenu.getItems().add(new SeparatorMenuItem());
		this.fileMenu.getItems().add(this.closeProgram);
	}

	public Menu getFileMenu() {
		return fileMenu;
	}

	public void setFileMenu(Menu fileMenu) {
		this.fileMenu = fileMenu;
	}

	public Menu getEditMenu() {
		return editMenu;
	}

	public void setEditMenu(Menu editMenu) {
		this.editMenu = editMenu;
	}

	public MenuItem getNewFile() {
		return newFile;
	}

	public void setNewFile(MenuItem newFile) {
		this.newFile = newFile;
	}

	public MenuItem getOpenFile() {
		return openFile;
	}

	public void setOpenFile(MenuItem openFile) {
		this.openFile = openFile;
	}

	public MenuItem getSaveFile() {
		return saveFile;
	}

	public void setSaveFile(MenuItem saveFile) {
		this.saveFile = saveFile;
	}

	public MenuItem getOpenSettings() {
		return openSettings;
	}

	public void setOpenSettings(MenuItem openSettings) {
		this.openSettings = openSettings;
	}

	public MenuItem getCloseProgram() {
		return closeProgram;
	}

	public void setCloseProgram(MenuItem closeProgram) {
		this.closeProgram = closeProgram;
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(MenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	public CodeEditor getEditor() {
		return editor;
	}

	public void setEditor(CodeEditor editor) {
		this.editor = editor;
	}
}
