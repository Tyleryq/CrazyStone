package com.itheima.dao;

import com.itheima.model.domain.Comment;
import org.apache.ibatis.annotations.*;
import java.util.List;
/**
 * @Classname CommentMapper
 * @Description TODO
 * @Date 2019-3-14 10:12
 * @Created by CrazyStone
 */

@Mapper
public interface CommentMapper {
    // 分页展示某个文章的评论
    @Select("SELECT * FROM t_comment WHERE article_id=#{aid} ORDER BY id DESC")
    public List<Comment> selectCommentWithPage(Integer aid);

    // 后台查询最新几条评论
    @Select("SELECT * FROM t_comment ORDER BY id DESC")
    public List<Comment> selectNewComment();

    // 发表评论
    @Insert("INSERT INTO t_comment (article_id,created,author,ip,content)" +
            " VALUES (#{articleId}, #{created},#{author},#{ip},#{content})")
    public void pushComment(Comment comment);

    // 站点服务统计，统计评论数量
    @Select("SELECT COUNT(1) FROM t_comment")
    public Integer countComment();

    // 通过文章id删除评论信息
    @Delete("DELETE FROM t_comment WHERE article_id=#{aid}")
    public void deleteCommentWithId(Integer aid);
}

