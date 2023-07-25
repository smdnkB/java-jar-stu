import com.liu.App;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/7/23 9:54
 * @version: 1.0
 */
@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class TestJedis {

    private Jedis jedis; // 单机模式
    private JedisCluster jedisCluster; // 集群模式


    @Before
    public void init(){
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.0.8",6100));
        jedisCluster = new JedisCluster(nodes);
//        jedis = new Jedis("192.168.0.8",6100);
//        jedis.auth("");
//        jedis.select(0);
    }

    @Test
    public void testString(){
        String set = jedisCluster.set("testString:name", "liu");
        System.out.println(set);
        String s = jedisCluster.get("testString:name");
        System.out.println(s);
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
        if (jedis!=null)jedis.close();
    }
}
