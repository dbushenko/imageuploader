package aws.imgupload.imgupload.application;

import aws.imgupload.imgupload.application.usecases.ImageFindByNameUcs;
import aws.imgupload.imgupload.application.usecases.ImageFindRandomUcs;
import aws.imgupload.imgupload.application.usecases.ImageUploadUcs;
import aws.imgupload.imgupload.service.MetadataService;
import aws.imgupload.imgupload.service.StorageService;
import aws.imgupload.imgupload.service.data.ImageMetadata;
import org.springframework.stereotype.Component;

@Component
public class ImageApp {
    private final MetadataService metadataService;
    private final StorageService storageService;

    public ImageApp(MetadataService metadataService, StorageService storageService) {
        this.metadataService = metadataService;
        this.storageService = storageService;
    }

    public void uploadImage(String name, final byte[] image) {
        new ImageUploadUcs(metadataService, storageService, new ImageMetadata(name), image)
                .run();
    }

    public byte[] findRandomImage() {
        return new ImageFindRandomUcs(metadataService, storageService)
                .run();
    }

    public byte[] findImageByName(final String name) {
        return new ImageFindByNameUcs(metadataService, storageService, name)
                .run();
    }
}
