package org.openmrs.eip.app.db.sync.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openmrs.eip.app.db.sync.entity.light.ProviderAttributeTypeLight;
import org.openmrs.eip.app.db.sync.entity.light.ProviderLight;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "provider_attribute")
@AttributeOverride(name = "id", column = @Column(name = "provider_attribute_id"))
public class ProviderAttribute extends Attribute<ProviderAttributeTypeLight> {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private ProviderLight referencedEntity;
}
