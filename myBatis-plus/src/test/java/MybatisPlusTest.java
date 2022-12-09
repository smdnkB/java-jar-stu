import com.liu.App;
import com.liu.entity.User;
import com.liu.mapper.UserMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootTest(classes = App.class) //启动类的字节码文件
@RunWith(SpringRunner.class)
public class MybatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectList(){
        List<User> users = userMapper.selectList(null);

        users.forEach(System.out::println);
    }
    @Test
    public void addOne(){
        for (int i = 0;i<10;i++){
            User user = new User("name"+i,(i+1)*10,i+"@qq.com");
            int insert = userMapper.insert(user);
            System.out.println(insert);
        }
    }
    @Test
    public void deleteAll(){
        ArrayList<Long> listParam = new ArrayList<>();
        for (int i = 0;i<10;i++){
            listParam.add(Long.valueOf(i+""));
        }
        int i = userMapper.deleteBatchIds(listParam); // Batch就是批量
        System.out.println(i);
    }

    @Test
    public void deleteByMap(){ // BaseMapper 接口中的map是用来构造条件的
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id",1L);  // 删除 id=1 的数据
        userMapper.deleteByMap(paramMap);
    }
}
