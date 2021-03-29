package aws.imgupload.imgupload.service.aws;

import aws.imgupload.imgupload.service.SubscriptionService;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;

import javax.annotation.PostConstruct;

public class SnsSubscriptionService implements SubscriptionService {
    private final String topicArn;
    private final String region;
    private final String profile;

    private SnsClient snsClient;

    public SnsSubscriptionService(String topicArn, String region, String profile) {
        this.topicArn = topicArn;
        this.region = region;
        this.profile = profile;
    }

    @PostConstruct
    public void initialize() {
        snsClient =
            SnsClient.builder()
                    .credentialsProvider( ProfileCredentialsProvider
                            .builder()
                            .profileName(profile)
                            .build() )
                     .region(Region.of(region))
                     .build();
    }

    @Override
    public void subscribe(final String email) {
        final SubscribeRequest request =
                    SubscribeRequest.builder()
                                    .protocol("email")
                                    .endpoint(email)
                                    .returnSubscriptionArn(true)
                                    .topicArn(topicArn)
                                    .build();

        snsClient.subscribe(request);
    }

    @Override
    public void sendMessage(final String messageTopic, final String messageBody) {
        final PublishRequest request =
                PublishRequest.builder()
                              .message(messageBody)
                              .subject(messageTopic)
                              .topicArn(topicArn)
                              .build();

        final PublishResponse result = snsClient.publish(request);
    }
}
