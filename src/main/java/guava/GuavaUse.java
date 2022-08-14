package guava;

import com.google.common.base.Preconditions;
import com.google.common.cache.*;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: SelectBook
 * @Date: 2022/7/10 2:46
 */
public class GuavaUse {
    public static void main(String[] args) {
        // 非空判断
        String param = "未读代码";
        String name = Preconditions.checkNotNull(param);
        System.out.println(name); // 未读代码
        String param2 = null;
        String name2 = Preconditions.checkNotNull(param2); // NullPointerException
        System.out.println(name2);
        // 预期值判断
        String param1 = "www.wdbyte.com2";
        String wdbyte = "www.wdbyte.com";
        Preconditions.checkArgument(wdbyte.equals(param1), "[%s] 404 NOT FOUND", param1);
        // java.lang.IllegalArgumentException: [www.wdbyte.com2] 404 NOT FOUND
        // 是否越界
        // Guava 中快速创建ArrayList
        List<String> list = Lists.newArrayList("a", "b", "c", "d");
        // 开始校验
        int index = Preconditions.checkElementIndex(5, list.size());
        // java.lang.IndexOutOfBoundsException: index (5) must be less than size (4)

        // 不可变集合
        // 创建方式1：of
        ImmutableSet<String> immutableSet = ImmutableSet.of("a", "b", "c");
        immutableSet.forEach(System.out::println);
        // a
        // b
        // c

        // 创建方式2：builder
        ImmutableSet<String> immutableSet2 = ImmutableSet.<String>builder()
                .add("hello")
                .add(new String("未读代码"))
                .build();
        immutableSet2.forEach(System.out::println);
        // hello
        // 未读代码

        // 创建方式3：从其他集合中拷贝创建
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("www.wdbyte.com");
        arrayList.add("https");
        ImmutableSet<String> immutableSet3 = ImmutableSet.copyOf(arrayList);
        immutableSet3.forEach(System.out::println);
        // www.wdbyte.com
        // https
        // 集合操作工厂
        // 创建一个 ArrayList 集合
        List<String> list1 = Lists.newArrayList();
        // 创建一个 ArrayList 集合，同时塞入3个数据
        List<String> list2 = Lists.newArrayList("a", "b", "c");
        // 创建一个 ArrayList 集合，容量初始化为10
        List<String> list3 = Lists.newArrayListWithCapacity(10);

        LinkedList<String> linkedList1 = Lists.newLinkedList();
        CopyOnWriteArrayList<String> cowArrayList = Lists.newCopyOnWriteArrayList();

        HashMap<Object, Object> hashMap = Maps.newHashMap();
        ConcurrentMap<Object, Object> concurrentMap = Maps.newConcurrentMap();
        TreeMap<Comparable, Object> treeMap = Maps.newTreeMap();

        HashSet<Object> hashSet = Sets.newHashSet();
        HashSet<String> newHashSet = Sets.newHashSet("a", "a", "b", "c");

        Set<String> newHashSet1 = Sets.newHashSet("a", "a", "b", "c");
        Set<String> newHashSet2 = Sets.newHashSet("b", "b", "c", "d");

        // 交集
        Sets.SetView<String> intersectionSet = Sets.intersection(newHashSet1, newHashSet2);
        System.out.println(intersectionSet); // [b, c]

        // 并集
        Sets.SetView<String> unionSet = Sets.union(newHashSet1, newHashSet2);
        System.out.println(unionSet); // [a, b, c, d]

        // newHashSet1 中存在，newHashSet2 中不存在
        Sets.SetView<String> setView = Sets.difference(newHashSet1, newHashSet2);
        System.out.println(setView); // [a]
        
    }

    public void testCache() throws ExecutionException, InterruptedException {

        CacheLoader cacheLoader = new CacheLoader<String, Animal>() {
            // 如果找不到元素，会调用这里
            @Override
            public Animal load(String s) {
                return null;
            }
        };
        LoadingCache<String, Animal> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000) // 容量
                .expireAfterWrite(3, TimeUnit.SECONDS) // 过期时间
                .removalListener(new MyRemovalListener()) // 失效监听器
                .build(cacheLoader); //
        loadingCache.put("狗", new Animal("旺财", 1));
        loadingCache.put("猫", new Animal("汤姆", 3));
        loadingCache.put("狼", new Animal("灰太狼", 4));

        loadingCache.invalidate("猫"); // 手动失效

        Animal animal = loadingCache.get("狼");
        System.out.println(animal);
        Thread.sleep(4 * 1000);
        // 狼已经自动过去，获取为 null 值报错
        System.out.println(loadingCache.get("狼"));
        /**
         * key=猫,value=Animal{name='汤姆', age=3},reason=EXPLICIT
         * Animal{name='灰太狼', age=4}
         * key=狗,value=Animal{name='旺财', age=1},reason=EXPIRED
         * key=狼,value=Animal{name='灰太狼', age=4},reason=EXPIRED
         *
         * com.google.common.cache.CacheLoader$InvalidCacheLoadException: CacheLoader returned null for key 狼.
         */
    }

    /**
     * 缓存移除监听器
     */
    class MyRemovalListener implements RemovalListener<String, Animal> {

        @Override
        public void onRemoval(RemovalNotification<String, Animal> notification) {
            String reason = String.format("key=%s,value=%s,reason=%s", notification.getKey(), notification.getValue(), notification.getCause());
            System.out.println(reason);
        }
    }

    class Animal {
        private String name;
        private Integer age;

        @Override
        public String toString() {
            return "Animal{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public Animal(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }
}
