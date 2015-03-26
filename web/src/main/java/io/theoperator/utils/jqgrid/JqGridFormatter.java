package io.theoperator.utils.jqgrid;

import io.theoperator.utils.Formatter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by andreas on 2/15/15.
 */
public class JqGridFormatter implements Formatter {

    public static Map<String, Object> format(List objs, Integer page, Integer pagesize) {

        Map<String, Object> results = new HashMap<String, Object>(5);
        results.put("total", objs.size()/pagesize + (objs.size()%pagesize == 0?0:1));
        results.put("page", page);
        results.put("records", objs.size());
        results.put("rows", objs);
        return results;

    }
}
