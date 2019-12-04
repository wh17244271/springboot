package wh.utils;

import com.alibaba.fastjson.JSONArray;

import java.util.Random;

public class JapaneseStudy {
    static int wordNum = 3;
    public static final String[] words = {
            "あ  ア", "い   イ", "う   ウ", "え   エ", "お   オ",
            "か  カ", "き   キ", "く   ク", "け   ケ", "こ   コ",
            "さ  サ", "し   シ", "す   ス", "せ   セ", "そ   ソ",
            "た  タ", "ち   チ", "つ   ツ", "て   テ", "と   ト",
            "な  ナ", "に   ニ", "ぬ   ヌ", "ね   ネ", "の   ノ",
            "は  ハ", "ひ   ヒ", "ふ   フ", "へ   へ", "ほ   ホ",
            "ま  マ", "み   ミ", "む   ム", "め   メ", "も   モ"
    };

    public JSONArray get(String[] args) {
        JSONArray array = new JSONArray();
        for (int i = 0; i < wordNum; i++) {
            Random rom = new Random();
            int i1 = rom.nextInt(words.length);
            System.err.println(words[i1]);
            array.add(words[i1]);
        }
        return array;

    }

    public static void main(String[] args) {

    }
}
