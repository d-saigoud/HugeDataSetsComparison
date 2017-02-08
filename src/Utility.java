import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utility {

	public static Map<String, MainDTO> loadData(String inputFile) throws IOException {

		Map<String, MainDTO> dataMap = new HashMap<String, MainDTO>();

		BufferedReader br = new BufferedReader(new FileReader(new File(inputFile)));

		String line = "";
		br.readLine();

		while ((line = br.readLine()) != null) {

			line = line.trim();

			if (line.length() > 0) {

				MainDTO mainDto = new MainDTO();

				mainDto.setCol1(line.substring(0, line.indexOf(";")));

				mainDto.setCol2(line.substring(line.indexOf(";") + 1));
				

				if (line.split("\\|\\|\\|\\|").length > 1) {
					
					mainDto.setCol2(mainDto.getCol2().split("\\|\\|\\|\\|")[0]);
					
					mainDto.setCoveredTables(line.split("\\|\\|\\|\\|")[1]);
					

				}

				dataMap.put(mainDto.getCol1() + ";" + mainDto.getCol2(), mainDto);

			}
		}

		br.close();

		return dataMap;

	}

	public static void writeData(String outputFile, String line) throws IOException {

		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(outputFile), true));
		bw.write(line); 

		bw.newLine();

		bw.close();

	}
	
	public static List<String> getFinalColumnNames(String filepath) throws IOException {
		
		List<String> finalColumnNames = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));
		String line = br.readLine().trim();

		line = line.split("\\|\\|\\|\\|")[1];

		String []splits = line.split(",");
		
		for(String str : splits) {
			
			finalColumnNames.add(str.trim());
			
		}
		
		br.close();
		
		return finalColumnNames;
		
	}

	public static void writeOutFileDataFromListOfLines(String outputFile, List<String> outLines, int blankLinesToBeWritten) throws IOException {
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(outputFile), true));
		long linesCounter = 0;
		for(String str : outLines) {
			bw.write(str); 
			bw.newLine();
			System.out.println("Writing Line : " + (linesCounter++));
			
			for(int i=0; i< blankLinesToBeWritten; i++) {
				bw.newLine();
			}
			
		}

		bw.close();
		
	}

}
