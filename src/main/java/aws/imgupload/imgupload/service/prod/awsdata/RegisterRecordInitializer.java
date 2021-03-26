package aws.imgupload.imgupload.service.prod.awsdata;

import aws.imgupload.imgupload.service.data.ImageMetadata;

public class RegisterRecordInitializer {
    public static RegisterRecord initialize(ImageMetadata data, RegisterRecord origRecord) {
        origRecord.setFilename(data.getFileName());
        origRecord.setS3(data.getFileName());

        return origRecord;
    }
}
