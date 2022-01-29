package com.example.fullproject.controller;

import com.example.fullproject.dto.OpdDTO;
import com.example.fullproject.service.OpdService;
import com.example.fullproject.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/opd")
public class OpdController {

    private OpdService opdService;

    @Autowired
    public OpdController(OpdService opdService) {
        this.opdService = opdService;
    }

    @PostMapping("/")
    public CommonResponse saveOpd(@RequestBody OpdDTO opdDTO){
        return opdService.saveOpd(opdDTO);

    }

    @PutMapping("/update/{id}")
    public  CommonResponse updateOpd(@PathVariable String id,@RequestBody OpdDTO opdDTO){
        return opdService.updateOpd(id,opdDTO);
    }

    @DeleteMapping("/{id}")
    public CommonResponse deleteOpd(@PathVariable String id){
        return opdService.deleteOpd(id);
    }

    @GetMapping("/")
    public  CommonResponse getAll(){
        return opdService.getAll();
    }

    @GetMapping("/{id}")
    public  CommonResponse getById(@PathVariable String id){
        return opdService.getById(id);
    }


}
