package aws.imgupload.imgupload.application.usecases;

import aws.imgupload.imgupload.application.HasFlows;
import aws.imgupload.imgupload.service.MetadataService;
import aws.imgupload.imgupload.service.StorageService;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Arrays;

public class ImageFindByNameUcs implements HasFlows<byte[]> {
    private final MetadataService metadataService;
    private final StorageService storageService;
    private final String name;

    public ImageFindByNameUcs(MetadataService metadataService, StorageService storageService, String name) {
        this.metadataService = metadataService;
        this.storageService = storageService;
        this.name = name;

        Assert.noNullElements(Arrays.asList(metadataService, storageService, name), "Null elements are not allowed!");
    }

    @Override
    public byte[] mainFlow() throws IOException {
        return storageService.findImageByName(name+".png").get();
    }

    @Override
    public byte[] alternateFlows(Exception exception) {
        return null;
    }
}
