package com.example.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;

@Repository
public class EmployeeImpl implements IEmployeeDAO {
	
    private final String hashReference= "Employee";
	
 // 'redisTemplate' is defined as a Bean in RedisConfig.java
    @Resource(name="redisTemplate")
    private HashOperations<String, Integer, Employee> hashOperations;

	@Override
	public void saveEmployee(Employee emp) {
		//creates one record in Redis DB if record with that Id is not present
        hashOperations.putIfAbsent(hashReference, emp.getEmpId(), emp);
	}
	
	@Override
    public void saveAllEmployees(Map<Integer, Employee> map) {
        hashOperations.putAll(hashReference, map);
    }

    @Override
    public Employee getOneEmployee(Integer id) {
       return hashOperations.get(hashReference, id);
    }

    @Override
    public void updateEmployee(Employee emp) {
       hashOperations.put(hashReference, emp.getEmpId(), emp);
    }

    @Override
    public Map<Integer, Employee> getAllEmployees() {
       return hashOperations.entries(hashReference);
    }

    @Override
    public void deleteEmployee(Integer id) {
       hashOperations.delete(hashReference, id);
    }

}
