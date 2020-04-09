package com.magnoliaory.scullientityoperation.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.magnoliaory.scullientityoperation.common.CommonResult;
import com.magnoliaory.scullientityoperation.mapper.MonitorMapper;
import com.magnoliaory.scullientityoperation.model.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MonitorMapper monitorMapper;

    /**
     * 新增数据,成功后将数据存入缓存
     * @param monitor
     * @return
     */
    public CommonResult<Monitor> addMonitor(Monitor monitor) {
        QueryWrapper<Monitor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("monitor_id", monitor.getMonitorId());
        Monitor exit = monitorMapper.selectOne(queryWrapper);
        if (exit != null) {
            return CommonResult.failed("监控点Id已经存在,请重新输入");
        }
        queryWrapper.clear();
        queryWrapper.eq("host", monitor.getHost())
                .eq("port", monitor.getPort());
        if (monitorMapper.selectOne(queryWrapper) != null) {
            return CommonResult.failed("监控点的主机地址和端口已经存在");
        }
        monitorMapper.insert(monitor);
        String monitorKey = "monitor:" + monitor.getHost()+":"+monitor.getPort();
        redisTemplate.opsForValue().set(monitorKey, monitor);
        return CommonResult.success(monitor, "新增数据成功");
    }



    /**
     * 更新Monitor
     * @param monitor
     * @return
     */
    public boolean update(Monitor monitor) {
        QueryWrapper<Monitor> queryWrapper = new QueryWrapper();
        String monitorId = monitor.getMonitorId();
        queryWrapper.eq("monitor_id", monitorId);
        if (monitorMapper.selectOne(queryWrapper) != null) {
            return false;
        }
        queryWrapper.clear();
        queryWrapper.eq("host", monitor.getHost())
                .eq("port", monitor.getPort());
        if (monitorMapper.selectOne(queryWrapper) != null) {
            return false;
        }
        int success = monitorMapper.update(monitor, null);
        if (success == 1) {
            String key = "monitor:" + monitor.getId();
            redisTemplate.opsForValue().set(key, monitor);
        }
        return success == 1;
    }

    /**
     * 根据Id获取
     * @param id
     * @return
     */
    public Monitor selectMonitorById(Integer id) {
        QueryWrapper<Monitor> wrapper = new QueryWrapper<>();
        wrapper.eq("delete_status", false)
                .eq("id", id);
        String monitorKey = "monitor:" + id;
        Monitor monitor = null;
        if (redisTemplate.hasKey(monitorKey)) {
            monitor = (Monitor) redisTemplate.opsForValue().get(monitorKey);
            return monitor;
        }
        monitor = monitorMapper.selectOne(wrapper);
        redisTemplate.opsForValue().set(monitorKey, monitor);
        return monitor;
    }

    /**
     * 查询所有,未被删除的
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<Monitor> selectAll(Integer pageNum, Integer pageSize) {
        QueryWrapper<Monitor> wrapper = new QueryWrapper<>();
        wrapper.eq("delete_status", false);
        PageHelper.startPage(pageNum, pageSize);
        return monitorMapper.selectList(wrapper);
    }

    /**
     * 根据Host和port查找
     * @param host
     * @param port
     * @return
     */
    public Monitor selectByHostAndPort(String host, Integer port) {
        String monitorKey = "extension:" + host + ":" + port;
        if (redisTemplate.hasKey(monitorKey)) {
            Monitor monitor = (Monitor) redisTemplate.opsForValue().get(monitorKey);
            return monitor;
        }
        QueryWrapper<Monitor> wrapper = new QueryWrapper<>();
        wrapper.eq("host", host)
                .eq("port", port);
        Monitor monitor = monitorMapper.selectOne(wrapper);
        redisTemplate.opsForValue().set(monitorKey, monitor);
        return monitor;
    }

}
