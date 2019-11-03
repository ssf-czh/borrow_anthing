package com.czh.Controller;

import com.czh.common.vo.Result;
import com.czh.pojo.Good;
import com.czh.pojo.User;
import com.czh.service.GoodsService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/good")
@RestController
public class GoodController {
    @Autowired
    private GoodsService goodsService;

//    根据type查询所有空闲物品
    @GetMapping("/findAllGoods")
//    @JsonView(Good.OneView.class)
//    @JsonView(Result.ZeroView.class)
//    ResponseEntity<Result>
    public ResponseEntity<Result> findAllGoods(@RequestParam(required = true,defaultValue = "0") String type){
        List<Good> goodList = goodsService.findAllGoods(type);
        Result result = new Result(200,"查询商品成功",goodList);
        return ResponseEntity.ok(result);
//        return ResponseEntity.ok(goodList);
    }

//    根据gid寻找商品
    @GetMapping("/findGood")
    public ResponseEntity<Result> findGoodByGid(@RequestParam(required = true) Integer gid){
        Good good = goodsService.findGoodByGid(gid);
        Result result = new Result(200, "查询商品成功", good);
        return ResponseEntity.ok(result);
    }


}
