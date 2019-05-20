package com.finalproj.finalproject.data;

import java.util.Date;

public class MailBody {


    public String getSpecialGuestMailAddress(String eventName){

        String specialMail = "<html lang=\\\"en\\\">"
                + "<head>"
                + "<meta charset=\\\"UTF-8\\\">"
                + "<meta name=\\\"viewport\\\" content=\\\"width=device-width, initial-scale=1\\\">"
                + "<title>HeliumX</title>"
                + "<style>.logo-img{width: 7%;padding: 15px 15px;}@media (max-width: 768px) {.box{}.box-in{padding: 0 15px;}.logo-img{padding: 20px 15px 0 15px;width: 15%;}}</style>"
                + "</head>"
                + "<body style=\\\"padding: 0px; margin: 0px;\\\">"
                + "<div class=\\\"logo\\\"><img src=\\\"https://storage.googleapis.com/arimac/arimac_logo.png\\\" class=\\\"logo-img\\\"></div>"
                + "<div class=\\\"box\\\" style=\\\"width:100%;max-width:700px; margin: 0 auto;\\\">"
                + "<div class=\\\"box-in\\\"><p style=\\\"color: #e63c2d; font-size: 20px; font-weight: 700;\\\">Contacting Us.</p>"
                + "<p style=\\\"font-size: 16px;color: #000000;\\\">Hello Sir/Madam,</p>"
                + "<p style=\\\"font-size: 16px;color: #000000;\\\">You have been invited as a special guest for "+eventName+"!</p>"
                + "<P style=\\\"font-size: 16px;color: #000000;\\\">Please Click the below link to inform about your attendance.</P>"
                + "<a href=\"http://www.google.com\">Click To Confirm</a>"
                + "<P style=\\\"font-size: 16px;color: #000000;\\\">Thank you!!.</P>"
                + "<P style=\\\"font-size: 16px;color: #000000;\\\">Team&nbsp;HeliumX.</P>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        return specialMail;

    }

    @SuppressWarnings("Duplicates")
    public String getGeneralGuestMailAddress(String eventName){

        String specialMail = "<html lang=\\\"en\\\">"
                + "<head>"
                + "<meta charset=\\\"UTF-8\\\">"
                + "<meta name=\\\"viewport\\\" content=\\\"width=device-width, initial-scale=1\\\">"
                + "<title>HeliumX</title>"
                + "<style>.logo-img{width: 7%;padding: 15px 15px;}@media (max-width: 768px) {.box{}.box-in{padding: 0 15px;}.logo-img{padding: 20px 15px 0 15px;width: 15%;}}</style>"
                + "</head>"
                + "<body style=\\\"padding: 0px; margin: 0px;\\\">"
                + "<div class=\\\"logo\\\"><img src=\\\"https://storage.googleapis.com/arimac/arimac_logo.png\\\" class=\\\"logo-img\\\"></div>"
                + "<div class=\\\"box\\\" style=\\\"width:100%;max-width:700px; margin: 0 auto;\\\">"
                + "<div class=\\\"box-in\\\"><p style=\\\"color: #e63c2d; font-size: 20px; font-weight: 700;\\\">Contacting Us.</p>"
                + "<p style=\\\"font-size: 16px;color: #000000;\\\">Hello Sir/Madam,</p>"
                + "<p style=\\\"font-size: 16px;color: #000000;\\\">You have been invited as a guest for "+eventName+"!</p>"
                + "<P style=\\\"font-size: 16px;color: #000000;\\\">Please Click the below link to inform about your attendance.</P>"
                + "<a href=\"http://www.google.com\">Click To Confirm</a>"
                + "<P style=\\\"font-size: 16px;color: #000000;\\\">Thank you!!.</P>"
                + "<P style=\\\"font-size: 16px;color: #000000;\\\">Team&nbsp;HeliumX.</P>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        return specialMail;

    }

    @SuppressWarnings("Duplicates")
    public String getSubEventEamail(String eventName, String subEvent, String subEventDate, String subEventTime){

        String specialMail = "<html lang=\\\"en\\\">"
                + "<head>"
                + "<meta charset=\\\"UTF-8\\\">"
                + "<meta name=\\\"viewport\\\" content=\\\"width=device-width, initial-scale=1\\\">"
                + "<title>HeliumX</title>"
                + "<style>.logo-img{width: 7%;padding: 15px 15px;}@media (max-width: 768px) {.box{}.box-in{padding: 0 15px;}.logo-img{padding: 20px 15px 0 15px;width: 15%;}}</style>"
                + "</head>"
                + "<body style=\\\"padding: 0px; margin: 0px;\\\">"
                + "<div class=\\\"logo\\\"><img src=\\\"https://storage.googleapis.com/arimac/arimac_logo.png\\\" class=\\\"logo-img\\\"></div>"
                + "<div class=\\\"box\\\" style=\\\"width:100%;max-width:700px; margin: 0 auto;\\\">"
                + "<div class=\\\"box-in\\\"><p style=\\\"color: #e63c2d; font-size: 20px; font-weight: 700;\\\">Contacting Us.</p>"
                + "<p style=\\\"font-size: 16px;color: #000000;\\\">Hello Sir/Madam,</p>"
                + "<p style=\\\"font-size: 16px;color: #000000;\\\">You have been invited as a guest for "+subEvent+" of "+eventName+"!</p>"
                + "<p style=\\\"font-size: 16px;color: #000000;\\\">This will be held on"+subEventDate+" at "+subEventTime+"!</p>"
                + "<P style=\\\"font-size: 16px;color: #000000;\\\">Please Click the below link to inform about your attendance.</P>"
                + "<a href=\"http://www.google.com\">Click To Confirm</a>"
                + "<P style=\\\"font-size: 16px;color: #000000;\\\">Thank you!!.</P>"
                + "<P style=\\\"font-size: 16px;color: #000000;\\\">Team&nbsp;HeliumX.</P>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        return specialMail;

    }



}
