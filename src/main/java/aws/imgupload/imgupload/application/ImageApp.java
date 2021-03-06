package aws.imgupload.imgupload.application;

import aws.imgupload.imgupload.application.usecases.ImageFindByNameUcs;
import aws.imgupload.imgupload.application.usecases.ImageFindRandomUcs;
import aws.imgupload.imgupload.application.usecases.ImageUploadUcs;
import aws.imgupload.imgupload.service.MetadataService;
import aws.imgupload.imgupload.service.StorageService;
import aws.imgupload.imgupload.service.SubscriptionService;
import aws.imgupload.imgupload.service.data.ImageMetadata;
import org.springframework.stereotype.Component;

@Component
public class ImageApp {
    private final MetadataService metadataService;
    private final StorageService storageService;
    private final SubscriptionService subscriptionService;

    public ImageApp(MetadataService metadataService, StorageService storageService, SubscriptionService subscriptionService) {
        this.metadataService = metadataService;
        this.storageService = storageService;
        this.subscriptionService = subscriptionService;
    }

    public void uploadImage(String originalFileName, final byte[] image) {
        new ImageUploadUcs(metadataService, storageService, subscriptionService, new ImageMetadata(prepare(originalFileName)), image)
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

    ////
    private String prepare(final String originalFileName) {
        final var splitted = originalFileName.split("[\\/]]");
        return splitted[splitted.length-1];
    }
}
