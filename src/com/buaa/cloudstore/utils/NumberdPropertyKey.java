package com.buaa.cloudstore.utils;

/*
 * Created on 2004-4-5
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
import java.util.*;
/*
 * Created on 2004-4-5
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
import java.util.*;

/**
 * 类似A.C1.RouteRuleN的属性键值，后面的N可以是不连续的正整数。
 * 本类还带一个静态工具方法getNumberdPropertyKeys()。
 * @author administrator
 */
public class NumberdPropertyKey {
        /**
         * 该属性的键值（名称）
         */
        public String key = null;
        /**
         * 序号。如A.C1.RouteRuleN中的N。
         */
        public int keyNo = -1;

        public NumberdPropertyKey(String k, int n)
        {
                key = k;
                keyNo = n;
        }

        /**
         * 静态工具方法。从属性集中找到所有键值是prefixNpostfix形式的属性的键值。
         * 按N从小到大排序。
         * @param props: 前缀
         * @param prfix: 后缀
         * @return 所有键值是prefixN形式的属性的键值数组。按N从小到大排序。
         */
        public static NumberdPropertyKey[] getNumberdPropertyKeys(Properties myprops, String prefix,String postfix)
        {
                Vector routeRuleKeys = new Vector();
                Vector routeRuleNos = new Vector();
                if(prefix == null)
                        prefix = "";
                if(myprops != null)
                {
                        Enumeration allkeys = myprops.propertyNames();
                        // 取得所有“prefixN”的属性键值放入routeRuleKeys，按N从小到大排序。
                        while(allkeys.hasMoreElements())
                        {
                                String pkey = (String)allkeys.nextElement();
                                //---modify by zhanghaiqi 2004-9-27 加入了后缀
                                int postfixIndex = pkey.length();
                                if(postfix != null)
                                  postfixIndex = pkey.lastIndexOf(postfix);

                                if(pkey.startsWith(prefix))
                                {
                                        try
                                        {
                                                String rulenokey = pkey.substring((prefix).length(),postfixIndex);

                                                int ruleno = Integer.parseInt(rulenokey);
                                                int i;
                                                for(i=routeRuleNos.size()-1;i>=0;i--)
                                                {
                                                        int pruleno = ((Integer)routeRuleNos.get(i)).intValue();
                                                        if(ruleno > pruleno)
                                                                break;
                                                }
                                                routeRuleKeys.add(i+1,pkey);
                                                routeRuleNos.add(i+1,new Integer(ruleno));
                                        }
                                        catch(IndexOutOfBoundsException e)
                                        {	// substring错，no way。
                                                continue;
                                        }
                                        catch(NumberFormatException e)
                                        {	// Integer.parseInt错，说明不是RouteRuleN属性。
                                                continue;
                                        }
                                }
                        }
                }

                NumberdPropertyKey[] keys = new NumberdPropertyKey[routeRuleKeys.size()];
                for(int i=0;i<routeRuleKeys.size();i++)
                {
                        keys[i] = new NumberdPropertyKey(
                                                                (String)(routeRuleKeys.get(i)),
                                                                ((Integer)(routeRuleNos.get(i))).intValue());
                }

                return keys;
        }

        /**
         * 静态工具方法。从属性集中找到所有键值是prefixN形式的属性的键值。
         * 按N从小到大排序。
         * @param props
         * @param prfix
         * @return 所有键值是prefixN形式的属性的键值数组。按N从小到大排序。
         */
        public static NumberdPropertyKey[] getNumberdPropertyKeys(Properties myprops, String prefix)
        {
          return getNumberdPropertyKeys(myprops,prefix,null);
        }

}
