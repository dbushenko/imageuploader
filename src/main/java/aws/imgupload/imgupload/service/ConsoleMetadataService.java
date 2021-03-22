package aws.imgupload.imgupload.service;

import aws.imgupload.imgupload.service.data.ImageMetadata;

public class ConsoleMetadataService implements MetadataService{
    @Override
    public void startRegister(ImageMetadata metadata) {
        System.out.println("[ConsoleMetadataService] Registering metadata:");
        System.out.println(metadata);
    }

    @Override
    public void commitRegister(ImageMetadata metadata) {
        System.out.println("[ConsoleMetadataService] Committing metadata:");
        System.out.println(metadata);
    }

    @Override
    public void rollbackRegister(ImageMetadata metadata) {
        System.out.println("[ConsoleMetadataService] Rolling back metadata:");
        System.out.println(metadata);
    }

    @Override
    public int fetchImageCount() {
        return 0;
    }
}
