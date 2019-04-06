package org.sofl.soptorshi.repository;

import org.sofl.soptorshi.model.TrainingInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingInformationRepository extends JpaRepository<TrainingInformation, Long> {
}
