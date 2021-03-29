package aws.imgupload.imgupload;

import aws.imgupload.imgupload.service.MetadataService;
import aws.imgupload.imgupload.service.StorageService;
import aws.imgupload.imgupload.service.SubscriptionService;
import aws.imgupload.imgupload.service.aws.RDSMetadataService;
import aws.imgupload.imgupload.service.aws.RegisterRepository;
import aws.imgupload.imgupload.service.aws.S3StorageService;
import aws.imgupload.imgupload.service.aws.SnsSubscriptionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("aws.imgupload.imgupload.service.aws")
@Profile("prod")
public class ProdConfiguration {
    private final RegisterRepository registerRepository;
    private final String bucket;
    private final String profile;
    private final String region;
    private final String topicArn;

    public ProdConfiguration(@Value("${s3.bucket}") String bucket, @Value("${s3.profile}") String profile, @Value("${s3.region}") String region, RegisterRepository registerRepository, @Value("${sns.topicArn}") String topicArn) {
        this.registerRepository = registerRepository;
        this.bucket = bucket;
        this.profile = profile;
        this.region = region;
        this.topicArn = topicArn;
    }

    @Bean
    public MetadataService metadataService() { return new RDSMetadataService(registerRepository); }

    @Bean
    public StorageService storageService() { return new S3StorageService(bucket, profile, region); }

    @Bean
    public SubscriptionService subscriptionService() { return new SnsSubscriptionService(topicArn, region); }

}
