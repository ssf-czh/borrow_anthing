package com.czh.Controller;

import com.czh.common.utils.JWTUtil;
import com.czh.common.vo.Result;
import com.czh.pojo.Request;
import com.czh.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    /**
     * 发出申请
     * @param request
     * @param httpServletRequest
     * @return
     */
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

    /**
     * 查找自己申请的记录
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/findApply")
    public ResponseEntity<Result> findUserApplication(HttpServletRequest httpServletRequest){
        String token =httpServletRequest.getHeader("XW-Token");
        //拥有者对应的id pid
        Integer pid = JWTUtil.parseToUid(token);

        List<Request> requestList =  requestService.findApplicationByUid(pid);

        Result result = new Result(200, "查询申请记录成功", requestList);
        return ResponseEntity.ok(result);
    }

    /**
     * 查找该用户所被申请的记录
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/findBeApply")
    public ResponseEntity<Result> findBeApplication(HttpServletRequest httpServletRequest){
        String token =httpServletRequest.getHeader("XW-Token");
        Integer uid = JWTUtil.parseToUid(token);
        List<Request> requestList = requestService.findBeApplicationByUid(uid);
        Result result = new Result(200, "查询被申请记录成功",requestList);
        return ResponseEntity.ok(result);
    }

    /**
     * 处理请求
     * @param type 0拒绝 1同意
     * @param rid
     * @param gid
     * @param request
     * @return
     */
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

    @PostMapping("/returnThing")
    public ResponseEntity<Result> returnThing(@RequestParam(value = "rid",required = true) Integer rid,
                                              HttpServletRequest request){
        String token =request.getHeader("XW-Token");
        Integer uid = JWTUtil.parseToUid(token);

        requestService.returnThing(uid,rid);

        Result result = new Result(200, "处理成功，等待确认！");
        return ResponseEntity.ok(result);
    }

    @PostMapping("/confirmReturn")
    public ResponseEntity<Result> confirmReturn(@RequestParam(value = "rid",required = true) Integer rid,
                                                HttpServletRequest request,
                                                Integer type){

        String token =request.getHeader("XW-Token");
        Integer uid = JWTUtil.parseToUid(token);

        requestService.confirmReturn(uid,rid,type);

        Result result = new Result(200, "确认归还成功");
        return ResponseEntity.ok(result);
    }


}
