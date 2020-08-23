package mybatis.service;

import com.github.pagehelper.PageInfo;
import mybatis.model.UserDomain;

/**
 * Author Bryan.C <br>
 * Date 2018/6/20
 */
public interface UserService {
    int addUser(UserDomain user);

    PageInfo findAllUser(int pageNum, int pageSize);

    String selectUserName(int userId);
}
