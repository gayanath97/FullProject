package com.example.fullproject.controller;

import com.example.fullproject.dto.RrDTO;
import com.example.fullproject.service.RrService;
import com.example.fullproject.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rr")
public class RrController {

    private RrService rrService;

    @Autowired
    public RrController(RrService rrService) {
        this.rrService = rrService;
    }

    @PostMapping("/")
    public CommonResponse saveRr(@RequestBody RrDTO rrDTO){
        return rrService.saveRr(rrDTO);
    }

    @PutMapping("/update/{id}")
    public CommonResponse updateRr(@PathVariable String id,@RequestBody RrDTO rrDTO){
        return  rrService.updateRr(id,rrDTO);
    }

    @DeleteMapping("/{id}")
    public CommonResponse deleteRr(@PathVariable String id){
        return rrService.deleteRr(id);
    }


    @GetMapping("/")
    public CommonResponse getAll(){
        return rrService.getAll();
    }

    @GetMapping("/{id}")
    public CommonResponse getById(@PathVariable String id){
        return rrService.getById(id);
    }


}
