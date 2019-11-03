package com.czh.Controller;

import com.czh.common.utils.JWTUtil;
import com.czh.common.vo.Result;
import com.czh.pojo.Request;
import com.czh.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping("/apply")
    public ResponseEntity<Result> appyFor(Request request, HttpServletRequest httpServletRequest){
        String token =httpServletRequest.getHeader("XW-Token");

        Integer uid = JWTUtil.parseToUid(token);
        //通过token查找发起的uid
        request.setUid(uid);
        //设置申请时间
        long l = System.currentTimeMillis()/1000;
        System.out.println("time: "+l);
        request.setReq_time((int) l);
        //设置初始状态  0是待审核 1是使用中 2是结束
        request.setStatus(0);

        requestService.addApplication(request);
        Result result = new Result(200, "申请成功");
        return ResponseEntity.ok(result);
    }

    @GetMapping("/findApply")
    public ResponseEntity<Result> findUserApplication(HttpServletRequest httpServletRequest){
        String token =httpServletRequest.getHeader("XW-Token");
        //拥有者对应的id pid
        Integer pid = JWTUtil.parseToUid(token);

        List<Request> requestList =  requestService.findApplicationByUid(pid);

        Result result = new Result(200, "查询申请记录成功", requestList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/findBeApply")
    public ResponseEntity<Result> findBeApplication(HttpServletRequest httpServletRequest){
        String token =httpServletRequest.getHeader("XW-Token");
        Integer uid = JWTUtil.parseToUid(token);
        List<Request> requestList = requestService.findBeApplicationByUid(uid);
        Result result = new Result(200, "查询被申请记录成功",requestList);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/handleApply")
    public ResponseEntity<Result> handleApplication(@RequestParam(value = "type",required = true) Integer type
            ,@RequestParam(value = "rid",required = true) Integer rid
            ,@RequestParam(value = "gid",required = true) Integer gid
            ,HttpServletRequest request){
//        type 1表示接受 ， 0表示拒绝
        String token =request.getHeader("XW-Token");
        Integer uid = JWTUtil.parseToUid(token);
        requestService.handleApplication(gid,type,uid,rid);
        Result result = new Result(200, "处理成功");
        return ResponseEntity.ok(result);
    }


}
