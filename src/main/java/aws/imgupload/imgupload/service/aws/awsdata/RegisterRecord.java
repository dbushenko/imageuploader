package aws.imgupload.imgupload.service.aws.awsdata;

import javax.persistence.*;

@Entity
@Table(name = "register")
public class RegisterRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String filename;
    private String s3;
    private Boolean committed = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    public Boolean getCommitted() {
        return committed;
    }

    public void setCommitted(Boolean committed) {
        this.committed = committed;
    }

    @Override
    public String toString() {
        return "RegisterRecord{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", s3='" + s3 + '\'' +
                ", committed=" + committed +
                '}';
    }
}
