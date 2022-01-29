package com.example.fullproject.service;

import com.example.fullproject.dto.OpdDTO;
import com.example.fullproject.entity.Opd;
import com.example.fullproject.repository.OpdRepository;
import com.example.fullproject.util.CommonResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OpdService {

    private OpdRepository opdRepository;
    private ModelMapper modelMapper;
    private RewardService rewardService;
    private EmployeeService employeeService;
    private OpdBillService opdBillService;

    @Autowired
    @Lazy
    public OpdService(OpdRepository opdRepository, ModelMapper modelMapper,RewardService rewardService,EmployeeService employeeService,OpdBillService opdBillService) {
        this.opdRepository = opdRepository;
        this.modelMapper = modelMapper;
        this.rewardService = rewardService;
        this.employeeService = employeeService;
        this.opdBillService = opdBillService;
    }

    public CommonResponse saveOpd(OpdDTO opdDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            Opd opd = new Opd();

            opd.setId(Long.valueOf(opdDTO.getId()));
            opd.setDate(rewardService.stringToDate(opdDTO.getDate()));
            opd.setEmployee(employeeService.findById(opdDTO.getEmployee()));

 //           opd.setOpdBills(opdBillService.opdBillDTOSIntoOpds(opdDTO.getOpdBills()));

            opdRepository.save(opd);
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }
       return commonResponse;
    }

    public CommonResponse updateOpd(String id, OpdDTO opdDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            Opd opd = opdRepository.getById(Long.valueOf(id));

            opd.setId(Long.valueOf(opdDTO.getId()));
            opd.setDate(rewardService.stringToDate(opdDTO.getDate()));
            opd.setEmployee(employeeService.findById(opdDTO.getEmployee()));

   //         opd.setOpdBills(opdBillService.opdBillDTOSIntoOpds(opdDTO.getOpdBills()));

            opdRepository.save(opd);
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }
        return commonResponse;
    }

    public CommonResponse deleteOpd(String id) {

      CommonResponse commonResponse = new CommonResponse();
      try {

          Opd opd = opdRepository.getById(Long.valueOf(id));
          opdRepository.delete(opd);

          commonResponse.setStatus(true);

      }catch (Exception e){
          System.out.println(e);
      }

     return commonResponse;
    }

    public CommonResponse getAll() {

         CommonResponse commonResponse = new CommonResponse();
        List<OpdDTO> opdDTOS = null;

         try {
             List<Opd> opds = opdRepository.findAll();

             opdDTOS = opdsTOopdDTOS(opds);

             commonResponse.setPayload(Collections.singletonList(opdDTOS));
             commonResponse.setStatus(true);


         }catch (Exception e){
             System.out.println(e);
         }

        return commonResponse;
    }

    private List<OpdDTO> opdsTOopdDTOS(List<Opd> opds) {

        List<OpdDTO> opdDTOS = new ArrayList<>();

        for(Opd opd : opds){
            OpdDTO opdDTO = modelMapper.map(opd,OpdDTO.class);
            opdDTOS.add(opdDTO);
        }
       return  opdDTOS;
    }
    public Set<Opd> opdDTOSTOopds(Set<OpdDTO> opdDTOs) {

        Set<Opd> opds = new HashSet<>();

        for(OpdDTO opdDTO : opdDTOs){
            Opd opd = modelMapper.map(opdDTO,Opd.class);
            opds.add(opd);
        }
        return  opds;
    }

    public CommonResponse getById(String id) {

       CommonResponse commonResponse = new CommonResponse();
       OpdDTO opdDTO = null;

        try {

            Opd opd = opdRepository.findById(Long.valueOf(id)).get();

            opdDTO = modelMapper.map(opd,OpdDTO.class);

            commonResponse.setPayload(Collections.singletonList(opdDTO));
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }
     return  commonResponse;
    }

    public Opd findById(String id){
           return opdRepository.findById(Long.valueOf(id)).get();
    }

}
