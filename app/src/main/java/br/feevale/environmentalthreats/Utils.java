package br.feevale.environmentalthreats;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {

    public static class DateUtils{

        public static boolean validateDate(String date){
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try{
                format.parse(date);
                return true;
            }catch (ParseException e){
                return false;
            }
        }
    }

}
