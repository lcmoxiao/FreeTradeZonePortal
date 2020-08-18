package com.ftzp.service.lc.vote;

import com.ftzp.mapper.lc.vote.VotingMapper;
import com.ftzp.pojo.lc.vote.Voting;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VotingService {


    @Resource
    VotingMapper votingMapper;


    public Integer getOIdFromVotingByVIdAndVuIP(Voting vt) {
        return votingMapper.getOIdFromVotingByVIdAndVuIP(vt);
    }


    public void insertVoting(Voting vt) {
        votingMapper.insertVoting(vt);
    }


    public void deleteVotingByVId(Integer vId) {
        votingMapper.deleteVotingByVId(vId);
    }
}
