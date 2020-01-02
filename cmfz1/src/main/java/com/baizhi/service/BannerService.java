package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService {
    List<Banner> allBanner(Integer page, Integer rows);

    Integer totalSize();

    Integer totalPage(Integer rows);

    void updateBanner(Banner banner);

    void insertBanner(Banner banner);

    void deleteBanner(Banner banner);

    List<Map<String, Object>> selectallBanner();

}
