
package com.rtmap.airport.group.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rtmap.airport.group.conf.RedisUtils;
import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.result.ResponseStatusEnum;
import com.rtmap.airport.group.service.IBaggageService;
import com.rtmap.airport.group.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: gaowm
 * @date: 2022/3/7
 * @desc: 行李查询业务处理
 */
@Service
public class BaggageServiceImpl implements IBaggageService {

    private Logger logger = LoggerFactory.getLogger(BaggageServiceImpl.class);

    private static final String BAGGAGE_TOKEN = "BAGGAGE_TOKEN";

    @Value("${userName}")
    private String userName;

    @Value("${password}")
    private String password;

    @Value("${loginUrl}")
    private String loginUrl;

    @Value("${BaggageUrl}")
    private String baggageUrl;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private Environment environment;

    Map<String, String> loginParams = new HashMap();

    {
        loginParams.put("userName", userName);
        loginParams.put("password", password);
    }

    @Override
    public CommonResponse queryBaggage(String bagNo) {
        String[] ens = environment.getActiveProfiles();
        if (ens[0].equals("test") || ens[0].equals("dev")) {
            return defaultBack();
        } else {
            //1.查询redis中token是否存在，存在直接调用接口,不存在登录获取token
            String token = redisUtils.get(BAGGAGE_TOKEN);
            if (token == null) {
                try {
                    String result = HttpUtils.post(loginUrl, JSONObject.toJSONString(loginParams));
                    JSONObject jsonObject = JSONObject.parseObject(result);
                    String code = (String) jsonObject.get("code");
                    if ("000".equals(code)) {
                        /**登录成功,获取token进行缓存*/
                        token = (String) JSONObject.parseObject(JSONObject.toJSONString(jsonObject.get("data"))).get("token");
                        redisUtils.setWithTimeOut(BAGGAGE_TOKEN, token, 60 * 24 * 7);
                    } else
                        return new CommonResponse(ResponseStatusEnum.LoginFailed);
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("baggage login in throw Exception:" + e.getMessage() + e.getLocalizedMessage());
                    return new CommonResponse(ResponseStatusEnum.FAIL);
                }
            }
            //2.调用行李查询接口，成功(code为000)直接封装数据返回前端
            Map<String, String> headers = new HashMap<>();
            Map<String, String> params = new HashMap<>();
            headers.put("token", token);
            params.put("baggagenumber", bagNo);
            try {
                String r = HttpUtils.postHeader(baggageUrl, JSONObject.toJSONString(params), headers);
                JSONObject jsonObject = JSONObject.parseObject(r);
                String code = (String) jsonObject.get("code");
                if ("000".equals(code)) {
                    return getCommonResponse(jsonObject);
                } else if ("002".equals(code)) {
                    //3.如果查询失败,判断code值是否为002（未登录或登录异常）,需要重新登录,刷新缓存token 有效期设置为一周,重新发起一次查询请求.
                    String result = HttpUtils.post(loginUrl, JSONObject.toJSONString(loginParams));
                    JSONObject jb = JSONObject.parseObject(result);
                    String c = (String) jb.get("code");
                    if ("000".equals(c)) {
                        /**登录成功,获取token进行缓存*/
                        token = (String) JSONObject.parseObject(JSONObject.toJSONString(jb.get("data"))).get("token");
                        redisUtils.setWithTimeOut(BAGGAGE_TOKEN, token, 60 * 24 * 7);
                    } else
                        return new CommonResponse(ResponseStatusEnum.LoginFailed);
                    /**重新在查询一次接口*/
                    headers.put("token", token);
                    r = HttpUtils.postHeader(baggageUrl, JSONObject.toJSONString(params), headers);
                    jsonObject = JSONObject.parseObject(r);
                    code = (String) jsonObject.get("code");
                    if ("000".equals(code)) {
                        return getCommonResponse(jsonObject);
                    } else
                        return new CommonResponse(code, (String) jsonObject.get("msg"));
                } else
                    return new CommonResponse(code, (String) jsonObject.get("msg"));
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("baggage query throw Exception:" + e.getMessage() + e.getLocalizedMessage());
                return new CommonResponse(ResponseStatusEnum.FAIL);
            }
        }

    }

