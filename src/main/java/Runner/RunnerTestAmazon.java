package Runner;

import static org.junit.Assert.*;

import org.junit.Test;

public class RunnerTestAmazon {

	@Test
	public void test() throws Exception {
		Runner runner = new Runner();
		String[] args = {"https://www.amazon.com"};
		runner.main(args);
	}
}