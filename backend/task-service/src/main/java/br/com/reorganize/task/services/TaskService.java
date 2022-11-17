package br.com.reorganize.task.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.reorganize.task.dtos.TaskDTO;
import br.com.reorganize.task.entities.Task;
import br.com.reorganize.task.entities.enums.Status;
import br.com.reorganize.task.repositories.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository repository;
	
	@Transactional
	public TaskDTO save(TaskDTO dto) {
		var task = new Task();
		var taskDTO = new TaskDTO();
		dto.setStatus(Status.CREATE);
		BeanUtils.copyProperties(dto, task);
		BeanUtils.copyProperties(repository.save(task), taskDTO);
		return taskDTO;
	}
	
	public Optional<TaskDTO> findById(Long id) {
		var task = repository.findById(id);
		var taskDTO = new TaskDTO();
		
		if (task.isPresent()) {
			BeanUtils.copyProperties(task.get(), taskDTO);
			return Optional.of(taskDTO);
		}
		
		return Optional.empty();
	}
	
	public Page<Task> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	@Transactional
	public boolean delete(Long id) {
		Optional<Task> task = repository.findById(id);
		
		if (!task.isPresent()) {
			return false;
		}
		
		repository.delete(task.get());
		return true;
	}
	
	@Transactional
	public boolean update(Long id, TaskDTO dto) {
		Optional<Task> task = repository.findById(id);
		
		if (!task.isPresent()) {
			return false;
		}
		
		if (dto.getStatus() == null) {			
			dto.setStatus(task.get().getStatus());
		}
		
		BeanUtils.copyProperties(dto, task.get());
		repository.save(task.get());
		return true;
	}
	
}
