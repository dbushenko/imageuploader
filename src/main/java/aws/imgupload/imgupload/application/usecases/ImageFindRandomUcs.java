package aws.imgupload.imgupload.application.usecases;

import aws.imgupload.imgupload.application.HasFlows;
import aws.imgupload.imgupload.service.MetadataService;
import aws.imgupload.imgupload.service.StorageService;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Random;

public class ImageFindRandomUcs implements HasFlows<byte[]> {
    private final MetadataService metadataService;
    private final StorageService storageService;

    public ImageFindRandomUcs(MetadataService metadataService, StorageService storageService) {
        this.metadataService = metadataService;
        this.storageService = storageService;

        Assert.noNullElements(Arrays.asList(metadataService, storageService), "Null elements are not allowed!");
    }

    @Override
    public byte[] mainFlow() {
        final var count = assertCount( metadataService.fetchImageCount() );
        final var random = new Random().nextInt();
        final var index = random % count;

        return storageService.findImageByIndex(index).get();
    }

    private int assertCount(int count) {
        if (count < 1) throw new IllegalArgumentException();
        else return count;
    }

    @Override
    public byte[] alternateFlows(Exception exception) {
        return null;
    }
}
