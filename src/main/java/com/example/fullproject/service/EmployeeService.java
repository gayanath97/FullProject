package com.example.fullproject.service;

import com.example.fullproject.dto.EmployeeDTO;
import com.example.fullproject.entity.Employee;
import com.example.fullproject.repository.EmployeeRepository;
import com.example.fullproject.util.CommonResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private RewardService rewardService;
    private ExpenseService expenseService;
    private RrService rrService;
    private OpdService opdService;
    private OpdAmountService opdAmountService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper, RewardService rewardService,ExpenseService expenseService,RrService rrService,OpdService opdService,OpdAmountService opdAmountService) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.rewardService = rewardService;
        this.expenseService = expenseService;
        this.rrService = rrService;
        this.opdService = opdService;
        this.opdAmountService = opdAmountService;

    }
// method to save employee
    public CommonResponse saveEmployee(EmployeeDTO employeeDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            Employee employee = new Employee();
            employee.setEmployeeCode(Long.valueOf(employeeDTO.getEmployeeCode()));
            employee.setEmail(employeeDTO.getEmail());
            employee.setName(employeeDTO.getName());
            employee.setPassword(employeeDTO.getPassword());
            employee.setPhoneNumber(Long.valueOf(employeeDTO.getPhoneNumber()));
            employee.setType(employeeDTO.getType());
//            employee.setRewards(rewardService.castRewardDTOSIntoRewards(employeeDTO.getRewards()));
//            employee.setExpenses(expenseService.castExpenseDTOSIntoExpenses(employeeDTO.getExpenses()));
//            employee.setRrs(rrService.castRrDTOListIntoRrList(employeeDTO.getRrs()));
//            employee.setOpds(opdService.opdDTOSTOopds(employeeDTO.getOpds()));
            employee.setOpdAmount(opdAmountService.findById(employeeDTO.getOpdAmount()));

            employeeRepository.save(employee);
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }
       return commonResponse;
    }
    // method to update employee
    public CommonResponse updateEmployee(String employeeCode, EmployeeDTO employeeDTO) {

        CommonResponse commonResponse = new CommonResponse();
        try {

            Employee employee = employeeRepository.getById(Long.valueOf(employeeCode));

            employee.setEmployeeCode(Long.valueOf(employeeDTO.getEmployeeCode()));
            employee.setEmail(employeeDTO.getEmail());
            employee.setName(employeeDTO.getName());
            employee.setPassword(employeeDTO.getPassword());
            employee.setPhoneNumber(Long.valueOf(employeeDTO.getPhoneNumber()));
            employee.setType(employeeDTO.getType());
//            employee.setRewards(rewardService.castRewardDTOSIntoRewards(employeeDTO.getRewards()));
//            employee.setExpenses(expenseService.castExpenseDTOSIntoExpenses(employeeDTO.getExpenses()));
//            employee.setRrs(rrService.castRrDTOListIntoRrList(employeeDTO.getRrs()));
//            employee.setOpds(opdService.opdDTOSTOopds(employeeDTO.getOpds()));
            employee.setOpdAmount(opdAmountService.findById(employeeDTO.getOpdAmount()));

            employeeRepository.save(employee);
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }

        return commonResponse;

    }
    // method to delete employee
    public CommonResponse deleteEmployee(String employeeCode) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            Employee employee = employeeRepository.getById(Long.valueOf(employeeCode));
            employeeRepository.delete(employee);
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }
      return commonResponse;
    }
//method to get all employees
    public CommonResponse getAll() {
       CommonResponse commonResponse = new CommonResponse();
        List<EmployeeDTO> employeeDTOS = null;

       try {
           List<Employee> employees = employeeRepository.findAll();
           employeeDTOS = castEmployeesIntoEmployeeDTOS(employees);
           commonResponse.setPayload(Collections.singletonList(employeeDTOS));
           commonResponse.setStatus(true);

       }catch (Exception e){
           System.out.println(e);
       }
     return commonResponse;
    }

    private List<EmployeeDTO> castEmployeesIntoEmployeeDTOS(List<Employee> employees) {

        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for(Employee employee : employees){

            EmployeeDTO employeeDTO;
            employeeDTO = modelMapper.map(employee,EmployeeDTO.class);
            employeeDTOS.add(employeeDTO);

        }

        return employeeDTOS;
    }

    public CommonResponse getById(String employeeCode) {

        CommonResponse commonResponse =new CommonResponse();
        EmployeeDTO employeeDTO = null;
        try {

            Employee employee = employeeRepository.findById(Long.valueOf(employeeCode)).get();
            employeeDTO=modelMapper.map(employee,EmployeeDTO.class);
            commonResponse.setPayload(Collections.singletonList(employeeDTO));
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }
      return commonResponse;
    }

    public  Employee findById(String employeeCode){
        return employeeRepository.findById(Long.valueOf(employeeCode)).get();
    }

}
