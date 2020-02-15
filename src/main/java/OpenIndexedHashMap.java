import java.util.NoSuchElementException;
import java.util.Objects;

public class OpenIndexedHashMap {
    private static final int CAPACITY = 10;
    private static final double LOAD_FACTOR = 0.7;
    private int initialCapacity;
    public Entry[] table;
    private int size = 0;

    public OpenIndexedHashMap() {
        initialCapacity = CAPACITY;
        table = new Entry[initialCapacity];
    }

    private int countBucketPosition(Integer key) {
        return key == null ? 0 : Math.abs(key.hashCode() % table.length);
    }

    private void putInEmptyBucket(int position, Entry entry) {
        table[position] = entry;
        size++;
    }

    public void put(int key, long value) {
        resize();
        Entry newEntry = new Entry(Integer.valueOf(key), Long.valueOf(value));
        int position = countBucketPosition(newEntry.getKey());
        Entry temp = table[position];
        if (temp == null) {
            putInEmptyBucket(position, newEntry);
            return;
        }
        while (position < table.length) {
            if (position == table.length - 1) {
                position = 0;
            }

            temp = table[position];
            if (temp == null) {
                putInEmptyBucket(position, new Entry(key, value));
                return;
            }
            if (newEntry.getKey() == (temp.getKey())) {
                temp.setValue(value);
                return;
            }
            position++;
        }
    }

    public long getValue(int key) {
        int position = countBucketPosition(Integer.valueOf(key));
        Entry temp = table[position];
        while (position < table.length && temp != null) {
            if (position == table.length - 1) {
                position = 0;
            }
            if (temp.getKey() == key) {
                return temp.getValue();
            }
            temp = table[position];
            position++;
        }
        throw new NoSuchElementException("There isn't element with such key");
    }

    public int getSize() {
        return size;
    }

    public void resize() {
        if (size == initialCapacity * LOAD_FACTOR) {
            size = 0;
            Entry[] tempTable = table;
            initialCapacity = initialCapacity * 2;
            if (initialCapacity > (Integer.MAX_VALUE - 1)) {
                throw new RuntimeException("Can't resize map, it has maximum capacity.");
            }
            table = new Entry[table.length * 2];
            for (int i = 0; i < tempTable.length; i++) {
                if (tempTable[i] != null) {
                    put(tempTable[i].getKey(), tempTable[i].getValue());
                }
            }
        }
    }

    private static class Entry {
        private Integer key;
        private Long value;

        public Entry(Integer key, Long value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Entry entry = (Entry) o;
            return key.intValue() == entry.key.intValue()
                    && value.intValue() == entry.value.intValue();
        }

        @Override
        public int hashCode() {
            return Math.abs(Objects.hash(key) ^ Objects.hash(value));
        }

        public int getKey() {
            return key.intValue();
        }

        public long getValue() {
            return value.longValue();
        }

        public void setValue(long value) {
            this.value = Long.valueOf(value);
        }
    }
}
