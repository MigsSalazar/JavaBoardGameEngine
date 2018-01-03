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

public class KeyMethodTest {

	private static Map<String,File> dummyFiles;
	private static ArrayList<BufferedReader> readers;
	private static String homeDir;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		readers = new ArrayList<BufferedReader>();
		homeDir = System.getProperty("user.dir");
		
		dummyFiles = new HashMap<String,File>();
		
		dummyFiles.put("standardMethodWriterTest", new File(homeDir+"/DummyTestClasses/edu/illinois/masalzr2/standardMethodWriter.java"));
		dummyFiles.put("standardMethodWriterPublicTest", new File(homeDir+"/DummyTestClasses/edu/illinois/masalzr2/standardMethodWriterPublic.java"));
		dummyFiles.put("standardMethodWriterPrivateTest", new File(homeDir+"/DummyTestClasses/edu/illinois/masalzr2/standardMethodWriterPrivate.java"));
		dummyFiles.put("standardMethodWriterProtectedTest", new File(homeDir+"/DummyTestClasses/edu/illinois/masalzr2/standardMethodWriterProtected.java"));
		dummyFiles.put("standardMethodWriterNoScopeTest", new File(homeDir+"/DummyTestClasses/edu/illinois/masalzr2/standardMethodWriterNoScope.java"));
		dummyFiles.put("finalMethodWriterTest", new File(homeDir+"/DummyTestClasses/edu/illinois/masalzr2/finalMethodWriter.java"));
		dummyFiles.put("staticMethodWriterTest", new File(homeDir+"/DummyTestClasses/edu/illinois/masalzr2/staticMethodWriter.java"));
		dummyFiles.put("abstractMethodWriterTest", new File(homeDir+"/DummyTestClasses/edu/illinois/masalzr2/abstractMethodWriter.java"));
		dummyFiles.put("finalStaticMethodWriterTest", new File(homeDir+"/DummyTestClasses/edu/illinois/masalzr2/finalStaticMethodWriter.java"));
		dummyFiles.put("badDelimeterMethodWriterTest", new File(homeDir+"/DummyTestClasses/edu/illinois/masalzr2/badDelimeterMethodWriter.java"));
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		for(BufferedReader bfr : readers){
			try {
				bfr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void comparingKeyMethodToTestFile(BufferedReader template, File pass, KeyMethod methodWriter) {
		File collect = new File(pass+"/standardMethodWriter.java");
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(collect));
			
			methodWriter.writeOut(writer);
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
	
	@Test
	public void finalMethodWriterTest(){
		BufferedReader template = getBufferedReader(dummyFiles.get("finalMethodWriterTest"));
		File pass = new File(homeDir+"/FileDump/edu/illinois/masalzr2/");
		
		KeyMethod methodWriter = new KeyMethod("standardMethodWriter",-1,3);
		
		comparingKeyMethodToTestFile(template, pass, methodWriter);
	}
	
	@Test
	public void staticMethodWriterTest(){
		BufferedReader template = getBufferedReader(dummyFiles.get("staticMethodWriterTest"));
		File pass = new File(homeDir+"/FileDump/edu/illinois/masalzr2/");
		
		KeyMethod methodWriter = new KeyMethod("standardMethodWriter",-1,1);
		
		comparingKeyMethodToTestFile(template, pass, methodWriter);
	}
	
	@Test
	public void abstractMethodWriterTest(){
		BufferedReader template = getBufferedReader(dummyFiles.get("abstractMethodWriterTest"));
		File pass = new File(homeDir+"/FileDump/edu/illinois/masalzr2/");
		
		KeyMethod methodWriter = new KeyMethod("standardMethodWriter",-1,2);
		
		comparingKeyMethodToTestFile(template, pass, methodWriter);
	}
	
	@Test
	public void finalStaticMethodWriterTest(){
		BufferedReader template = getBufferedReader(dummyFiles.get("finalStaticMethodWriterTest"));
		File pass = new File(homeDir+"/FileDump/edu/illinois/masalzr2/");
		
		KeyMethod methodWriter = new KeyMethod("standardMethodWriter",-1,4);
		
		comparingKeyMethodToTestFile(template, pass, methodWriter);
	}
	
	@Test
	public void badDelimeterMethodWriterTest(){
		BufferedReader template = getBufferedReader(dummyFiles.get("badDelimeterMethodWriterTest"));
		File pass = new File(homeDir+"/FileDump/edu/illinois/masalzr2/");
		
		KeyMethod methodWriter = new KeyMethod("standardMethodWriter",-1,-1);
		
		comparingKeyMethodToTestFile(template, pass, methodWriter);
	}
	
	@Test
	public void standardMethodWriterNoScopeTest() {
		BufferedReader template = getBufferedReader(dummyFiles.get("standardMethodWriterNoScopeTest"));
		File pass = new File(homeDir+"/FileDump/edu/illinois/masalzr2/");
		
		KeyMethod methodWriter = new KeyMethod("standardMethodWriter",3);
		
		comparingKeyMethodToTestFile(template, pass, methodWriter);
		
	}
	
	
	@Test
	public void standardMethodWriterProtectedTest() {
		BufferedReader template = getBufferedReader(dummyFiles.get("standardMethodWriterProtectedTest"));
		File pass = new File(homeDir+"/FileDump/edu/illinois/masalzr2/");
		
		KeyMethod methodWriter = new KeyMethod("standardMethodWriter",1);
		
		comparingKeyMethodToTestFile(template, pass, methodWriter);
		
	}
	
	@Test
	public void standardMethodWriterPrivateTest() {
		BufferedReader template = getBufferedReader(dummyFiles.get("standardMethodWriterPrivateTest"));
		File pass = new File(homeDir+"/FileDump/edu/illinois/masalzr2/");
		
		KeyMethod methodWriter = new KeyMethod("standardMethodWriter",2);
		
		comparingKeyMethodToTestFile(template, pass, methodWriter);
		
	}
	
	@Test
	public void standardMethodWriterPublicTest() {
		BufferedReader template = getBufferedReader(dummyFiles.get("standardMethodWriterPublicTest"));
		File pass = new File(homeDir+"/FileDump/edu/illinois/masalzr2/");
		
		KeyMethod methodWriter = new KeyMethod("standardMethodWriter",0);
		
		comparingKeyMethodToTestFile(template, pass, methodWriter);
		
	}
	
	@Test
	public void standardMethodWriterTest() {
		BufferedReader template = getBufferedReader(dummyFiles.get("standardMethodWriterTest"));
		File pass = new File(homeDir+"/FileDump/edu/illinois/masalzr2/");
		
		KeyMethod methodWriter = new KeyMethod("standardMethodWriter");
		
		comparingKeyMethodToTestFile(template, pass, methodWriter);
		
	}

}
