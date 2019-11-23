package demo.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import demo.entity.Employee;

@Repository
@Profile("test")
public class MockEmployeeRepository implements EmployeeRepository {
	
	public List<Employee> findAllEmployees() {
		return Arrays.asList(
				new Employee(1, "Employee1", 50000),
				new Employee(2, "Employee2", 75000),
				new Employee(3, "Employee3", 43000));

	}
}