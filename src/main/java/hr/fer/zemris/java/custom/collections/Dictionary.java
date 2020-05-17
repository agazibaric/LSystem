package hr.fer.zemris.java.custom.collections;

import java.util.Objects;

/**
 * Class represents specific collection that stores given key and value as a couple. </br>
 * Given value is assigned to the given key and stored in the <code>Dictionary</code>. </br>
 * <code>Dictionary</code> only allows storing keys that are not <code>null</code>, </br>
 * however value that is assigned to the key is allowed be <code>null</code>. </br>
 * If you try to put key that is already contained in the <code>Dictionary</code> </br>
 * old value will be overridden by newly given value. </br>
 * 
 * @author Ante Gazibarić
 * @version 1.0
 *
 */
public class Dictionary {
	
	/**
	 * Private collection for storing records that used like adaptee
	 */
	private ArrayIndexedCollection map;
	
	/**
	 * Constructor used for creating new <code>Dictionary</code>.
	 */
	public Dictionary() {
		map = new ArrayIndexedCollection();
	}
	
	/**
	 * Method checks if dictionary contains any records.
	 * 
	 * @return <code>true</code> if dictionary contains at least one record, otherwise <code>false</code>
	 */
	public boolean isEmpty() {
		return map.isEmpty();
	}
	
	/**
	 * Method that returns number of records stored in dictionary.
	 * 
	 * @return number of records stored in dictionary
	 */
	public int size() {
		return map.size();
	}
	
	/**
	 * Method deletes all records that dictionary contained.
	 */
	public void clear() {
		map.clear();
	}
	
	/**
	 * Method adds given key and value to the dictionary as a one record. </br>
	 * In <code>Dictionary<code> given value is assigned to the given key.
	 * 
	 * @param key   <code>Object</code> key to which given value is mapped
	 * @param value <code>Object</code> value that is assigned to the given key
	 */
	public void put(Object key, Object value) {
		Objects.requireNonNull(key, "Key must not be null");
		
		Record newRecord = findRecord(key);
		if (newRecord == null) {
			map.add(new Record(key, value));
		} else {
			newRecord.setValue(value);
		}
	}
	
	/**
	 * Method that returns value that is assigned to the given key in dictionary.
	 * 
	 * @param key <code>Object</code> key whose value is returned
	 * @return    <code>Object</code> value that is assigned to the given <code>key</code> </br>
	 * 			  only if the <code>key</code> is contained in dictionary, </br>
	 * 			  otherwise returns <code>null</code>
	 * @throws    <code>NullPointerException</code> if the given <code>key</code> is <code>null</code>
	 */
	public Object get(Object key) {
		Objects.requireNonNull(key, "Key must not be null");
		
		Record record = findRecord(key);
		return record == null ? null : record.getValue();
	}
	
	/**
	 * Helper method for finding <code>Record</code> that contains given <code>key</code>.
	 * 
	 * @param key <code>Object</code> key that requested <code>Record</code> contains.
	 * @return    <code>Record</code> that contains given key. </br>
	 * 			  If there's no such a <code>Record</code> returns <code>null</code>
	 */
	private Record findRecord(Object key) {
		Object[] records = map.toArray();
		
		for (Object obj : records) {
			Record record = (Record) obj;
			if (record.getKey().equals(key))
				return record;
		}
		return null;
	}
	
	/**
	 * Private class that represents one record in dictionary. </br>
	 * Record contains key and value that is assigned to the given key.
	 * 
	 * @author Ante Gazibarić
	 * @version 1.0
	 *
	 */
	private static class Record {
		/**
		 * key of the record
		 */
		private Object key;
		/**
		 * value that is assigned to the key
		 */
		private Object value;
		
		/**
		 * Constructor for creating new <code>Record</code> object.
		 * 
		 * @param key   <code>Object</code> to which given value is assigned
		 * @param value <code>Object</code> that is assigned to the given key
		 */
		public Record(Object key, Object value) {
			this.key = key;
			this.value = value;
		}
		
		/**
		 * Method returns key of record.
		 * 
		 * @return <code>Object</code> that represents key of the record
		 */
		public Object getKey() {
			return key;
		}
		
		/**
		 * Method returns value of record.
		 * 
		 * @return <code>Object</code> that represents value of the record
		 */
		public Object getValue() {
			return value;
		}
		
		/**
		 * Method sets value of the record
		 * 
		 * @param value <code>Object</code> new value of the record
		 */
		public void setValue(Object value) {
			this.value = value;
		}
	}
	
	
	

}
