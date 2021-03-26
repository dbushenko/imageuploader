package aws.imgupload.imgupload;

import aws.imgupload.imgupload.service.MetadataService;
import aws.imgupload.imgupload.service.StorageService;
import aws.imgupload.imgupload.service.prod.RDSMetadataService;
import aws.imgupload.imgupload.service.prod.RegisterRepository;
import aws.imgupload.imgupload.service.prod.S3StorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("aws.imgupload.imgupload.service.prod")
@Profile("prod")
public class ProdConfiguration {
    private final RegisterRepository registerRepository;

    public ProdConfiguration(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @Bean
    public MetadataService metadataService() { return new RDSMetadataService(registerRepository); }

    @Bean
    public StorageService storageService() { return new S3StorageService(); }

}
