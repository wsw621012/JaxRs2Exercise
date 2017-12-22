package com.trend.frs.lucifer.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.trend.frs.lucifer.models.Employee;

@Service
public class EmployeeService {

	private final List<Employee> employees = new ArrayList<>();

	private Map<String, Employee> dict;

	@PostConstruct
	public void init() {
		employees.add(new Employee("John", "john@lucifer.com", 50));
		employees.add(new Employee("Sam", "sam@lucifer.com", 50));
		employees.add(new Employee("King", "king@lucifer.com", 100));
		employees.add(new Employee("Jack", "jack@lucifer.com", 100));
		dict = employees.stream().collect(Collectors.toMap(e -> e.getName(), e -> e));
	}

	public Stream<Employee> all() {
		return StreamSupport.stream(employees.spliterator(), false);
	}

	public Employee search(String name) {
		Employee e = dict.get(name);
		if (e == null) {
			try {
				Thread.sleep((long) Math.random() * 8000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		return e;
	}
}
