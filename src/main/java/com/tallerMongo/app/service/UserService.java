package com.tallerMongo.app.service;

import com.tallerMongo.app.exception.NotFoundException;
import com.tallerMongo.app.helpers.ChangesUpdates;
import com.tallerMongo.app.model.RoleModel;
import com.tallerMongo.app.model.StudentModel;
import com.tallerMongo.app.model.UserModel;
import com.tallerMongo.app.repository.RoleRepository;
import com.tallerMongo.app.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final RoleService roleService;

  public UserService(UserRepository userRepository, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleService = new RoleService(roleRepository);
  }

  public List<UserModel> getAllUsers() {
    return userRepository.findAll();
  }

  public UserModel saveUser(UserModel user) {
    return userRepository.save(user);
  }

  public UserModel getOneUserById(ObjectId id) {
    return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
  }

  public UserModel updateUser(ObjectId id, UserModel user) {
    UserModel savedUser = this.getOneUserById(id);

    String newFirsName = ChangesUpdates.changeData(user.getFirstName(), savedUser.getFirstName());
    String newPassword = ChangesUpdates.changeData(user.getPassword(), savedUser.getPassword());
    String newEmail = ChangesUpdates.changeData(user.getEmail(), savedUser.getEmail());
    String newFirstSurname = ChangesUpdates.changeData(user.getFirstSurname(), savedUser.getFirstSurname());
    StudentModel newStudent = ChangesUpdates.changeData(user.getStudent(), savedUser.getStudent());

    savedUser.setFirstName(newFirsName);
    savedUser.setPassword(newPassword);
    savedUser.setEmail(newEmail);
    savedUser.setFirstSurname(newFirstSurname);
    savedUser.setStudent(newStudent);

    return userRepository.save(savedUser);
  }

  public UserModel deleteUserById(ObjectId id) {
    UserModel user = this.getOneUserById(id);
    userRepository.deleteById(id);
    return user;
  }

  public UserModel loginUser(String email, String password) {
    return userRepository.loginUser(email, password);
  }

  public UserModel getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public UserModel getUserByDocumentId(String documentId) {
    return userRepository.findByDocumentId(documentId);
  }

  public ObjectId verifyExistPassword (UserModel user) {
    UserModel userByDocumentId = this.getUserByDocumentId(user.getDocumentId());

    if (userByDocumentId != null && userByDocumentId.getPassword() == null) {
      return userByDocumentId.getId();
    }
    return user.getId();
  }

  public void updateStudent (ObjectId userId, StudentModel student) {
    UserModel user = this.getOneUserById(userId);
    user.setStudent(student);
    userRepository.save(user);
  }

  public boolean uniqueUser (UserModel user) {
    UserModel userByEmail = this.getUserByEmail(user.getEmail());
    UserModel userByDocumentId = this.getUserByDocumentId(user.getDocumentId());

    return (userByEmail == null || userByEmail.getPassword() == null) && (userByDocumentId == null || userByDocumentId.getPassword() == null);
  }

  public List<UserModel> getUserStudentsAvailables () {
    return userRepository.findAll().stream().filter(user -> this.nothingRegisterStudent(user)).toList();
  }

  public List<UserModel> getUserStudents () {
    return userRepository.findAll().stream().filter(user -> this.registerStudentsAvailable(user)).toList();
  }

  public boolean nothingRegisterStudent (UserModel user) {
    RoleModel role = roleService.getRolById(user.getRole().getId());
    return user.getStudent() == null && role.getNameId().equals("1");
  }
  public boolean registerStudentsAvailable (UserModel user) {
    RoleModel role = roleService.getRolById(user.getRole().getId());
    return user.getStudent() != null && role.getNameId().equals("1");
  }

  public boolean getUserWithStudent (ObjectId userId) {
    UserModel user = this.getOneUserById(userId);
    return user.getStudent() != null || user.getRole().getNameId().equals("2");
  }
}