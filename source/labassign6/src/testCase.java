import static org.junit.Assert.*;

import org.junit.Test;

public class testCase {

	@Test
	public void test() {
		Assign da=new Assign();
		String a=da.callURL("http://api.openweathermap.org/data/2.5/weather?q='+destination+'&appid=fe8bd79a2a1b8bf2f7abcc7bb4314b91");
		assertEquals(null, a);
		
		       
		
		
	}

}
