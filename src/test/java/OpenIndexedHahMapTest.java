import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class OpenIndexedHahMapTest {

    @Test
    public void sizeTest() {
        OpenIndexedHashMap openIndexedHashMap = new OpenIndexedHashMap();
        for (int i = 0; i < 10; i++) {
            openIndexedHashMap.put(i, i * i);
        }

        Assert.assertEquals("The test was failed! The size of map isn't correct. " +
                "Expected 10 but was " + openIndexedHashMap.getSize(),
                10, openIndexedHashMap.getSize());
    }

    @Test(expected = NoSuchElementException.class)
    public void getByNonExistedKey() {
        OpenIndexedHashMap openIndexedHashMap = new OpenIndexedHashMap();
        openIndexedHashMap.getValue(21);
    }

    @Test(expected = NoSuchElementException.class)
    public void getByNonExistedKeyButWithSameBucket() {
        OpenIndexedHashMap openIndexedHashMap = new OpenIndexedHashMap();
        openIndexedHashMap.put(41, 12);
        openIndexedHashMap.getValue(21);
    }

    @Test
    public void putAndGetTest() {
        OpenIndexedHashMap openIndexedHashMap = new OpenIndexedHashMap();
        openIndexedHashMap.put(-5, 123);
        openIndexedHashMap.put(12, 321);
        openIndexedHashMap.put(-21, 2479);
        openIndexedHashMap.put(33, 452);
        openIndexedHashMap.put(0, 1334);

        Assert.assertEquals("The test was failed! It's wrong value. Expected 321 but was "
                + openIndexedHashMap.getValue(12), 321L, openIndexedHashMap.getValue(12));
        Assert.assertEquals("The test was failed! It's wrong value. Expected 123 but was "
                + openIndexedHashMap.getValue(-5), 123L, openIndexedHashMap.getValue(-5));
        Assert.assertEquals("The test was failed! It's wrong value. Expected 2479 but was "
                + openIndexedHashMap.getValue(-21), 2479L, openIndexedHashMap.getValue(-21));
        Assert.assertEquals("The test was failed! It's wrong value. Expected 452 but was "
                + openIndexedHashMap.getValue(33), 452L, openIndexedHashMap.getValue(33));
        Assert.assertEquals("The test was failed! It's wrong value. Expected 1334 but was "
                + openIndexedHashMap.getValue(0), 1334L, openIndexedHashMap.getValue(0));
    }

    @Test
    public void putTheElementWithSameKeyTest() {
        OpenIndexedHashMap openIndexedHashMap = new OpenIndexedHashMap();
        openIndexedHashMap.put(12, 21);
        openIndexedHashMap.put(12, 33);

        Assert.assertEquals("Test failed! It should change value when put with same key ",
                1, openIndexedHashMap.getSize());

        Assert.assertEquals("The test was failed! It's wrong value. Expected 33 but was "
                + openIndexedHashMap.getValue(12), 33L, openIndexedHashMap.getValue(12));
    }

    @Test
    public void putAndGetWhenValueOverriddenTest() {
        OpenIndexedHashMap openIndexedHashMap = new OpenIndexedHashMap();
        openIndexedHashMap.put(12, 21);

        Assert.assertEquals("The test was failed! It's wrong value. Expected 21 but was "
                + openIndexedHashMap.getValue(12), 21L, openIndexedHashMap.getValue(12));

        openIndexedHashMap.put(12, 33);

        Assert.assertEquals("The test was failed! It's wrong value. Expected 33 but was "
                + openIndexedHashMap.getValue(12), 33L, openIndexedHashMap.getValue(12));
    }

    @Test
    public void putAndGetWithCollisionTest() {
        OpenIndexedHashMap openIndexedHashMap = new OpenIndexedHashMap();
        openIndexedHashMap.put(12, 1);
        openIndexedHashMap.put(-22, 2);
        openIndexedHashMap.put(32, 3);
        openIndexedHashMap.put(-52, 4);
        openIndexedHashMap.put(72, 5);

        Assert.assertEquals("The test was failed! The size of map isn't correct. Expected 3 but was "
                + openIndexedHashMap.getSize(), 5, openIndexedHashMap.getSize());
    }

    @Test
    public void getSizeOfEmptyMapTest() {
        OpenIndexedHashMap openIndexedHashMap = new OpenIndexedHashMap();
        Assert.assertEquals("The test was failed! The size of map isn't correct. Expected 0 but was "
                + openIndexedHashMap.getSize(), 0, openIndexedHashMap.getSize());
        ;
    }

    @Test
    public void checkIfMapSizeIncreasedTest() {
        OpenIndexedHashMap openIndexedHashMap = new OpenIndexedHashMap();
        for (int i = 0; i < 1000; i++) {
            openIndexedHashMap.put(i, i * i);
        }
        Assert.assertEquals("Test failed! The size isn't correct. Expected 1000 but was "
                + openIndexedHashMap.getSize(), 1000, openIndexedHashMap.getSize());
        for (int i = 0; i < 1000; i++) {
            Assert.assertEquals(i * i, openIndexedHashMap.getValue(i));
        }
    }
}
