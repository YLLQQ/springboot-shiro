package org.yang.springboot.shiro.common;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * org.yang.springboot.shiro.config.ShiroConst
 *
 * @author eleven
 * @date 2019/05/23
 */
public class ShiroConst {

    public static Map<String, String> ROUTE_FILTER = new LinkedHashMap<String, String>() {{
        put("/user/admin", "authc,roles[admin]");
        put("/user/system", "authc,roles[system]");

        put("/**", "authc");
    }};
}
