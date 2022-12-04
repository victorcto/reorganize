package br.com.reorganize.task.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.reorganize.task.dtos.UserDTO;
import br.com.reorganize.task.entities.User;
import br.com.reorganize.task.services.UserService;
import br.com.reorganize.task.utils.Util;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseEntity<Object> save(@RequestBody @Valid UserDTO userDTO) {
		if (service.haveRegisteredUser(userDTO)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já cadastrado.");
		}
		
		var user = new User();
		BeanUtils.copyProperties(userDTO, user);
		return ResponseEntity.status(HttpStatus.OK).body(service.save(user));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
		Optional<User> user = service.findById(id);
		
		if (!user.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(user.get());
	}
	
	@GetMapping
	public ResponseEntity<Page<User>> findAll(@PageableDefault(page = 0, size = 10, sort = "id", 
			direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
		if (!service.delete(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado para remoção");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Usuário removido com sucesso.");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody UserDTO userDTO) {
		Optional<User> userOptional = service.findById(id);
		Optional<UserDTO> dto = Optional.of(userDTO);
		
		BeanUtils.copyProperties(userDTO, userOptional.get(), Util.getNullPropertyNames(dto));
		
		if (!service.update(id, userOptional.get())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado para atualização"); 
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
	}
	
}
