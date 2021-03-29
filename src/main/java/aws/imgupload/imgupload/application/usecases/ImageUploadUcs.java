package aws.imgupload.imgupload.application.usecases;

import aws.imgupload.imgupload.application.HasFlows;
import static aws.imgupload.imgupload.application.HasFlows.Unit;
import aws.imgupload.imgupload.service.MetadataService;
import aws.imgupload.imgupload.service.StorageService;
import aws.imgupload.imgupload.service.SubscriptionService;
import aws.imgupload.imgupload.service.data.ImageMetadata;
import org.springframework.util.Assert;

import java.util.Arrays;

public class ImageUploadUcs implements HasFlows<Unit> {
    private final MetadataService metadataService;
    private final StorageService storageService;
    private final SubscriptionService subscriptionService;

    private final ImageMetadata metadata;
    private final byte[] image;

    public ImageUploadUcs(MetadataService metadataService, StorageService storageService, SubscriptionService subscriptionService, ImageMetadata metadata, byte[] image) {
        this.metadataService = metadataService;
        this.storageService = storageService;
        this.subscriptionService = subscriptionService;
        this.metadata = metadata;
        this.image = image;

        Assert.noNullElements(Arrays.asList(metadataService, storageService, image, metadata), "Null elements are not allowed!");
    }

    @Override
    public Unit mainFlow() {
        metadataService.startRegister(metadata);
        storageService.store(metadata, image);
        metadataService.commitRegister(metadata);
        subscriptionService.sendMessage("Image uploaded", "Image " + metadata.getFileName() + " was uploaded!");

        return Unit.instance;
    }

    @Override
    public Unit alternateFlows(Exception exception) {
        try {
            storageService.delete(metadata);
        } finally {
            metadataService.rollbackRegister(metadata);
        }

        return Unit.instance;
    }
}
