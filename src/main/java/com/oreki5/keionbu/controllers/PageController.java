package com.oreki5.keionbu.controllers;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/")
public class PageController {

    @GetMapping
    public void getMethodName(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

}
