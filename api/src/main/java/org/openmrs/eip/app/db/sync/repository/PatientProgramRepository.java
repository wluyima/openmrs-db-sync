package org.openmrs.eip.app.db.sync.repository;

import org.openmrs.eip.app.db.sync.entity.PatientProgram;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientProgramRepository extends SyncEntityRepository<PatientProgram> {

    @Override
    @Query("select p from PatientProgram p " +
            "where p.dateChanged is null and p.dateCreated >= :lastSyncDate " +
            "or p.dateChanged >= :lastSyncDate")
    List<PatientProgram> findModelsChangedAfterDate(@Param("lastSyncDate") LocalDateTime lastSyncDate);
}
