package Runner;

import static org.junit.Assert.*;

import org.junit.Test;

public class RunnerTestGoDaddy {

	@Test
	public void test() throws Exception {
		Runner runner = new Runner();
		String[] args = {"https://www.godaddy.com"};
		runner.main(args);
	}

}
