package aws.imgupload.imgupload.service.aws;

import aws.imgupload.imgupload.service.StorageService;
import aws.imgupload.imgupload.service.data.ImageMetadata;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Optional;

public class S3StorageService implements StorageService {
    private final String bucket;
    private final String profile;
    private final String region;

    private S3Client s3Client;

    public S3StorageService(String bucket, String profile, String region) {
        this.bucket = bucket;
        this.profile = profile;
        this.region = region;
    }

    @PostConstruct
    public void init() throws IOException {
        s3Client = S3Client
                .builder()
                .credentialsProvider( ProfileCredentialsProvider
                                        .builder()
                                        .profileName(profile)
                                        .build() )
                .region(Region.of(region)).build();
    }

    @Override
    public void store(ImageMetadata metadata, byte[] data) {
        final PutObjectRequest objectRequest =
                PutObjectRequest.builder()
                                .bucket(bucket)
                                .key(metadata.getFileName())
                                .build();

        s3Client.putObject(objectRequest, RequestBody.fromBytes(data));
    }

    @Override
    public void delete(ImageMetadata metadata) {
        final DeleteObjectRequest deleteObjectRequest =
                DeleteObjectRequest.builder()
                                   .bucket(bucket)
                                   .key(metadata.getFileName())
                                   .build();

        s3Client.deleteObject(deleteObjectRequest);
    }

    @Override
    public Optional<byte[]> findImageByName(String name) throws IOException {
        final GetObjectRequest getObjectRequest =
                GetObjectRequest.builder()
                                .bucket(bucket)
                                .key(name)
                                .build();

        final var response = s3Client.getObject(getObjectRequest);

        return Optional.ofNullable(response.readAllBytes());
    }

}
