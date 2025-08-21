package org.mbc.czo.function.member.security.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import lombok.Builder;
import org.antlr.v4.runtime.misc.NotNull;
import org.mbc.czo.function.member.constant.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class MemberSecurityDTO extends User {

    private String mid;

    private String mname;

    private String mphoneNumber;

    private String memail;  // 회원 검색 처리용

    private String mpassword;

    private String maddress;

    private Set<Role> mroleSet = new HashSet<Role>();

    private Long mmileage;

    private boolean misActivate;

    private boolean misSocialActivate;

    // 생성자
    public MemberSecurityDTO(String username, String password, String email,
                             boolean del, boolean social,
                             String mname,  String mphoneNumber, String maddress, Long mmileage,
                             Collection<? extends GrantedAuthority> authorities) {

        super(username, password, authorities);  // User 객체

        this.mid = username;
        this.mpassword = password;
        this.memail = email;
        this.misActivate = del;
        this.misSocialActivate = social;
        this.mname = mname;
        this.mphoneNumber = mphoneNumber;
        this.maddress = maddress;
        this.mmileage = mmileage;
    }
}
