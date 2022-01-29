package com.example.fullproject.service;

import com.example.fullproject.dto.ExpenseBillDTO;
import com.example.fullproject.entity.ExpenseBill;
import com.example.fullproject.repository.ExpenseBillRepository;
import com.example.fullproject.util.CommonResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExpenseBillService {

    private ExpenseBillRepository expenseBillRepository;
    private ModelMapper modelMapper;
    private ExpenseService expenseService;

    @Autowired
    public ExpenseBillService(ExpenseBillRepository expenseBillRepository, ModelMapper modelMapper,ExpenseService expenseService) {
        this.expenseBillRepository = expenseBillRepository;
        this.modelMapper = modelMapper;
        this.expenseService = expenseService;
    }


    public CommonResponse saveExpenseBill(ExpenseBillDTO expenseBillDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            ExpenseBill expenseBill = new ExpenseBill();

            expenseBill.setId(Long.valueOf(expenseBillDTO.getId()));
            expenseBill.setParticulars(expenseBillDTO.getParticulars());
            expenseBill.setAmount(Double.valueOf(expenseBillDTO.getAmount()));
            expenseBill.setStatus(expenseBillDTO.getStatus());
            expenseBill.setExpense(expenseService.findById(expenseBillDTO.getExpense()));

            expenseBillRepository.save(expenseBill);
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }

        return commonResponse;
    }

    public CommonResponse updateExpenseBill(String id, ExpenseBillDTO expenseBillDTO) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            ExpenseBill expenseBill = expenseBillRepository.getById(Long.valueOf(id));

            expenseBill.setId(Long.valueOf(expenseBillDTO.getId()));
            expenseBill.setParticulars(expenseBillDTO.getParticulars());
            expenseBill.setAmount(Double.valueOf(expenseBillDTO.getAmount()));
            expenseBill.setStatus(expenseBillDTO.getStatus());
            expenseBill.setExpense(expenseService.findById(expenseBillDTO.getExpense()));

            expenseBillRepository.save(expenseBill);
            commonResponse.setStatus(true);



        }catch (Exception e){
            System.out.println(e);
        }

        return commonResponse;

    }

    public CommonResponse deleteExpenseBill(String id) {

        CommonResponse commonResponse = new CommonResponse();

        try {

            ExpenseBill expenseBill = expenseBillRepository.getById(Long.valueOf(id));

            expenseBillRepository.delete(expenseBill);
            commonResponse.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
        }
        return commonResponse;
    }

    public CommonResponse getAll() {

        CommonResponse commonResponse = new CommonResponse();
        List<ExpenseBillDTO> expenseBillDTOS = null;

        try {
            List<ExpenseBill> expenseBills = expenseBillRepository.findAll();

            expenseBillDTOS = castExpenseBillsIntoExpenseBillDTOS(expenseBills);

            commonResponse.setPayload(Collections.singletonList(expenseBillDTOS));
            commonResponse.setStatus(true);


        }catch (Exception e){
            System.out.println(e);
        }

        return commonResponse;
    }

    private List<ExpenseBillDTO> castExpenseBillsIntoExpenseBillDTOS(List<ExpenseBill> expenseBills) {

        List<ExpenseBillDTO > expenseBillDTOS = new ArrayList<>();

        for(ExpenseBill expenseBill : expenseBills){
            ExpenseBillDTO expenseBillDTO = modelMapper.map(expenseBill,ExpenseBillDTO.class);
            expenseBillDTOS.add(expenseBillDTO);
        }
      return expenseBillDTOS;
    }
    public Set<ExpenseBill> castExpenseBillDTOSIntoExpenseBills(Set<ExpenseBillDTO> expenseBillDTOS) {

        Set<ExpenseBill > expenseBills = new HashSet<>();

        for(ExpenseBillDTO expenseBillDTO : expenseBillDTOS){
            ExpenseBill expenseBill = modelMapper.map(expenseBillDTO,ExpenseBill.class);
            expenseBills.add(expenseBill);
        }
        return expenseBills;
    }

    public CommonResponse getById(String id) {

        CommonResponse commonResponse = new CommonResponse();
        ExpenseBillDTO expenseBillDTO = null;

        try {

            ExpenseBill expenseBill = expenseBillRepository.findById(Long.valueOf(id)).get();

            expenseBillDTO = modelMapper.map(expenseBill,ExpenseBillDTO.class);

            commonResponse.setPayload(Collections.singletonList(expenseBillDTO));
            commonResponse.setStatus(true);


        }catch (Exception e){
            System.out.println(e);
        }

       return commonResponse;
    }

    public ExpenseBill findById(String id){
        return expenseBillRepository.findById(Long.valueOf(id)).get();
    }


}
