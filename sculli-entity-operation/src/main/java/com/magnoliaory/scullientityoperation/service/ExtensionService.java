package com.magnoliaory.scullientityoperation.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.magnoliaory.scullientityoperation.mapper.ExtensionMapper;
import com.magnoliaory.scullientityoperation.model.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ExtensionService {

    @Autowired
    private ExtensionMapper extensionMapper;

    public Extension selectExtensionById(Integer id) {
        return extensionMapper.selectById(id);
    }

    /**
     * 根据Host获取Extension
     * @param post
     * @return
     */
    public Extension selectExtensionByHost(String post) {
        if (!StringUtils.isEmpty(post)) {
            QueryWrapper<Extension> extensionQueryWrapper = new QueryWrapper<>();
            extensionQueryWrapper.eq("extension_host", post);
            return extensionMapper.selectOne(extensionQueryWrapper);
        }
        return null;
    }

    
}
