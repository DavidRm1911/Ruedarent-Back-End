package pe.upc.ruedarentprojectmobile.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.ruedarentprojectmobile.model.User;
import pe.upc.ruedarentprojectmobile.response.ApiResponse;
import pe.upc.ruedarentprojectmobile.service.User.IUserService;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {
    private final IUserService userService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUsers(){
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(new ApiResponse("Found", users));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/id/{userId}/user")
    public ResponseEntity<ApiResponse> getAcquirer(@PathVariable Long userId){
        try {
            User user = userService.getUser(userId);
            return ResponseEntity.ok(new ApiResponse("Found", user));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/email/{email}/user")
    public ResponseEntity<ApiResponse> getUserByEmail(@PathVariable String email){
        try {
            User user = userService.getUserByEmail(email);
            return ResponseEntity.ok(new ApiResponse("Found", user));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }

    @DeleteMapping("/delete/{userId}/user")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId){
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok(new ApiResponse("Deleted", null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/update/{userId}/user")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long userId,@RequestBody User user){
        try {
            User updatedUser = userService.updateUser(user, userId);
            return ResponseEntity.ok(new ApiResponse("Updated", updatedUser));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addUser(@RequestBody User user){
        try {
            User newUser = userService.addUser(user);
            return ResponseEntity.ok(new ApiResponse("Added", newUser));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }

}
