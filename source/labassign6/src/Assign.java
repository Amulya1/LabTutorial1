

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class Assign
 */
@WebServlet("/Assign")
public class Assign extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Assign() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			String getIRL="http://maps.googleapis.com/maps/api/geocode/json?latlng=54,21&sensor=true";
			
			URL url = new URL(getIRL);
			String qwe=url.toString();
			String qwerty=callURL(qwe);
			System.out.println(qwerty);
			
	try {
		JSONObject jObject=new JSONObject(qwerty);
		JSONArray jArray = jObject.getJSONArray("results");
		String address= jArray.getJSONObject(1).getString("formatted_address");
		System.out.println(address);
		PrintWriter write1=response.getWriter();
	    write1.println("<p>Address of  the locationn based on latitude and longitude: "+address+" </p>");
		
	} catch (JSONException e) {
		// TODO Auto-generated catch block
//		e.printStackTrace();
	}	  	
	try{
		
	String IRL1="http://api.openweathermap.org/data/2.5/weather?q='+destination+'&appid=fe8bd79a2a1b8bf2f7abcc7bb4314b91'";
	URL url1 = new URL(IRL1);
	String qwe1=url1.toString();
	String qwerty1=callURL(qwe1);
	System.out.println(qwerty1);
	
	JSONObject jsonObject  = new JSONObject(qwerty1);
    System.out.println("JSON details"+jsonObject);
    JSONObject a=jsonObject.getJSONObject("main");
    JSONArray weathercondition=jsonObject.getJSONArray("weather");
    System.out.println("Get the temperature"+weathercondition);
    Double b=(Double)a.get("temp");
    
    System.out.println("Get the details"+a);
    String desc=(String)weathercondition.getJSONObject(0).getString("description");
    String b1=b.toString();
    PrintWriter write=response.getWriter();
    write.println("<p>Temperature is: "+b+" </p>");
    write.println("<p>Humidity: "+desc+"</p>");
    
	}
	catch(Exception e)
	{
		
	}
	}
	
	
		public static String callURL(String myURL) {
			StringBuilder sb = new StringBuilder();
			URLConnection urlConn = null;
			InputStreamReader in = null;
			try {
				URL url = new URL(myURL);
				urlConn = url.openConnection();
				if (urlConn != null)
					urlConn.setReadTimeout(60 * 1000);
				if (urlConn != null && urlConn.getInputStream() != null) {
					in = new InputStreamReader(urlConn.getInputStream(),
							Charset.defaultCharset());
					BufferedReader bufferedReader = new BufferedReader(in);
					if(bufferedReader!=null)
					{
						int a;
						while ((a = bufferedReader.read()) != -1) {
							sb.append((char) a);
						}
						bufferedReader.close();
					}
				}
			in.close();
			} catch (Exception e) {
				throw new RuntimeException("Exception while calling URL:"+ myURL, e);
			} 
	 
			return sb.toString();
					}

		public Double Celcius(double n) {
			double x = ((n-32)*5/9);
			Double celcius=(double) Math.round(x);
			return celcius;
		}
		
		
}
