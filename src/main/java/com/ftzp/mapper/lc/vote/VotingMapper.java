package com.ftzp.mapper.lc.vote;

import com.ftzp.pojo.lc.vote.Voting;

import java.util.List;

public interface VotingMapper {


    List<Integer> getOIdsByVId(Integer vId);

    Integer getOIdFromVotingByVIdAndVuIP(Voting vt);

    void insertVoting(Voting vt);

    void deleteVotingByVId(Integer vId);

}