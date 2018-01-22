package com.hengkang.app.controllers;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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

    private final int pageSize = 30;

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
        return "index";
    }

    @RequestMapping("summary")
    public String summery(HttpSession session, ModelMap map, Integer page, String date) {
        Object userid = session.getAttribute("userid");
        if (userid != null) {
            if (page == null) page = 1;
            int limit = page * pageSize;
            int start = (page - 1) * pageSize;
            PageHelper.startPage(page, pageSize);
            String sql = "select * from yssmgw_direct_ng.ys_sms_summary";
            if (StringUtils.isNotEmpty(date))
                sql += "where sdate='" + date + "'";
            else
                sql += " order by to_date(sdate,'yyyymmdd') desc";
            List<YsSmsSummary> ysSmsSummaries = summaryMapper.selectByParam(sql);
            if (ysSmsSummaries.size() > 0) {
                PageInfo<YsSmsSummary> infos = new PageInfo<>(ysSmsSummaries);
                map.put("pages", infos.getPages());
                map.put("page", infos.getPageNum());
                map.put("total", infos.getTotal());
                map.put("infoList", infos.getList());
                map.put("start", start);
                map.put("limit", limit);
            }
        }
        return "summary";
    }


    @RequestMapping("traffic")
    public String staffic(HttpSession session, ModelMap map, String date, Integer page) {
        Object userid = session.getAttribute("userid");
        if (userid != null) {
            if (page == null) page = 1;
            int limit = page * pageSize;
            int start = (page - 1) * pageSize;
            PageHelper.startPage(page, limit);
            String sql = "select * from  HKSMGATEWAY_SMS.Traffic_Statistics";
            if (StringUtils.isNotEmpty(date))
                sql += "where STATDATE ='" + date + "'";
            else
                sql += " order by to_date(statdate,'yyyymmdd') desc";
            List<Traffic_Statistics> traffic_statistics = trafficMapper.selectByParam(sql);
            if (traffic_statistics.size() > 0) {
                PageInfo<Traffic_Statistics> infos = new PageInfo<>(traffic_statistics);
                map.put("pages", infos.getPages());
                map.put("page", infos.getPageNum());
                map.put("total", infos.getTotal());
                map.put("info", infos.getList());
                map.put("start", start);
                map.put("limit", limit);
            }
        }
        return "traffic";
    }


    @RequestMapping("mtsend")
    public String mtsend(HttpSession session, ModelMap map, String date, String phone, Integer page) {
        Object userid = session.getAttribute("userid");
        if (userid != null) {
            if (page == null) page = 1;
            int limit = page * pageSize;
            int start = (page - 1) * pageSize;
            PageHelper.startPage(page, limit);
            String sql = "select * from HKSMGATEWAY_SMS.mtsend_info";
            if (StringUtils.isNotEmpty(date))
                sql += date;
            if (StringUtils.isNotEmpty(phone))
                sql += " where phone='" + phone + "'";
            List<Mtsend_Info> mtsend_infos = infoMapper.selectByParam(sql);
            if (mtsend_infos.size() > 0) {
                PageInfo<Mtsend_Info> infos = new PageInfo<>(mtsend_infos);
                map.put("pages", infos.getPages());
                map.put("page", infos.getPageNum());
                map.put("total", infos.getTotal());
                map.put("info", infos.getList());
                map.put("start", start);
                map.put("limit", limit);
            }
        }
        return "mtsend";
    }

    @RequestMapping("ysmt")
    public String ysmt(HttpSession session, ModelMap map, String date, String phone, Integer page) {
        Object userid = session.getAttribute("userid");
        if (userid != null) {
            if (page == null) page = 1;
            int limit = page * pageSize;
            int start = (page - 1) * pageSize;
            PageHelper.startPage(page, limit);
            String sql = "select * from ys_sms_mt";
            if (StringUtils.isNotEmpty(date))
                sql += date;
            if (StringUtils.isNotEmpty(phone))
                sql += " where dst_phone='" + phone + "'";
            List<YsSmsMt> ysSmsMts = mtMapper.selectByParam(sql);
            if (ysSmsMts.size() > 0) {
                PageInfo<YsSmsMt> infos = new PageInfo<>(ysSmsMts);
                map.put("pages", infos.getPages());
                map.put("page", infos.getPageNum());
                map.put("total", infos.getTotal());
                map.put("info", infos.getList());
                map.put("start", start);
                map.put("limit", limit);
            }
        }
        return "ysmt";
    }

}
