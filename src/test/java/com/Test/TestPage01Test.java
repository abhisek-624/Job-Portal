package com.Test;

import org.testng.annotations.Test;

import com.generics.TestBase;
import com.pageObjects.TestPage01;

public class TestPage01Test extends TestBase {

	@Test
	public void test() {

		TestPage01 t1 = new TestPage01(driver);
		t1.testing();

	}

}
