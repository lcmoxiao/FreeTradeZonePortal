package com.ftzp.service.lc.vote;

import com.ftzp.mapper.lc.vote.VoteMapper;
import com.ftzp.pojo.lc.vote.Vote;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VoteService {

    @Resource
    VoteMapper voteMapper;


    public List<Vote> getVote(Integer vId) {
        return voteMapper.getVote(vId);
    }

    public void insertVote(Vote v) {
        voteMapper.insertVote(v);
    }

    public void deleteVote(Integer vId) {
        voteMapper.deleteVote(vId);
    }

    public void stopVote(Integer vId) {
        voteMapper.stopVote(vId);
    }
}
