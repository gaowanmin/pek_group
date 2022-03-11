package com.rtmap.airport.group.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rtmap.airport.group.conf.RedisUtils;
import com.rtmap.airport.group.entity.User;
import com.rtmap.airport.group.mapper.UserMapper;
import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.result.ResponseStatusEnum;
import com.rtmap.airport.group.service.IUserService;
import com.rtmap.airport.group.util.Aes;
import com.rtmap.airport.group.util.HttpUtils;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: shan
 * @Date:2022/2/16
 * @Description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${miniWechat.appId}")
    private String appId;
    @Value("${miniWechat.appSecret}")
    private String appSecret;
    @Resource
    private RedisUtils redisUtils;

    private static final String AUTH_URL = "https://api.weixin.qq.com/sns/jscode2session";

    @Override
    public CommonResponse login(String code) {
        logger.info("==littleWxLogin==,code:{}", code);
        if (StringUtils.isEmpty(code)) {
            return new CommonResponse(ResponseStatusEnum.PARAMS_ERROR);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("appid", appId);
        params.put("secret", appSecret);
        params.put("grant_type", "authorization_code");
        params.put("js_code", code);
        String result = HttpUtils.get(AUTH_URL, params, null);
        if (!StringUtils.isEmpty(result)) {
            JSONObject resultJson = JSONObject.parseObject(result);
            if (resultJson.containsKey("openid")) {
                Map<String, Object> userInfo = new HashMap<>(2);
                String openId = resultJson.getString("openid");
                userInfo.put("openId", openId);
                //缓存openid和sessionkey 等待手机号数据授权后 判断在入库
                redisUtils.setWithTimeOut(openId, resultJson.getString("session_key"), 24 * 60);
                return new CommonResponse(userInfo);

            } else {
                CommonResponse commonResponse = new CommonResponse(ResponseStatusEnum.FAIL);
                commonResponse.setMsg(resultJson.getString("errmsg"));
                return commonResponse;
            }
        }
        return new CommonResponse(ResponseStatusEnum.FAIL);
    }

    @Override
    public CommonResponse getUserInfo(String openId, String encryptedData, String iv) {
        logger.info("openId : " + openId);
        logger.info("encryptedData : " + encryptedData);
        logger.info("iv : " + iv);
        String sessionKey = redisUtils.get(openId);
        logger.info("sessionKey : " + sessionKey);
        if (StringUtils.isEmpty(sessionKey)) {
            return new CommonResponse(ResponseStatusEnum.FAIL);
        }
        try {
            Aes aes = new Aes();
            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
            if (null != resultByte && resultByte.length > 0) {
                String userInfo = new String(resultByte, "UTF-8");
                logger.info("微信小程解密返回userInfo:" + userInfo);
                JSONObject jsonObject = JSON.parseObject(userInfo);
                return new CommonResponse(jsonObject);
            }
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new CommonResponse(ResponseStatusEnum.FAIL);
    }
}
