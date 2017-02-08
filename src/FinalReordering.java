import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FinalReordering {

	public static void main(String[] args) throws IOException {
		
		String finalFilePath = "C:/Users/sai goud/Desktop/one1.txt";
		
		String outfilePath = "C:/Users/sai goud/Desktop/Out_" + System.currentTimeMillis() + ".txt";
		
		List<String> tableNames = Utility.getFinalColumnNames(finalFilePath);
		
		BufferedReader br = new BufferedReader(new FileReader(new File(finalFilePath)));
		
		String line = "";
		
		List<String> outLines = new ArrayList<String>();
		
		br.readLine();
		while((line=br.readLine()) != null) {
			
			line = line.trim();
			
			
			
			if(line.length() > 0) {
				
				String originalLine = line;
				
				line = line.split("\\|\\|\\|\\|")[1];
				Map<String, String> columnMapping = new HashMap<String, String>();
				
				String[] splits = line.split(",");
				
				for(String str : splits) {
					
					columnMapping.put(str.trim(), "");
					
				}
				
				String finalOutLine = originalLine.split("\\|\\|\\|\\|")[0];
				
				for(String tableName : tableNames) {
					
					if(columnMapping.containsKey(tableName)) {
						finalOutLine += ";YES";
					}
					else {
						finalOutLine += ";NO";
					}
					
				}
				
				outLines.add(finalOutLine);
				
			}
			
		}
		
		br.close();
		
		Utility.writeOutFileDataFromListOfLines(outfilePath, outLines, 0);
		
	}
	
}
