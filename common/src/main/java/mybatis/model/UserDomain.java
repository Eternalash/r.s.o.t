package mybatis.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Author Bryan.C <br>
 * Date 2018/6/20
 */
@Setter
@Getter
public class UserDomain {
    private Integer userId;
    private String userName;
    private String password;
    private String phone;
}
