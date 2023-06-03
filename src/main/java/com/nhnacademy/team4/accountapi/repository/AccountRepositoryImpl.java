//package com.nhnacademy.team4.accountapi.repository;
//
//import com.nhnacademy.team4.accountapi.domain.Account;
//import com.nhnacademy.team4.accountapi.domain.QAccount;
//import com.nhnacademy.team4.accountapi.dto.AccountDTO;
//import com.querydsl.core.types.Projections;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//import org.springframework.data.support.PageableExecutionUtils;
//
//import java.util.List;
//
//public class AccountRepositoryImpl extends QuerydslRepositorySupport implements AccountRepositoryCustom {
//
//    public AccountRepositoryImpl(){
//        super(Account.class);
//    }
//
//
//    @Override
//    public Page<AccountDTO> findAllByPage(Long currentAccont, Pageable pageable) {
//        QAccount account = QAccount.account;
//        List<AccountDTO> allAccounts = from(account)
////                .where(account.accountId.notIn(currentAccont).and(account.status.eq(AccountStauts)))
//                .select(
//                    Projections.bean(
//                            AccountDTO.class,
//                            account.accountId,
//                            account.loginId,
//                            account.email
//                    )
//                )
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize()+1)
//                .fetch();
//
//                return PageableExecutionUtils.getPage(allAccounts, pageable, allAccounts::size);
//    }
//}
