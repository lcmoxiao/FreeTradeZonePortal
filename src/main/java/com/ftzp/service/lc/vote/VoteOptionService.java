package com.ftzp.service.lc.vote;

import com.ftzp.mapper.lc.vote.VoteOptionMapper;
import com.ftzp.pojo.lc.vote.VoteOption;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VoteOptionService {

    @Resource
    VoteOptionMapper voteOptionMapper;

    public List<Integer> getOIdsByVId(Integer vId) {
        return voteOptionMapper.getOIdsByVId(vId);
    }

    public List<VoteOption> getVoteOptions(List<Integer> oIds) {
        return voteOptionMapper.getVoteOptions(oIds);
    }

    public void insertVoteOptions(VoteOption vos) {
        voteOptionMapper.insertVoteOptions(vos);
    }

    public void deleteVoteOptions(Integer oId) {
        voteOptionMapper.deleteVoteOptions(oId);
    }

    public void addOCount(Integer oId) {
        voteOptionMapper.addOCount(oId);
    }
}
