package mybatis.mapper;

import mybatis.model.UserDomain;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

/**
 * Author Bryan.C <br>
 * Date 2018/7/1
 */
public interface UserMapper {
    @Insert("Insert into t_user(userName,password,phone) values (#{userName},#{password},#{phone})")
    @SelectKey(statement = "select @@IDENTITY",keyColumn = "userId", keyProperty = "userId", before = false, resultType = int.class)
    int insert(UserDomain record);

    @Select("select * from t_user")
    List<UserDomain> selectUsers();

    @Select("select userName from t_user where userId=#{userId}")
    String selectUserName(int userId);
}
