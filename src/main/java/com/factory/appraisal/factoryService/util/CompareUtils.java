package com.factory.appraisal.factoryService.util;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class CompareUtils {

    /**
     * This method compare the old and new values
     * @param oldValue
     * @param newValue
     * @return boolean value
     */
    public Boolean compareValues(String oldValue, String newValue){
        if(null==oldValue && null==newValue){
            return true;
        }
        else if(null!=oldValue&& null!=newValue) {

            return oldValue.equals(newValue);
        }

        return false;
    }

    /**
     * This method compare the old and new values
     * @param oldValue
     * @param newValue
     * @return boolean value
     */
      public Boolean compareValues(Long oldValue, Long newValue){
          if(null==oldValue && null==newValue){
              return true;
          }
          else if(null!=oldValue&& null!=newValue) {

              return oldValue.equals(newValue);
          }

          return false;
    }

    /**
     * This method compare the old and new values
     * @param oldValue
     * @param newValue
     * @return boolean value
     */

    public Boolean compareValues(Double oldValue, Double newValue){
        if(null==oldValue && null==newValue){
            return true;
        }
        else if(null!=oldValue&& null!=newValue) {

            return oldValue.equals(newValue);
        }

        return false;
    }



    public static Boolean isEmptyMap(Map<?, ?> map) {

        return (map == null || map.isEmpty());
    }
    public static boolean isNotEmptyMap(Map<?, ?> map) {
        return !isEmptyMap(map);
    }

    /**
     * This method check the database values
     * @param dbValue
     * @return val
     */


    public String checkDbVariable(String dbValue) {
        String val;
        if (null != dbValue) {
            val = dbValue;
        } else {
            val = "Null";
        }
        return val;

    }
    public String checkDbVariable(List<String> dbValue) {
        String val;
        if (null != dbValue  && dbValue.size()>0) {

            val = dbValue.toString();
        } else {
            val = "Null";
        }
        return val;

    }

    /**
     * This method check the database values
     * @param dbValue
     * @return val
     */

    public Long checkDbVariable(Long dbValue) {
        Long val;
        if (null != dbValue) {
            val = dbValue;
        } else {
            val = 0L;
        }
        return val;

    }

    /**
     * This method check the database values
     * @param dbValue
     * @return val
     */

    public Double checkDbVariable(Double dbValue) {
        Double val;
        if (null != dbValue) {
            val = dbValue;
        } else {
            val = 0.0;
        }
        return val;
    }

    public List<Date> StringParseToDate(String start,String end) throws ParseException {
        String s1=null;
        String s2=null;
        if(null!=start && null!=end && !end.equals("")){
            s1 =start+" 00:00:00";
            s2 =end+" 23:59:59";
        }else {
            s1 =start+" 00:00:00";
            s2 =start+" 23:59:59";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = dateFormat.parse(s1);
        Date endDate = dateFormat.parse(s2);
        List<Date> dates=new ArrayList<>();
        dates.add(startDate);
        dates.add(endDate);
        return dates;
    }

}
