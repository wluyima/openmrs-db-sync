package org.openmrs.eip.app.db.sync.service.light.impl;

import org.openmrs.eip.app.db.sync.entity.light.EncounterLight;
import org.openmrs.eip.app.db.sync.entity.light.EncounterTypeLight;
import org.openmrs.eip.app.db.sync.repository.OpenmrsRepository;
import org.openmrs.eip.app.db.sync.entity.light.PatientLight;
import org.openmrs.eip.app.db.sync.service.light.AbstractLightService;
import org.openmrs.eip.app.db.sync.service.light.LightService;
import org.springframework.stereotype.Service;

@Service
public class EncounterLightService extends AbstractLightService<EncounterLight> {

    private LightService<PatientLight> patientService;

    private LightService<EncounterTypeLight> encounterTypeService;

    public EncounterLightService(final OpenmrsRepository<EncounterLight> repository,
                                 final LightService<PatientLight> patientService,
                                 final LightService<EncounterTypeLight> encounterTypeService) {
        super(repository);
        this.patientService = patientService;
        this.encounterTypeService = encounterTypeService;
    }

    @Override
    protected EncounterLight createPlaceholderEntity(final String uuid) {
        EncounterLight encounter = new EncounterLight();
        encounter.setDateCreated(DEFAULT_DATE);
        encounter.setCreator(DEFAULT_USER_ID);
        encounter.setEncounterType(encounterTypeService.getOrInitPlaceholderEntity());
        encounter.setEncounterDatetime(DEFAULT_DATE);
        encounter.setPatient(patientService.getOrInitPlaceholderEntity());
        return encounter;
    }
}
