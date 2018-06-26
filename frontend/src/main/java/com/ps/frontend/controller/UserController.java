package com.ps.frontend.controller;

import com.ps.common.dto.UserDTO;
import com.ps.frontend.gateway.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserGateway userGateway;

    @Autowired
    public UserController(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public void getTest() {
        String test = userGateway.test();
        System.out.println(test);
    }

    @GetMapping("/{id}")
    public ModelAndView details(@PathVariable("id") Long id, ModelAndView mav) {
        UserDTO user = userGateway.findById(id);

        mav.addObject("user", user);
        mav.setViewName("user/details");
        return mav;
    }

    @GetMapping("/list")
    public ModelAndView list(ModelAndView mav) {
        List<UserDTO> all = userGateway.findAll();

        mav.addObject("users", all);
        mav.setViewName("user/list");
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView openCreate(ModelAndView mav) {
        mav.addObject("user", new UserDTO());
        mav.setViewName("user/create");
        return mav;
    }

    @PostMapping("/create")
    public String create(UserDTO userDTO) {

        userGateway.save(userDTO);
        return "redirect:/user/list";
    }


    @GetMapping("/{id}/edit")
    public ModelAndView openEdit(@PathVariable("id") Long id, ModelAndView mav) {
        UserDTO user = userGateway.findById(id);

        mav.addObject("user", user);
        mav.setViewName("user/create");
        return mav;
    }
}
