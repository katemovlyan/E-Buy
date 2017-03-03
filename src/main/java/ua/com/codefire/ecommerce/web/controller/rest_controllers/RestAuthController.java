package ua.com.codefire.ecommerce.web.controller.rest_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.codefire.ecommerce.data.entity.User;
import ua.com.codefire.ecommerce.data.service.UserService;
import ua.com.codefire.ecommerce.utils.AttributeNames;

import javax.servlet.http.HttpSession;

/**
 * Created by ankys on 15.02.2017.
 */
@RequestMapping("/rest")
@RestController
public class RestAuthController {
    @Autowired
    UserService userService;

    @RequestMapping(path = "/token", method = RequestMethod.GET)
    public ResponseEntity<String> getToken(@RequestParam String userName, HttpSession session) {
        if(userService.getUserByName(userName) != null) {
            return new ResponseEntity<String>(session.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(path = "/getUser", method = RequestMethod.GET)
    public ResponseEntity<User> getTest1User(HttpSession session) {
        User result = userService.getUserByName("test1");
            return new ResponseEntity<User>(result, HttpStatus.OK);
    }
}
