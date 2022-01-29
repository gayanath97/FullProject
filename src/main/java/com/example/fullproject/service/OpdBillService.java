package com.example.fullproject.service;

import com.example.fullproject.dto.OpdBillDTO;
import com.example.fullproject.entity.OpdBill;
import com.example.fullproject.repository.OpdBillRepository;
import com.example.fullproject.util.CommonResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OpdBillService {

    private OpdBillRepository opdBillRepository;
    private ModelMapper modelMapper;
    private  RewardService rewardService;
    private OpdService opdService;

    @Autowired
    public OpdBillService(OpdBillRepository opdBillRepository, ModelMapper modelMapper,RewardService rewardService,OpdService opdService) {
        this.opdBillRepository = opdBillRepository;
        this.modelMapper = modelMapper;
        this.rewardService = rewardService;
        this.opdService = opdService;
    }

    public CommonResponse saveOpdBill(OpdBillDTO opdBillDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            OpdBill opdBill = new OpdBill();

            opdBill.setId(Long.valueOf(opdBillDTO.getId()));
            opdBill.setAmount(Double.valueOf(opdBillDTO.getAmount()));
            opdBill.setDate(rewardService.stringToDate(opdBillDTO.getDate()));
            opdBill.setParticulars(opdBillDTO.getParticulars());
            opdBill.setStatus(opdBillDTO.getStatus());

            opdBill.setOpd(opdService.findById(opdBillDTO.getOpd()));

            opdBillRepository.save(opdBill);
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }

        return  commonResponse;
    }

    public CommonResponse updateOpdBill(String id, OpdBillDTO opdBillDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            OpdBill opdBill = opdBillRepository.getById(Long.valueOf(id));

            opdBill.setId(Long.valueOf(opdBillDTO.getId()));
            opdBill.setAmount(Double.valueOf(opdBillDTO.getAmount()));
            opdBill.setDate(rewardService.stringToDate(opdBillDTO.getDate()));
            opdBill.setParticulars(opdBillDTO.getParticulars());
            opdBill.setStatus(opdBillDTO.getStatus());

            opdBill.setOpd(opdService.findById(opdBillDTO.getOpd()));

            opdBillRepository.save(opdBill);
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }

        return  commonResponse;


    }

    public CommonResponse deleteOpdBill(String id) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            OpdBill opdBill = opdBillRepository.getById(Long.valueOf(id));

            opdBillRepository.delete(opdBill);

            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }

        return  commonResponse;
    }

    public CommonResponse getAll() {

        CommonResponse commonResponse = new CommonResponse();
        List<OpdBillDTO> opdBillDTOList = null;

        try {

            List<OpdBill> opdBills = opdBillRepository.findAll();

            opdBillDTOList = opdBillsIntoOpdDTOS(opdBills);

            commonResponse.setPayload(Collections.singletonList(opdBillDTOList));
            commonResponse.setStatus(true);


        }catch (Exception e){
            System.out.println(e);
        }
        return  commonResponse;
    }

    private List<OpdBillDTO> opdBillsIntoOpdDTOS(List<OpdBill> opdBills) {

        List<OpdBillDTO> opdBillDTOS = new ArrayList<>();

        for(OpdBill opdBill : opdBills){
            OpdBillDTO opdBillDTO = modelMapper.map(opdBill,OpdBillDTO.class);
            opdBillDTOS.add(opdBillDTO);
        }
      return  opdBillDTOS;
    }

    public Set<OpdBill> opdBillDTOSIntoOpds(Set<OpdBillDTO> opdBillDTOS) {

        Set<OpdBill> opdBills = new HashSet<>();

        for(OpdBillDTO opdBillDTO : opdBillDTOS){
            OpdBill opdBill = modelMapper.map(opdBillDTO,OpdBill.class);
            opdBills.add(opdBill);
        }
        return  opdBills;
    }

    public CommonResponse getById(String id) {

        CommonResponse commonResponse = new CommonResponse();
        OpdBillDTO opdBillDTO = null;

        try {

            OpdBill opdBill = opdBillRepository.findById(Long.valueOf(id)).get();
            opdBillDTO = modelMapper.map(opdBill,OpdBillDTO.class);

            commonResponse.setPayload(Collections.singletonList(opdBillDTO));
            commonResponse.setStatus(true);


        }catch (Exception e){
            System.out.println(e);
        }
        return  commonResponse;
    }
    public OpdBill findById(String id){
        return  opdBillRepository.findById(Long.valueOf(id)).get();
    }
}
