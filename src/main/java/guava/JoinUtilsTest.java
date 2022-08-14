package guava;

import com.google.common.collect.Table;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: SelectBook
 * @Date: 2022/7/10 18:35
 */
public class JoinUtilsTest {
    private Table<String, String, Object> leftTable;
    private Table<String, String, Object> rightTable;
    
    void createTable() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        map.put("id", "a");
        map.put("name", "x");
        map1.put("id", "b");
        map1.put("name", "y");
        map2.put("id", "c");
        map2.put("name", "z");
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map);
        list.add(map1);
        list.add(map2);
        leftTable = Join.createTable(list, "id");
        Map<String, Object> map3 = new HashMap<>();
        Map<String, Object> map4 = new HashMap<>();
        Map<String, Object> map5 = new HashMap<>();
        map3.put("id", "a");
        map3.put("val", "o");
        map4.put("id", "b");
        map4.put("val", "p");
        map5.put("id", "d");
        map5.put("val", "q");
        List<Map<String, Object>> list2 = new ArrayList<>();
        list2.add(map3);
        list2.add(map4);
        list2.add(map5);
        rightTable = Join.createTable(list2, "id");
        System.out.println("LeftTable");
//        Join.printTable(leftTable);
        System.out.println("RightTable");
//        Join.printTable(rightTable);
    }
    
    void testCreateTable2() {
//        User tom = new User(1, "tom", false);
//        User john = new User(2, "john", true);
//        User pet = new User(3, "pet", true);
//        ArrayList<User> users = new ArrayList<>();
//        users.add(tom);
//        users.add(john);
//        users.add(pet);
//        Table<Integer, String, Object> userTable = Join.createTable(users, User.class, "name");        
//        JoinUtils.printTable(userTable);
    }
    @Test
    void testStrategy() {
        JoinStrategy[] values = JoinStrategy.values();
        for (JoinStrategy strategy : values) {
            System.out.println(strategy);
//            JoinUtils.printTable(Join.join(leftTable, rightTable, strategy));
        }
    }
}
