package com.example.fullproject.controller;

import com.example.fullproject.dto.ExpenseBillDTO;
import com.example.fullproject.dto.RrBillDTO;
import com.example.fullproject.service.RrBillService;
import com.example.fullproject.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rrbill")
public class RrBillController {


    private RrBillService rrBillService;

    @Autowired
    public RrBillController(RrBillService rrBillService) {
        this.rrBillService = rrBillService;
    }

    @PostMapping("/")
    public CommonResponse saveRrBill(@RequestBody RrBillDTO rrBillDTO){
        return rrBillService.saveRrBill(rrBillDTO);
    }
    @PutMapping("/update/{id}")
    public CommonResponse updateRrBill(@PathVariable String id,@RequestBody RrBillDTO rrBillDTO){
        return rrBillService.updateRrBill(id,rrBillDTO);
    }

    @DeleteMapping("/{id}")
    public CommonResponse deleteRrBill(@PathVariable String id){
        return rrBillService.deleteRrBill(id);
    }

    @GetMapping("/")
    public CommonResponse getAll(){
        return rrBillService.getAll();
    }
    @GetMapping("/{id}")
    public CommonResponse getById(@PathVariable String id){
        return rrBillService.getById(id);
    }
}
