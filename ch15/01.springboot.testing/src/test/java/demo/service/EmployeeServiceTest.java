package demo.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import demo.entity.Employee;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@Test
	public void test_getMaxSalariedEmployee() {
		Employee emp = employeeService.getMaxSalariedEmployee();
		assertNotNull(emp);

		assertEquals(2, emp.getId());
		assertEquals("Employee2", emp.getName());
		assertEquals(75000, emp.getSalary());
	}
}
