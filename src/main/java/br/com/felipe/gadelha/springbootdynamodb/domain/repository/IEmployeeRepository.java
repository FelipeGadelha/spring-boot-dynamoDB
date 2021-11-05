package br.com.felipe.gadelha.springbootdynamodb.domain.repository;

import br.com.felipe.gadelha.springbootdynamodb.domain.entity.Employee;
import org.springframework.stereotype.Repository;


@Repository
public interface IEmployeeRepository {
    Employee findById(Long id);
    Employee save(Employee employee);
    void delete(Long id);
    Long update(Long id, Employee employee);
}
