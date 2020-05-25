package com.hwhhhh.fleamarket.controller;

import com.hwhhhh.fleamarket.controller.param.CommentReq;
import com.hwhhhh.fleamarket.controller.param.CommentRespReq;
import com.hwhhhh.fleamarket.domain.CommentDTO;
import com.hwhhhh.fleamarket.domain.pojo.ResponseCode;
import com.hwhhhh.fleamarket.domain.pojo.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/8 10:37
 */
@RestController
@Slf4j
public class CommentController extends BaseController {

    @PostMapping("/comments")
    public ResponseData createComment(@RequestBody CommentReq commentReq) {
        return new ResponseData(ResponseCode.SUCCSEE, this.commentService.createComment(commentReq));
    }

    @PutMapping("/comments/{id}")
    public ResponseData updateComment(@PathVariable("id") long commentId, @RequestBody CommentReq commentReq) {
        return new ResponseData(ResponseCode.SUCCSEE, this.commentService.updateComment(commentId, commentReq));
    }

    @DeleteMapping("/comments/{id}")
    public ResponseData deleteComment(@PathVariable("id") long commentId) {
        this.commentService.deleteComment(commentId);
        return new ResponseData(ResponseCode.SUCCSEE);
    }

    @GetMapping("/commodities/{commodityId}/comments")
    public ResponseData getAllCommentByCommodityId(@PathVariable long commodityId) {
        List<CommentDTO> commentDTO = this.commentService.getCommentsByCommodityId(commodityId);
        return new ResponseData(ResponseCode.SUCCSEE, commentDTO);
    }

    @PostMapping("/responses}")
    public ResponseData createCommentResp(@RequestBody CommentRespReq commentRespReq) {
        return new ResponseData(ResponseCode.SUCCSEE, this.commentService.createCommentResp(commentRespReq));
    }

    @DeleteMapping("/responses/{id}")
    public ResponseData deleteCommentResp(@PathVariable("id") long commentRespId) {
        this.commentService.deleteCommentResp(commentRespId);
        return new ResponseData(ResponseCode.SUCCSEE);
    }

    @RequestMapping("/comments/push")
    public String pushToWeb(@RequestParam String userId) {
        WebSocketServer.sendInfo("你好！", null);
        return "你好";
    }
}
