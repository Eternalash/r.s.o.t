package mockito;

import org.junit.Test;
import org.mockito.InOrder;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Author: Bryan.C <br>
 * Date:2020/3/12
 */
public class SpyTest {
    @Test
    public void testLinkedListSpyWrong() {
        // 让我们来模拟一个LinkedList
        List<String> list = new LinkedList<>();
        List<String> spy = spy(list);
        spy.add("A");
        spy.add("A");
        spy.add("B");
        spy.add("A");
//fails:
        InOrder inOrder = inOrder(spy);
//        inOrder.verify(spy, atLeast(2)).add("A");
//        inOrder.verify(spy, atLeast(1)).add("B");

        //passes:
        inOrder.verify(spy, times(2)).add("A");
        inOrder.verify(spy, atLeast(1)).add("B");

        // spy.get(0)将会调用真实的方法
        // 将会抛出 IndexOutOfBoundsException (list是空的)
        when(spy.get(0)).thenReturn("foo");
        assertEquals("foo", spy.get(0));

    }
    @Test
    public void testLinkedListSpyCorrect() {
        // 让我们来模拟一个LinkedList
        List<String> list = new LinkedList<>();
        List<String> spy = spy(list);
        // 必须使用doReturn来插桩
        doReturn("foo").when(spy).get(0);
        assertEquals("foo", spy.get(0));
    }
}
