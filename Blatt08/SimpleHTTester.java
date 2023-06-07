public class SimpleHTTester {
    public static void main(String[] args) {
        SimpleHT hashTable = new SimpleHT(10);
        
        hashTable.insert(1, 10);
        hashTable.insert(2, 20);

        System.out.println(hashTable.data.toString());

        hashTable.insert(11, 30); // Kollision mit Schlüssel 1

        System.out.println(hashTable.data.toString());

        hashTable.insert(21, 40); // Kollision mit Schlüssel 1

        System.out.println(hashTable.data.toString());

        hashTable.insert(12, 50);
        
        System.out.println(hashTable.data.toString());

        System.out.println(hashTable.get(1)); // Ausgabe: 10
        System.out.println(hashTable.get(2)); // Ausgabe: 20
        System.out.println(hashTable.get(11)); // Ausgabe: 30
        System.out.println(hashTable.get(21)); // Ausgabe: 40
        System.out.println(hashTable.get(12)); // Ausgabe: 50
        
        hashTable.remove(1);
        System.out.println(hashTable.get(1)); // Ausgabe: null
        System.out.println(hashTable.size); // Ausgabe: 4
    }
}

