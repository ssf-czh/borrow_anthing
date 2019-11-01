package com.czh.Controller;

import com.czh.common.vo.Result;
import com.czh.pojo.Good;
import com.czh.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/good")
@RestController
public class GoodController {
    @Autowired
    private GoodsService goodsService;

//    查询所有空闲物品
    @GetMapping("/findAllGoods")
    public ResponseEntity<Result> findAllGoods(){
        List<Good> goodList = goodsService.findAllGoods();
        Result result = new Result(200,"查询商品成功",goodList);
        return ResponseEntity.ok(result);
    }

}
