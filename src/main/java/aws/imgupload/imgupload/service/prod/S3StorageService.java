package aws.imgupload.imgupload.service.prod;

import aws.imgupload.imgupload.service.StorageService;
import aws.imgupload.imgupload.service.data.ImageMetadata;

import java.util.Optional;

public class S3StorageService implements StorageService {
    @Override
    public void store(ImageMetadata metadata, byte[] data) {

    }

    @Override
    public void delete(ImageMetadata metadata) {

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
