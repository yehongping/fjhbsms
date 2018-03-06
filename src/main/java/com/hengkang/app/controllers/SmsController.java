package com.hengkang.app.controllers;

import com.hengkang.app.mappers.*;
import com.hengkang.app.models.*;
import com.hengkang.app.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    @Autowired
    private CorpLoginMapper corpLoginMapper;
    private Logger logger = LoggerFactory.getLogger(SmsController.class);

    private final String muser = "fjhb";
    private final String mpwd = "fjhb123";
    private final int mid = 111;

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public int login(String user, String pwd, @RequestParam(value = "remind", defaultValue = "false") Boolean re, @RequestParam(value = "type", defaultValue = "false") Boolean type, HttpSession session) {
        if (type) {
            String sql = "select loginid,password from hksmgateway_sms.corplogin where loginname='" + user + "'";
            List<CorpLogin> corp = corpLoginMapper.selectByParam(sql);
            if (corp.size() > 0) {
                if (pwd.equals(corp.get(0).getPassword())) {
                    session.setAttribute("loginid", corp.get(0).getLoginid());
                    if (re) {
                        //记住密码
                        Cookie uCookie = new Cookie("user", user);
                        Cookie pCookie = new Cookie("pwd", pwd);
                    }
                    session.setAttribute("utype", 1);
                    return 0;
                }
                return 4;
            } else return 5;
        } else {
            if (muser.equals(user) && mpwd.equals(pwd)) {
                session.setAttribute("loginid", mid);
                session.setAttribute("utype", 0);
                return 0;
            }
            return 4;
        }
    }

    @RequestMapping(value = "loginout", method = RequestMethod.POST)
    @ResponseBody
    public int loginout(HttpSession session) {
        session.removeAttribute("loginid");
        session.removeAttribute("utype");
        return 0;
    }

    @RequestMapping("index")
    public String index(HttpSession session, ModelMap map) {
        Object userid = session.getAttribute("loginid");
        Object utype = session.getAttribute("utype");
        if (userid != null) {
            if (utype.equals(0))
                return "index";
            return "uindex";
        }
        return "login";
    }

    @RequestMapping("summary")
    public String summery(HttpSession session, ModelMap map, @RequestParam(value = "page", defaultValue = "1") Integer page, String date, @RequestParam(value = "pageSize", defaultValue = "25") Integer pageSize) {
        Object userid = session.getAttribute("loginid");
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
    public String staffic(HttpSession session, ModelMap map, String date, String date1, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "100") Integer pageSize, Short channal, @RequestParam(value = "sufa", defaultValue = "0") Short sufa) {
        Object userid = session.getAttribute("loginid");
        Object utype = session.getAttribute("utype");
        Integer limit = page * pageSize;
        Integer start = (page - 1) * pageSize;
        String sql = "";
        String sql2 = "";
        if (userid != null) {
            if (utype.equals(1)) {
                sql = "select * from(select a.*,rownum ro from(select type,mtnum,feesuccess,feefailure,statdate,to_char(to_date(statdate,'yyyy/mm/dd'),'yyyy-mm-dd') strdate from HKSMGATEWAY_SMS.Traffic_Statistics  where loginid=" + userid;
                sql2 = "select count(*) from HKSMGATEWAY_SMS.Traffic_Statistics where loginid=" + userid;
            } else {
                sql = "select * from(select a.*,rownum ro from(select type,mtnum,feesuccess,feefailure,statdate,to_char(to_date(statdate,'yyyy/mm/dd'),'yyyy-mm-dd') strdate from HKSMGATEWAY_SMS.Traffic_Statistics where 1=1 ";
                sql2 = "select count(*) from HKSMGATEWAY_SMS.Traffic_Statistics where 1=1";
            }
            if (channal != null || sufa != null) {
                if (channal != null) {
                    map.put("channal", channal);
                    sql += " and type=" + channal;
                    sql2 += " and type=" + channal;
                }
                if (sufa == 0) {
                    map.put("sufa", sufa);
                    sql += " and feefailure>0";
                    sql2 += " and feefailure>0";
                } else if (sufa == 1) {
                    map.put("sufa", sufa);
                    sql += " and feefailure=0";
                    sql2 += " and feefailure=0";
                }
            }
            if (StringUtils.isNotEmpty(date) || StringUtils.isNotEmpty(date1)) {
                map.put("date", date);
                map.put("date1", date1);
                if (StringUtils.isNotEmpty(date) && StringUtils.isEmpty(date1)) {
                    date = date.replaceAll("-", "");
                    sql += " and STATDATE like '" + date + "%'";
                    sql2 += " and STATDATE like '" + date + "%'";
                } else if (StringUtils.isEmpty(date) && StringUtils.isNotEmpty(date1)) {
                    date1 = date1.replaceAll("-", "");
                    sql += " and STATDATE like '" + date1 + "%'";
                    sql2 += " and STATDATE like '" + date1 + "%'";
                } else {
                    //开始结束日期都不为空
                    date = date.replaceAll("-", "");
                    date1 = date1.replaceAll("-", "");
                    Integer a = Integer.parseInt(date);
                    Integer b = Integer.parseInt(date1);
                    if (b > a) {
                        sql += " and  STATDATE between " + date + " and " + date1;
                        sql2 += " and STATDATE between " + date + " and " + date1;
                    } else {
                        sql += " and STATDATE between " + date1 + " and " + date;
                        sql2 += " and STATDATE between " + date1 + " and " + date;
                    }
                }
                sql += "order by statdate desc nulls last) a)";
            } else {
                sql += "  order by statdate desc nulls last) a where rownum <=" + limit + " ) where ro >" + start;
            }

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
                List<Traffic_Statistics> ydinfo = new ArrayList<>();
                List<Traffic_Statistics> ltinfo = new ArrayList<>();
                List<Traffic_Statistics> dxinfo = new ArrayList<>();
                List<Traffic_Statistics> qwinfo = new ArrayList<>();
                for (int i = 0; i < traffic_statistics.size(); i++) {
                    int ty = traffic_statistics.get(i).getType();
                    if (ty == 0 || ty == 4)
                        ydinfo.add(traffic_statistics.get(i));
                    if (ty == 1)
                        ltinfo.add(traffic_statistics.get(i));
                    if (ty == 2)
                        dxinfo.add(traffic_statistics.get(i));
                    if (ty == 3)
                        qwinfo.add(traffic_statistics.get(i));
                }
                map.put("ydinfo", ydinfo);
                map.put("ltinfo", ltinfo);
                map.put("dxinfo", dxinfo);
                map.put("qwinfo", qwinfo);
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
    public String mtsend(HttpSession session, ModelMap map, String date, String phone, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "25") Integer pageSize) {
        Object userid = session.getAttribute("loginid");
        Object utype = session.getAttribute("utype");
        if (userid != null) {
            //查询条数
            Integer limit = page * pageSize;
            Integer start = (page - 1) * pageSize;
            String sql = "", sql2 = "";
            if (utype.equals(1)) {
                sql = "select oidnew,to_char(putintime,'yyyy-mm-dd HH:mi:ss') timestr1,putintime,phone,msgcont,uc,channelid,pri,pknum,pktotal,state,feenum,submitmsgid,rptstate,rptinfo,to_char(rptrecvtime,'yyyy-mm-dd HH:mi:ss') timestr2,to_char(submittime,'yyyy-mm-dd HH:mi:ss') timestr3,chpri,linkid from(select a.*,rownum ro from HKSMGATEWAY_SMS.mtsend_info";
                sql2 = "select count(*) from HKSMGATEWAY_SMS.mtsend_info";
                if (StringUtils.isNotEmpty(date) && !date.equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))) {
                    map.put("date", date);
                    date = date.replaceAll("-", "");
                    sql += date;
                    sql2 += date;
                }
                sql += " a where loginid=" + userid;
                sql2 += " where loginid=" + userid;
                if (StringUtils.isNotEmpty(phone)) {
                    sql += " and phone='" + phone + "'";
                    sql2 += " and phone='" + phone + "'";
                }
                sql += " and rownum <=" + limit + ") where ro >" + start;
            } else if (utype.equals(0)) {
                sql = "select oidnew,to_char(putintime,'yyyy-mm-dd HH:mi:ss') timestr1,putintime,phone,msgcont,uc,channelid,pri,pknum,pktotal,state,feenum,submitmsgid,rptstate,rptinfo,to_char(rptrecvtime,'yyyy-mm-dd HH:mi:ss') timestr2,to_char(submittime,'yyyy-mm-dd HH:mi:ss') timestr3,chpri,linkid from(select a.*,rownum ro from HKSMGATEWAY_SMS.mtsend_info";
                sql2 = "select count(*) from HKSMGATEWAY_SMS.mtsend_info";
                if (StringUtils.isNotEmpty(date) && !date.equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))) {
                    map.put("date", date);
                    date = date.replaceAll("-", "");
                    sql += date;
                    sql2 += date;
                }
                sql += " a where ";
                if (StringUtils.isNotEmpty(phone)) {
                    sql += " phone='" + phone + "' and ";
                    sql2 += " where phone='" + phone + "'";
                }
                sql += " rownum <=" + limit + ") where ro >" + start;
            }
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
    public String ysmt(HttpSession session, ModelMap map, String date, String phone, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "25") Integer pageSize) {
        Object userid = session.getAttribute("loginid");
        Object utype = session.getAttribute("utype");
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

    @RequestMapping("test")
    public String test() {
        return "test";
    }
}
