package br.com.felipe.gadelha.springbootdynamodb.infra.repository;

import br.com.felipe.gadelha.springbootdynamodb.domain.entity.Employee;
import br.com.felipe.gadelha.springbootdynamodb.domain.repository.IEmployeeRepository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeRepository implements IEmployeeRepository {

    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public EmployeeRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public Employee findById(Long id) {
        return dynamoDBMapper.load(Employee.class, id);
    }

    @Override
    public Employee save(Employee employee) {
        dynamoDBMapper.save(employee);
        return employee;
    }

    @Override
    public void delete(Long id) {
        var employee = this.findById(id);
        dynamoDBMapper.delete(employee);
    }

    @Override
    public Long update(Long id, Employee employee) {
        dynamoDBMapper.save(employee,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("id",
                                new ExpectedAttributeValue(
                                    new AttributeValue().withS(id.toString())
                                )));
        return id;
    }
}
