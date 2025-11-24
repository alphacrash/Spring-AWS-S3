package com.alphacrash.aws.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {
    private final Environment env;

    public S3Config(Environment env) {
        this.env = env;
    }

    @Bean
    public S3Client s3Client() {
        String accessKeyId = env.getProperty("aws.access_key");
        String secretAccessKey = env.getProperty("aws.secret_key");
        AwsBasicCredentials awsCredentials = AwsBasicCredentials
                .builder()
                .accessKeyId(accessKeyId)
                .secretAccessKey(secretAccessKey)
                .build();
        return S3Client.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();
    }
}
