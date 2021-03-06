package com.fh.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.common.ResponseCode;
import com.fh.product.mapper.ProductMapper;
import com.fh.product.model.Product;
import com.fh.product.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public ResponseCode queryIsHotProductList() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper();
        //查询热销产品为1的产品   1：热销 0；不热销
        queryWrapper.eq("isHot",1);
        List<Product> list = productMapper.selectList(queryWrapper);
        return ResponseCode.success(list);
    }

    @Override
    public ResponseCode queryProductList() {
        List<Product> list = productMapper.selectList(null);
        return ResponseCode.success(list);
    }

    //查询商品展示的数据及分页
    @Override
    public ResponseCode queryListPage(long CurrentPage , long pageSize) {
        //初始值 = 当前页数-1*每页条数
        long start = (CurrentPage-1) * pageSize;
        //总条数
        Long totalCount = productMapper.queryTotalCount();

        List<Product> list = productMapper.queryList(start,pageSize);

        //页数
        long totalPage = totalCount%pageSize ==0?totalCount/pageSize:totalCount/pageSize+1;

        Map map = new HashMap();
        map.put("list",list);
        map.put("totalPage",totalPage);

        return ResponseCode.success(map);
    }

    //购物车方法调用商品里service方法  根据id查询该商品是否存在
    @Override
    public Product selectById(Integer productId) {
        return productMapper.selectById(productId);
    }

    //在添加订单详情，出现订单不足时修改库存
    @Override
    public Long updateAmount(Integer id, Integer count) {
        return productMapper.updateAmount(id,count);
    }
}
