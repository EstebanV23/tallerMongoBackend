package com.tallerMongo.app.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorReponseModel {
  public String error;

  public ErrorReponseModel(String message) {
    this.error = message;
  }

  static public ResponseEntity<ErrorReponseModel> NOT_FOUND (ErrorReponseModel errorReponseModel) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorReponseModel);
  }
  static public ResponseEntity<ErrorReponseModel> BAD_REQUEST (ErrorReponseModel errorReponseModel) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorReponseModel);
  }
  static public ResponseEntity<ErrorReponseModel> CONFLICT (ErrorReponseModel errorReponseModel) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(errorReponseModel);
  }

  static public ResponseEntity<ErrorReponseModel> UNAUTHORIZED (ErrorReponseModel errorReponseModel) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorReponseModel);
  }
}
