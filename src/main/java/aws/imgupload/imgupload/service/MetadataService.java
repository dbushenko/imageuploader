package aws.imgupload.imgupload.service;

import aws.imgupload.imgupload.service.data.ImageMetadata;

public interface MetadataService {
    void startRegister(ImageMetadata metadata);
    void commitRegister(ImageMetadata metadata);
    void rollbackRegister(ImageMetadata metadata);
    long fetchImageCount();
    ImageMetadata fetchRecordByIndex(long index);
}
