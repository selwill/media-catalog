package org.launchcode.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.launchcode.models.MediaMessage.createMessage;
import static org.launchcode.models.MediaMessage.getLanguages;



@Controller
public class MediaController {

    private Integer count = 0;

    @RequestMapping(value = "")
    @ResponseBody
    public String index(HttpServletRequest request) {


        String name = request.getParameter("name");
        if (name == null) {
            name = "World";
        }
        return "Hello " + name;
    }

    @RequestMapping(value = "hello", method=RequestMethod.GET)
    @ResponseBody
    public String helloForm() {
        HashMap<String, String> languages = getLanguages();

        String html = "<form method='post'>" +
                "<input type='text' name='name' />" +
                "<select name='lang'>";

        for (Map.Entry<String, String> lang : languages.entrySet()) {
            html += "<option value='" + lang.getKey() + "'>" + lang.getValue() + "</option>";
        }

        html += "</select>" +
                "<input type='submit' value='Greet Me!' />" +
                "</form>";
        return html;
    }

    @RequestMapping(value = "hello", method=RequestMethod.POST)
    @ResponseBody
    public String helloPost(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String language = request.getParameter("lang");

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("page-views")) {
                    count = Integer.parseInt(c.getValue()) + 1;
                    c.setValue(count.toString());
                    response.addCookie(c);
                }
            }
        }
        else {
            count = 1;
            Cookie cookie = new Cookie("page-views", count.toString());
            response.addCookie(cookie);
        }

        return createMessage(language, name) + " " + count;
    }

    @RequestMapping(value = "hello/{name}")
    @ResponseBody
    public String helloUrlSegment(@PathVariable String name) {

        return "Hello " + name;
    }

    @RequestMapping(value = "goodbye")
    public String goodbye() {

        return "redirect:/";
    }


}
