package pe.upc.ruedarentprojectmobile.service.User;

import pe.upc.ruedarentprojectmobile.model.User;

import java.util.List;

public interface IUserService {
    User addUser(User user);
    User updateUser(User user, Long userId);
    User getUser(Long userId);
    void deleteUser(Long userId);
    User getUserByEmail(String email);

    List<User> getAllUsers();
}
