
public class MyHashTable<Key extends Comparable<Key>, Value> {
	private Integer capacity;
	private Key[] keyBuckets;
	private Value[] valueBuckets;
	private Integer size;
	public MyArrayList<Key> keys;
	public Integer comparisons;
	public Integer maxProbe;

	@SuppressWarnings("unchecked")
	public MyHashTable(Integer capacity) {
		this.capacity = capacity;
		this.size = 0;
		this.comparisons = 0;
		this.maxProbe = 0;
		this.keyBuckets = (Key[]) new Comparable[capacity];
		this.valueBuckets = (Value[]) new Object[capacity];
		this.keys = new MyArrayList<Key>();

	}

	private Integer hash(Key key) {
		return ((Integer) (Math.abs(key.hashCode()) % this.capacity));
	}

	public Value get(Key key) {
		for (int count = 1, i = hash(key); keyBuckets[i] != null; count++) {
			if (keyBuckets[i].compareTo(key) == 0) {
				return valueBuckets[i];
			}
			i = (i + 1) % this.capacity;
			if (count > maxProbe) {
				maxProbe = count;
			}
		}
		return null;
	}

	public void put(Key key, Value value) {
		comparisons++;
		int temp = hash(key);
		int i = temp;
		int count = 1;
		do {
			if (keyBuckets[i] == null) {
				keyBuckets[i] = key;
				valueBuckets[i] = value;
				size++;
				return;
			}
			if (keyBuckets[i].compareTo(key) == 0) {
				valueBuckets[i] = value;
				return;
			}
			i = (i + 1) % this.capacity;
			count = count + 1;
			comparisons++;
			if (count > maxProbe) {
				maxProbe = count;
			}
		} while (i != temp);
	}

	public Integer size() {
		return size;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (size == 0) {
			return "[]";
		} else {
			sb.append("[");
			for (int i = 0; i < this.capacity; i++) {
				if (valueBuckets[i] != null) {
					sb.append(keyBuckets[i] + ":" + valueBuckets[i] + ", ");
				}
			}
			sb.replace(sb.length() - 2, sb.length(), "");
			sb.append("]");
			return sb.toString();
		}
	}
	public static void main(String [] args) {
		MyHashTable<Integer,Integer> s = new MyHashTable<Integer,Integer>(10);
		for(int i = 0; i < 10; i++) {
			s.put(i, i);
		}
		for(int i = 0; i < 10; i++) {
			s.put(i, i * 10);
		}
		System.out.println(s.comparisons);
		System.out.println(s.size());
		System.out.println(s.maxProbe);
		System.out.println(s);
		
	}
}
