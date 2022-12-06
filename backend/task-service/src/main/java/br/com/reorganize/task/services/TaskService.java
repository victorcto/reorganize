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
import br.com.reorganize.task.entities.User;
import br.com.reorganize.task.entities.enums.Status;
import br.com.reorganize.task.repositories.TaskRepository;
import br.com.reorganize.task.repositories.UserRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public TaskDTO save(TaskDTO dto) {
		var task = new Task();
		var taskDTO = new TaskDTO();
		dto.setStatus(Status.CREATE);
		Optional<User> user = userRepository.findById(dto.getUser());
		BeanUtils.copyProperties(dto, task);
		task.setUser(user.get());
		BeanUtils.copyProperties(taskRepository.save(task), taskDTO);
		taskDTO.setUser(user.get().getId());
		return taskDTO;
	}
	
	public Optional<TaskDTO> findById(Long id) {
		var task = taskRepository.findById(id);
		var taskDTO = new TaskDTO();
		
		if (task.isPresent()) {
			BeanUtils.copyProperties(task.get(), taskDTO);
			return Optional.of(taskDTO);
		}
		
		return Optional.empty();
	}
	
	public Page<Task> findAll(Pageable pageable) {
		return taskRepository.findAll(pageable);
	}
	
	@Transactional
	public boolean delete(Long id) {
		Optional<Task> task = taskRepository.findById(id);
		
		if (!task.isPresent()) {
			return false;
		}
		
		taskRepository.delete(task.get());
		return true;
	}
	
	@Transactional
	public boolean update(Long id, TaskDTO dto) {
		Optional<Task> task = taskRepository.findById(id);
		
		if (!task.isPresent()) {
			return false;
		}
		
		if (dto.getStatus() == null) {			
			dto.setStatus(task.get().getStatus());
		}
		
		BeanUtils.copyProperties(dto, task.get());
		taskRepository.save(task.get());
		return true;
	}
	
}
