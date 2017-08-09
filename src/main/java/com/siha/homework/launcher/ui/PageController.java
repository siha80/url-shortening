package com.siha.homework.launcher.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * JSP 페이지 로딩을 위한 Controller
 */
@Controller
public class PageController {
    @RequestMapping(value = "convert_page")
    public String convertPage() {
        return "convert_page";
    }
}
