import java.util.ArrayList;

public class SimpleHT {
    
    public int size;
    public ArrayList<ArrayList<Pair>> data;


    public SimpleHT(int capacity){
        this.size = capacity;
        data = new ArrayList<ArrayList<Pair>>(capacity);

        for (int i = 0; i < capacity; i++) {
            data.add(null);
        }
    }

    public int addressOf(Integer key){
        return Math.floorMod(key, size);
    }

    public void insert(Integer key, Integer value) {
        Pair newPair = new Pair(key, value);
        int hashIndex = addressOf(key);
        ArrayList<Pair> list = data.get(hashIndex);


        if( list == null ){
            list = new ArrayList<Pair>();
            list.add(newPair);
            data.set(hashIndex, list);
        }
        else{
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getKey().equals(key)) {
                    list.set(i, newPair);
                    return;
                }
            }
            list.add(newPair);
        }
    }

    public Integer get(Integer key) {
        int hashIndex = addressOf(key);
        ArrayList<Pair> list = data.get(hashIndex);

        if (list != null) {
            for (Pair p: list) {
                if (p.getKey().equals(key)) {
                    return p.getValue();
                }
            }
        }
        return null;
    }

    public boolean remove(Integer key) {
        int hashIndex = addressOf(key);
        boolean flag = false;
        ArrayList<Pair> list = data.get(hashIndex);

        if (list != null){
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getKey().equals(key)) {
                    flag = true;
                    list.remove(i);
                }
            }
        }
        return flag;
    }

    public class Pair{
        Integer key;
        Integer value;

        public Pair(int key, int value){
            this.key = key;
            this.value = value;
        }

        public Integer getKey(){
            return key;
        }

        public Integer getValue(){
            return value;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "key=" + key +
                    ", value='" + value + '}';
        }

    }

}
