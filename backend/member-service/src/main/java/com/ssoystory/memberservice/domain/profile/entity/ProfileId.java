package com.ssoystory.memberservice.domain.profile.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProfileId implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    private Long memberId;
}
