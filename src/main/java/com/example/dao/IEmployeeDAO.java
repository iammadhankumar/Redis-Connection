package com.example.dao;

import java.util.Map;

import com.example.domain.Employee;

public interface IEmployeeDAO {
	
    void saveEmployee(Employee emp);
    Employee getOneEmployee(Integer id);
    void updateEmployee(Employee emp);
    Map<Integer, Employee> getAllEmployees();
    void deleteEmployee(Integer id);
    void saveAllEmployees(Map<Integer, Employee> map);

}
