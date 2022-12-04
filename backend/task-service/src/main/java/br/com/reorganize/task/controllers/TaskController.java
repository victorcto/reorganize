package br.com.reorganize.task.controllers;

import java.util.Optional;

import javax.validation.Valid;

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

import br.com.reorganize.task.dtos.TaskDTO;
import br.com.reorganize.task.entities.Task;
import br.com.reorganize.task.services.TaskService;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService service;

	@PostMapping
	public ResponseEntity<TaskDTO> save(@RequestBody @Valid TaskDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
		Optional<TaskDTO> taskOptional = service.findById(id);

		if (!taskOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada.");
		}

		return ResponseEntity.status(HttpStatus.OK).body(taskOptional.get());
	}

	@GetMapping
	public ResponseEntity<Page<Task>> findAll(@PageableDefault(page = 0, size = 10, sort = "id", 
			direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
		if (!service.delete(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada.");
		}
	
		return ResponseEntity.status(HttpStatus.OK).body("Tarefa deletada com sucesso.");
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid TaskDTO taskDTO){
        if (!service.update(id, taskDTO)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada.");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(taskDTO);
    }

}
