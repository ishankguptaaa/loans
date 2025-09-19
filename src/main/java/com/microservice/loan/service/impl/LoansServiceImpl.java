package com.microservice.loan.service.impl;

import com.microservice.loan.constants.LoansConstants;
import com.microservice.loan.dto.LoansDto;
import com.microservice.loan.entity.Loans;
import com.microservice.loan.exception.LoanAlreadyExistsException;
import com.microservice.loan.exception.ResourceNotFoundException;
import com.microservice.loan.mapper.LoansMapper;
import com.microservice.loan.repository.LoansRepository;
import com.microservice.loan.service.ILoansService;
import java.time.LocalDateTime;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoansServiceImpl implements ILoansService {

  private final LoansRepository loansRepository;

  /**
   * @param mobileNumber - Mobile Number of the Customer
   */
  @Override
  public void createLoan(String mobileNumber) {
    var loans = loansRepository.findByMobileNumber(mobileNumber);
    if (loans.isPresent()) {
      throw new LoanAlreadyExistsException(
          "Loan already registered with given mobile number " + mobileNumber);
    }
    loansRepository.save(createNewLoan(mobileNumber));
  }

  @Override
  public LoansDto fetchLoan(String mobileNumber) {
    var loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
        () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));

    return LoansMapper.mapToLoansDto(loans);

  }

  @Override
  public boolean updateLoan(LoansDto loansDto) {
    log.info("Updating loan with mobile number " + loansDto.getMobileNumber());
    var loans = loansRepository.findByMobileNumber(loansDto.getMobileNumber()).orElseThrow(
        () -> new ResourceNotFoundException("Loan", "mobileNumber", loansDto.getMobileNumber()));

    var updatedLoan = LoansMapper.mapToLoans(loansDto, loans);
    loansRepository.save(updatedLoan);
    return true;
  }

  @Override
  public boolean deleteLoan(String mobileNumber) {
    return false;
  }

  /**
   * @param mobileNumber - Mobile Number of the Customer
   * @return the new loan details
   */
  private Loans createNewLoan(String mobileNumber) {
    var newLoan = new Loans();
    var randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
    newLoan.setLoanNumber(Long.toString(randomLoanNumber));
    newLoan.setMobileNumber(mobileNumber);
    newLoan.setLoanType(LoansConstants.HOME_LOAN);
    newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
    newLoan.setAmountPaid(0);
    newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
    newLoan.setCreatedBy("test");
    newLoan.setCreatedAt(LocalDateTime.now());
    return newLoan;
  }
}
