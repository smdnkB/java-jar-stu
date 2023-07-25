import com.liu.App;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/7/23 13:31
 * @version: 1.0
 */
@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class TestJedisPool {

    private JedisCluster jedisCluster;

    @Before
    public void init(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接
        poolConfig.setMaxTotal(8);
        // 最大空闲连接
        poolConfig.setMaxIdle(8);
        // 最小空闲连接
        poolConfig.setMinIdle(0);
        // 最长等待时间
        poolConfig.setMaxWaitMillis(300);

        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.0.8",6100));

        jedisCluster = new JedisCluster(nodes,poolConfig);


    }

    @Test
    public void testHash(){
        Long hset = jedisCluster.hset("testHash:student1", "name", "liu");

        HashMap<String,String> val = new HashMap<>();
        val.put("name","long");
        val.put("age","18");
        String hmset = jedisCluster.hmset("testHash:student2", val);

        System.out.println(hset+"====="+hmset);
    }


    @After
    public void dis(){
        if (jedisCluster!=null)jedisCluster.close();

    }
}
