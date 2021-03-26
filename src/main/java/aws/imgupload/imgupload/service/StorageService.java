package aws.imgupload.imgupload.service;

import aws.imgupload.imgupload.service.data.ImageMetadata;

import java.io.IOException;
import java.util.Optional;

public interface StorageService {
    void store(ImageMetadata metadata, byte[] data);
    void delete(ImageMetadata metadata);
    Optional<byte[]> findImageByName(String name) throws IOException;
}
