package org.openmrs.eip.app.db.sync.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openmrs.eip.app.db.sync.entity.light.ConceptAttributeTypeLight;
import org.openmrs.eip.app.db.sync.entity.light.ConceptLight;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "concept_attribute")
@AttributeOverride(name = "id", column = @Column(name = "concept_attribute_id"))
public class ConceptAttribute extends Attribute<ConceptAttributeTypeLight> {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "concept_id")
    private ConceptLight referencedEntity;
}
