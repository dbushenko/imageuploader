package aws.imgupload.imgupload.service;

import aws.imgupload.imgupload.service.data.ImageMetadata;

import java.util.Optional;

public interface StorageService {
    void store(ImageMetadata metadata, byte[] data);
    void delete(ImageMetadata metadata);
    Optional<byte[]> findImageByIndex(long index);
    Optional<byte[]> findImageByName(String name);
}
