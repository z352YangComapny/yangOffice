package com.ssoystory.memberservice.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "member")
public class Authority {
    @Id
    private String authority;

    @Id
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
