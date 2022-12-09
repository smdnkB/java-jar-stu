import com.liu.App;
import com.liu.redis.RedisStu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class testRedis {
    @Autowired
    private RedisStu redisStu;

    @Test
    public void test01(){
        redisStu.testRedisSet();
    }
    @Test
    public void test02(){
        redisStu.testRedisGet();
    }
}
