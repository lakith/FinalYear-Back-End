package com.finalproj.finalproject.controller;

import com.finalproj.finalproject.service.ReportService;
import com.finalproj.finalproject.service.impl.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("report-view")
@CrossOrigin
public class ReportViewController {

     @Autowired
     ReportService reportService;

    @GetMapping(
            value = "report",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public byte[] genarateReport(@RequestParam("eventId") int eventId) throws Exception {
        return reportService.genarateReports(eventId);
    }

}

