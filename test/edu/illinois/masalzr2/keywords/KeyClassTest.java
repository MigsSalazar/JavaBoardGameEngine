package edu.illinois.masalzr2.keywords;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.illinois.masalzr2.keywords.KeyClass;

public class KeyClassTest {

	private static Map<String,File> dummyFiles;
	private static ArrayList<BufferedReader> readers;
	private static String homeDir;
	
	@BeforeClass
	public static void setBefore(){
		
		readers = new ArrayList<BufferedReader>();
		
		homeDir = System.getProperty("user.dir");
		dummyFiles = new HashMap<String, File>();
		
		dummyFiles.put("classWriterTest", new File(homeDir+"/DummyTestClasses/edu/illinois/masalzr2/classWriter.java"));
	}
	
	@Test
	public void classWriterTest(){
		BufferedReader template = getBufferedReader(dummyFiles.get("classWriterTest"));
		File pass = new File(homeDir+"/FileDump/edu/illinois/masalzr2/");
		KeyClass classWritter = new KeyClass("classWriter");
		File collect = new File(pass+"/classWriter.java");
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(collect));
			classWritter.writeOut(writer);
			writer.close();
			BufferedReader readin = getBufferedReader(collect);
			while(readin.ready() && template.ready()){
				String read = readin.readLine();
				String temp = template.readLine();
				//System.out.println("readin:"+read+" template:"+temp);
				assertTrue( read.equals(temp) );
			}
			assertEquals(template.ready(), readin.ready());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public BufferedReader getBufferedReader(File file){
		if(!file.exists()){
			fail("file does not exist");
		}
		BufferedReader readin = null;
		try {
			readin = new BufferedReader(new FileReader(file));
			readers.add(readin);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return readin;
	}
	
	@AfterClass
	public static void killReaders(){
		for(BufferedReader bfr : readers){
			try {
				bfr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
