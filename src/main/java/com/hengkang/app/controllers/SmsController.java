package com.hengkang.app.controllers;

import com.hengkang.app.mappers.Mtsend_InfoMapper;
import com.hengkang.app.mappers.Traffic_StatisticsMapper;
import com.hengkang.app.mappers.YsSmsMtMapper;
import com.hengkang.app.mappers.YsSmsSummaryMapper;
import com.hengkang.app.models.Mtsend_Info;
import com.hengkang.app.models.Traffic_Statistics;
import com.hengkang.app.models.YsSmsMt;
import com.hengkang.app.models.YsSmsSummary;
import com.hengkang.app.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */
@Controller
@RequestMapping("/sms")
public class SmsController {
    @Autowired
    private Mtsend_InfoMapper infoMapper;

    @Autowired
    private Traffic_StatisticsMapper trafficMapper;
    @Autowired
    private YsSmsMtMapper mtMapper;
    @Autowired
    private YsSmsSummaryMapper summaryMapper;
    private Logger logger = LoggerFactory.getLogger(SmsController.class);

    private final String user = "fjhb";
    private final Integer userid = 11;
    private final String possword = "fjhb123";

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public int login(String user, String pwd, HttpSession session) {
        if (pwd.equals(possword) && user.equals(this.user)) {
            session.setAttribute("userid", this.userid);
            return 0;
        } else return 4;
    }

    @RequestMapping(value = "loginout", method = RequestMethod.POST)
    @ResponseBody
    public int loginout(HttpSession session) {
        session.removeAttribute("userid");
        return 0;
    }

    @RequestMapping("index")
    public String index(HttpSession session, ModelMap map) {
        Object userid = session.getAttribute("userid");
        if (userid != null)
            return "index";
        return "login";
    }

