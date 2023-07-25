import com.liu.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/7/23 13:53
 * @version: 1.0
 */
@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class TestSpringRedis {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void testString(){
        redisTemplate.opsForValue().set("redisTemplate:testString:name","liu");
        System.out.println(redisTemplate.opsForValue().get("redisTemplate:testString:name"));

    }
}
