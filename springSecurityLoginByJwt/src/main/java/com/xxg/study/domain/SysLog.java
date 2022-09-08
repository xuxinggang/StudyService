package com.xxg.study.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_log")
public class SysLog {

    @TableId
    private Long id;

    private String username;

    private String operation;

    private String method;

    private String ip;

    private String params;

    private LocalDate createDate;


}
