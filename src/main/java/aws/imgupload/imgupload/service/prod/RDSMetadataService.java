package aws.imgupload.imgupload.service.prod;

import aws.imgupload.imgupload.service.MetadataService;
import aws.imgupload.imgupload.service.data.ImageMetadata;
import aws.imgupload.imgupload.service.prod.awsdata.RegisterRecord;
import aws.imgupload.imgupload.service.prod.awsdata.RegisterRecordInitializer;

import java.util.NoSuchElementException;

public class RDSMetadataService implements MetadataService {
    private final RegisterRepository registerRepository;

    public RDSMetadataService(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @Override
    public void startRegister(final ImageMetadata metadata) {
        final var maybeRecord = registerRepository.findByFilename(metadata.getFileName());
        final var origRecord = maybeRecord.isPresent() ? maybeRecord.get() : new RegisterRecord();
        final var record = RegisterRecordInitializer.initialize(metadata, origRecord);

        registerRepository.save(record);
    }

    @Override
    public void commitRegister(ImageMetadata metadata) throws NoSuchElementException {
        final var record = registerRepository.findByFilename(metadata.getFileName()).get();
        record.setCommitted(true);
    }

    @Override
    public void rollbackRegister(ImageMetadata metadata) throws NoSuchElementException {
        final var record = registerRepository.findByFilename(metadata.getFileName()).get();
        registerRepository.deleteById(record.getId());
    }

    @Override
    public long fetchImageCount() {
        return registerRepository.count();
    }
}
