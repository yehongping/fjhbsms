package com.hengkang.app.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhuangying on 16/4/11.
 */
public class StatusStringHelper {


    private static final Map<String, String> GZZTMap = new HashMap<String, String>() {{
        put("0", "无");
        put("1", "离职，可立即上岗");
        put("2", "在职，欲换岗位");
        put("3", "应届毕业生");
    }};

    public final static String GZZTCodeToMsg(String msg) {
        if (GZZTMap.containsKey(msg)) {
            return GZZTMap.get(msg);
        }
        return msg;
    }
//月薪范围
    private static final Map<String, String> XZMap = new HashMap<String, String>() {{
        put("1", "面议");
        put("2", "不低于500元");
        put("3", "不低于1000元");
        put("4", "不低于1500元");
        put("5", "不低于2000元");
        put("6", "不低于3000元");
        put("7","不低于4000元");
        put("8","不低于6000元");
        put("9","不低于8000元");
        put("10","不低于10000元");
        put("11","不低于15000元");
        put("12","不低于15000元");
    }};

    public final static String XZCodeToMsg(String msg) {
        if (XZMap.containsKey(msg)) {
            return XZMap.get(msg);
        }
        return msg;
    }

    private static final Map<String, String> XLMap = new HashMap<String, String>() {{
        put("0", "无");
        put("1", "中专及其他");
        put("2", "大专");
        put("3", "本科");
        put("4", "硕士");
        put("5", "博士");
    }};

    public final static String XLCodeToMsg(String msg) {
        if (XLMap.containsKey(msg)) {
            return XLMap.get(msg);
        }
        return msg;
    }

    private static final Map<String, String> XBMap = new HashMap<String, String>() {{
        put("0", "无");
        put("1", "男");
        put("2", "女");
    }};

    public final static String XBCodeToMsg(String code) {
        if (XBMap.containsKey(code)) {
            return XBMap.get(code);
        }
        return code;
    }

    //    是否统招
    private static final Map<String, String> TZMap = new HashMap<String, String>() {{
        put("0", "无");
        put("1", "是");
        put("2", "否");
    }};
    public final static String TZCodeToMsg(String code) {
        if (TZMap.containsKey(code)) {
            return TZMap.get(code);
        }
        return code;
    }

    //    企业类型
    private static final Map<String, String> QYLXMap = new HashMap<String, String>() {{
        put("1", "民营公司");
        put("2", "国企");
        put("3", "政府机关");
        put("4", "事业单位");
        put("5", "非营利机构");
        put("6", "上市公司");
        put("7", "创业公司");
        put("8", "外资(欧美)");
        put("9", "外资(非欧美)");
        put("10", "台资");
        put("11", "其他");
    }};
    public final static String QYLXCodeToMsg(String code) {
        if (QYLXMap.containsKey(code)) {
            return QYLXMap.get(code);
        }
        return code;
    }

    //    企业人数
    private static final Map<String, String> QYRSMap = new HashMap<String, String>() {{
        put("1", "0-10人");
        put("2", "11-20人");
        put("3", "21-100人");
        put("4", "101-500人");
        put("5", "500人以上");
    }};
    public final static String QYRSCodeToMsg(String code) {
        if (QYRSMap.containsKey(code)) {
            return QYRSMap.get(code);
        }
        return code;
    }

    //    行业和岗位

    public final static String HYCodeToMsg(String code) {
        if (HYMap.containsKey(code)) {
            return HYMap.get(code);
        }
        return code;
    }

