package br.com.reorganize.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.reorganize.task.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
