package com.finalproj.finalproject.service.impl;

import com.finalproj.finalproject.model.FormData;
import com.finalproj.finalproject.repository.FormDataRepository;
import com.finalproj.finalproject.service.ReportService;
import com.finalproj.finalproject.util.PdfGenaratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    FormDataRepository formDataRepository;

    @Autowired
    PdfGenaratorUtil pdfGenaratorUtil;

    @Override
    public byte[] genarateReports(int eventId) throws Exception {

        List<FormData> formDataList = formDataRepository.getEventUserData(eventId);
        Map<String, List<FormData>> elements = new HashMap<>();
        elements.put("elements",formDataList);
        File fileOutputStream =pdfGenaratorUtil.createPdf("report",elements);
        byte[] fileContent = Files.readAllBytes(fileOutputStream.toPath());
        return fileContent;

    }
}
