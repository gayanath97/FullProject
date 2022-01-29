package com.example.fullproject.controller;

import com.example.fullproject.dto.EmployeeDTO;
import com.example.fullproject.service.EmployeeService;
import com.example.fullproject.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

   @PostMapping("/")
    public CommonResponse saveEmployee(@RequestBody EmployeeDTO employeeDTO){

        return employeeService.saveEmployee(employeeDTO);

   }

   @PutMapping("/update/{employeeCode}")
    public CommonResponse updateEmployee(@PathVariable String employeeCode,@RequestBody EmployeeDTO employeeDTO){
        return employeeService.updateEmployee(employeeCode,employeeDTO);
   }

   @DeleteMapping("/{employeeCode}")
    public CommonResponse deleteEmployee(@PathVariable String employeeCode){
        return employeeService.deleteEmployee(employeeCode);
   }

   @GetMapping("/")
    public CommonResponse getAll(){
        return employeeService.getAll();
   }

   @GetMapping("/{employeeCode}")
    public CommonResponse getById(@PathVariable String employeeCode){
        return employeeService.getById(employeeCode);
   }













}
