package com.oreki5.keionbu.controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
@CrossOrigin
public class PageController {

    @GetMapping
    public void getMethodName(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

}
