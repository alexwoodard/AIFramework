package Runner;

import static org.junit.Assert.*;

import org.junit.Test;

public class RunnerTestGoogle {

	@Test
	public void test() throws Exception {
		Runner runner = new Runner();
		String[] args = {"https://www.google.com"};
		runner.main(args);
	}

}
