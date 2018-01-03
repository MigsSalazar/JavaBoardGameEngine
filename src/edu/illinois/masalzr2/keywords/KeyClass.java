package edu.illinois.masalzr2.keywords;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class KeyClass implements KeyWord {
	/*
	 * classes can be public and/or
	 * final or abstract
	 */
	
	private String name;
	private HashMap<String, Boolean> delimeters;
	private HashMap<String, KeyMethod> methods;			//TODO make a KeyMethod class
	private HashMap<String, KeyWord> variables;			//TODO make a KeyVariable class
	private HashMap<String, KeyClass> internalClasses;

	public KeyClass(String n){
		name = n;
		delimeters = new HashMap<String, Boolean>();
		delimeters.put("public", true);
		delimeters.put("final", false);
		delimeters.put("abstract", false);
		methods = new HashMap<String, KeyMethod>();
		variables = new HashMap<String, KeyWord>();
		internalClasses = new HashMap<String, KeyClass>();
	}
	
	
	
	@Override
	public void writeOut(BufferedWriter writer) throws IOException{
		
		//writer.write("/*class=\""+name+"\"*/");
		if(delimeters.get("public")){
			writer.write("public ");
		}
		if(delimeters.get("final")){
			writer.write("final ");
		}else if(delimeters.get("abstract")){
			writer.write("abstract ");
		}
		writer.write("class "+name+"{");
		writer.newLine();
		for(String s : variables.keySet()){
			variables.get(s).writeOut(writer);
			writer.newLine();
		}
		for(String key : methods.keySet()){
			methods.get(key).writeOut(writer);
			writer.newLine();
		}
		for(String key : internalClasses.keySet()){
			internalClasses.get(key).writeOut(writer);
			writer.newLine();
		}
		writer.write("}");
		
		writer.close();
	}
	
	public boolean isPublic(){
		return delimeters.get("public");
	}

	public void setPublic(boolean p){
		delimeters.put("public", p);
	}
	
	public boolean isFinal(){
		return delimeters.get("final");
	}

	public void setFinal(boolean p){
		delimeters.put("final", p);
		if(p){
			delimeters.put("abstract", false);
		}
	}
	
	public boolean isAbstract(){
		return delimeters.get("abstract");
	}

	public void setAbstract(boolean p){
		delimeters.put("abstract", p);
		if(p){
			delimeters.put("final", false);
		}
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public HashMap<String, KeyMethod> getMethods() {
		return methods;
	}



	public void setMethods(HashMap<String, KeyMethod> methods) {
		this.methods = methods;
	}



	public HashMap<String, KeyWord> getVariables() {
		return variables;
	}



	public void setVariables(HashMap<String, KeyWord> variables) {
		this.variables = variables;
	}



	public HashMap<String, KeyClass> getInternalClasses() {
		return internalClasses;
	}



	public void setInternalClasses(HashMap<String, KeyClass> internalClasses) {
		this.internalClasses = internalClasses;
	}

}
