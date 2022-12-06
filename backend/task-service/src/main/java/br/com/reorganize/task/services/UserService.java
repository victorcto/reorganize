package br.com.reorganize.task.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.reorganize.task.dtos.UserDTO;
import br.com.reorganize.task.dtos.UserLoginDTO;
import br.com.reorganize.task.dtos.UserLoginIdDTO;
import br.com.reorganize.task.entities.User;
import br.com.reorganize.task.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Transactional
	public User save(User user) {
		String encrypt = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(encrypt);
		return repository.save(user);
	}
	
	public Optional<UserLoginIdDTO> login(UserLoginDTO user) {
		Optional<User> userOptional = repository.findByUsername(user.getUsername());
		
		if (!userOptional.isPresent()) {
			return null;
		}
		
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		
		if (!bcrypt.matches(user.getPassword(), userOptional.get().getPassword())) {
			return Optional.empty();
		}
		
		UserLoginIdDTO loginWithID = new UserLoginIdDTO(userOptional.get().getId(), user.getUsername(), user.getPassword());
		
		return Optional.of(loginWithID);
	}
	
	public boolean haveRegisteredUser(UserDTO user) {
		boolean haveEmail = repository.findByEmail(user.getEmail()).isPresent();
		boolean haveUsername = repository.findByUsername(user.getUsername()).isPresent();
		
		if (haveEmail || haveUsername) {
			return true;
		}
		
		return false;
	}
	
	public Optional<User> findById(Long id) {
		return repository.findById(id);
	}
	
	public Page<User> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	@Transactional
	public boolean delete(Long id) {
		Optional<User> user = findById(id);
		
		if (!user.isPresent()) {
			return false;
		}
		
		repository.delete(user.get());
		return true;
	}
	
	@Transactional
	public User update(Long id, User user, User userUpdate) {
		BeanUtils.copyProperties(userUpdate, user);
		String encrypt = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(encrypt);
		return repository.save(user);
	}
	
}
