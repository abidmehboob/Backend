package com.alseyahat.app.commons;

import com.google.common.io.Files;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.servlet.http.Cookie;

import java.util.Random;

public class AppUtils {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static boolean validateFileExtensions(MultipartFile multipartFile) {
        boolean isValid = Boolean.TRUE;
        String extension = Files.getFileExtension(multipartFile.getOriginalFilename());
        if (!multipartFile.getContentType().contains("image/")) {
            isValid = Boolean.FALSE;
        }

        if (!extension.equals("jpg") && !extension.equals("png") && !extension.equals("jpeg") && !extension.equals("gif")) {
            isValid = Boolean.FALSE;
        }
        return isValid;
    }

    public static boolean validateFileExtensions(String extension) {
        boolean isValid = Boolean.TRUE;
        if (!extension.equals("jpg") && !extension.equals("png") && !extension.equals("jpeg") && !extension.equals("gif")) {
            isValid = Boolean.FALSE;
        }
        return isValid;
    }

    public static final String getUserNameFromAuthentication() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static final String getClientTypeFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        OAuth2Request clientToken = oAuth2Authentication.getOAuth2Request();
        return clientToken.getClientId();
    }

    public static String getRndNumber(int length) {
        Random random = new Random();
        int randomNumber = 0;
        boolean loop = true;
        while (loop) {
            randomNumber = random.nextInt();
            if (Integer.toString(randomNumber).length() == length && !Integer.toString(randomNumber).startsWith("-")) {
                loop = false;
            }
        }
        return String.valueOf(randomNumber);
    }

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static Cookie createTokenCookie(final String cookieName, final String token) {
        final Cookie tokenCookie = new Cookie(cookieName, token);
        tokenCookie.setHttpOnly(true);
        tokenCookie.setSecure(true);
        tokenCookie.setPath("/");
        return tokenCookie;
    }
    
    public static Cookie removeTokenCookie(final String cookieName, final String token) {
        final Cookie tokenCookie = new Cookie(cookieName, token);
        tokenCookie.setHttpOnly(true);
        tokenCookie.setSecure(true);
        tokenCookie.setPath("/");
        tokenCookie.setMaxAge(0);
        return tokenCookie;
    }
    
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) 
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
    
    @SuppressWarnings("deprecation")
    public static Timestamp withTZ(Timestamp timestamp, Calendar cal) {
      if (timestamp == null)
        return null;
      int year = timestamp.getYear()+ 1900;
      int month = timestamp.getMonth()+1;
      int day = timestamp.getDate();
      int hour = timestamp.getHours();
      int minute = timestamp.getMinutes();
      int second = timestamp.getSeconds();
      int nanos = timestamp.getNanos();
      int millis = nanos / 1000000;
      nanos = nanos - millis * 1000000;
      Timestamp r = new Timestamp(getMillis(cal, year, month, day, hour, minute, second, millis));
      r.setNanos(nanos + millis * 1000000);
      return r;
    }
    

    private static long getMillis(Calendar cal, int year, int month, int day, int hour, int minute, int second,
        int millis) {
        cal = (Calendar) cal.clone();
        cal.clear();
        cal.setLenient(true);
 
        if (year <= 0) {
            cal.set(Calendar.ERA, GregorianCalendar.BC);
            cal.set(Calendar.YEAR, 1 - year);
        }
        else {
            cal.set(Calendar.ERA, GregorianCalendar.AD);
            cal.set(Calendar.YEAR, year);
        }
 
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, millis);
 
        return cal.getTimeInMillis();
    }
}