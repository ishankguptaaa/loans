package com.microservice.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanDto {

  private String mobileNumber;
  private String loanNumber;
  private String loanType;
  private int totalLoan;
  private int amountPaid;
  private int outstandingAmount;

}
