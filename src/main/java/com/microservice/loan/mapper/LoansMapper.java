package com.microservice.loan.mapper;

import com.microservice.loan.dto.LoansDto;
import com.microservice.loan.entity.Loans;

public class LoansMapper {

  public static LoansDto mapToLoansDto(Loans loans) {
    LoansDto loansDto=new LoansDto();
    loansDto.setLoanNumber(loans.getLoanNumber());
    loansDto.setLoanType(loans.getLoanType());
    loansDto.setMobileNumber(loans.getMobileNumber());
    loansDto.setTotalLoan(loans.getTotalLoan());
    loansDto.setAmountPaid(loans.getAmountPaid());
    loansDto.setOutstandingAmount(loans.getOutstandingAmount());
    return loansDto;
  }

  public static Loans mapToLoans(LoansDto loansDto, Loans loans) {
    loans.setLoanNumber(loansDto.getLoanNumber());
    loans.setLoanType(loansDto.getLoanType());
    loans.setMobileNumber(loansDto.getMobileNumber());
    loans.setTotalLoan(loansDto.getTotalLoan());
    loans.setAmountPaid(loansDto.getAmountPaid());
    loans.setOutstandingAmount(loansDto.getOutstandingAmount());
    return loans;
  }

}
