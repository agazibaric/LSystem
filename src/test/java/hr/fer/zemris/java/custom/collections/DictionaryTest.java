package hr.fer.zemris.java.custom.collections;

import org.junit.Assert;
import org.junit.Test;

public class DictionaryTest {
	
	@Test
	public void testIsEmpty() {
		Dictionary dictionary = new Dictionary();
		dictionary.put(1, "one");
		dictionary.put(2, "two");
		dictionary.put(3, "three");
		
		Assert.assertFalse(dictionary.isEmpty());
	}
	
	@Test
	public void testSize() {
		Dictionary dictionary = new Dictionary();
		dictionary.put(1, "one");
		dictionary.put(2, "two");
		dictionary.put(3, "three");
		
		int expectedSize = 3;
		int actualSize = dictionary.size();
		Assert.assertEquals(expectedSize, actualSize);
	}
	
	@Test
	public void testClear() {
		Dictionary dictionary = new Dictionary();
		dictionary.put(1, "one");
		dictionary.put(2, "two");
		dictionary.put(3, "three");
		
		dictionary.clear();
		Assert.assertTrue(dictionary.isEmpty());
	}
	
	@Test
	public void testGetValue() {
		Dictionary dictionary = new Dictionary();
		dictionary.put(1, "one");
		dictionary.put(2, "two");
		dictionary.put(3, "three");
		
		String expectedValue = "two";
		String actualValue = (String) dictionary.get(2);
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testGetNotContainedValue() {
		Dictionary dictionary = new Dictionary();
		dictionary.put(1, "one");
		dictionary.put(2, "two");
		dictionary.put(3, "three");
		
		Assert.assertNull(dictionary.get(4));
	}
	
	@Test (expected = NullPointerException.class)
	public void testGetNull() {
		Dictionary dictionary = new Dictionary();
		dictionary.put(1, "one");
		dictionary.put(2, "two");
		dictionary.put(3, "three");
		dictionary.get(null);
	}
	
	@Test
	public void testStoreValueNull() {
		Dictionary dictionary = new Dictionary();
		dictionary.put(1, "one");
		dictionary.put(2, "two");
		dictionary.put(3, null);
		
		Assert.assertNull(dictionary.get(3));
	}

	@Test
	public void testGetOverriddenValue() {
		Dictionary dictionary = new Dictionary();
		dictionary.put("key", "value1");
		dictionary.put("key", "value2");
		
		String expectedValue = "value2";
		String actualValue = (String) dictionary.get("key");
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	
}
