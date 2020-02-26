package networking;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject; 

/**
 * 
 */

/**
 * @author micha
 *
 */
public class getDataFromInternet {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws IOException, JSONException, ParseException {
		URL url1 = new URL("http://api.openweathermap.org/data/2.5/forecast?zip=94040,us&appid=594e9de078a3ccde7630bc9017a457ff");
		HttpURLConnection con1 = (HttpURLConnection) url1.openConnection();
		con1.setRequestMethod("GET");
		con1.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responsecode1 = con1.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url1);
		if(responsecode1 != 200) {
			throw new RuntimeException("HttpResonseCode:" + responsecode1);
		} else {
			BufferedReader in1 = new BufferedReader(
					new InputStreamReader(con1.getInputStream()));
			String inputLine1;
			StringBuffer response1 = new StringBuffer();
			while ((inputLine1 = in1.readLine()) != null) {
				response1.append(inputLine1);
			}
			in1.close();
			//print in String
			System.out.println(response1.toString());
			//Read JSON response and print
//			JSONObject myResponse1 = new JSONObject(response1.toString());
		}
		
		
		
		// TODO Auto-generated method stub
		URL url2 = new URL("http://api.openweathermap.org/data/2.5/weather?zip=94040,us&appid=594e9de078a3ccde7630bc9017a457ff");
		HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
		con2.setRequestMethod("GET");
		con2.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responsecode2 = con2.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url2);
		if(responsecode2 != 200) {
			throw new RuntimeException("HttpResonseCode:" + responsecode2);
		} else {
			BufferedReader in2 = new BufferedReader(
					new InputStreamReader(con2.getInputStream()));
			String inputLine2;
			StringBuffer response2 = new StringBuffer();
			while ((inputLine2 = in2.readLine()) != null) {
				response2.append(inputLine2);
			}
			in2.close();
			//print in String
			//		     System.out.println(response.toString());
			//Read JSON response and print
			JSONObject myResponse2 = new JSONObject(response2.toString());

			System.out.println("result after Reading JSON Response");
			JSONObject coord2 = new JSONObject(myResponse2.get("coord").toString());
			System.out.println("lon: " + coord2.get("lon") + " - lat: " + coord2.get("lat"));

			//		     System.out.println("weather - "+ myResponse.get("weather"));


			JSONObject main = new JSONObject(myResponse2.get("main").toString());
			System.out.println("temp: " + main.get("temp") + " - temp_min: " + main.get("temp_min") 
			+ " - humidity: " + main.get("humidity") + " - pressure: " + main.get("pressure") 
			+ " - feels_like: " + main.get("feels_like") + " - temp_max: " + main.get("temp_max"));

			JSONObject wind = new JSONObject(myResponse2.get("wind").toString());
			System.out.println("deg: " + wind.get("deg") + " - speed: " + wind.get("speed"));

			JSONObject clouds = new JSONObject(myResponse2.get("clouds").toString());
			System.out.println("clouds: " + clouds.get("all"));

			//The current time
			SimpleDateFormat originalFormat = new SimpleDateFormat("MM-dd-YYYY HH:mm:ss");
			Date currentDate = new Date ((long)((int)myResponse2.get("dt")) * 1000);
			String date = originalFormat.format(currentDate);
			System.out.println(date);

			//The sunset time
			JSONObject sys = new JSONObject(myResponse2.get("sys").toString());
			Date sunrise = new Date ((long)((int)sys.get("sunrise")) * 1000);
			String sunriseTime = originalFormat.format(sunrise);
			System.out.println(sunriseTime);
			
			//The sunrise time
			Date sunset = new Date ((long)((int)sys.get("sunset")) * 1000);
			String sunsetTime = originalFormat.format(sunset);
			System.out.println(sunsetTime);
		}
	}

}
