package com.example.fullproject.service;

import com.example.fullproject.dto.EmployeeDTO;
import com.example.fullproject.dto.OpdAmountDTO;
import com.example.fullproject.entity.OpdAmount;
import com.example.fullproject.repository.OpdAmountRepository;
import com.example.fullproject.util.CommonResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OpdAmountService {
    
    private OpdAmountRepository opdAmountRepository;
    private ModelMapper modelMapper;
    private RewardService rewardService;
    private EmployeeService employeeService;


    @Autowired
    @Lazy
    public OpdAmountService(OpdAmountRepository opdAmountRepository, ModelMapper modelMapper,RewardService rewardService,EmployeeService employeeService) {
        this.opdAmountRepository = opdAmountRepository;
        this.modelMapper = modelMapper;
        this.rewardService = rewardService;
        this.employeeService=employeeService;
    }


    public CommonResponse saveOpdAmount(OpdAmountDTO opdAmountDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            OpdAmount opdAmount = new OpdAmount();
           // EmployeeDTO employeeDTO = new EmployeeDTO();

            opdAmount.setId(Long.valueOf(opdAmountDTO.getId()));
            opdAmount.setAmount(Double.valueOf(opdAmountDTO.getAmount()));
            opdAmount.setExpireDate(rewardService.stringToDate(opdAmountDTO.getExpireDate()));
        //    opdAmount.setEmployee(employeeService.findById(opdAmountDTO.getEmployee()));

            opdAmountRepository.save(opdAmount);
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }

        return commonResponse;
    }

    public CommonResponse updateOpdAmount(OpdAmountDTO opdAmountDTO, String id) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            OpdAmount opdAmount = opdAmountRepository.getById(Long.valueOf(id));

            opdAmount.setId(Long.valueOf(opdAmountDTO.getId()));
            opdAmount.setAmount(Double.valueOf(opdAmountDTO.getAmount()));
            opdAmount.setExpireDate(rewardService.stringToDate(opdAmountDTO.getExpireDate()));
         //   opdAmount.setEmployee(employeeService.findById(opdAmountDTO.getEmployee()));

            opdAmountRepository.save(opdAmount);
            commonResponse.setStatus(true);


        }catch (Exception e){
            System.out.println(e);
        }

        return  commonResponse;

    }

    public CommonResponse deleteOpdAmount(String id) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            OpdAmount opdAmount = opdAmountRepository.getById(Long.valueOf(id));

            opdAmountRepository.delete(opdAmount);
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }
        return  commonResponse;
    }

    public CommonResponse getAll() {

        CommonResponse commonResponse = new CommonResponse();

        List<OpdAmountDTO> opdAmountDTOS = null;

        try {

            List<OpdAmount> opdAmounts = opdAmountRepository.findAll();
            opdAmountDTOS = castopdAmountsIntoOpdAmountdDTOS(opdAmounts);

            commonResponse.setPayload(Collections.singletonList(opdAmountDTOS));
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }

        return commonResponse;

    }

    private List<OpdAmountDTO> castopdAmountsIntoOpdAmountdDTOS(List<OpdAmount> opdAmounts) {

        List<OpdAmountDTO> opdAmountDTOS = new ArrayList<>();

        for(OpdAmount opdAmount : opdAmounts){

            OpdAmountDTO opdAmountDTO;
            opdAmountDTO = modelMapper.map(opdAmount,OpdAmountDTO.class);
            opdAmountDTOS.add(opdAmountDTO);

        }
        return opdAmountDTOS;
    }
    public Set<OpdAmount> castopdAmountDTOSIntoOpdAmounts(Set<OpdAmountDTO> opdAmountDTOs) {

        Set<OpdAmount> opdAmounts = new HashSet<>();

        for(OpdAmountDTO opdAmountDTO : opdAmountDTOs){

            OpdAmount opdAmount;
            opdAmount = modelMapper.map(opdAmountDTO,OpdAmount.class);
            opdAmounts.add(opdAmount);

        }
        return opdAmounts;
    }

    public CommonResponse getById(String id) {

        CommonResponse commonResponse = new CommonResponse();
        OpdAmountDTO opdAmountDTO =null;

        try {

            OpdAmount opdAmount = opdAmountRepository.findById(Long.valueOf(id)).get();
            opdAmountDTO =modelMapper.map(opdAmount,OpdAmountDTO.class);
            commonResponse.setPayload(Collections.singletonList(opdAmountDTO));
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }

        return  commonResponse;

    }

    public OpdAmount findById(String id){
        return opdAmountRepository.findById(Long.valueOf(id)).get();
    }


}
