package br.com.felipe.gadelha.springbootdynamodb.domain.repository;

import br.com.felipe.gadelha.springbootdynamodb.domain.entity.Employee;

import java.util.List;

public interface IEmployeeRepository {
    List<Employee> findAll();
    Employee findById(String id);
    Employee save(Employee employee);
    void delete(String id);
    String update(String id, Employee employee);
}
