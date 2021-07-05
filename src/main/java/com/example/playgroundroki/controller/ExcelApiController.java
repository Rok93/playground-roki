package com.example.playgroundroki.controller;

import com.example.playgroundroki.domain.ExcelGenerator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExcelApiController {

    private final ExcelGenerator excelGenerator;

    @GetMapping(value = "/api/excel")
    public ResponseEntity<InputStreamResource> createExcel(@RequestParam("name") String sheetName)
        throws IOException {
        String[] headerTitles = {"이름", "이메일", "전화번호", "성별", "생년월일", "지원 일시", "부정 행위자"};
        ByteArrayInputStream byteArrayInputStream = excelGenerator
            .generateBy(headerTitles, Collections.emptyList());

        HttpHeaders headers = new HttpHeaders();
        ContentDisposition contentDisposition = ContentDisposition.builder("attatchment")
            .filename(sheetName + ".xlsx")
            .build();
        headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
        headers.setContentDisposition(contentDisposition);

        return ResponseEntity.ok()
            .headers(headers)
            .body(new InputStreamResource(byteArrayInputStream));
    }

}
