package aws.imgupload.imgupload.service.prod;

import aws.imgupload.imgupload.service.prod.awsdata.RegisterRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterRepository extends JpaRepository<RegisterRecord, Long> {
    Optional<RegisterRecord> findByFilename(String filename);
}
