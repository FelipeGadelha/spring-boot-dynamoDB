package br.com.felipe.gadelha.springbootdynamodb.infra.repository;

import br.com.felipe.gadelha.springbootdynamodb.domain.entity.Employee;
import br.com.felipe.gadelha.springbootdynamodb.domain.repository.IEmployeeRepository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository implements IEmployeeRepository {

    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public EmployeeRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public List<Employee> findAll() {
        return dynamoDBMapper.scan(Employee.class, new DynamoDBScanExpression());
    }

    public List<Employee> findAllPagination() {
//        var employee = new Employee(); //test
        var queryExpression = new DynamoDBQueryExpression<Employee>()
//                .withHashKeyValues(employee)
                .withLimit(10);
        return dynamoDBMapper.query(Employee.class, queryExpression);
    }

    @Override
    public Employee findById(String id) {
        return dynamoDBMapper.load(Employee.class, id);
    }

    @Override
    public Employee save(Employee employee) {
        dynamoDBMapper.save(employee);
        return employee;
    }

    @Override
    public void delete(String id) {
        var employee = this.findById(id);
        dynamoDBMapper.delete(employee);
    }

    @Override
    public String update(String id, Employee employee) {
        dynamoDBMapper.save(employee,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("id",
                                new ExpectedAttributeValue(
                                    new AttributeValue().withS(id)
                                )));
        return id;
    }
}
