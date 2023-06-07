public class GenHTTester {
    public static void main(String[] args) {
        GenHT<String, Integer> hashTable = new GenHT<>(10);
        
        hashTable.insert("key1", 10);
        hashTable.insert("key2", 20);
        hashTable.insert("key3", 30);
        hashTable.insert("key4", 40);
        
        System.out.println(hashTable.get("key1")); // Ausgabe: 10
        System.out.println(hashTable.get("key2")); // Ausgabe: 20
        System.out.println(hashTable.get("key3")); // Ausgabe: 30
        System.out.println(hashTable.get("key4")); // Ausgabe: 40
        
        hashTable.remove("key2");
        System.out.println(hashTable.get("key2")); // Ausgabe: null
        System.out.println(hashTable.size); // Ausgabe: 3
    }
}

