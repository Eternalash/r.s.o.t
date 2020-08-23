package mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Author: Bryan.C <br>
 * Date:2020/3/23
 */
@RunWith(MockitoJUnitRunner.class)
public class InOrderTest {

    @Test
    public void test() {
        System.out.println("[{\"name\":\"Andrea\",\"age\":31,\"gender\":\"Male\",\"skilled\":true},{\"name\":\"Eva\",\"age\":27,\"gender\":\"Female\",\"skilled\":true},{\"name\":\"Daniele\",\"age\":26,\"gender\":\"Male\",\"skilled\":false}]".length());
        System.out.println("[[\"name\",\"age\",\"gender\",\"skilled\"],[\"Andrea\",31,\"Male\",true],[\"Eva\",27,\"Female\",true],[\"Daniele\",26,\"Male\",false]]".length());
    }
}
