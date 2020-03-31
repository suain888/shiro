package com.dmj.admincommon.pojo.query;

import lombok.Data;
/**
 * @author Suian
 */
@Data
public class BaseQuery {

    private Integer pageNum;

    private Integer pageSize;

    private String keyWord;
}
