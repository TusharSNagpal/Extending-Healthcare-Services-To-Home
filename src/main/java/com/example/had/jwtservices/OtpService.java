package com.example.had.jwtservices;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {
    private static final  Integer EXPIRE_MIN = 20;
    private LoadingCache<String,String> otpCache;
//    @Autowired

    public OtpService() {
        otpCache = CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRE_MIN, TimeUnit.MINUTES)
                .build(new CacheLoader<>() {
                    @Override
                    public String load(String s) {
                        return "";
                    }
                });
    }

    public String generateOtp(String phoneNo){
        Twilio.init("AC8a446114f4673e85a4d0969bef749872", "26fec6cfd6dafcc9c1ba6cf218a32631");
//        PhoneNumber to = new PhoneNumber(phoneNo);
//        PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
        String otp = getRandomOTP(phoneNo);
        String otpMessage = "Dear Customer , Your OTP is " + otp + ".";
//        Message message = Message.creator(new PhoneNumber(phoneNo), new PhoneNumber("+14345954775"),
//                otpMessage).create();
        return  otp;
    }

    private String getRandomOTP(String phoneNo) {
        String otp =  new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
        otpCache.put(phoneNo,otp);
        return otp;
    }
    //get saved otp
    public String getCacheOtp(String key){
        try{
            return otpCache.get(key);
        }catch (Exception e){
            return "";
        }
    }
    //clear stored otp
    public void clearOtp(String key){
        otpCache.invalidate(key);
    }
}
