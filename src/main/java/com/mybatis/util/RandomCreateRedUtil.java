package com.mybatis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * 随机生成红包
 *
 * Created by yunkai on 2017/11/30.
 */
public class RandomCreateRedUtil {

    private static Logger logger = LoggerFactory.getLogger(RandomCreateRedUtil.class);

    public static double[] setRedBag(double money, int num){
        Random random = new Random();
        DecimalFormat format = new DecimalFormat(".##");

        double middle = Double.parseDouble(format.format(money/num));
        double [] bags = new double[num];
        double redMoney = 0;
        double nextMoney = money;
        double sum = 0;
        int index = 0;
        for(int i=num;i>0;i--){
            if(i == 1){
                bags[index] = nextMoney;
            }else{
                while(true){
                    String str = format.format(random.nextDouble()*nextMoney);
                    redMoney = Double.parseDouble(str);
                    if(redMoney>0 && redMoney < middle){
                        break;
                    }
                }
                nextMoney = Double.parseDouble(format.format(nextMoney - redMoney));
                sum = sum + redMoney;
                bags[index] = redMoney;
                middle = Double.parseDouble(format.format(nextMoney/(i-1)));
                index++;
            }
        }
        return bags;
    }
}
