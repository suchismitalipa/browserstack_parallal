package TestNG_Parallel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.appium.java_client.android.AndroidDriver;

public class BS_Config_Parallal {
	public AndroidDriver driver;
	JSONParser parser=null;
	JSONObject config=null;
	
	@BeforeMethod(alwaysRun=true)
	@Parameters(value= {"deviceIndex"})
	public void setUp(String deviceIndex) throws FileNotFoundException, IOException, ParseException 
	{
		JSONParser parser=new JSONParser();
		JSONObject config=(JSONObject)parser.parse(new FileReader("src/test/resources/parallel_conf.json"));
		JSONArray envs= (JSONArray) config.get("environments");
		DesiredCapabilities caps=new DesiredCapabilities();
		Map<String, String> envCapabilities=(Map<String, String>) envs.get(Integer.parseInt(deviceIndex));
		Iterator<Entry<String, String>> itr=envCapabilities.entrySet().iterator();
		while(itr.hasNext()) {
			Map.Entry pair=itr.next();
			caps.setCapability(pair.getKey().toString(), pair.getValue().toString());
			System.out.println(pair.getKey().toString()+":"+pair.getValue().toString());
		}
		Map<String,String> commonCapabilities=(Map<String, String>) config.get("capabilities");
		itr= commonCapabilities.entrySet().iterator();
		while(itr.hasNext()){
			Map.Entry pair=itr.next();
			if(caps.getCapability(pair.getKey().toString())==null) {
				caps.setCapability(pair.getKey().toString(), pair.getValue());
			}
			
		}
		String username=System.getenv("BROWSERSTACK_USERNAME");
		if(username==null) {
			username=(String) config.get("username");
		}
		
		String accesskey=System.getenv("BROWSERSTACK_ACCESS_KEY");
		if(accesskey==null) {
			accesskey=(String) config.get("access_key");
		}
		
		
		driver=new AndroidDriver(new URL("http://"+username+":"+accesskey+"@"+config.get("server")+"/wd/hub"),caps);
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown() {
		driver.quit();
	}

}
