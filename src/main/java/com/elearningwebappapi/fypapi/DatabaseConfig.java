package com.elearningwebappapi.fypapi;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.regions.Regions;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        return new HikariDataSource(config);
    }

    @Value("${aws.keyId}")
    private String awsKeyId;

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.region}")
    private String region;

    @Bean
    public AmazonS3 awsS3Client() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsKeyId, accessKey);
        return AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
    }
}