package com.xinyu.friday.service;


import com.xinyu.friday.dao.DiscussPostMapper;
import com.xinyu.friday.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussPostService {
    @Autowired
    private DiscussPostMapper discussPostMapper;

    public List<DiscussPost> findDiscussPosts(int usrId, int offset, int limit){
        return discussPostMapper.selectDiscussPosts(usrId, offset,limit);
    }

    public int findDiscussPostRows(int userId){
        return discussPostMapper.selectDiscussPostRows(userId);
    }
}
