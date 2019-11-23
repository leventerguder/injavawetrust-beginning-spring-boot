package demo.repository;

import java.util.List;

import demo.entity.Employee;

public interface EmployeeRepository {

	public List<Employee> findAllEmployees();
}
