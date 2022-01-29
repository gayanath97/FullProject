package com.example.fullproject.service;

import com.example.fullproject.dto.RrDTO;
import com.example.fullproject.entity.Rr;
import com.example.fullproject.repository.RrRepository;
import com.example.fullproject.util.CommonResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RrService {

    private RrRepository rrRepository;
    private ModelMapper modelMapper;
    private EmployeeService employeeService;
    private RrBillService rrBillService;

    @Autowired
    @Lazy
    public RrService(RrRepository rrRepository, ModelMapper modelMapper,EmployeeService employeeService,RrBillService rrBillService) {
        this.rrRepository = rrRepository;
        this.modelMapper = modelMapper;
        this.employeeService = employeeService;
        this.rrBillService = rrBillService;
    }

    public CommonResponse saveRr(RrDTO rrDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            Rr rr = new Rr();

            rr.setId(Long.valueOf(rrDTO.getId()));
            rr.setExtensionNo(Integer.valueOf(rrDTO.getExtensionNo()));
            rr.setCustomer(rrDTO.getCustomer());
            rr.setLocation(rrDTO.getLocation());
            rr.setStatus(rrDTO.getStatus());

            rr.setEmployee(employeeService.findById(rrDTO.getEmployee()));

      //      rr.setRrBills(rrBillService.castRrBillDTOSIntoRrBills(rrDTO.getRrBills()));

            rrRepository.save(rr);
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }
        return commonResponse;
    }

    public CommonResponse updateRr(String id, RrDTO rrDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            Rr rr = rrRepository.getById(Long.valueOf(id));

            rr.setId(Long.valueOf(rrDTO.getId()));
            rr.setExtensionNo(Integer.valueOf(rrDTO.getExtensionNo()));
            rr.setCustomer(rrDTO.getCustomer());
            rr.setLocation(rrDTO.getLocation());
            rr.setStatus(rrDTO.getStatus());

            rr.setEmployee(employeeService.findById(rrDTO.getEmployee()));

         //   rr.setRrBills(rrBillService.castRrBillDTOSIntoRrBills(rrDTO.getRrBills()));

            rrRepository.save(rr);
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }
        return commonResponse;
    }

    public CommonResponse deleteRr(String id) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            Rr rr = rrRepository.getById(Long.valueOf(id));

            rrRepository.delete(rr);

            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }
        return commonResponse;
    }

    public CommonResponse getAll() {

        CommonResponse commonResponse = new CommonResponse();
        List<RrDTO> rrDTOS = null;

        try {

            List<Rr> rrs = rrRepository.findAll();

            rrDTOS = castRrListIntoRrDTOList(rrs);
            commonResponse.setPayload(Collections.singletonList(rrDTOS));
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }
        return commonResponse;
    }

    private List<RrDTO> castRrListIntoRrDTOList(List<Rr> rrs) {

        List<RrDTO> rrDTOList = new ArrayList<>();

        for (Rr rr : rrs) {
            RrDTO rrDTO;
            rrDTO = modelMapper.map(rr,RrDTO.class);
            rrDTOList.add(rrDTO);
        }

        return rrDTOList;


    }

    public Set<Rr> castRrDTOListIntoRrList(Set<RrDTO> rrDTOSet) {

        Set<Rr> rrList = new HashSet<>();

        for (RrDTO rrDTO : rrDTOSet) {
            Rr rr;
            rr = modelMapper.map(rrDTO,Rr.class);
            rrList.add(rr);
        }

        return rrList;


    }

    public CommonResponse getById(String id) {

        CommonResponse commonResponse =new CommonResponse();

        RrDTO rrDTO =null;


        try {

            Rr rr = rrRepository.findById(Long.valueOf(id)).get();
            rrDTO = modelMapper.map(rr,RrDTO.class);

            commonResponse.setPayload(Collections.singletonList(rrDTO));
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }

        return commonResponse;
    }

    public Rr findById(String id){
        return rrRepository.findById(Long.valueOf(id)).get();
    }



}
