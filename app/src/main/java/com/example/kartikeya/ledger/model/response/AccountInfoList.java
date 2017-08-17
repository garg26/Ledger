package com.example.kartikeya.ledger.model.response;

import java.util.List;

/**
 * Created by kartikeya on 15/8/17.
 */

public class AccountInfoList {
    private List<AccountInfoResponse> accountInfoResponseList;

    public List<AccountInfoResponse> getAccountInfoResponseList() {
        return accountInfoResponseList;
    }

    public void setAccountInfoResponseList(List<AccountInfoResponse> accountInfoResponseList) {
        this.accountInfoResponseList = accountInfoResponseList;
    }
}
