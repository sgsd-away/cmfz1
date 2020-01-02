package com.baizhi.service;

import com.baizhi.dao.BannerDAO;
import com.baizhi.entity.Banner;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @className BannerServiceImpl
 * @Description
 * @Authour 官帅
 * @Date 2019/12/18  16:22
 */
@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDAO bannerDAO;

    @Override
    public List<Banner> allBanner(Integer page, Integer rows) {
        Banner banner = new Banner();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Banner> banners = bannerDAO.selectByRowBounds(banner, rowBounds);
        return banners;
    }

    @Override
    public Integer totalSize() {
        Banner banner = new Banner();
        Integer totalSize = bannerDAO.selectCount(banner);
        return totalSize;
    }

    @Override
    public Integer totalPage(Integer rows) {
        Banner banner = new Banner();
        Integer totalSize = bannerDAO.selectCount(banner);
        Integer totalPage = totalSize % rows == 0 ? totalSize / rows : totalSize / rows + 1;
        return totalPage;
    }

    @Override
    public void updateBanner(Banner banner) {
        bannerDAO.updateByPrimaryKeySelective(banner);
    }

    @Override
    public void insertBanner(Banner banner) {
        banner.setId(UUID.randomUUID().toString());
        banner.setCreate_date(new Date());
        bannerDAO.insertSelective(banner);
    }

    @Override
    public void deleteBanner(Banner banner) {
        bannerDAO.deleteByPrimaryKey(banner);
    }

    @Override
    public List<Map<String, Object>> selectallBanner() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        List<Banner> banners = bannerDAO.selectAll();
        for (Banner banner : banners) {
            map.put("thumbnail", banner.getCover());
            map.put("desc", banner.getDescription());
            map.put("id", banner.getId());
            list.add(map);
        }
        return list;
    }


}
