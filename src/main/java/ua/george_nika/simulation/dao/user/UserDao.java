package ua.george_nika.simulation.dao.user;

/**
 * Created by george on 30.12.2015.
 */
public interface UserDao {

    boolean isExist(String userName, String password);

}
