package rahulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		// Java: Read Json file and convert it into string also provide what encoding format
		String jsonContent = FileUtils.readFileToString(new File(
				System.getProperty("user.dir") + "//src//main//java//rahulshettyacademy//data/PurchaseOrder.json"),
				StandardCharsets.UTF_8);

		// Convert string into HashMap -Jackson Databind,
		ObjectMapper mapper = new ObjectMapper();
		// We are asking to create HashMap based upon number of indexes we have in that
		// Json file and all HashMap put me in one List
		// and give it back (data) so that List is returned, so data list has 2
		// arguments {map, map}
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

}
