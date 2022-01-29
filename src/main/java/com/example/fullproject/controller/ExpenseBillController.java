package com.example.fullproject.controller;

import com.example.fullproject.dto.ExpenseBillDTO;
import com.example.fullproject.service.ExpenseBillService;
import com.example.fullproject.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expensebill")
public class ExpenseBillController {

    private ExpenseBillService expenseBillService;

    @Autowired
    public ExpenseBillController(ExpenseBillService expenseBillService) {
        this.expenseBillService = expenseBillService;
    }

    @PostMapping("/")
    public CommonResponse saveExpenseBill(@RequestBody ExpenseBillDTO expenseBillDTO){
        return expenseBillService.saveExpenseBill(expenseBillDTO);
    }
    @PutMapping("/update/{id}")
    public CommonResponse updateExpenseBill(@PathVariable String id,@RequestBody ExpenseBillDTO expenseBillDTO){
        return expenseBillService.updateExpenseBill(id,expenseBillDTO);
    }

    @DeleteMapping("/{id}")
    public CommonResponse deleteExpenseBill(@PathVariable String id){
        return expenseBillService.deleteExpenseBill(id);
    }

    @GetMapping("/")
    public CommonResponse getAll(){
        return expenseBillService.getAll();
    }
    @GetMapping("/{id}")
    public CommonResponse getById(@PathVariable String id){
        return expenseBillService.getById(id);
    }


}
