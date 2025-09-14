package com.microservice.loan.controller;

import com.microservice.loan.constants.LoansConstants;
import com.microservice.loan.dto.ResponseDto;
import com.microservice.loan.service.ILoansService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class LoanController {

  private final ILoansService iLoansService;

  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createLoan(String mobileNumber){
    iLoansService.createLoan(mobileNumber);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
  }

}
