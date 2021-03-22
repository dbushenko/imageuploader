package aws.imgupload.imgupload.service.data;

public class ImageMetadata {
    private final String fileName;

    public ImageMetadata(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return "ImageMetadata{" +
                "fileName='" + fileName + '\'' +
                '}';
    }
}
