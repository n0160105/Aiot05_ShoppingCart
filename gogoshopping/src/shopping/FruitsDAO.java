package shopping;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FruitsDAO {
	
	private static final String HOME = System.getProperty("user.home");
	
	public FruitsDAO() {		
	}
	
	public List<FruitsModel> selectFruits(String user) throws IOException {
		
		List<FruitsModel> fruits = new ArrayList<>();
		DecimalFormat fmt = new DecimalFormat("##.0000");
		JSONParser parser = new JSONParser();
		
		try {
  
		     Object obj = parser.parse(new FileReader(HOME + "\\Desktop\\pricing.json"));
		     JSONObject jsonObject = (JSONObject)obj;

		     InputStreamReader isr = new InputStreamReader(new FileInputStream(HOME + "\\Desktop\\yolo-object-detection\\output\\" + user + ".csv"));
			 BufferedReader reader = new BufferedReader(isr);
			 String line = null;
			   
			 while((line=reader.readLine())!=null){
			      
				 String item[] = line.split(",");
			     String id     = item[0].trim();
			     String name   = item[1].trim();
			     String prob   = item[2].trim();
			     Long   price  = (Long)jsonObject.get(name);
			     
			     fruits.add(new FruitsModel(Integer.parseInt(id), name, Double.parseDouble(fmt.format(Double.parseDouble(prob))), (double)price));
	
			 }
			 
			 reader.close();
			  
		} catch (FileNotFoundException e) {  
			e.printStackTrace();	
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return fruits;
	
	}
	
}