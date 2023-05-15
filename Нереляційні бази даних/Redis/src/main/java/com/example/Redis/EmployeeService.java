package com.example.Redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final RedisTemplate<String, Employee> redisTemplate;
    private static final String HASH_KEY = "Employee";

    public EmployeeService(RedisTemplate<String, Employee> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void addEmployee(Employee employee) {
        redisTemplate.opsForHash().put(HASH_KEY, employee.getId(), employee);
    }

    public void updateEmployee(String id, Employee employee) {
        redisTemplate.opsForHash().put(HASH_KEY, id, employee);
    }

    public Employee getEmployeeById(String id) {
        return (Employee) redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    public void deleteEmployeeById(String id) {
        redisTemplate.opsForHash().delete(HASH_KEY, id);
    }
}

