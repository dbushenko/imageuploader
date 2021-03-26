package aws.imgupload.imgupload;

import aws.imgupload.imgupload.service.localdev.ConsoleMetadataService;
import aws.imgupload.imgupload.service.localdev.FileStorageService;
import aws.imgupload.imgupload.service.MetadataService;
import aws.imgupload.imgupload.service.StorageService;
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
        return new FileStorageService();
    }
}
