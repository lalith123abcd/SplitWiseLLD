package com.example.SplitWiseMachineCoding.services;

import com.example.SplitWiseMachineCoding.dto.BalanceDTO;

import java.util.List;

public interface ISettleUpService {

    List<BalanceDTO> settleUpUser(Long userId);
}
