package ua.com.codefire.ecommerce.web.controller.web_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.codefire.ecommerce.data.entity.User;
import ua.com.codefire.ecommerce.data.service.UserService;
import ua.com.codefire.ecommerce.utils.AttributeNames;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by ankys on 11.02.2017.
 */
@RequestMapping("/users")
@Controller
public class UserController {
    @Autowired
    UserService userService;

    private static final int amountByPage = 20;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String getListUsers(Model model) {
        List<User> usersByPage = userService.getUsersByPage(1, amountByPage);
        long totalUsers = userService.getAmountOfEntities();

        model.addAttribute("users", usersByPage);
        model.addAttribute("totalUsersCount", totalUsers);

        long remainder = totalUsers % amountByPage;
        model.addAttribute("numberOfPages", (int) (Math.ceil(totalUsers / amountByPage) + remainder / 10));

        return "users/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddUser() {
        return "users/edit";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postAddUser() {
        return "users/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEditUser() {
        return "users/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEditUser() {
        return "users/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String pageList(@RequestParam Integer pageNumber, @RequestParam Integer amountPerPage, Model model) {
        List<User> allProducts = userService.getUsersByPage(pageNumber, amountPerPage);
        model.addAttribute("users", allProducts);
        model.addAttribute("totalUsersCount", userService.getAmountOfEntities());

        long totalProducts = userService.getAmountOfEntities();
        long remainder = totalProducts % amountByPage;
        model.addAttribute("numberOfPages", (int) (Math.ceil(totalProducts / amountByPage) + remainder / 10));

        return "users/list";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getUserProfile(Model model) {
        return "users/profile";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute(AttributeNames.SESSION_AUTHENTICATED);
        session.removeAttribute(AttributeNames.SESSION_USER);
        session.removeAttribute(AttributeNames.SESSION_USERNAME);
        return "redirect:/auth/";
    }
}
