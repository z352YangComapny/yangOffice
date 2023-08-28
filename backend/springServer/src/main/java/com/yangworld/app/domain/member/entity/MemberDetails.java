package com.yangworld.app.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@Data
@ToString(callSuper = true) // 부모에 대한 내용까지 toString에 포함!
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder // 부모필드까지 설정가능한 Builder
public class MemberDetails extends Member{
    int totalMemberCount;
}
