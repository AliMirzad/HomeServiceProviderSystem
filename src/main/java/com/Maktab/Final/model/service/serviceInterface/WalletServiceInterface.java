package com.Maktab.Final.model.service.serviceInterface;

import com.Maktab.Final.model.entity.Customer;
import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.Wallet;

public interface WalletServiceInterface {
	Wallet findWalletById(Integer id);

	Wallet findWalletByExpert(String expert);

	Wallet findWalletByCustomer(String customer);
}
