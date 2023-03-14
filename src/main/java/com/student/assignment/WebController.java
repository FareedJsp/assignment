package com.student.assignment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping(value = "/index")
        public String index() {
        return "index";
    }

    @RequestMapping(value = "/front")
        public String front() {
        return "front";
    }

    @RequestMapping(value = "/author")
        public String author() {
        return "user";
    }
}
