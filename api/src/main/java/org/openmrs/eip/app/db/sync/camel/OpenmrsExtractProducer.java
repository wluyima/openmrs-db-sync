package org.openmrs.eip.app.db.sync.camel;

import org.apache.camel.Exchange;
import org.openmrs.eip.app.db.sync.camel.fetchmodels.FetchModelsRuleEngine;
import org.openmrs.eip.app.db.sync.model.BaseModel;
import org.openmrs.eip.app.db.sync.model.SyncModel;
import org.openmrs.eip.app.db.sync.utils.JsonUtils;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OpenmrsExtractProducer extends AbstractOpenmrsProducer {

    public OpenmrsExtractProducer(final OpenmrsEndpoint endpoint,
                                  final ApplicationContext applicationContext,
                                  final ProducerParams params) {
        super(endpoint, applicationContext, params);
    }

    @Override
    public void process(final Exchange exchange) {
        FetchModelsRuleEngine ruleEngine = (FetchModelsRuleEngine) applicationContext.getBean("fetchModelsRuleEngine");

        List<BaseModel> models = ruleEngine.process(params);

        List<SyncModel> modelsArray = models.stream()
                .filter(Objects::nonNull)
                .map(this::buildSyncModel)
                .collect(Collectors.toList());

        exchange.getIn().setBody(JsonUtils.marshall(modelsArray));
    }

    private SyncModel buildSyncModel(final BaseModel model) {
        return SyncModel.builder()
                .tableToSyncModelClass(model.getClass())
                .model(model)
                .build();
    }
}
