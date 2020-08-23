package mybatis.dao;


import mybatis.model.UserDomain;

import java.util.List;

/**
 * Author Bryan.C <br>
 * Date 2018/6/20
 */
public interface UserDao {
    int insert(UserDomain record);
    List<UserDomain> selectUsers();
}
