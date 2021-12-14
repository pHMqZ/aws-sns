package com.ph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;

@SpringBootApplication
@RestController
public class AwsSnSApplication {

	// dentro controller SNS
	@Autowired
	private AmazonSNSClient snsClient;

	String TOPIC_ARN = ""; // add arn do seu topico

	@GetMapping("/addSubscription/{email}")
	public String addSubscription(@PathVariable String email) {
		SubscribeRequest request = new SubscribeRequest(TOPIC_ARN, "email", email);
		snsClient.subscribe(request);
		return "Subscription request está pedente. Para confirmar, verifique seu e-mail:" + email;
	}

	@GetMapping("/sendNotification")
	public String publishMessageToTopic() {
		PublishRequest publishRequest = new PublishRequest(TOPIC_ARN, buildEmailBody(),
				"Notificatio: Network connectivity issue");
		snsClient.publish(publishRequest);
		return "Notificação enviada com sucesso!";
	}

	private String buildEmailBody() {
		return "Querido cliente,\n" +
				"\n" +
				"\n" +
				"Verificamos que você esta com debito em aberto, favor regulariza para evitar bloqueio de conta";
	}

	public static void main(String[] args) {
		SpringApplication.run(AwsSnSApplication.class, args);
	}

}
