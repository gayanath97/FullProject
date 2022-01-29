package com.example.fullproject.service;

import com.example.fullproject.dto.RewardDTO;
import com.example.fullproject.entity.Reward;
import com.example.fullproject.repository.RewardRepository;
import com.example.fullproject.util.CommonResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class RewardService {

    private RewardRepository rewardRepository;
    private ModelMapper moddelMapper;
    private EmployeeService employeeService;



    @Autowired
    @Lazy
    public RewardService(RewardRepository rewardRepository, ModelMapper moddelMapper,EmployeeService employeeService) {
        this.rewardRepository = rewardRepository;
        this.moddelMapper = moddelMapper;
        this.employeeService = employeeService;
    }

    //method to save reward
    public CommonResponse saveReward(RewardDTO rewardDTO) {

        CommonResponse commonResponse = new CommonResponse();
        try {
          //  Reward reward = moddelMapper.map(rewardDTO,Reward.class);

            Reward reward = new Reward();
            reward.setRewardId(Long.valueOf(rewardDTO.getRewardId()));
            reward.setAmount(Double.parseDouble(rewardDTO.getAmount()));
            reward.setAddedDate(stringToDate(rewardDTO.getAddedDate()));
            reward.setExpireDate(stringToDate(rewardDTO.getExpireDate()));
            reward.setEmployee(employeeService.findById(rewardDTO.getEmployee()));



            rewardRepository.save(reward);
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }
        return commonResponse;
    }
    //method to update reward
    public CommonResponse updateReward(String rewardId, RewardDTO rewardDTO) {
        CommonResponse commonResponse = new CommonResponse();

        try {

            Reward reward = rewardRepository.getById(Long.valueOf(rewardId));

            reward.setRewardId(Long.valueOf(rewardDTO.getRewardId()));
            reward.setAmount(Double.parseDouble(rewardDTO.getAmount()));
            reward.setAddedDate(stringToDate(rewardDTO.getAddedDate()));
            reward.setExpireDate(stringToDate(rewardDTO.getExpireDate()));
            reward.setEmployee(employeeService.findById(rewardDTO.getEmployee()));

            rewardRepository.save(reward);
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }
        return commonResponse;
    }
    //method to convert string date to date data type
    public Date stringToDate(String date){

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date1 = null;

        try {
            date1 = format.parse(date);
        } catch (Exception e) {
            System.out.println(e);
        }

        return date1;
    }
   //method to delete reward
    public CommonResponse deleteReward(String rewardId) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            Reward reward = rewardRepository.getById(Long.valueOf(rewardId));
            rewardRepository.delete(reward);
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }
        return commonResponse;
    }
   //method to get all rewards
    public CommonResponse getAll() {

     CommonResponse commonResponse = new CommonResponse();
        Set<RewardDTO> rewardDTOS = null;

     try {
         Set<Reward> rewards = (Set<Reward>) rewardRepository.findAll();
         rewardDTOS = castRewardsIntoRewardDTOS(rewards);
         commonResponse.setPayload(Collections.singletonList(rewardDTOS));
         commonResponse.setStatus(true);


     }catch (Exception e){
         System.out.println(e);
     }
        return commonResponse;
    }

    //method to convert rewards list to rewarddto list
    private Set<RewardDTO> castRewardsIntoRewardDTOS(Set<Reward> rewards) {

        Set<RewardDTO> rewardDTOS = new HashSet<>();

        for(Reward reward : rewards){
            RewardDTO rewardDTO = moddelMapper.map(reward,RewardDTO.class);
            rewardDTOS.add(rewardDTO);
        }

        return rewardDTOS;
    }

    //method to convert rewarddtos list to rewards list
    public Set<Reward> castRewardDTOSIntoRewards(Set<RewardDTO> rewardDTOS) {

        Set<Reward> rewards = new HashSet<>();

        for(RewardDTO rewardDTO : rewardDTOS){
            Reward reward = moddelMapper.map(rewardDTO,Reward.class);
            rewards.add(reward);
        }

        return rewards;
    }


    //method to get a reward by id
    public CommonResponse getById(String rewardId) {

        CommonResponse commonResponse = new CommonResponse();
        RewardDTO rewardDTO = null;
        try {

            Reward reward = rewardRepository.findById(Long.valueOf(rewardId)).get();
            rewardDTO = moddelMapper.map(reward,RewardDTO.class);
            commonResponse.setPayload(Collections.singletonList(rewardDTO));
            commonResponse.setStatus(true);


        }catch (Exception e){
            System.out.println(e);
        }
      return commonResponse;
    }

    //method to find reward by id
    public Reward findById(String rewardId){
        return rewardRepository.findById(Long.valueOf(rewardId)).get();
    }


}
