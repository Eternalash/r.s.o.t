package mybatis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mybatis.mapper.UserMapper;
import mybatis.model.UserDomain;
import mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author Bryan.C <br>
 * Date 2018/6/20
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userDao;

    @Override
    public int addUser(UserDomain user) {
        return userDao.insert(user);
    }

    /**
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     */
    @Override
    public PageInfo findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<UserDomain> userDomains = userDao.selectUsers();
        return new PageInfo(userDomains);
    }


    @Override
    public String selectUserName(int userId) {
        return userDao.selectUserName(userId);
    }
}
