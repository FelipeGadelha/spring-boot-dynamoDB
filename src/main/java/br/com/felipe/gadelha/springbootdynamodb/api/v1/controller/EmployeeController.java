package br.com.felipe.gadelha.springbootdynamodb.api.v1.controller;

import br.com.felipe.gadelha.springbootdynamodb.api.v1.dto.request.EmployeeRq;
import br.com.felipe.gadelha.springbootdynamodb.domain.entity.Employee;
import br.com.felipe.gadelha.springbootdynamodb.domain.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final IEmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(employeeRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable String id) {
        return ResponseEntity.ok(employeeRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody EmployeeRq employeeRq) {
        var employee = employeeRq.convert();
        return ResponseEntity.ok(employeeRepository.save(employee));
    }

    @PutMapping
    public void update(@PathVariable String id, @RequestBody EmployeeRq employeeRq) {
        var employee = employeeRq.convert();
        employeeRepository.update(id, employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        employeeRepository.delete(id);
    }

}
