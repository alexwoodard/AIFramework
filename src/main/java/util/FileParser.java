package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class FileParser {
	public ArrayList<String> parseLines(String fileLocation) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		File file = new File(fileLocation);
		LineIterator it = FileUtils.lineIterator(file, "UTF-8");
		try {
			while (it.hasNext()) {
				lines.add(it.nextLine());
			}
		} finally {
			it.close();
		}
		return lines;
	}
}
