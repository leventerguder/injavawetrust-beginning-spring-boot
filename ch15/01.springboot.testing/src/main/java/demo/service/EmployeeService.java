package demo.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.entity.Employee;
import demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private EmployeeRepository employeeRepository;

	@Autowired(required = false)
	public EmployeeService(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	public Employee getMaxSalariedEmployee() {
		List<Employee> employees = employeeRepository.findAllEmployees();
		return employees.stream().collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary))).get();
	}

}
