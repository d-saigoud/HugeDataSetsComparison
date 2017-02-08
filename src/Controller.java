import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Controller {
public static void main(String[] args) throws IOException {
	
	/*if(1==1) {
		String str = "123;123";
		System.out.println(str.substring(0, str.indexOf(";")) + " : " + str.substring(str.indexOf(";") + 1));
		return;
	}*/
	
	//First Table Name
	String inputTable1 = "someTable1";
	
	//Second Table Name
	String inputTable2 = "someTable2";
	
	//First Input File Path Example : C:/Users/sai goud/Desktop/one.txt
	String inputFile1 = "C:/Users/sai goud/Desktop/one.txt";
	
	//Second Input File Path Example : C:/Users/sai goud/Desktop/two.txt
	String inputFile2 = "C:/Users/sai goud/Desktop/two.txt";
	
	String outputFile = "C:/Users/sai goud/Desktop/Out_" + System.currentTimeMillis() + ".txt";
	
	
	int longCounter = 0;
	
	Map<String, MainDTO> dataMap1 = Utility.loadData(inputFile1);
	
	System.out.println("Infile 1 Loaded");
	
	Map<String, MainDTO> dataMap2 = Utility.loadData(inputFile2);
	
	System.out.println("Infile 2 Loaded");
	
	List<String> outLines = new ArrayList<String>();
	
	for(String key : dataMap2.keySet()) {
		
		if(dataMap1.containsKey(key)) {
			
			if(dataMap1.get(key).getCoveredTables() == null) {
				//Utility.writeData(outputFile, key + "||||" + inputTable1 + "," + inputTable2);
				outLines.add(key + "||||" + inputTable1 + "," + inputTable2);
			}
			else {
				//Utility.writeData(outputFile, key + "||||" + dataMap1.get(key).getCoveredTables() + "," + inputTable2);
				outLines.add(key + "||||" + dataMap1.get(key).getCoveredTables() + "," + inputTable2);
			}
		}
		else {
			//Utility.writeData(outputFile, key + "||||" + inputTable2);
			outLines.add(key + "||||" + inputTable2);
		}
		
		System.out.println("Iterator 1 : " + (longCounter++));
		
	}
	
	longCounter = 0;
	
	for(String key : dataMap1.keySet()) {
		
		if(dataMap2.containsKey(key)) {
			
		}
		else {
			if(dataMap1.get(key).getCoveredTables() == null) {
				//Utility.writeData(outputFile, key + "||||" + inputTable1);
				outLines.add(key + "||||" + inputTable1);
			}
			else {
				//Utility.writeData(outputFile, key + "||||" + dataMap1.get(key).getCoveredTables());
				outLines.add(key + "||||" + dataMap1.get(key).getCoveredTables());
			}
			
		}
		
		System.out.println("Iterator 2 : " + (longCounter++));
		
	}
	
	
	Utility.writeOutFileDataFromListOfLines(outputFile, outLines, 0);
	
	
}
}
