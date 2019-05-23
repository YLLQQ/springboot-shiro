package org.yang.springboot.shiro.model.shiro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * org.yang.springboot.shiro.model.shiro.DefineClaim
 *
 * @author eleven
 * @date 2019/05/23
 */
@Getter
@ToString
@AllArgsConstructor
public class DefineClaim {

    private String key;

    private String value;
}