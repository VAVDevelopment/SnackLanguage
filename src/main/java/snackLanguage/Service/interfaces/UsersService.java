package snackLanguage.Service.interfaces;

import org.hibernate.Session;
import snackLanguage.dao.Entities.UserEntity;

import java.util.List;

public interface UsersService {

    void setSession(Session session);
    void saveUser(UserEntity user);
    UserEntity getUserById(int id);
    List<UserEntity> getAllUsers();
    void updateUser(UserEntity user);
    void deleteUser(UserEntity user);
    void closeSession();

}
