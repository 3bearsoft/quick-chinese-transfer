package com.github.liuyueyi.quick.transfer.test.issues;

import com.github.liuyueyi.quick.transfer.ChineseUtils;

/**
 * @author YiHui
 * @date 2022/12/30
 */
public class Issue31Fix {

    public static void fix31() {
        String text = "把语言设置成简体中文后「碁圣」就直接显示成「棋圣」了，尴尬的是「碁圣」和「棋圣」乃是两个不同的头衔";
        System.out.println("origin:" + text);
        System.out.println("s2t:" + ChineseUtils.s2t(text));
        System.out.println("s2tw:" + ChineseUtils.s2tw(text));
        System.out.println("s2hk:" + ChineseUtils.s2hk(text));
        System.out.println("t2s:" + ChineseUtils.t2s(text));
    }

    public static void main(String[] args) {
        fix31();
    }

}
