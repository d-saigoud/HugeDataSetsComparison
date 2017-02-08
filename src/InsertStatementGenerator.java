import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertStatementGenerator {

	public static void main(String[] args) throws IOException {
		
		String seperateColumnValuesCreatedFile = "C:/Users/sai goud/Desktop/Out_1486184678770.txt";
		
		String outfilePath = "C:/Users/sai goud/Desktop/Out_" + System.currentTimeMillis() + ".txt";
		
		BufferedReader br = new BufferedReader(new FileReader(new File(seperateColumnValuesCreatedFile)));
		
		String line = "";
		
		List<String> outLines = new ArrayList<String>();
		
		br.readLine();
		while((line=br.readLine()) != null) {
			
			line = line.trim();
			
			
			if(line.length() > 0) {
				StringBuilder insertStatement = new StringBuilder("INSERT INTO databaseName.tableName (column1, column2, column3, column4, column5, column6 column7, column8, column9) VALUES (");
				
				String[] splits = line.split(";");
				
				for(int i=0; i<splits.length; i++) {
					
					insertStatement.append(" '");
					insertStatement.append(splits[i]);
					insertStatement.append("'");
					
					if(i != (splits.length - 1)) {
						
						insertStatement.append(", ");
						
					}
					
				}
				
				insertStatement.append(");");
				
				outLines.add(insertStatement.toString());
				
			}
			
		}
		
		br.close();
		
		Utility.writeOutFileDataFromListOfLines(outfilePath, outLines, 2);
		
	}
	
}