    private CommonResponse defaultBack() {
        String result = "{\n" +
                "\t\"code\": \"000\",\n" +
                "\t\"msg\": \"成功\",\n" +
                "\t\"data\": {\n" +
                "\t\t\"message\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\"Address\": null,\n" +
                "\t\t\t\"BaggageStatus\": \"\",\n" +
                "\t\t\t\"BeginTime\": null,\n" +
                "\t\t\t\"CurrentAirport\": \"PKX\",\n" +
                "\t\t\t\"CurrentPlaceID\": \"DXCOB30 \",\n" +
                "\t\t\t\"DeviceNo\": \"0000\",\n" +
                "\t\t\t\"EndTime\": null,\n" +
                "\t\t\t\"FlightDate\": \"2021-12-13T00:00:00+08:00\",\n" +
                "\t\t\t\"FlightNo\": \"MF8114\",\n" +
                "\t\t\t\"NodeName\": \"值机\",\n" +
                "\t\t\t\"OperateArea\": \"\",\n" +
                "\t\t\t\"OperateGate\": \"\",\n" +
                "\t\t\t\"OperateTeminal\": \"\",\n" +
                "\t\t\t\"OperateTime\": \"2021-12-13T12:01:42+08:00\",\n" +
                "\t\t\t\"OperatorID\": \"\",\n" +
                "\t\t\t\"Result\": \"\",\n" +
                "\t\t\t\"SecondCode\": \"G\",\n" +
                "\t\t\t\"SendtoPlaceID\": \"\",\n" +
                "\t\t\t\"TagID\": \"3731520806\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\"Address\": null,\n" +
                "\t\t\t\"BaggageStatus\": \"\",\n" +
                "\t\t\t\"BeginTime\": null,\n" +
                "\t\t\t\"CurrentAirport\": \"PKX\",\n" +
                "\t\t\t\"CurrentPlaceID\": \"DBCTB02 \",\n" +
                "\t\t\t\"DeviceNo\": \"B02\",\n" +
                "\t\t\t\"EndTime\": null,\n" +
                "\t\t\t\"FlightDate\": \"2021-12-13T00:00:00+08:00\",\n" +
                "\t\t\t\"FlightNo\": \"MF8114\",\n" +
                "\t\t\t\"NodeName\": \"安检\",\n" +
                "\t\t\t\"OperateArea\": \"\",\n" +
                "\t\t\t\"OperateGate\": \"\",\n" +
                "\t\t\t\"OperateTeminal\": \"\",\n" +
                "\t\t\t\"OperateTime\": \"2021-12-13T12:04:01+08:00\",\n" +
                "\t\t\t\"OperatorID\": \"\",\n" +
                "\t\t\t\"Result\": \"\",\n" +
                "\t\t\t\"SecondCode\": \"S\",\n" +
                "\t\t\t\"SendtoPlaceID\": \"\",\n" +
                "\t\t\t\"TagID\": \"3731520806\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\"Address\": null,\n" +
                "\t\t\t\"BaggageStatus\": \"\",\n" +
                "\t\t\t\"BeginTime\": null,\"CurrentAirport\": \"PKX\",\n" +
                "\t\t\t\"CurrentPlaceID\": \"DBSTIB \",\n" +
                "\t\t\t\"DeviceNo\": \"RFIDS2\",\n" +
                "\t\t\t\"EndTime\": null,\n" +
                "\t\t\t\"FlightDate\": \"2021-12-13T00:00:00+08:00\",\n" +
                "\t\t\t\"FlightNo\": \"MF8114\",\n" +
                "\t\t\t\"NodeName\": \"分拣\",\n" +
                "\t\t\t\"OperateArea\": \"\",\n" +
                "\t\t\t\"OperateGate\": \"\",\n" +
                "\t\t\t\"OperateTeminal\": \"\",\n" +
                "\t\t\t\"OperateTime\": \"2021-12-13T12:10:33+08:00\",\n" +
                "\t\t\t\"OperatorID\": \"\",\n" +
                "\t\t\t\"Result\": \"\",\n" +
                "\t\t\t\"SecondCode\": \"S\",\n" +
                "\t\t\t\"SendtoPlaceID\": \"\",\n" +
                "\t\t\t\"TagID\": \"3731520806\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\"Address\": null,\n" +
                "\t\t\t\"BaggageStatus\": \"\",\n" +
                "\t\t\t\"BeginTime\": null,\n" +
                "\t\t\t\"CurrentAirport\": \"PKX\",\n" +
                "\t\t\t\"CurrentPlaceID\": \"DSBHA04 \",\"DeviceNo\": \"S62768\",\n" +
                "\t\t\t\"EndTime\": null,\n" +
                "\t\t\t\"FlightDate\": \"2021-12-13T00:00:00+08:00\",\n" +
                "\t\t\t\"FlightNo\": \"MF8114\",\n" +
                "\t\t\t\"NodeName\": \"装车\",\n" +
                "\t\t\t\"OperateArea\": \"\",\n" +
                "\t\t\t\"OperateGate\": \"\",\n" +
                "\t\t\t\"OperateTeminal\": \"\",\n" +
                "\t\t\t\"OperateTime\": \"2021-12-13T12:20:45+08:00\",\n" +
                "\t\t\t\"OperatorID\": \"CZ\",\n" +
                "\t\t\t\"Result\": \"\",\n" +
                "\t\t\t\"SecondCode\": \"R\",\n" +
                "\t\t\t\"SendtoPlaceID\": \"\",\n" +
                "\t\t\t\"TagID\": \"3731520806\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\"Address\": null,\n" +
                "\t\t\t\"BaggageStatus\": \"\",\n" +
                "\t\t\t\"BeginTime\": null,\n" +
                "\t\t\t\"CurrentAirport\": \"PKX\",\n" +
                "\t\t\t\"CurrentPlaceID\": \"DSAC \",\n" +
                "\t\t\t\"DeviceNo\": \"S62768\",\n" +
                "\t\t\t\"EndTime\": null,\n" +
                "\t\t\t\"FlightDate\": \"2021-12-13T00:00:00+08:00\",\n" +
                "\t\t\t\"FlightNo\": \"MF8114\",\n" +
                "\t\t\t\"NodeName\": \"装机\",\n" +
                "\t\t\t\"OperateArea\": \"\",\n" +
                "\t\t\t\"OperateGate\": \"\",\n" +
                "\t\t\t\"OperateTeminal\": \"\",\n" +
                "\t\t\t\"OperateTime\": \"2021-12-13T13:31:30+08:00\",\n" +
                "\t\t\t\"OperatorID\": \"CZ\",\n" +
                "\t\t\t\"Result\": \"\",\n" +
                "\t\t\t\"SecondCode\": \"R\",\n" +
                "\t\t\t\"SendtoPlaceID\": \"\",\n" +
                "\t\t\t\"TagID\": \"3731520806\"\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t}\n" +
                "}\n";
        String noResult ="{\n" +
                "\t\"code\": \"000\",\n" +
                "\t\"msg\": \"成功\",\n" +
                "\t\"data\": null\n" +
                "\t}";
//        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject jsonObject = JSONObject.parseObject(noResult);
        String code = (String) jsonObject.get("code");
        if ("000".equals(code)) {
            return getCommonResponse(jsonObject);
        } else
            return new CommonResponse(code, (String) jsonObject.get("msg"));

    }

