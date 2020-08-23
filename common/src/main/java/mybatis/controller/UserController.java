package mybatis.controller;

import mybatis.model.UserDomain;
import mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Author Bryan.C <br>
 * Date 2018/6/20
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/add")
    public int addUser(UserDomain user) {
        return userService.addUser(user);
    }

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize) {
        return userService.findAllUser(pageNum, pageSize);
    }

    @ResponseBody
    @GetMapping("/get/{userId}")
    public String selectUserName(@PathVariable("userId") int userId) {
        return userService.selectUserName(userId);
    }
}
