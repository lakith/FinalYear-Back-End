package com.finalproj.finalproject.service.impl;

import com.sendgrid.*;
import java.io.IOException;

public class SendGridMailClient {


    public static void sendMail(String mailbody, String sendMail,String eventName) throws Exception {




                Email from = new Email("lakith1995@gmail.com");
                String subject = eventName;
                Email to = new Email(sendMail);
                Content content = new Content("text/html", mailbody);
                Mail mail = new Mail(from, subject, to, content);

                SendGrid sg = new SendGrid("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");
                Request request = new Request();
                try {
                    request.setMethod(Method.POST);
                    request.setEndpoint("mail/send");
                    request.setBody(mail.build());
                    Response response = sg.api(request);
                    System.out.println(response.getStatusCode());
                    System.out.println(response.getBody());
                    System.out.println(response.getHeaders());
                } catch (IOException ex) {
                    throw new Exception(ex.getMessage());
                }
    }

}



