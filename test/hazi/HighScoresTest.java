package hazi;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class HighScoresTest {

	@Test
	public void test() {
		HighScores hs = new HighScores();
		hs.reset();
		hs.add(10);
		hs.add(2);
		hs.add(9);
		hs.add(3);
		hs.add(12);
		hs.add(4);
		hs.add(13);
		//13,12,10,9,4
		hs = new HighScores();
		Assert.assertEquals(4,hs.get(0));
		Assert.assertEquals(9,hs.get(1));
		Assert.assertEquals(10,hs.get(2));
		Assert.assertEquals(12,hs.get(3));
		Assert.assertEquals(13,hs.get(4));
		
	}

}
