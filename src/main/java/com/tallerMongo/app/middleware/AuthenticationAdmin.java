package com.tallerMongo.app.middleware;

import com.tallerMongo.app.model.ErrorReponseModel;
import com.tallerMongo.app.model.UserModel;
import com.tallerMongo.app.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;

public class AuthenticationAdmin {
  private final UserService userService;

  public AuthenticationAdmin (UserService userService) {
    this.userService = userService;
  }
  public ResponseEntity isAdmin (ObjectId userId) {
    UserModel user = userService.getOneUserById(userId);
    if (!user.getRole().getNameId().equals("2")) {
      ErrorReponseModel error = new ErrorReponseModel("You don't have permission to do this");
      return ErrorReponseModel.UNAUTHORIZED(error);
    } else {
      return null;
    }
  }
}
