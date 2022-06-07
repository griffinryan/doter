package com.griffinryan.doter.editor;

import com.griffinryan.doter.gui.DoterMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditorTool{

	private Workspace workspace;
	private CodeEditor editor;

	public void setEditorDocument(String document){
		this.editor.getMonaco().getEditor().setCurrentLanguage(workspace.getFileExtension());
		this.editor.getMonaco().getEditor().getDocument().setText(document);
	}

	public String parseDocument(CodeEditor editor){
		return editor.getMonaco().getEditor().getDocument().getText();
	}

	private void saveStringToFile(String parsed, File file){
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

	private String saveFileToString(File file){
		String result = "";
		Path filePath = file.toPath();
		try {
			result = Files.readString(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public CodeEditor getEditor() {
		return editor;
	}

	public void setEditor(CodeEditor editor) {
		this.editor = editor;
	}
}
