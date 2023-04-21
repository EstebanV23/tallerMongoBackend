package com.tallerMongo.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tallerMongo.app.exception.NotFoundException;
import com.tallerMongo.app.model.RoleModel;
import com.tallerMongo.app.repository.RoleRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Service
public class RoleService {
  private final RoleRepository roleRepository;

  public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  public List<RoleModel> getAllRoles () {
    return roleRepository.findAll();
  }

  public RoleModel getRolById (ObjectId id) {
    return roleRepository.findById(id).orElseThrow(() -> new NotFoundException("rol not found"));
  }

  public RoleModel saveRole (RoleModel rol) {
    return roleRepository.save(rol);
  }
}
