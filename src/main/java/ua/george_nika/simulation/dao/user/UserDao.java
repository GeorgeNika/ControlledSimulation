/**
 * Work with table in witch stored user`s login and password
 */

package ua.george_nika.simulation.dao.user;

public interface UserDao {

    boolean isExist(String userName, String password);

}
