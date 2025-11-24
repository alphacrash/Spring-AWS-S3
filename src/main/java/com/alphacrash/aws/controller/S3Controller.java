package com.alphacrash.aws.controller;

import com.alphacrash.aws.service.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")
public class S3Controller {
    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping
    public ResponseEntity<String> getPatients() {
        byte[] contentApiDistributionSwaggerYaml = s3Service.getFile("content-distribution-x", "ContentAPIDistributionSwagger.yaml");
        String contentApiDistributionSwaggerYamlString = new String(contentApiDistributionSwaggerYaml, StandardCharsets.UTF_8);
        return ResponseEntity.ok(contentApiDistributionSwaggerYamlString);
    }
}
