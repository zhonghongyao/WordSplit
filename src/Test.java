import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("qwe", 234);
        map.put("wer", 5341);
        map.put("sdf",56);
        map.put("cvb",78);

        Map<String, Integer> map1=MapSort.sortMapByValue(map);
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }
}
