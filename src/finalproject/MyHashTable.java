package finalproject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;




public class MyHashTable<K,V> implements Iterable<MyPair<K,V>>{
	// num of entries to the table
	private int size;
	// num of buckets 
	private int capacity = 16;
	// load factor needed to check for rehashing 
	private static final double MAX_LOAD_FACTOR = 0.75;
	// ArrayList of buckets. Each bucket is a LinkedList of HashPair
	private ArrayList<LinkedList<MyPair<K,V>>> buckets; 


	// constructors
	public MyHashTable() {
		size=0;
		capacity=16;
		buckets=new ArrayList<>(capacity);
		for(int i=0;i<capacity;i++) {
			buckets.add(new LinkedList<>());
		}
	}

	public MyHashTable(int initialCapacity) {
		size=0;
		capacity=initialCapacity;
		buckets=new ArrayList<>(this.capacity);
		for(int i=0;i<this.capacity;i++) {
			buckets.add(new LinkedList<>());
		}
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public int numBuckets() {
		return this.capacity;
	}

	/**
	 * Returns the buckets variable. Useful for testing  purposes.
	 */
	public ArrayList<LinkedList< MyPair<K,V> > > getBuckets(){
		return this.buckets;
	}

	/**
	 * Given a key, return the bucket position for the key. 
	 */
	public int hashFunction(K key) {
		int hashValue = Math.abs(key.hashCode())%this.capacity;
		return hashValue;
	}

	/**
	 * Takes a key and a value as input and adds the corresponding HashPair
	 * to this HashTable. Expected average run time  O(1)
	 */
	public V put(K key, V value) {
		int hashvalue=hashFunction(key);
		MyPair<K,V> inputNode=new MyPair<K,V>(key,value);
		LinkedList<MyPair<K,V>> bucketList=this.buckets.get(hashvalue);

		for(MyPair<K,V> node:bucketList){
			if(node.getKey().equals(key)){
				V returnValue=node.getValue();
				node.setValue(value);
				return returnValue;
			}
		}

		bucketList.addLast(inputNode);
		size++;
		if(size > MAX_LOAD_FACTOR*this.capacity){ //never be greater than the maximum load factor
			this.rehash();
		}

		return null;
	}


	/**
	 * Get the value corresponding to key. Expected average runtime O(1)
	 */

	public V get(K key) {
		int hashvalue=hashFunction(key);
		V returnValue=null;
		LinkedList<MyPair<K,V>> bucketList=this.buckets.get(hashvalue);

		for(MyPair<K,V> node:bucketList) {
			if (node.getKey().equals(key)) {
				returnValue = node.getValue();
			}
		}

		return returnValue;

		//ADD YOUR CODE ABOVE HERE
	}

	/**
	 * Remove the HashPair corresponding to key . Expected average runtime O(1) 
	 */
	public V remove(K key) {
		int hashvalue=hashFunction(key);
		V returnValue=null;
		LinkedList<MyPair<K,V>> bucketList=this.buckets.get(hashvalue);

		if(!bucketList.isEmpty()){
			for (MyPair<K, V> node : bucketList) {
				if (node.getKey().equals(key)) {
					returnValue = node.getValue();
					bucketList.remove(node);
				}
			}
			size--;
		}

		return returnValue;

		//ADD YOUR CODE ABOVE HERE
	}


	/** 
	 * Method to double the size of the hashtable if load factor increases
	 * beyond MAX_LOAD_FACTOR.
	 * Made public for ease of testing.
	 * Expected average runtime is O(m), where m is the number of buckets
	 */
	public void rehash() {
		this.capacity=this.capacity*2;

		ArrayList<LinkedList<MyPair<K,V>>> resizeBucket=new ArrayList<>(this.capacity);
		for(int i=0;i < this.capacity;i++) {//need to initialize?
			resizeBucket.add(new LinkedList<>());
		}

		for(int i=0;i < this.capacity/2;i++) {
			LinkedList<MyPair<K,V>> bucketList=this.buckets.get(i);
			for (MyPair<K, V> node : bucketList) {
				int hashvalue=hashFunction(node.getKey());
				resizeBucket.get(hashvalue).addFirst(node);
			}
		}

		this.buckets=resizeBucket;
	}


	/**
	 * Return a list of all the keys present in this hashtable.
	 * Expected average runtime is O(m), where m is the number of buckets
	 */

	public ArrayList<K> getKeySet() {
		ArrayList<K> output=new ArrayList<>();

		for(int i=0;i < this.capacity;i++) {
			LinkedList<MyPair<K,V>> bucketList=this.buckets.get(i);
			if(!bucketList.isEmpty()){
				for (MyPair<K, V> node : bucketList) {
					output.add(node.getKey());
				}
			}
		}
		
		return output;
	}

	/**
	 * Returns an ArrayList of unique values present in this hashtable.
	 * Expected average runtime is O(m) where m is the number of buckets
	 */
	public ArrayList<V> getValueSet() {
		MyHashTable<V,V> output=new MyHashTable<>();

		for(int i=0;i < this.capacity;i++) {
			LinkedList<MyPair<K,V>> bucketList=this.buckets.get(i);
			for (MyPair<K, V> node : bucketList) {
				V nodeValue=node.getValue();
				output.put(nodeValue,nodeValue);//used as a hashset?
			}
		}

		ArrayList<V> result=output.getKeySet();
		return result;
	}


	/**
	 * Returns an ArrayList of all the key-value pairs present in this hashtable.
	 * Expected average runtime is O(m) where m is the number of buckets
	 */
	public ArrayList<MyPair<K, V>> getEntries() {
		ArrayList<MyPair<K, V>> output = new ArrayList<>();
		for (int i = 0; i < this.capacity; i++) {
			LinkedList<MyPair<K, V>> bucketList = this.buckets.get(i);
			output.addAll(bucketList);//does this work?
		}

		return output;
	}

	
	
	@Override
	public MyHashIterator iterator() {
		return new MyHashIterator();
	}   

	
	private class MyHashIterator implements Iterator<MyPair<K,V>> {
		//added by myself:
		private ArrayList<MyPair<K, V>> myEntries;
		private int index;

		private MyHashIterator() {
			myEntries=new ArrayList<>(getEntries());
			index=0;
		}

		@Override
		public boolean hasNext() {
			return index<myEntries.size();
		}

		@Override
		public MyPair<K,V> next() {//if the iteration has no more elements?
			if(index==myEntries.size()) return null;

			MyPair<K,V> output=myEntries.get(index);
			index++;

			return output;
		}

	}
	
}
