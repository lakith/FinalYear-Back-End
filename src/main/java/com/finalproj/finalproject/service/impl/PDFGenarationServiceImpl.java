package com.finalproj.finalproject.service.impl;

import com.finalproj.finalproject.util.PdfGenaratorUtil;
import org.apache.pdfbox.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class PDFGenarationServiceImpl {

    @Autowired
    PdfGenaratorUtil pdfGenaratorUtil;

    public byte[] createPdf() throws Exception {
        Map<String,String> data = new HashMap<>();
        data.put("name","James");
        File fileOutputStream =pdfGenaratorUtil.createPdf("greeting",data);
        byte[] fileContent = Files.readAllBytes(fileOutputStream.toPath());
        return fileContent;
    }

}
