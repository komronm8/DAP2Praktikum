class WorstHashEver<K> implements SimpleHashFunction<K> {
    public int getHash(K key, int m) {
        return (key.equals(0)) ? 1 : 0;
    }
}

class WorstStringHashEver implements SimpleHashFunction<String> {
    public int getHash(String key, int m) {
        try {
            return Integer.parseInt(key);
        } catch (Exception e) {
            return 0;
        }
    }
}

public class ParamHTTester {
    public static void main(String[] args) {
        ParamHT<Double, Float> hashTable1 = new ParamHT<>(1337, new WorstHashEver<>());
        hashTable1.insert(0.0, 3.14f);
        hashTable1.insert(1.23, 4.56f);
        
        System.out.println(hashTable1.get(0.0)); // Ausgabe: 3.14
        System.out.println(hashTable1.get(1.23)); // Ausgabe: 4.56
        
        ParamHT<String, Object> hashTable2 = new ParamHT<>(1337, new WorstStringHashEver());
        hashTable2.insert("123", "abc");
        hashTable2.insert("456", "def");
        
        System.out.println(hashTable2.get("123")); // Ausgabe: abc
        System.out.println(hashTable2.get("456")); // Ausgabe: def
    }
}

