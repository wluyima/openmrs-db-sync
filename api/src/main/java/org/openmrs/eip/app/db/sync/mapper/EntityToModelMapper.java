package org.openmrs.eip.app.db.sync.mapper;

import lombok.extern.slf4j.Slf4j;
import org.openmrs.eip.app.db.sync.mapper.operations.Context;
import org.openmrs.eip.app.db.sync.model.BaseModel;
import org.openmrs.eip.app.db.sync.entity.BaseEntity;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

@Slf4j
@Component
public class EntityToModelMapper<E extends BaseEntity, M extends BaseModel> implements Function<E, M> {

    private Function<E, Context<E, M>> instantiateModel;

    private UnaryOperator<Context<E, M>> copyStandardFields;

    private BiConsumer<Context<E, M>, String> extractUuid;

    private BiFunction<Context<E, M>, BiConsumer<Context<E, M>, String>, M> forEachLinkedEntity;

    public EntityToModelMapper(final Function<E, Context<E, M>> instantiateModel,
                               final UnaryOperator<Context<E, M>> copyStandardFields,
                               final BiConsumer<Context<E, M>, String> extractUuid,
                               final BiFunction<Context<E, M>, BiConsumer<Context<E, M>, String>, M> forEachLinkedEntity) {
        this.instantiateModel = instantiateModel;
        this.copyStandardFields = copyStandardFields;
        this.extractUuid = extractUuid;
        this.forEachLinkedEntity = forEachLinkedEntity;
    }

    @Override
    public M apply(final E entity) {

        return instantiateModel
                .andThen(copyStandardFields)
                .andThen(forEachLinkedEntity(extractUuid))
                .apply(entity);
    }

    private Function<Context<E, M>, M> forEachLinkedEntity(final BiConsumer<Context<E, M>, String> extractUuid) {
        return context -> forEachLinkedEntity.apply(context, extractUuid);
    }
}
