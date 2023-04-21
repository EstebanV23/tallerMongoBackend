package com.tallerMongo.app.helpers;

import com.tallerMongo.app.model.StudentModel;

public class ChangesUpdates {
  static public String changeData (String newData, String oldData) {
    if (newData == null) {
      return oldData;
    } else {
      return newData;
    }
  }

  static public int changeData (Integer newData, Integer oldData) {
    if (newData == null) {
      return oldData;
    } else {
      return newData;
    }
  }

  static public StudentModel changeData (StudentModel newData, StudentModel oldData) {
    if (newData == null) {
      return oldData;
    } else {
      return newData;
    }
  }
}
