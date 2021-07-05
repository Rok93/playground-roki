package com.example.playgroundroki.controller;

import com.example.playgroundroki.domain.ExcelGenerator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExcelApiController {

    private final ExcelGenerator excelGenerator;

    @GetMapping(value = "/api/excel", produces = {"application/vnd.ms-excel"})
    public ResponseEntity<InputStreamResource> createExcel(@RequestParam("name") String sheetName) throws IOException {
        String[] headerTitles = {"이름", "이메일", "전화번호", "성별", "생년월일", "지원 일시", "부정 행위자"};
        ByteArrayInputStream byteArrayInputStream = excelGenerator
            .generateBy(headerTitles, Collections.emptyList());

        System.out.println("head");
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''\"" + sheetName+ "\".xls")
            .body(new InputStreamResource(byteArrayInputStream));
    }

}
