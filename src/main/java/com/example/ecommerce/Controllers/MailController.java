package com.example.ecommerce.Controllers;

import com.example.ecommerce.Models.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.ecommerce.utils.EmailService;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/email")
public class MailController {

        @Autowired
        private EmailService emailservice;

        //@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
        @PostMapping("/sendMail")
        public String sendMail() {

            System.out.println("Spring Mail -Sending Simple Email with JavaMailSender Exemple ");
            Mail mail = new Mail();
            mail.setFrom("oumaimasouguir79@gmail.com");
            mail.setTo("oumaima@wc5c8.onmicrosoft.com");
            mail.setSubject("sending simple mail with javamailsender example");
            mail.setContent("this tutorial demonstrates how to send a spring framework ");
            emailservice.sendSimpleMessage(mail);
            return "ok";


        }
    }
