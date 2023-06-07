import java.util.Arrays;

public class SimpleHTTest {
    
    public static void main(String[] args){

        SimpleHT hash = new SimpleHT(10);

        hash.insert(40, 2);
        hash.insert(41, 5);
        hash.insert(50, 3);

        System.out.println(hash.data.toString());

        System.out.println(hash.get(Integer.valueOf(40)).toString());

        hash.remove(40);

        System.out.println(hash.data.toString());

    }

}
