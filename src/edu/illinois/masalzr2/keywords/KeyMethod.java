package edu.illinois.masalzr2.keywords;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class KeyMethod implements KeyWord {
	
	private String name;
	private HashMap<String,Boolean> scope;
	private HashMap<String,Boolean> delimeters;
	private HashMap<String,String> arguments;
	private String returnType;
	private ArrayList<String> lines;
	
	public KeyMethod(String n){
		name = n;
		defineScope(0);
		defineDelimeters(0);
		arguments = new HashMap<String, String>();
		returnType = "void";
		lines = new ArrayList<String>();
	}
	
	public KeyMethod(String n, int sc){
		name = n;
		defineScope(sc);
		defineDelimeters(0);
		arguments = new HashMap<String, String>();
		returnType = "void";
		lines = new ArrayList<String>();
	}

	public KeyMethod(String n, int sc, String rt){
		name = n;
		defineScope(sc);
		defineDelimeters(0);
		returnType = rt;
		lines = new ArrayList<String>();
	}
	
	
	public KeyMethod(String n, int sc, int del){
		name = n;
		defineScope(sc);
		defineDelimeters(del);
		arguments = new HashMap<String,String>();
		returnType = "void";
		lines = new ArrayList<String>();
	}
	
	public KeyMethod(String n, int sc, int del, String rt){
		name = n;
		defineScope(sc);
		defineDelimeters(del);
		arguments = new HashMap<String,String>();
		returnType = rt;
		lines = new ArrayList<String>();
	}
	
	public String getName(){
		return name+" ";
	}
	
	public void setName(String n){
		name = n;
	}
	
	public void addLine(String l){
		if(!l.endsWith(";")){
			l+=";";
		}
		lines.add(l);
	}
	
	public String getReturnType(){
		return returnType+" ";
	}
	
	public void setReturnType(String rt){
		returnType = rt;
	}
	
	public void putArgument(String variable, String type){
		arguments.put(variable, type);
	}
	
	public void setDelimeters(int del){
		defineDelimeters(del);
	}
	
	public String getDelimeters(){
		String retval = "";
		
		if(delimeters.get("abstract")){
			retval += "abstract ";
		}else{
			if(delimeters.get("final")){
				retval += "final ";
			}
			if(delimeters.get("static")){
				retval += "static ";
			}
		}
		
		return retval;
	}
	
	public void setScope(int sc){
		defineScope(sc);
	}
	
	public String getScope(){
		String retval = "";
		
		if(scope.get("private")){
			retval = "private ";
		}else if(scope.get("protected")){
			retval = "protected ";
		}else if(scope.get("public")){
			retval = "public ";
		}
		return retval;
		
	}
	
	private void defineDelimeters(int del){
		/*
		 * abstract
		 * final
		 * static
		 */
		delimeters = new HashMap<String,Boolean>();
		if(del == 0){
			//no special behavior
			delimeters.put("abstract", false);
			delimeters.put("final", false);
			delimeters.put("static", false);
		}else if(del == 1){
			//static method
			delimeters.put("abstract", false);
			delimeters.put("final", false);
			delimeters.put("static", true);
		}else if(del == 2){
			//abstract method
			delimeters.put("abstract", true);
			delimeters.put("final", false);
			delimeters.put("static", false);
		}else if(del == 3){
			//final method
			delimeters.put("abstract", false);
			delimeters.put("final", true);
			delimeters.put("static", false);
		}else if(del == 4){
			//final and static method
			delimeters.put("abstract", false);
			delimeters.put("final", true);
			delimeters.put("static", true);
		}else{
			defineDelimeters(0);
		}
		
		
	}
	
	private void defineScope(int sc){
		scope = new HashMap<String,Boolean>();
		if(sc == 0){
			scope.put("public", true);
			scope.put("private", false);
			scope.put("protected", false);
		}else if(sc == 1){
			scope.put("public", false);
			scope.put("private", false);
			scope.put("protected", true);
		}else if(sc == 2 ){
			scope.put("public", false);
			scope.put("private", true);
			scope.put("protected", false);
		}else{
			scope.put("public", false);
			scope.put("private", false);
			scope.put("protected", false);
		}
	}
	
	
	
	@Override
	public void writeOut(BufferedWriter writer) throws IOException{
		// TODO Auto-generated method stub
		writer.write(getScope());
		writer.write(getDelimeters());
		writer.write(getReturnType());
		writer.write(getName()+"(");
		String args = "";
		for(String variable : arguments.keySet()){
			args += arguments.get(variable)+" "+variable+",";
		}
		if(arguments.size() > 0){
			args = args.substring(0, args.length()-1);
		}
		writer.write(args);
		writer.write("){");
		writer.newLine();
		for(String l : lines){
			writer.write(l);
			writer.newLine();
		}
		
		writer.write("}");
		
	}

}
