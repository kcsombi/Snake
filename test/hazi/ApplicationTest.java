package hazi;

import org.junit.Assert;
import org.junit.Test;

public class ApplicationTest {
	@Test
	public void testInit() {
		Application app = new Application();
		Assert.assertSame(app.game, app.game.window.game);
	}
} 