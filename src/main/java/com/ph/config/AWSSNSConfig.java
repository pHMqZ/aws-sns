package com.ph.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

@Configuration
public class AWSSNSConfig {

	public static final String SECRET_KEY = ""; // senha_credencial_IAM
	public static final String ACCESS_KEY = ""; // ID_credencial_IAM

	@Primary
	@Bean
	public AmazonSNSClient getSnsClient() {

		return (AmazonSNSClient) AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_1)
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY)))
				.build();

	}

}
