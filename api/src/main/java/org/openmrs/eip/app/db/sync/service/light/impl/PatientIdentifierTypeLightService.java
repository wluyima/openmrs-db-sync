package org.openmrs.eip.app.db.sync.service.light.impl;

import org.openmrs.eip.app.db.sync.entity.light.PatientIdentifierTypeLight;
import org.openmrs.eip.app.db.sync.repository.OpenmrsRepository;
import org.openmrs.eip.app.db.sync.service.light.AbstractLightService;
import org.springframework.stereotype.Service;

@Service
public class PatientIdentifierTypeLightService extends AbstractLightService<PatientIdentifierTypeLight> {

    public PatientIdentifierTypeLightService(final OpenmrsRepository<PatientIdentifierTypeLight> repository) {
        super(repository);
    }

    @Override
    protected PatientIdentifierTypeLight createPlaceholderEntity(final String uuid) {
        PatientIdentifierTypeLight patientIdentifierType = new PatientIdentifierTypeLight();
        patientIdentifierType.setDateCreated(DEFAULT_DATE);
        patientIdentifierType.setCreator(DEFAULT_USER_ID);
        patientIdentifierType.setName(DEFAULT_STRING);
        return patientIdentifierType;
    }
}
