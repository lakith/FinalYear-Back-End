package com.finalproj.finalproject.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalproj.finalproject.service.impl.PDFGenarationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    PDFGenarationServiceImpl pdfGenarationService;

    @PostMapping
    public String testController(@RequestParam String testString,@RequestParam int eventId,@RequestParam String email) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(testString);
        System.out.println(testString);
        return "success";
    }

    @GetMapping(
            value = "test",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public byte[] pdfTest() throws Exception {
        return pdfGenarationService.createPdf();
    }
}
