package aws.imgupload.imgupload.service.localdev;

import aws.imgupload.imgupload.service.MetadataService;
import aws.imgupload.imgupload.service.data.ImageMetadata;

public class ConsoleMetadataService implements MetadataService {
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
    public long fetchImageCount() {
        return 0;
    }

    @Override
    public ImageMetadata fetchRecordByIndex(long index) {
        return new ImageMetadata("super-image");
    }
}