    public final static String GWCodeToMsg(String code) {
        if (GWMap.containsKey(code)) {
            return GWMap.get(code);
        }
        return code;
    }
//行业类别
    private static final Map<String, String> HYMap = new HashMap<String, String>() {
        {
            put("0100", "IT/通信/电子/互联网");
            put("0200", "金融/银行/保险");
            put("0300", "房产/建筑建设/物业");
            put("0400", "广告/传媒/印刷出版");
            put("0500", "消费零售/贸易/交通物流");
            put("0600", "加工制造/设备仪表");
            put("0700", "咨询中介/教育科研/专业服务");
            put("0800", "医药生物/医疗保健");
            put("0900", "服务业");
            put("1000", "能源/矿产/石油化工");
            put("1100", "政府/非盈利机构/其他");
            put("1200", "销售/市场/客服/贸易");
            put("1300", "生产/质管/技工");
            put("1400", "经营管理/人力资源/行政");
            put("1500", "计算机/电子/电器/仪表/通信");
            put("1600", "财务/金融/保险/银行");
            put("1700", "建筑/房地产/物业管理");
            put("1800", "电气/电力/能源/化工");
            put("1900", "汽车/机械/服装");
            put("2000", "教育/法律/咨询/翻译/医药");
            put("2100", "广告/媒体/艺术/出版");
            put("2200", "服务业");
            put("2300", "其他专业人员");

        }
    };
//    职位类别
    private static final Map<String, String> GWMap = new HashMap<String, String>() {
        {
            put("0101", "计算机软件");
            put("0102", "计算机硬件");
            put("0103", "计算机服务(系统/数据/维护)");
            put("0104", "互联网/电子商务");
            put("0105", "网络游戏");
            put("0106", "通信(设备/运营/增值服务)");
            put("0107", "电子技术/半导体/集成电路");

            put("0201", "金融/银行/证券/基金");
            put("0202", "投资");
            put("0203", "保险");

            put("0301", "房地产开发");
            put("0302", "建筑/建材/工程");
            put("0303", "家居/室内设计/装饰装潢");
            put("0304", "物业管理/商业中心");

            put("0401", "广告/会展/公关");
            put("0402", "媒体/出版/影视/文化");
            put("0403", "印刷/包装/造纸");


            put("0501", "零售/批发");
            put("0502", "贸易/进出口");
            put("0503", "快速消费品(饮食/烟酒/日化)");
            put("0504", "服饰/纺织/皮革");
            put("0505", "家具/家电");
            put("0506", "礼品/玩具/工艺品/收藏品");
            put("0507", "交通/运输/物流");


            put("0601", "机械/设备/重工");
            put("0602", "仪器仪表/工业自动化");
            put("0603", "汽车及零配件");
            put("0604", "办公用品及设备");
            put("0605", "原材料及加工");

            put("0701", "教育/培训");
            put("0702", "学术/科研");
            put("0703", "中介服务");
            put("0704", "专业服务(咨询/财会/法律)");
            put("0705", "检验/检测/认证");

            put("0801", "制药/生物工程");
            put("0802", "医疗/卫生");
            put("0803", "医疗设备/器械");

            put("0901", "酒店/旅游");
            put("0902", "餐饮业");
            put("0903", "娱乐/休闲/体育");
            put("0904", "美容/保健");
            put("0905", "生活服务");

            put("1001", "电力/水利");
            put("1002", "能源/矿产/采掘/冶炼");
            put("1003", "石油/石化/化工");

            put("1101", "政府/公共事业/非营利机构");
            put("1102", "环保");
            put("1103", "农/林/牧/渔");
            put("1104", "跨行业多种经营");
            put("1105", "其他行业");

            put("1201", "销售管理");
            put("1202", "销售人员");
            put("1203", "销售行政及商务");
            put("1204", "市场/策划/公关");
            put("1205", "客服/技术支持");
            put("1206", "贸易/采购");

            put("1301", "生产制造管理");
            put("1302", "质量/安全管理");
            put("1303", "技术工人");

            put("1401", "经营管理");
            put("1402", "人力资源");
            put("1403", "行政/后勤");

            put("1501", "计算机应用");
            put("1502", "互联网/网络");
            put("1503", "计算机硬件");
            put("1504", "IT管理");
            put("1505", "IT品质/技术支持");
            put("1506", "电子/半导体/电器/仪器");
            put("1507", "通信");

            put("1601", "财务/审计/税收");
            put("1602", "证券/金融/投资/银行");
            put("1603", "保险");

            put("1701", "建筑");
            put("1702", "房地产");
            put("1703", "物业管理");

            put("1801", "电气");
            put("1802", "电力/能源");
            put("1803", "化工");

            put("1901", "汽车");
            put("1902", "机械");
            put("1903", "服装/纺织品");

            put("2001", "教育");
            put("2002", "法律");
            put("2003", "咨询");
            put("2001", "翻译");
            put("2002", "医院/医疗");
            put("2003", "制药/医疗器械");

            put("2101", "广告");
            put("2102", "影视/媒体");
            put("2103", "艺术设计");
            put("2104", "新闻/出版");

            put("2201", "百货/零售");
            put("2202", "保安/家政");
            put("2203", "餐饮/旅游/娱乐");
            put("2204", "美容/健身");
            put("2205", "物流/交通/仓储");

            put("2301", "其他专业人员");

        }
    };
//企业福利
    private static final Map<String, String> QYFLMap = new HashMap<String, String>() {
        {
            put("1","五险一金");
            put("2","补充医疗保险");
            put("3","补充公积金");
            put("4","免费班车");
            put("5","员工旅游");
            put("6","交通补贴");
            put("7","餐饮补贴");
            put("8","通讯补贴");
            put("9","专业培训");
            put("10","出国机会");
            put("11","绩效奖金");
            put("12","年终奖金");
            put("13","股票期权");
            put("14","弹性工作");
            put("15","定期体检");
        }
    };
    public static final String QYFLCodeToMsg(String code) {
        if (QYFLMap.containsKey(code)) {
            return QYFLMap.get(code);
        }
        return code;
    }
// 工作经验
    private static final Map<String, String> GZJYMap = new HashMap<String, String>() {
        {
            put("1","应届毕业生");
            put("2","1-2年");
            put("3","2-5年");
            put("4","5-10年");
            put("5","10年以上");
        }
    };
    public static final String GZJYCodeToMsg(String code){
        if(GZJYMap.containsKey(code)){
            return GZJYMap.get(code);
        }
        return code;
    }

//    年龄要求
    private static final Map<String,String> NLYQMap=new HashMap<String, String>(){
    {
        put("1","18-25岁");
        put("2","25-35岁");
        put("3","35-45岁");
        put("4","45-55岁");
        put("5","55-65岁");
    }
   };

    public static final String NLYQCodeToMsg(String code){
        if(NLYQMap.containsKey(code)){
            return NLYQMap.get(code);
        }
        return code;
    }
}
