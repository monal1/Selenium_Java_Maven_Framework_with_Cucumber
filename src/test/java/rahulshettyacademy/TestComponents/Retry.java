package rahulshettyacademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	
	int count = 0;
	int maxTry = 1;
	
	// If test passes then it does not care and control will not come here
	@Override
	public boolean retry(ITestResult result) {
		// TODO When ever test fail it also come to this block it reports failure
		// It will come here to check do I have to re-run again just to make sure I 
		// might be flake Test, do you want me to re-run
		if(count < maxTry) {
			count++;
			return true;
		}
		return false;
	}

}
