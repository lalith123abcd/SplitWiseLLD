package com.example.SplitWiseMachineCoding.controllers;

import com.example.SplitWiseMachineCoding.dto.BalanceDTO;
import com.example.SplitWiseMachineCoding.services.ISettleUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/settle")
@RequiredArgsConstructor
public class SettleUpController {

    private final ISettleUpService settleUpService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BalanceDTO>> settleUser(
            @PathVariable Long userId) {

        List<BalanceDTO> balances = settleUpService.settleUpUser(userId);

        return ResponseEntity.ok(balances);
    }
}