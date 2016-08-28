/**
 * Created by yanzexin on 16/8/28.
 * All right reserved.
 */
import Json.JSONArray;
public class TestJson {
    public static void main(String[] args) {
        JSONArray jarray = new JSONArray("[\"yan\",\"ze\"]");

        jarray.put("test");
//        jarray.put(0, "yan");
        for (Object per :
                jarray) {
            System.out.println(per);
        }
        System.out.println(jarray);
    }
}
