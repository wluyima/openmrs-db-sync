package org.openmrs.eip.app.db.sync.service.light.impl;

import org.openmrs.eip.app.db.sync.entity.light.PatientLight;
import org.openmrs.eip.app.db.sync.entity.light.VisitLight;
import org.openmrs.eip.app.db.sync.entity.light.VisitTypeLight;
import org.openmrs.eip.app.db.sync.repository.OpenmrsRepository;
import org.openmrs.eip.app.db.sync.service.light.LightService;
import org.openmrs.eip.app.db.sync.service.light.AbstractLightService;
import org.springframework.stereotype.Service;

@Service
public class VisitLightService extends AbstractLightService<VisitLight> {

    private LightService<PatientLight> patientService;

    private LightService<VisitTypeLight> visitTypeService;

    public VisitLightService(final OpenmrsRepository<VisitLight> repository,
                             final LightService<PatientLight> patientService,
                             final LightService<VisitTypeLight> visitTypeService) {
        super(repository);
        this.patientService = patientService;
        this.visitTypeService = visitTypeService;
    }

    @Override
    protected VisitLight createPlaceholderEntity(final String uuid) {
        VisitLight visit = new VisitLight();
        visit.setPatient(patientService.getOrInitPlaceholderEntity());
        visit.setDateStarted(DEFAULT_DATE);
        visit.setVisitType(visitTypeService.getOrInitPlaceholderEntity());
        visit.setCreator(DEFAULT_USER_ID);
        visit.setDateCreated(DEFAULT_DATE);
        return visit;
    }
}
