package edu.illinois.masalzr2.keywords;

import java.io.BufferedWriter;
import java.io.IOException;

public interface KeyWord {

	public abstract void writeOut(BufferedWriter writer) throws IOException;
	
}
