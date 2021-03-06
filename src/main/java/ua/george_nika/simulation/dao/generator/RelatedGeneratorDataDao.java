/**
 * Work with related generator data
 */

package ua.george_nika.simulation.dao.generator;

import ua.george_nika.simulation.model.generator.RelatedGeneratorData;

import java.util.List;

public interface RelatedGeneratorDataDao {

    List<RelatedGeneratorData> getAllRelatedGeneratorDataById(int idExperiment);

    void setDelayToRelatedGeneratorData(int idGenerator, int relatedPosition, long delay);

    void upPositionToRelatedGeneratorData(int idGenerator, int position);

    void downPositionToRelatedGeneratorData(int idGenerator, int position);

    void deleteRelatedGeneratorData(int idGenerator, int position);

    void createRelatedGeneratorData(int idGenerator, int idRelatedGenerator);

    void updateRelatedGeneratorData(int idGenerator, int idRelatedGenerator, int position);
}
