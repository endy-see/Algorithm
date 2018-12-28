package HashRelevant;

import java.util.HashMap;

/**
 * Created by yetmare on 18-12-27.
 * 设计RandomPool结构
 * 题目：设计一种结构，在该结构中有如下三个功能：
 * insert(key): 将某个key加入到该结构，做到不重复加入
 * delete(key):　将原本在结构中的某个key移除
 * getRandom(): 等概率随机返回结构中的任何一个key
 * 要求：Insert，delete和getRandom方法的时间复杂度都是O(1)
 */
public class o1_RandomPool {
    public static class RandomPool<K> {
        private HashMap<K, Integer> map1;
        private HashMap<Integer, K> map2;
        private int size;

        public RandomPool() {
            this.map1 = new HashMap<>();
            this.map2 = new HashMap<>();
            this.size = 0;
        }

        public void insert(K key) {
            if(!this.map1.containsKey(key)) {
                map1.put(key, this.size);
                map2.put(this.size, key);
                size++;
            }
        }

        public K getRandom() {
            if(this.size == 0) {
                return null;
            }
            int index = (int) (Math.random()*this.size);
            return this.map2.get(index);
        }

        // 由于删除元素后仍然需要保持随机等概率　所以把index=size-1的元素覆盖待删除元素，而该index就仍然会维持连续了
        public void delete(K key) {
            if(this.map1.containsKey(key)) {
                Integer index = map1.get(key);
                int lastIndex = --size;
                K lastStr = map2.get(lastIndex);
                map1.put(lastStr, index);
                map1.remove(key);
                map2.put(index, lastStr);
                map2.remove(lastIndex);
            }
        }
    }

    public static void main(String[] args) {
        RandomPool<String> pool = new RandomPool<String>();
        pool.insert("zuo");
        pool.insert("cheng");
        pool.insert("yun");
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());

    }
}
