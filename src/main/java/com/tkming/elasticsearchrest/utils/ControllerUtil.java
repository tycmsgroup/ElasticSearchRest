package com.tkming.elasticsearchrest.utils;

import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSONPObject;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by SEELE on 2017/11/13.
 */
public class ControllerUtil {
    public ControllerUtil() {
    }

    public static Map<String, Object> defaultSuccResult() {
        HashMap result = new HashMap();
        result.put("status", "1");
        result.put("msg", "操作成功");
        return result;
    }

    public static Map<String, Object> defaultErrResult() {
        HashMap result = new HashMap();
        result.put("status", "0");
        result.put("msg", "操作失败");
        return result;
    }

    public static Map<String, Object> defaultNoLoginResult() {
        HashMap result = new HashMap();
        result.put("status", "0");
        result.put("msg", "请登陆");
        return result;
    }

    public static Object jsonpHandler(Object o, String callback) {
        if(StringUtils.isBlank(callback)) {
            return o;
        } else {
            JSONPObject jsonp = new JSONPObject(callback);
            jsonp.addParameter(o);
            return jsonp;
        }
    }
}
