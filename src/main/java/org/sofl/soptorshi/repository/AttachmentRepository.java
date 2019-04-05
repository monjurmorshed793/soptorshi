package org.sofl.soptorshi.repository;

import org.sofl.soptorshi.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
