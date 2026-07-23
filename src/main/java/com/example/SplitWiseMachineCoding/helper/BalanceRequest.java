package com.example.SplitWiseMachineCoding.helper;

import com.example.SplitWiseMachineCoding.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Builder
public class BalanceRequest {

    private User user;;
    private Double balance;


}
