/**
 * Here defined major fields in database
 */

package ua.george_nika.simulation.dao;

public interface DaoConst {

    String EXP_TYPE_IN_EXP_MAIN_TABLE = "experiment_type";
    String EXP_TYPE_IN_EXP_HISTORY_MAIN_TABLE = "experiment_type";
    String FIELD_FOR_ID_EXPERIMENT_FILTER = "id_experiment";
    String FIELD_FOR_ID_EXPERIMENT_HISTORY_FILTER = "id_experiment_history";


    String GEN_TYPE_IN_GEN_MAIN_TABLE = "generator_type";
    String GEN_TYPE_IN_GEN_HISTORY_MAIN_TABLE = "generator_type";
    String GEN_ID_IN_GEN_EXTRA_DATA_TABLE = "id_generator";

    String FIELD_FOR_ID_GENERATOR_FILTER = "id_generator";
    String FIELD_FOR_ID_GENERATOR_HISTORY_FILTER = "id_generator_history";

    String ENTITY_TYPE_IN_ENTITY_HISTORY_MAIN_TABLE = "entity_type";
    String FIELD_FOR_ID_ENTITY_FILTER = "id_entity";
    String FIELD_FOR_ID_ENTITY_HISTORY_FILTER = "id_entity_history";

    String FIELD_USER_PASSWORD_IN_USER_TABLE = "user_password";

}
