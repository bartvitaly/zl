package test.java;

import junit.framework.Assert;

import org.testng.annotations.Test;

import zl.Retrieve_2;

public class TestRetreive {

	@Test(groups = "main")
	public void test_12() {
		System.out.println("test_12 - check non alphabetical chars. 'ad as' also a possible return in a test");
		Assert.assertEquals(
				executeTest(new Object[] {
						"asd ad as s&^%&* 234 jknjdsaf^&*( asdf a ghjfk", 5,
						"ghjfk" }), true);
	}

	@Test(groups = "main")
	public void test() {
		System.out.println("test - check both max part of a string and min are retreived");
		Assert.assertEquals(executeTest(new Object[] { "asd ad as s", 5,
				"asd s" }), true);
	}

	@Test(groups = "main")
	public void test_1() {
		System.out.println("test_1 - Check two items of maximum length in a string");
		Assert.assertEquals(executeTest(new Object[] {
				"asdsd asdse asd asd as s", 12, "asdsd asd as" }), true);
	}

	@Test(groups = "main")
	public void test_11() {
		System.out.println("test_11 - Check that a string of length less then expected will be returned untouched");
		Assert.assertEquals(executeTest(new Object[] {
				"asdsd asdse asd asd as s", 110, "asdsd asdse asd asd as s" }),
				true);
	}

	@Test(groups = "main")
	public void test_2() {
		System.out.println("test_2 - check a string of zero length is returned");
		Assert.assertEquals(
				executeTest(new Object[] { "asdsd asd as s", 0, "" }), true);
	}

	@Test(groups = "main")
	public void test_3() {
		System.out.println("test_3 - check only one item is retreived - (border condition)");
		Assert.assertEquals(executeTest(new Object[] { "asdsd asd as s", 2,
				"as" }), true);
	}

	@Test(groups = "main")
	public void test_33() {
		System.out.println("test_33 check an item is retreived that is less than required");
		Assert.assertEquals(
				executeTest(new Object[] { "asdsd asd", 4, "asd" }), true);
	}

	@Test(groups = "main")
	public void test_4() {
		System.out.println("test_4");
		Assert.assertEquals(executeTest(new Object[] { "asdsd asd as s", 10,
				"asdsd as s" }), true);
	}

	boolean executeTest(Object[] obj) {
		System.out.println("---------------- STARTED TEST -----------------\n");
		String actual = Retrieve_2.getEssence((String) obj[0], (int) obj[1]);
		System.out.println("Actual: '" + actual + "', Expected: '"
				+ ((String) obj[2]) + "'");
		System.out.println("---------------- TEST ENDED -------------------\n");
		return ((String) obj[2]).equals(actual);
	}

}
