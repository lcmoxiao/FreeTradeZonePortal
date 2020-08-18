package com.ftzp.mapper.lc.vote;

import com.ftzp.pojo.lc.vote.Vote;

import java.util.List;

public interface VoteMapper {

    List<Vote> getVote(Integer vId);

    void insertVote(Vote v);

    void deleteVote(Integer vId);

    void stopVote(Integer vId);
}
