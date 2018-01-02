package edu.illinois.masalzr2;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.illinois.masalzr2.keywords.KeyClass;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class TestDeserializeJavaClass {

	private static Map<String,File> dummyFiles;
	private static ArrayList<BufferedReader> readers;
	private static String homeDir;
	
	@BeforeClass
	public static void setBefore(){
		
		readers = new ArrayList<BufferedReader>();
		
		homeDir = System.getProperty("user.dir");
		dummyFiles = new HashMap<String, File>();
		
		dummyFiles.put("HelloWorldTest", new File(homeDir+"/DummyTestClasses/edu/illinois/masalzr2/HelloWorld.java") );
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
	
	@Test
	public void helloWorldTest() {
		BufferedReader readin = getBufferedReader(dummyFiles.get("HelloWorldTest"));
		DeserializeJavaClass jds = new DeserializeJavaClass();
		fail("Not yet implemented");
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