    private CommonResponse getCommonResponse(JSONObject jsonObject) {
        Object data = jsonObject.get("data");
        JSONObject jb1 = JSONObject.parseObject(JSONObject.toJSONString(data));
//        processDesc(jb1);
//        jsonObject.put("data", jb1);
        //返回数据
        return jb1 == null ? new CommonResponse() : new CommonResponse(processDesc(jb1));
    }

    /**
     * 加工行李话术
     *
     * @param data 需要获取data目录下的message集合进行数据处理
     */
    private JSONArray processDesc(JSONObject data) {
        JSONArray jsonArray = (JSONArray) data.get("message");
        if (jsonArray != null && jsonArray.size() > 0) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String nodeName = (String) jsonObject.get("NodeName");
                if ("值机".equals(nodeName)) {
                    jsonObject.put("BaggageStatus", "行李托运");
                    jsonObject.put("BaggageStatusDesc", "您的行李已进入行李传输系统，即将进行安全检查。");
                } else if ("安检".equals(nodeName)) {
                    jsonObject.put("BaggageStatus", "行李安检");
                    jsonObject.put("BaggageStatusDesc", "您的行李正在进行安全检查。");
                } else if ("分拣".equals(nodeName)) {
                    jsonObject.put("BaggageStatus", "行李分拣");
                    jsonObject.put("BaggageStatusDesc", "您的行李已完成安全检查和行李分拣，等待行李装车，请放心。");
                } else if ("装车".equals(nodeName)) {
                    jsonObject.put("BaggageStatus", "装车运输");
                    jsonObject.put("BaggageStatusDesc", "您的行李已经乘坐行李车愉快地朝您的航班飞奔而去，请放心。");
                } else if ("装机".equals(nodeName)) {
                    jsonObject.put("BaggageStatus", "行李装机");
                    jsonObject.put("BaggageStatusDesc", "您的行李已登上您的航班，将与您同机抵达目的地，祝您旅途愉快。");
                } else {
                }
            }
//            data.put("message", jsonArray);
        }
        return jsonArray;

    }
}
