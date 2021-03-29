package aws.imgupload.imgupload;

import aws.imgupload.imgupload.service.SubscriptionService;
import aws.imgupload.imgupload.service.console.ConsoleMetadataService;
import aws.imgupload.imgupload.service.console.ConsoleStorageService;
import aws.imgupload.imgupload.service.MetadataService;
import aws.imgupload.imgupload.service.StorageService;
import aws.imgupload.imgupload.service.console.ConsoleSubscriptionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("localDev")
public class LocalDevConfiguration {
    @Bean
    public MetadataService metadataService() {
        return new ConsoleMetadataService();
    }

    @Bean
    public StorageService storageService() {
        return new ConsoleStorageService();
    }

    @Bean
    public SubscriptionService subscriptionService() { return new ConsoleSubscriptionService(); }
}
