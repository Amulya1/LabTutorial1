import static org.junit.Assert.*;

import org.junit.Test;

public class FirsttestCase {

	

		@Test
		public void test() {
			//fail("Not yet implemented");
			
			
			Assign a=new Assign();
			Double d=a.Celcius(250);
			Double cor=121.0;
			assertEquals(d,cor);
			
			
			
			
		}

	}

