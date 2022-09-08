package com.xxg.study.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_menu")
public class Menu {

    @TableId
    private Long id;

    private String userId;

    private String name;

    private String role;


}
