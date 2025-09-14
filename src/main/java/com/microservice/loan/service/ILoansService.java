package com.microservice.loan.service;

import org.springframework.stereotype.Service;

public interface ILoansService {

  /**
   *
   * @param mobileNumber - Mobile Number of the Customer
   */
  void createLoan(String mobileNumber);

}
