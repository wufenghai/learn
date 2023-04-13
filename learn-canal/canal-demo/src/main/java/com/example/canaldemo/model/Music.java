package com.example.canaldemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wfh
 * @create 2023/4/12 15:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Music {

    /**
     * id
     */
    private String id;

    /**
     * 歌名
     */
    private String name;

    /**
     * 歌手名
     */
    private String singer;

    /**
     * 封面图地址
     */
    private String imageUrl;

    /**
     * 歌曲地址
     */
    private String musicUrl;

    /**
     * 歌词地址
     */
    private String lrcUrl;

    /**
     * 歌曲类型id
     */
    private String typeId;

    /**
     * 是否被逻辑删除，1 是，0 否
     */
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}