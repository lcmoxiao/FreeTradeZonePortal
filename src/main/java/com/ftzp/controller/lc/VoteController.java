package com.ftzp.controller.lc;

import com.ftzp.pojo.lc.vote.Vote;
import com.ftzp.pojo.lc.vote.VoteOption;
import com.ftzp.pojo.lc.vote.Voting;
import com.ftzp.service.lc.vote.VoteOptionService;
import com.ftzp.service.lc.vote.VoteService;
import com.ftzp.service.lc.vote.VotingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.ftzp.TimeUtils.nowTime;
import static com.ftzp.controller.lc.LoginController.getRemoteIP;

@Controller
public class VoteController {

    private static final Logger logger = LoggerFactory.getLogger(VoteController.class);

    @Resource
    VoteService vs;
    @Resource
    VotingService vis;
    @Resource
    VoteOptionService vos;

    /*
    获取所有的投票信息
    */
    @RequestMapping(value = "/vote", method = RequestMethod.GET)
    @ResponseBody
    public List<Vote> getVotes() {
        return vs.getVote(null);
    }

    /*
    添加新投票
    v-> vdesc vName vDeadTime
    vo-> odesc
     */
    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    @ResponseBody
    public String insertVote(
            @RequestParam("vo") String[] vo,
            @RequestParam("vdesc") String vdesc,
            @RequestParam("vName") String vName,
            @RequestParam("vDeadTime") String vDeadTime
    ) throws ParseException {
        //添加投票选项
        Vote v = new Vote();
        v.setvPublishTime(nowTime());
        v.setvActive(true);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = sdf.parse(vDeadTime);
        v.setvDeadTime(d);
        v.setVdesc(vdesc);
        v.setvName(vName);
        vs.insertVote(v);
        //添加投票
        for (String s : vo) {
            VoteOption voteOption = new VoteOption();
            voteOption.setoCount(0);
            voteOption.setOdesc(s);
            voteOption.setOvId(v.getvId());
            vos.insertVoteOptions(voteOption);
        }
        logger.info("发起了新的投票");
        if (v.getvId() != null) return "success";
        else return "failure";
    }

    /*
    删除关于该投票的所有信息
    */
    @RequestMapping(value = "/vote/{vId}", method = RequestMethod.DELETE)
    public String deleteVote(@PathVariable("vId") Integer vId) {
        List<Integer> oIds = vos.getOIdsByVId(vId);
        oIds.forEach(it -> vos.deleteVoteOptions(it));
        vs.deleteVote(vId);
        vis.deleteVotingByVId(vId);
        logger.info("删除了投票 vId:" + vId);
        return ("删除了投票vId：" + vId);
    }

    /*
    提前终止投票
   */
    @RequestMapping(value = "/vote/stop/{vId}", method = RequestMethod.PUT)
    @ResponseBody
    public String stopVote(@PathVariable("vId") Integer vId) {
        vs.stopVote(vId);
        logger.info("提前终止了投票 vId:" + vId);
        return "提前终止了投票vId：" + vId;
    }

    /*
    获取该vote的详细投票信息
     */
    @RequestMapping(value = "/voting/{vId}", method = RequestMethod.GET)
    @ResponseBody
    public List<VoteOption> getVoting(
            @PathVariable("vId") Integer vId
    ) {
        List<Integer> oIds = vos.getOIdsByVId(vId);
        return vos.getVoteOptions(oIds);
    }

    /*
    进行投票，提供vId，以及oId即可
     */
    @RequestMapping(value = "/voting/{vId}/{oId}", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String insertVoting(
            @PathVariable("vId") Integer vId,
            @PathVariable("oId") Integer oId,
            HttpServletRequest re
    ) {
        Voting voting = new Voting();
        voting.setVuIP(getRemoteIP(re));
        voting.setoId(oId);
        voting.setvId(vId);
        //判断是不是投过票了，投过了返回之前投的选项ID
        Integer toId = vis.getOIdFromVotingByVIdAndVuIP(voting);
        if (toId != null) return "你已经投过了";
        Vote v = vs.getVote(vId).get(0);
        if (nowTime().after(v.getvDeadTime())) return "时间已经截止了";
        if (!v.getvActive()) return "已经停止投票了";
        vos.addOCount(oId);
        vis.insertVoting(voting);
        logger.info(getRemoteIP(re) + "投了一票 vId:" + vId + " oId:" + oId);
        return "成功投票哦";
    }


}
