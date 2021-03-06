package com.example.fullproject.controller;

import com.example.fullproject.dto.RewardDTO;
import com.example.fullproject.service.RewardService;
import com.example.fullproject.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reward")
public class RewardController {

    private RewardService rewardService;

    @Autowired
    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @PostMapping("/")
    public CommonResponse saveReward(@RequestBody RewardDTO rewardDTO){
       return rewardService.saveReward(rewardDTO);
 }

    @PutMapping("/update/{rewardId}")
    public CommonResponse updateReward(@PathVariable String rewardId,@RequestBody RewardDTO rewardDTO){
        return rewardService.updateReward(rewardId,rewardDTO);
    }

    @DeleteMapping("/{rewardId}")
    public CommonResponse deleteReward(@PathVariable String rewardId){
        return rewardService.deleteReward(rewardId);
    }

    @GetMapping("/")
    public CommonResponse getAll(){
        return rewardService.getAll();
    }

    @GetMapping("/{rewardId}")
    public CommonResponse getById(@PathVariable String rewardId){
            return rewardService.getById(rewardId);
        }





}
