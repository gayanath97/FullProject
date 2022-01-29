package com.example.fullproject.controller;

import com.example.fullproject.dto.ExpenseDTO;
import com.example.fullproject.service.ExpenseService;
import com.example.fullproject.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense")
public class ExpenseController {


    private ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/")
    public CommonResponse saveExpense(@RequestBody ExpenseDTO expenseDTO){
        return expenseService.saveExpense(expenseDTO);
    }

    @PutMapping("/update/{id}")
    public CommonResponse updateExpense(@PathVariable String id,@RequestBody ExpenseDTO expenseDTO){
        return expenseService.updateExpense(id,expenseDTO);
    }

    @DeleteMapping("/{id}")
    public CommonResponse deleteExpense(@PathVariable String id){
        return expenseService.deleteExpense(id);
    }

    @GetMapping("/")
    public CommonResponse getAll(){
        return expenseService.getAll();
    }

    @GetMapping("/{id}")
    public CommonResponse getById(@PathVariable String id){
        return expenseService.getById(id);
    }

}
