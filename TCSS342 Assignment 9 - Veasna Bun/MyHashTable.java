
public class MyHashTable<Key extends Comparable<Key>, Value> {
	private Integer capacity, size;
	private Key[] keyBuckets;
	private Value[] valueBuckets;
	public MyArrayList<Key> keys;
	public Integer comparisons, maxProbe;

	@SuppressWarnings("unchecked")
	public MyHashTable(Integer capacity) {
		this.capacity = capacity;
		this.size = this.comparisons = this.maxProbe = 0;
		this.keyBuckets = (Key[]) new Comparable[capacity];
		this.valueBuckets = (Value[]) new Object[capacity];
		this.keys = new MyArrayList<Key>();
	}

	private Integer hash(Key key) {
		return ((Integer) (Math.abs(key.hashCode()) % this.capacity));
	}

	public Value get(Key key) {
		for (int probe = 1, index = hash(key); keyBuckets[index] != null; probe++) {
			if (keyBuckets[index].compareTo(key) == 0) {
				return valueBuckets[index];
			}
			index = (index + 1) % this.capacity;
			if (probe > maxProbe) {
				maxProbe = probe;
			}
		}
		return null;
	}

	public void put(Key key, Value value) {
		int temp = hash(key), index = temp, probe = 1;
		comparisons++;
		do {
			if (keyBuckets[index] == null) {
				keyBuckets[index] = key;
				keys.insert(key, size);
				valueBuckets[index] = value;
				size++;
				return;
			}
			if (keyBuckets[index].compareTo(key) == 0) {
				valueBuckets[index] = value;
				return;
			}
			index = (index + 1) % this.capacity;
			probe++;
			comparisons++;
			if (probe > maxProbe) {
				maxProbe = probe;
			}
		} while (index != temp);
	}

	public Integer size() {
		return this.size;
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
}
