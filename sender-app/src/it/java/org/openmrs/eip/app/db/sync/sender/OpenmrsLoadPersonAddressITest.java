package org.openmrs.eip.app.db.sync.sender;

import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultExchange;
import org.junit.After;
import org.junit.Test;
import org.openmrs.eip.app.db.sync.entity.PersonAddress;
import org.openmrs.eip.app.db.sync.entity.light.UserLight;
import org.openmrs.eip.app.db.sync.model.PersonAddressModel;
import org.openmrs.eip.app.db.sync.repository.SyncEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class OpenmrsLoadPersonAddressITest extends OpenmrsLoadEndpointITest {

    @Autowired
    private SyncEntityRepository<PersonAddress> repository;

    @Test
    public void load() {
        // Given
        Exchange exchange = new DefaultExchange(camelContext);
        exchange.getIn().setBody(getPersonJson());

        // When
        template.send(exchange);

        // Then
        assertEquals(2, repository.findAll().size());
    }

    // TEAR-DOWN
    @After
    public void after() {
        PersonAddress p = repository.findByUuid("818b4ee6-8d68-4849-975d-80ab98016677");
        repository.delete(p);
    }

    private String getPersonJson() {
        return "{" +
                "\"tableToSyncModelClass\":\"" + PersonAddressModel.class.getName() + "\"," +
                "\"model\":{" +
                "\"uuid\":\"818b4ee6-8d68-4849-975d-80ab98016677\"," +
                "\"creatorUuid\":\"" + UserLight.class.getName() + "(1)\"," +
                "\"dateCreated\":\"2019-05-28T13:42:31+00:00\"," +
                "\"changedByUuid\":null," +
                "\"dateChanged\":null," +
                "\"voided\":false," +
                "\"voidedByUuid\":null," +
                "\"dateVoided\":null," +
                "\"voidReason\":null," +
                "\"address\":{" +
                "\"address1\":\"chemin perdu\"" +
                "}" +
                "}" +
                "}";
    }
}
