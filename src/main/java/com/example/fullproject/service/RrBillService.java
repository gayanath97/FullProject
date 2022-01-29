package com.example.fullproject.service;

import com.example.fullproject.dto.RewardDTO;
import com.example.fullproject.dto.RrBillDTO;
import com.example.fullproject.entity.Reward;
import com.example.fullproject.entity.Rr;
import com.example.fullproject.entity.RrBill;
import com.example.fullproject.repository.RrBillRepository;
import com.example.fullproject.util.CommonResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Service
public class RrBillService {

    private RrBillRepository rrBillRepository;
    private ModelMapper modelMapper;
    private RewardService rewardService;
    private RrService rrService;

    @Autowired
    public RrBillService(RrBillRepository rrBillRepository, ModelMapper modelMapper,RewardService rewardService,RrService rrService) {
        this.rrBillRepository = rrBillRepository;
        this.modelMapper = modelMapper;
        this.rewardService = rewardService;
        this.rrService = rrService;
    }


    public CommonResponse saveRrBill(RrBillDTO rrBillDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            RrBill rrBill = new RrBill();

            rrBill.setId(Long.valueOf(rrBillDTO.getId()));
            rrBill.setAmount(Double.valueOf(rrBillDTO.getAmount()));
            rrBill.setParticulars(rrBillDTO.getParticulars());
            rrBill.setDate(rewardService.stringToDate(rrBillDTO.getDate()));
            rrBill.setRr(rrService.findById(rrBillDTO.getRr()));

            rrBillRepository.save(rrBill);
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }
        return commonResponse;
    }

    public CommonResponse updateRrBill(String id, RrBillDTO rrBillDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            RrBill rrBill = rrBillRepository.getById(Long.valueOf(id));

            rrBill.setId(Long.valueOf(rrBillDTO.getId()));
            rrBill.setAmount(Double.valueOf(rrBillDTO.getAmount()));
            rrBill.setParticulars(rrBillDTO.getParticulars());
            rrBill.setDate(rewardService.stringToDate(rrBillDTO.getDate()));
            rrBill.setRr(rrService.findById(rrBillDTO.getRr()));

            rrBillRepository.save(rrBill);
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }
        return commonResponse;
    }

    public CommonResponse deleteRrBill(String id) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            RrBill rrBill = rrBillRepository.getById(Long.valueOf(id));

            rrBillRepository.delete(rrBill);

            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }

        return commonResponse;
    }

    public CommonResponse getAll() {

        CommonResponse commonResponse = new CommonResponse();
        List<RrBillDTO> rrBillDTOS = null;

        try {

            List<RrBill> rrBills = rrBillRepository.findAll();

            rrBillDTOS = castRrBillIntoRrBillDTOS(rrBills);

            commonResponse.setPayload(Collections.singletonList(rrBillDTOS));
            commonResponse.setStatus(true);


        }catch (Exception e){
            System.out.println(e);
        }
        return commonResponse;
    }

    private List<RrBillDTO> castRrBillIntoRrBillDTOS(List<RrBill> rrBills) {

        List<RrBillDTO> rrBillDTOS = new ArrayList<>();

        for(RrBill rrBill : rrBills){

            RrBillDTO rrBillDTO = modelMapper.map(rrBill,RrBillDTO.class);

            rrBillDTOS.add(rrBillDTO);

        }

     return rrBillDTOS;
}

    public Set<RrBill> castRrBillDTOSIntoRrBills(Set<RrBillDTO> rrBillDTOS) {

        Set<RrBill> rrBills = new HashSet<>();

        for(RrBillDTO rrBillDTO : rrBillDTOS){

            RrBill rrBill = modelMapper.map(rrBillDTO,RrBill.class);

            rrBills.add(rrBill);

        }

        return rrBills;
    }

    public CommonResponse getById(String id) {

        CommonResponse commonResponse = new CommonResponse();
        RrBillDTO rrBillDTO = null;

        try {

            RrBill rrBill = rrBillRepository.findById(Long.valueOf(id)).get();

            rrBillDTO = modelMapper.map(rrBill,RrBillDTO.class);

            commonResponse.setPayload(Collections.singletonList(rrBillDTO));
            commonResponse.setStatus(true);


        }catch (Exception e){
            System.out.println(e);
        }
        return commonResponse;
    }

    public RrBill findById(String id){
        return rrBillRepository.findById(Long.valueOf(id)).get();
    }

}
