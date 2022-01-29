package com.example.fullproject.service;

import com.example.fullproject.dto.ExpenseDTO;
import com.example.fullproject.entity.Expense;
import com.example.fullproject.repository.ExpenseRepository;
import com.example.fullproject.util.CommonResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExpenseService {

    private ExpenseRepository expenseRepository;
    private ModelMapper modelMapper;
    private EmployeeService employeeService;
    private ExpenseBillService expenseBillService;


    @Autowired
    @Lazy
    public ExpenseService(ExpenseRepository expenseRepository, ModelMapper modelMapper,EmployeeService employeeService,ExpenseBillService expenseBillService) {
        this.expenseRepository = expenseRepository;
        this.modelMapper = modelMapper;
        this.employeeService=employeeService;
        this.expenseBillService = expenseBillService;
    }


    public CommonResponse saveExpense(ExpenseDTO expenseDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            Expense expense = new Expense();

            expense.setId(Long.valueOf(expenseDTO.getId()));
            expense.setBuOrDept(expenseDTO.getBuOrDept());
            expense.setProject(expenseDTO.getProject());
            expense.setExtensionNo(Integer.valueOf(expenseDTO.getExtensionNo()));
            expense.setCustomer(expenseDTO.getCustomer());
            expense.setLocation(expenseDTO.getLocation());
            expense.setBillability(expenseDTO.getBillability());

            expense.setEmployee(employeeService.findById(expenseDTO.getEmployee()));
     //       expense.setExpenseBills(expenseBillService.castExpenseBillDTOSIntoExpenseBills(expenseDTO.getExpenseBill()));

            expenseRepository.save(expense);
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }
        return commonResponse;
    }

    public CommonResponse updateExpense(String id, ExpenseDTO expenseDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            Expense expense = expenseRepository.getById(Long.valueOf(id));

            expense.setId(Long.valueOf(expenseDTO.getId()));
            expense.setBuOrDept(expenseDTO.getBuOrDept());
            expense.setProject(expenseDTO.getProject());
            expense.setExtensionNo(Integer.valueOf(expenseDTO.getExtensionNo()));
            expense.setCustomer(expenseDTO.getCustomer());
            expense.setLocation(expenseDTO.getLocation());
            expense.setBillability(expenseDTO.getBillability());

            expense.setEmployee(employeeService.findById(expenseDTO.getEmployee()));
    //        expense.setExpenseBills(expenseBillService.castExpenseBillDTOSIntoExpenseBills(expenseDTO.getExpenseBill()));

            expenseRepository.save(expense);
            commonResponse.setStatus(true);


        }catch (Exception e){
            System.out.println(e);
        }
        return commonResponse;
    }

    public CommonResponse deleteExpense(String id) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            Expense expense = expenseRepository.getById(Long.valueOf(id));

            expenseRepository.delete(expense);
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }
        return commonResponse;
    }

    public CommonResponse getAll() {

        CommonResponse commonResponse = new CommonResponse();
        List<ExpenseDTO> expenseDTOS = null;

        try {

            List<Expense> expenses = expenseRepository.findAll();
            expenseDTOS = castExpensesIntoExpenseDTOS(expenses);
            commonResponse.setPayload(Collections.singletonList(expenseDTOS));
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }
        return commonResponse;
    }

    private List<ExpenseDTO> castExpensesIntoExpenseDTOS(List<Expense> expenses) {

        List<ExpenseDTO> expenseDTOS = new ArrayList<>();

        for (Expense expense : expenses){
            ExpenseDTO expenseDTO = modelMapper.map(expense,ExpenseDTO.class);
            expenseDTOS.add(expenseDTO);
        }

        return expenseDTOS;

    }
    public Set<Expense> castExpenseDTOSIntoExpenses(Set<ExpenseDTO> expenseDTOS) {

        Set<Expense> expenses = new HashSet<>();

        for (ExpenseDTO expenseDTO : expenseDTOS){
            Expense expense = modelMapper.map(expenseDTO,Expense.class);
            expenses.add(expense);
        }

        return expenses;

    }

    public CommonResponse getById(String id) {

        CommonResponse commonResponse = new CommonResponse();
        ExpenseDTO expenseDTO = null;

        try {

            Expense expense = expenseRepository.findById(Long.valueOf(id)).get();
            expenseDTO = modelMapper.map(expense,ExpenseDTO.class);
            commonResponse.setPayload(Collections.singletonList(expenseDTO));
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }
        return commonResponse;
    }

    public Expense findById(String id){

        Expense expense = expenseRepository.findById(Long.valueOf(id)).get();

        return expense;
    }


}
