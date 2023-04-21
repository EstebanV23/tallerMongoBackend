package com.tallerMongo.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tallerMongo.app.exception.NotFoundException;
import com.tallerMongo.app.model.RoleModel;
import com.tallerMongo.app.repository.RoleRepository;
import com.tallerMongo.app.service.RoleService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rol")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE
        , RequestMethod.PATCH})
public class RoleController {

  private final RoleService roleService;

  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping("/")
  public List<RoleModel> getAllRoles () {
    return roleService.getAllRoles();
  }

  @GetMapping("/{id}")
  public RoleModel getRolById (@PathVariable ObjectId id) {
    return roleService.getRolById(id);
  }

  @PostMapping("/add")
  public RoleModel saveRole (@RequestBody Map<String, Object> body) {
    ObjectMapper mapper = new ObjectMapper();
    RoleModel rol = mapper.convertValue(body, RoleModel.class);
    return roleService.saveRole(rol);
  }
}
