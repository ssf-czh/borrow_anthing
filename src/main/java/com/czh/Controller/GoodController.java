package com.czh.Controller;

import com.czh.common.utils.JWTUtil;
import com.czh.common.vo.Result;
import com.czh.pojo.Good;
import com.czh.pojo.User;
import com.czh.service.GoodsService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/good")
@RestController
public class GoodController {
    @Autowired
    private GoodsService goodsService;

//    根据type查询所有空闲物品 还有关键词搜索
    @GetMapping("/findAllGoods")
//    @JsonView(Good.OneView.class)
//    @JsonView(Result.ZeroView.class)
//    ResponseEntity<Result>
    public ResponseEntity<Result> findAllGoods(@RequestParam(required = true,defaultValue = "0") String type,String keyword){
        List<Good> goodList = goodsService.findAllGoods(type,keyword);
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

    //根据uid添加商品
    @PostMapping("/addGood")
    public ResponseEntity<Result> addGood(Good good, HttpServletRequest request){
        String token = request.getHeader("XW-Token");
        Integer uid = JWTUtil.parseToUid(token);
        good.setStatus(2);
        goodsService.addGood(uid,good);
        Result result = new Result(200, "新增物品成功");
        return ResponseEntity.ok(result);
    }


}
