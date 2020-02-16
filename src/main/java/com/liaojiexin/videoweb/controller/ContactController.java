package com.liaojiexin.videoweb.controller;

import com.liaojiexin.videoweb.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContactController {        //留言
    @Autowired
    private ContactService contactService;

    @PostMapping(value = "/contact/message")
    public String contact(@RequestParam("name") String name,
                          @RequestParam("email") String email,
                          @RequestParam("message") String message,
                          Model model)
    {
        contactService.insertContact(name, email, message);
        return "redirect:/contact";
    }
}
