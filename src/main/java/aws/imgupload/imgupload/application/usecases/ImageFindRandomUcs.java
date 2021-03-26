package aws.imgupload.imgupload.application.usecases;

import aws.imgupload.imgupload.application.HasFlows;
import aws.imgupload.imgupload.service.MetadataService;
import aws.imgupload.imgupload.service.StorageService;
import org.springframework.util.Assert;

import java.io.IOException;
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
    public byte[] mainFlow() throws IOException {
        final var count = assertCount( metadataService.fetchImageCount() );
        final var random = new Random().nextLong();
        final var index = random % count;
        final var data = metadataService.fetchRecordByIndex(index);

        return storageService.findImageByName(data.getFileName()).get();
    }

    private long assertCount(long count) {
        if (count < 1) throw new IllegalArgumentException();
        else return count;
    }

    @Override
    public byte[] alternateFlows(Exception exception) {
        return null;
    }
}