    @RequestMapping("summary")
    public String summery(HttpSession session, ModelMap map, @RequestParam(value = "page",defaultValue = "1") Integer page, String date, @RequestParam(value = "pageSize",defaultValue = "25") Integer pageSize) {
        Object userid = session.getAttribute("userid");
        if (userid != null) {
            Integer limit = page * pageSize;
            Integer start = (page - 1) * pageSize;
            String sql = "select * from(select a.*,rownum ro from yssmgw_direct_ng.ys_sms_summary a where rownum <=" + limit;
            String sql2 = "select count(*) from yssmgw_direct_ng.ys_sms_summary";
            if (StringUtils.isNotEmpty(date) && !date.equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))) {
                map.put("date", date);
                date = date.replaceAll("-", "");
                sql += " and sdate='" + date + "'";
                sql2 += " where sdate='" + date + "'";
            } else {
                sql += " order by to_date(sdate,'yyyymmdd')  desc nulls last";
                sql2 += " order by to_date(sdate,'yyyymmdd')  desc nulls last";
            }
            sql += ") where ro >" + start;
            Long tiem1 = System.currentTimeMillis();
            List<YsSmsSummary> ysSmsSummaries = summaryMapper.selectByParam(sql);
            System.out.print("耗时" + (System.currentTimeMillis() - tiem1) + "ms\n");
            if (ysSmsSummaries.size() > 0) {
                Integer total = summaryMapper.count(sql2);
                Integer pages = total / pageSize;
                if (total % pageSize > 0)
                    pages += 1;
                map.put("pages", pages);
                map.put("page", page);
                map.put("total", total);
                map.put("infoList", ysSmsSummaries);
                map.put("start", start);
                map.put("limit", limit);
            } else {
                map.put("total", 0);
                map.put("page", 1);
            }
            map.put("pageSize", pageSize);
            return "summary";
        }
        return "login";
    }


    @RequestMapping("traffic")
    public String staffic(HttpSession session, ModelMap map, String date, @RequestParam(value = "page",defaultValue = "1")Integer page, @RequestParam(value = "pageSize",defaultValue = "25") Integer pageSize) {
        Object userid = session.getAttribute("userid");
        if (userid != null) {
            Integer limit = page * pageSize;
            Integer start = (page - 1) * pageSize;
            String sql = "select * from(select a.*,rownum ro from HKSMGATEWAY_SMS.Traffic_Statistics a where rownum <=" + limit;
            String sql2 = "select count(*) from HKSMGATEWAY_SMS.Traffic_Statistics";
            if (StringUtils.isNotEmpty(date) && !date.equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))) {
                map.put("date", date);
                date = date.replaceAll("-", "");
                sql += " and STATDATE ='" + date + "'";
                sql2 += " where STATDATE ='" + date + "'";
            } else {
                sql += " order by to_date(statdate,'yyyymmdd') desc nulls last";
                sql2 += " order by to_date(statdate,'yyyymmdd') desc nulls last";
            }
            sql += ") where ro >" + start;
            Long tiem1 = System.currentTimeMillis();
            List<Traffic_Statistics> traffic_statistics = trafficMapper.selectByParam(sql);
            System.out.print("耗时" + (System.currentTimeMillis() - tiem1) + "ms\n");
            if (traffic_statistics.size() > 0) {
                Integer total = trafficMapper.count(sql2);
                Integer pages = total / pageSize;
                if (total % pageSize > 0)
                    pages += 1;
                map.put("pages", pages);
                map.put("page", page);
                map.put("total", total);
                map.put("infoList", traffic_statistics);
                map.put("start", start);
                map.put("limit", limit);
                map.put("pageSize", pageSize);
            } else {
                map.put("total", 0);
                map.put("page", 1);
            }
            map.put("pageSize", pageSize);
            return "traffic";
        }
        return "login";
    }


    @RequestMapping("mtsend")
    public String mtsend(HttpSession session, ModelMap map, String date, String phone, @RequestParam(value = "page",defaultValue = "1") Integer page, @RequestParam(value = "pageSize",defaultValue = "25") Integer pageSize) {
        Object userid = session.getAttribute("userid");
        if (userid != null) {
            //查询条数
            Integer limit = page * pageSize;
            Integer start = (page - 1) * pageSize;
            String sql = "select oidnew,to_char(putintime,'yyyy-mm-dd HH:mi:ss') timestr1,putintime,phone,msgcont,uc,channelid,pri,pknum,pktotal,state,feenum,submitmsgid,rptstate,rptinfo,to_char(rptrecvtime,'yyyy-mm-dd HH:mi:ss') timestr2,to_char(submittime,'yyyy-mm-dd HH:mi:ss') timestr3,chpri,linkid from(select a.*,rownum ro from HKSMGATEWAY_SMS.mtsend_info";
            String sql2 = "select count(*) from HKSMGATEWAY_SMS.mtsend_info";
            if (StringUtils.isNotEmpty(date) && !date.equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))) {
                map.put("date", date);
                date = date.replaceAll("-", "");
                sql += date;
                sql2 += date;
            }
            sql += " a where";
            if (StringUtils.isNotEmpty(phone)) {
                sql += "  phone='" + phone + "' and";
                sql2 += " where phone='" + phone + "'";
            }
            sql += "  rownum <=" + limit + ") where ro >" + start;
            Long tiem1 = System.currentTimeMillis();
            List<Mtsend_Info> mtsend_infos = infoMapper.selectByParam(sql);
            System.out.print("耗时" + (System.currentTimeMillis() - tiem1) + "ms\n");
            if (mtsend_infos.size() > 0) {
                Integer total = infoMapper.count(sql2);
                Integer pages = total / pageSize;
                if (total % pageSize > 0)
                    pages += 1;
                map.put("pages", pages);
                map.put("page", page);
                map.put("total", total);
                map.put("infoList", mtsend_infos);
                map.put("start", start);
                map.put("limit", limit);
                map.put("pageSize", pageSize);
            } else {
                map.put("total", 0);
                map.put("page", 1);
            }
            map.put("phone", phone);
            map.put("pageSize", pageSize);
            return "mtsend";
        }
        return "login";
    }

    @RequestMapping("ysmt")
    public String ysmt(HttpSession session, ModelMap map, String date, String phone, @RequestParam(value = "page",defaultValue = "1")Integer page, @RequestParam(value = "pageSize",defaultValue = "25") Integer pageSize) {
        Object userid = session.getAttribute("userid");
        if (userid != null) {
            Integer limit = page * pageSize;
            Integer start = (page - 1) * pageSize;
            String sql = "select dst_phone,sms,pksessioinlog,pktotal,pknumber,dst_term,userid,msgid,respmsgid,state,to_char(createtime,'yyyy-mm-dd HH:mi:ss') timestr1,to_char(resptime,'yyyy-mm-dd HH:mi:ss') timestr2,to_char(reporttime,'yyyy-mm-dd HH:mi:ss') timestr3, to_char(reportresp_time,'yyyy-mm-dd HH:mi:ss') timestr5 from (select a.*,rownum ro from ys_sms_mt a where rownum <=" + limit + ") where ro >" + start;
            String sql2 = "select count(*) from ys_sms_mt";
            if (StringUtils.isNotEmpty(date) && !date.equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))) {
                map.put("date", date);
                date = date.replaceAll("-", "");
                sql += "_" + date;
                sql2 += "_" + date;
            }
            if (StringUtils.isNotEmpty(phone)) {
                sql += " where phone='" + phone + "'";
                sql2 += " where phone='" + phone + "'";
            }
            Long tiem1 = System.currentTimeMillis();
            List<YsSmsMt> ysSmsMts = mtMapper.selectByParam(sql);
            System.out.print("耗时" + (System.currentTimeMillis() - tiem1) + "ms\n");
            if (ysSmsMts.size() > 0) {
                Integer total = mtMapper.count(sql2);
                Integer pages = total / pageSize;
                if (total % pageSize > 0)
                    pages += 1;
                map.put("pages", pages);
                map.put("page", page);
                map.put("total", total);
                map.put("infoList", ysSmsMts);
                map.put("start", start);
                map.put("limit", limit);
                map.put("pageSize", pageSize);
            } else {
                map.put("total", 0);
                map.put("page", 1);
            }
            map.put("phone", phone);
            map.put("pageSize", pageSize);
            return "ysmt";
        }
        return "login";
    }

}
