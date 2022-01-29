package com.example.fullproject.controller;

import com.example.fullproject.dto.OpdAmountDTO;
import com.example.fullproject.service.OpdAmountService;
import com.example.fullproject.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/opdamount")
public class OpdAmountController {

    private OpdAmountService opdAmountService;


    @Autowired
    public OpdAmountController(OpdAmountService opdAmountService) {
        this.opdAmountService = opdAmountService;
    }

    @PostMapping("/")
    public CommonResponse saveOpdAmount(@RequestBody OpdAmountDTO opdAmountDTO){

        return opdAmountService.saveOpdAmount(opdAmountDTO);

    }

    @PutMapping("/update/{id}")
    public CommonResponse updateOpdAmount(@RequestBody OpdAmountDTO opdAmountDTO, @PathVariable String id){
        return opdAmountService.updateOpdAmount(opdAmountDTO,id);
    }

    @DeleteMapping("/{id}")
    public CommonResponse deleteOpdAmount(@PathVariable String id){
        return opdAmountService.deleteOpdAmount(id);
    }

    @GetMapping("/")
    public CommonResponse getAll(){
        return opdAmountService.getAll();
    }

    @GetMapping("/{id}")
    public CommonResponse getById(@PathVariable String id){
        return opdAmountService.getById(id);
    }


}
