package aws.imgupload.imgupload.service.aws;

import aws.imgupload.imgupload.service.aws.awsdata.RegisterRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterRepository extends JpaRepository<RegisterRecord, Long> {
    Optional<RegisterRecord> findByFilename(String filename);
}
