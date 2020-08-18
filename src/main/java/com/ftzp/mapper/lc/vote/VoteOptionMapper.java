package com.ftzp.mapper.lc.vote;

import com.ftzp.pojo.lc.vote.VoteOption;

import java.util.List;

public interface VoteOptionMapper {

    List<VoteOption> getVoteOptions(List<Integer> oIds);

    void insertVoteOptions(VoteOption vos);

    void deleteVoteOptions(Integer oId);

    void addOCount(Integer oId);

}
