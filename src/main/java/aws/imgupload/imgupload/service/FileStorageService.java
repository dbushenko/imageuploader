package aws.imgupload.imgupload.service;

import aws.imgupload.imgupload.service.data.ImageMetadata;

import java.util.Optional;

public class FileStorageService implements StorageService{

    @Override
    public void store(ImageMetadata metadata, byte[] data) {
        System.out.println("[FileStorageService] Storing data:");
        System.out.println(metadata);
    }

    @Override
    public void delete(ImageMetadata metadata) {
        System.out.println("[FileStorageService] Deleting data:");
        System.out.println(metadata);
    }

    @Override
    public Optional<byte[]> findImageByIndex(int index) {
        return Optional.empty();
    }

    @Override
    public Optional<byte[]> findImageByName(String name) {
        return Optional.empty();
    }
}
