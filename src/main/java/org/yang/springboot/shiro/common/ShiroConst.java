package org.yang.springboot.shiro.common;

import org.yang.springboot.shiro.filter.JwtFilter;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * org.yang.springboot.shiro.config.ShiroConst
 *
 * @author eleven
 * @date 2019/05/23
 */
public class ShiroConst {

    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";

    public static final String JWT_ACCESS = "jwt";

    public static final long TOKEN_EXPIRE_TIME = 5 * 60;

    public static final String TOKEN_CLAIM_KEY = "username";

    public static Map<String, Filter> FILTER_MAP = new HashMap<String, Filter>() {{
        put(JWT_ACCESS, new JwtFilter());
    }};

    public static Map<String, String> ROUTE_FILTER = new LinkedHashMap<String, String>() {{
        put("/login", "anon");
        put("/401", "anon");

        put("/user/admin", "jwt,roles[admin]");
        put("/user/system", "jwt,roles[system]");

        put("/**", JWT_ACCESS);
    }};
}
