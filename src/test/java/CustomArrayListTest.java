import com.example.customlist.CustomArrayList;
import com.example.customlist.CustomList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.UUID;

class CustomArrayListTest {
    private CustomList<Integer> integerCustomList;
    private CustomList<String> stringCustomList;

    @BeforeEach
    void init() {
        integerCustomList = new CustomArrayList<>();
        stringCustomList = new CustomArrayList<>();
    }

    @Test
    void testNullTest() {
        Assertions.assertEquals(0, stringCustomList.size());
        Assertions.assertTrue(stringCustomList.isEmpty());
        Assertions.assertFalse(stringCustomList.contains("Peace"));
    }

    @Test
    void testAdd() {
        Integer number = 12;
        integerCustomList.add(number);
        Assertions.assertEquals(integerCustomList.get(0), number);
        Assertions.assertTrue(integerCustomList.contains(number));
    }

    @Test
    void testAddByIndex() {
        int loopTimes = (int) (Math.random() * 100);
        for (int i = 0; i < loopTimes; i++) {
            integerCustomList.add(i, i);
        }

        Assertions.assertEquals(integerCustomList.size(), loopTimes);
    }

    @Test
    void testGet() {
        Assertions.assertEquals(0, stringCustomList.size());
        int loopTimes = 20;
        String prefix = "item-";
        for (int i = 0; i < loopTimes; i++) {
            stringCustomList.add(prefix + i);
        }

        for (int i = 0; i < loopTimes; i++) {
            Assertions.assertEquals(stringCustomList.get(i), prefix + i);
        }
    }

    @Test
    void testSize() {
        int loopTimes = (int) (Math.random() * 100);
        for (int i = 0; i < loopTimes; i++) {
            stringCustomList.add("Apple-" + i);
        }

        Assertions.assertEquals(stringCustomList.size(), loopTimes);
    }

    @Test
    void testClear() {
        int loopTimes = (int) (Math.random() * 100);
        for (int i = 0; i < loopTimes; i++) {
            integerCustomList.add(i);
        }

        Assertions.assertEquals(integerCustomList.size(), loopTimes);
        Assertions.assertFalse(integerCustomList.isEmpty());

        integerCustomList.clear();
        Assertions.assertEquals(0, integerCustomList.size());
        Assertions.assertTrue(integerCustomList.isEmpty());
    }

    @Test
    void testRemove() {
        int loopTimes = (int) (Math.random() * 100);
        for (int i = 0; i < loopTimes; i++) {
            integerCustomList.add(i);
        }

        Assertions.assertEquals(integerCustomList.size(), loopTimes);

        for (int i = 0; i < loopTimes; i++) {
            integerCustomList.remove(integerCustomList.get(0));
        }

        Assertions.assertEquals(0, integerCustomList.size());
        Assertions.assertTrue(integerCustomList.isEmpty());
    }

    @Test
    void testSet() {
        int loopTimes = (int) (Math.random() * 100);

        for (int i = 0; i < loopTimes; i++) {
            stringCustomList.add(0, String.valueOf(i));
        }
        Assertions.assertNotEquals(0, stringCustomList.size());
        Assertions.assertFalse(stringCustomList.isEmpty());

        String randomString = UUID.randomUUID().toString();

        int randomIndex = (int) (Math.random() * stringCustomList.size());
        stringCustomList.set(randomIndex, randomString);
        Assertions.assertEquals(randomString, stringCustomList.get(randomIndex));

    }

    @Test
    void testIndexOf() {
        int loopTimes = (int) (Math.random() * 100);

        for (int i = 0; i < loopTimes; i++) {
            integerCustomList.add(i);
        }

        int randomIndex = (int) (Math.random() * integerCustomList.size());
        Integer target = integerCustomList.get(randomIndex);

        for (int i = 0; i < loopTimes; i++) {
            Assertions.assertEquals((int) randomIndex, integerCustomList.indexOf(target));
        }
    }

    @Test
    void testSort() {
        integerCustomList.add(5);
        integerCustomList.add(3);
        integerCustomList.add(7);
        integerCustomList.add(1);
        integerCustomList.sort();

        Integer[] expected = {1, 3, 5, 7};

        Assertions.assertArrayEquals(expected, integerCustomList.toArray());
    }

    @Test
    void testSortDescending() {
        stringCustomList.add("apple");
        stringCustomList.add("banana");
        stringCustomList.add("orange");
        stringCustomList.add("pear");
        stringCustomList.sortDescending();

        String[] expected = {"pear", "orange", "banana", "apple"};

        Assertions.assertArrayEquals(expected, stringCustomList.toArray());
    }


    @Test
    void testIterator() {
        integerCustomList.add(5);
        integerCustomList.add(7);

        Iterator<Integer> iterator = integerCustomList.iterator();

        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(5, iterator.next());

        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(7, iterator.next());

        Assertions.assertFalse(iterator.hasNext());

    }
}

