package pe.upc.ruedarentprojectmobile.service.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.upc.ruedarentprojectmobile.exceptions.AlreadyExistsException;
import pe.upc.ruedarentprojectmobile.exceptions.ResourceNotFoundException;
import pe.upc.ruedarentprojectmobile.model.User;
import pe.upc.ruedarentprojectmobile.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;




    @Override
    public User addUser(User user) {
        return Optional.ofNullable(user).filter(c -> !userRepository.existsUserByEmail(c.getEmail()))
                .map(userRepository::save)
                .orElseThrow(() -> new AlreadyExistsException(user.getEmail()+ "already exists"));
    }

    /**
     *
     private String name;
     private String email;
     private String password;
     private String phone;
     private String address;
     private String role;


     @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
     @JsonManagedReference
     private List<Vehicle> vehicles;

     @OneToMany(mappedBy = "usuarioDestino", cascade = CascadeType.ALL, orphanRemoval = true)
     @JsonManagedReference
     private List<Notification> notifications;
     */


    @Override
    public User updateUser(User user, Long userId) {
        return Optional.ofNullable(getUser(userId))
                .map(oldUser -> {
                    oldUser.setName(user.getName());
                    oldUser.setPhone(user.getPhone());
                    oldUser.setAddress(user.getAddress());
                    oldUser.setRole(user.getRole());
                    oldUser.setPassword(user.getPassword());
                    oldUser.setEmail(user.getEmail());
                    return userRepository.save(oldUser);
                }).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository::delete,() -> {throw new ResourceNotFoundException("User not found");});
    }

    @Override
    public User getUserByEmail(String email) {
        return   userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
