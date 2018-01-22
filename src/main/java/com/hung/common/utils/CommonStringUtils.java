package com.hung.common.utils;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.text.MessageFormat;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 【TIME-3】文字列操作ユーティリティ.
 *
 * <pre>
 * 文字列操作に関する共通的な処理
 * </pre>
 *
 * @author Mitsui Zosen Systems Research Inc.
 * @version X.X
 * @since TIME-3 X.X
 */
public class CommonStringUtils extends StringUtils {

    /** 空文字. */
    public static final String EMPTY = "";
    /** none. */
    protected static final String NONE_STR = "none";

    /** 正規表現 - 半角英数字. */
    protected static final String REGEX_HALF_NUM_ALPHA = "^[0-9a-zA-Z]+$";

    /** 正規表現 - 半角英数字記号. */
    protected static final String REGEX_HALF_NUM_ALPHA_MARK = "^[0-9a-zA-Z!\"#$%&'()*+,-./:;<=>?@\\[\\]\\^_`{\\|}~]+$";

    /**
     * [不可視] デフォルトコンストラクタ.
     */
    protected CommonStringUtils() {
    }

    /**
     * 対象がNullかどうか判定する.
     *
     * @param target 対象文字列
     * @return True: Null / False: 左記以外
     */
    public static boolean isNull(String target) {
        return target == null;
    }

    /**
     * 対象が非Nullかどうか判定する.
     *
     * @param target 対象文字列
     * @return True: 非Null / False: 左記以外
     */
    public static boolean isNotNull(String target) {
        return !isNull(target);
    }

    /**
     * 対象が空文字かどうか判定する.
     *
     * @param target 対象文字列
     * @return True: 空文字 / False: 左記以外
     */
    public static boolean isEmpty(String target) {
        return isNotNull(target) && target.isEmpty();
    }

    /**
     * 対象が非空文字かどうか判定する.
     *
     * @param target 対象文字列
     * @return True: 非空文字 / False: 左記以外
     */
    public static boolean isNotEmpty(String target) {
        return !isEmpty(target);
    }

    /**
     * 対象がNullまたは空文字かどうか判定する.
     *
     * @param target 対象文字列
     * @return True: Nullまたは空文字 / False: 左記以外
     */
    public static boolean isNullOrEmpty(String target) {
        return isNull(target) || target.isEmpty();
    }

    /**
     * 対象がNullまたは空文字かどうか判定する.
     *
     * <pre>
     * trim()判定付き
     * </pre>
     *
     * @param target 対象文字列
     * @return True: Nullまたは空文字 / False: 左記以外
     */
    public static boolean isNullOrEmptyWithTrim(String target) {
        return isNull(target) || target.isEmpty() || target.trim().isEmpty();
    }

    /**
     * 対象が非Nullかつ非空文字かどうか判定する.
     *
     * @param target 対象文字列
     * @return True: 非Nullかつ非空文字 / False: 左記以外
     */
    public static boolean isNotNullOrEmpty(String target) {
        // TODO INODAI かえられそう…
        return isNotNull(target) && isNotEmpty(target);
    }

    /**
     * 対象が非Nullかつ非空文字かどうか判定する.
     *
     * <pre>
     * trim()判定付き
     * </pre>
     *
     * @param target 対象文字列
     * @return True: 非Nullかつ非空文字 / False: 左記以外
     */
    public static boolean isNotNullOrEmptyWithTrim(String target) {
        return !isNullOrEmptyWithTrim(target);
    }

    /**
     * "none"かどうか判定する.
     *
     * @param target 対象文字列
     * @return True: "none" / False: 左記以外
     */
    public static boolean isEqualNone(String target) {
        return isEqual(target, NONE_STR);
    }

    /**
     * 非"none"かどうか判定する.
     *
     * @param target 対象文字列
     * @return True: 非"none" / False: 左記以外
     */
    public static boolean isNotEqualNone(String target) {
        return !isEqualNone(target);
    }

    /**
     * Nullを空文字に変換する.
     *
     * <p>
     * Nullではない場合はなにもしない
     * </p>
     *
     * @param target 対象文字列
     * @return 空文字または対象文字列
     */
    public static String toEmptyIfNull(String target) {
        if (isNull(target)) {
            return EMPTY;
        }
        return target;
    }

    /**
     * Nullを指定した文字列に変換する.
     *
     * <p>
     * Nullではない場合はなにもしない
     * </p>
     *
     * @param target 対象文字列
     * @param alt 置換文字列
     * @return 置換文字列または対象文字列
     */
    public static String toAltIfNull(String target, String alt) {
        if (isNull(target)) {
            return alt;
        }
        return target;
    }

    /**
     * Nullまたは空文字を指定した文字列に変換する.
     *
     * <p>
     * Nullまたは空文字ではない場合はなにもしない
     * </p>
     *
     * @param target 対象文字列
     * @param alt 置換文字列
     * @return 置換文字列または対象文字列
     */
    public static String toAltIfNullOrEmpty(String target, String alt) {
        if (isNullOrEmpty(target)) {
            return alt;
        }
        return target;
    }

    /**
     * 対象を左づめする.
     *
     * <p>
     * 対象文字列長 >= 指定長の場合、何もしない
     * </p>
     *
     * @param value 対象文字列
     * @param length 指定長
     * @param c Padding文字
     * @return 変換後文字列
     */
    public static String leftPadding(String value, int length, char c) {
        return leftPad(toEmptyIfNull(value), length, c);
    }

    /**
     * 対象を右づめする.
     *
     * <p>
     * 対象文字列長 >= 指定長の場合、何もしない
     * </p>
     *
     * @param value 対象文字列
     * @param length 指定長
     * @param c Padding文字
     * @return 変換後文字列
     */
    public static String rightPadding(String value, int length, char c) {
        return rightPad(toEmptyIfNull(value), length, c);
    }

    /**
     * 全角ひらがなを全角カタカナに変換する.
     *
     * @param target 対象文字列
     * @return 変換後文字列
     */
    public static String fullHiraToFullKana(String target) {
        StringBuffer sb = new StringBuffer(target);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if ((c >= 'ぁ') && (c <= 'ん')) {
                sb.setCharAt(i, (char) (c - 'ぁ' + 12449));
            }
        }
        return sb.toString();
    }

    /**
     * 文字列同士が同等かどうか判定する.
     *
     * <pre>
     * 以下のルールに従う
     * ・両方とも Null or Empty -> 同等
     * ・片方だけ Null or Empty -> 異なる
     * </pre>
     *
     * @param target1 文字列1
     * @param target2 文字列2
     * @return True: 同等 / False: 非同等
     */
    public static boolean isEqual(String target1, String target2) {

        // 両方Null or Empty -> 同等
        if (isNullOrEmpty(target1) && isNullOrEmpty(target2)) {
            return true;
        }

        // 片方Null or Empty -> 異なる
        if ((isNullOrEmpty(target1) && isNotNullOrEmpty(target2))
                || (isNotNullOrEmpty(target1) && isNullOrEmpty(target2))) {
            return false;
        }

        return target1.equals(target2);
    }

    /**
     * 文字列同士が同等かどうか判定する.
     *
     * <pre>
     * trim()判定付き
     * 以下のルールに従う
     * ・両方とも Null or Empty -> 同等
     * ・片方だけ Null or Empty -> 異なる
     * </pre>
     *
     * @param target1 文字列1
     * @param target2 文字列2
     * @return True: 同等 / False: 非同等
     */
    public static boolean isEqualWithTrim(String target1, String target2) {

        // 両方Null or Empty -> 同等
        if (isNullOrEmptyWithTrim(target1) && isNullOrEmptyWithTrim(target2)) {
            return true;
        }

        // 片方Null or Empty -> 異なる
        if ((isNullOrEmptyWithTrim(target1) && isNotNullOrEmptyWithTrim(target2))
                || (isNotNullOrEmptyWithTrim(target1) && isNullOrEmptyWithTrim(target2))) {
            return false;
        }

        return target1.equals(target2);
    }

    /**
     * 文字列同士が非同等かどうか判定する.
     *
     * @see CommonStringUtils#isEqual(String, String)
     * @param target1 文字列1
     * @param target2 文字列2
     * @return True: 非等価 / False: 等価
     */
    public static boolean isNotEqual(String target1, String target2) {
        return !isEqual(target1, target2);
    }

    /**
     * 数値(Integer)に変換する.
     *
     * <pre>
     * 変換に失敗した場合,Nullを返却
     * </pre>
     *
     * @param target 対象文字列
     * @return 数値化文字列
     */
    public static Integer toInteger(String target) {
        try {
            return Integer.parseInt(target);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 数値(BigDecimal)に変換する.
     *
     * <pre>
     * 変換に失敗した場合,Nullを返却
     * </pre>
     *
     * @param target 対象文字列
     * @return 数値化文字列
     */
    public static BigDecimal toBigDecimal(String target) {
        try {
            return new BigDecimal(target);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 数値(BigDecimal)に変換する.
     *
     * <pre>
     * 変換に失敗した場合,0を返却
     * </pre>
     *
     * @param target 対象文字列
     * @return 数値化文字列
     */
    public static BigDecimal convertToBigDecimal(String target) {
        BigDecimal bigDecimalnum = toBigDecimal(target);
        return bigDecimalnum == null ? new BigDecimal(0) : bigDecimalnum;
    }

    /**
     * 半角カナチェック<br>
     *
     * @param String input - チェック対象の文字列
     * @return boolean - 判定(true:チェックOK / false:チェックNG)
     */
    public static boolean isHankakuKana(String input) {

        final String HANKAKU_KANA = "ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿ" +
                "ﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝｧｨｩｪｫｯｬｭｮﾞﾟｰ ";

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (-1 == HANKAKU_KANA.indexOf(c)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 半角カナチェック<br>
     *
     * @param String str - チェック対象の文字列
     * @return boolean - 判定(true:チェックOK / false:チェックNG)
     */
    public static boolean isHalfSize(String str) {
        byte[] bytes;
        bytes = str.getBytes();

        int beams = str.length();

        StringBuffer sb = new StringBuffer(str);

        for (int i = 0; i < str.length(); i++) {
            // 改行コードは対象外とする
            if ('\n' == sb.charAt(i)) {
                beams = beams - 2;
            }
        }

        // lengthとバイト数が異なる場合は全角が含まれている
        if (beams != bytes.length) {
            return false;
        }

        return true;
    }

    /**
     * 全角入力チェック<br>
     *
     * @param String str - チェック対象の文字列
     * @return boolean - 判定(true:チェックOK / false:チェックNG)
     */
    public static boolean isFullSize(String str) {
        byte[] bytes;
        bytes = str.getBytes();

        // lengthを２倍する
        int beams = str.length() * 2;

        StringBuffer sb = new StringBuffer(str);

        for (int i = 0; i < str.length(); i++) {
            // 改行コードは対象外とする
            if ('\n' == sb.charAt(i)) {
                beams = beams - 2;
            }
        }

        // lengthの２倍とバイト数が異なる場合は半角が含まれている
        if (beams != bytes.length) {
            return false;
        }

        return true;
    }

    /**
     * 文字列が半角英数記号のみで構成されているかをチェックする.
     *
     * @param target 評価対象
     * @return True : 半角英数記号のみ / False : 左記以外
     */
    public static boolean isDigitAlphabetMarkCheck(String target) {
        return Pattern.compile(REGEX_HALF_NUM_ALPHA_MARK).matcher(target).matches();
    }

    /**
     * 文字列が半角英数のみで構成されているかをチェックする.
     *
     * @param target 評価対象
     * @return True : 半角英数のみ / False : 左記以外
     */
    public static boolean isDigitAlphabetCheck(String target) {
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            // 数字でない && 小文字アルファベットでない
            if ((c < '0' || c > '9') &&
                    (c < 'a' || c > 'z') && (c < 'A' || c > 'Z')) {
                return false;
            }
        }
        return true;
    }

    /**
     * 文字列が半角数字のみで構成されているかをチェックする。<br>
     * ※B03_004 累計訂正時のエラーチェック<br>
     *
     * @param String input - チェック対象の文字列
     * @return boolean - 判定(true:半角英数のみ / false:それ以外)
     */
    public static boolean isDigitNumericCheck(String input) {
        // 空白チェック
        if (isNullOrEmpty(input)) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * 文字列が半角数字、ハイフォン、小数点のみで構成されているかをチェックする。<br>
     * ※B03_004 累計訂正時のエラーチェック<br>
     *
     * @param String input - チェック対象の文字列
     * @return boolean - 判定(true:半角英数のみ / false:それ以外)
     */
    public static boolean isDigitNumericCheckModify(String input) {
        // 空白チェック
        if (isNullOrEmpty(input)) {
            return false;
        }
        // {.} / {-} チェック
        if (input.trim().equals("-") || input.trim().equals(".")) {
            return false;
        }
        // {.} 文頭文末チェック
        if (input.charAt(0) == '.' || input.charAt(input.length() - 1) == '.') {
            return false;
        }
        // 小数点使用フラグ
        boolean pointflg = false;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c < '0' || c > '9') {
                if (c != '.' && c != '-') {
                    return false;
                } else {
                    // マイナスは文頭のみ許すようにする
                    if (i != 0 && c == '-') {
                        return false;
                    }
                    // 小数点は一つのみ許す
                    if (c == '.') {
                        if (!pointflg) {
                            pointflg = true;
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * 文字列が小数で入力されていないかチェックする。<br>
     *
     * @param String input - チェック対象の文字列
     * @return boolean - 判定(true:小数ではない / false:小数)
     */
    public static boolean isDigitPointCheck(String input) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '.') {
                // << 数字でない >>
                return false;
            }
        }
        return true;
    }

    /**
     * 文字列が整数もしくは小数であるかをチェックする。<br>
     *
     * @param String input - チェック対象の文字列
     * @return boolean - 判定(true:整数もしくは小数 / false:整数もしくは小数ではない)
     */
    public static boolean isPointCheck(String input) {
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= '0' && c <= '9' || c == '.') {
                if (c == '.') {
                    count++;
                }
            } else {
                return false;
            }
        }
        if (count > 1) {
            return false;
        }
        return true;
    }

    /**
     * 文字列が整数もしくはハイフンであるかをチェックする。<br>
     *
     * @param String input - チェック対象の文字列
     * @return boolean - 判定(true:整数もしくはハイフン / false:整数もしくはハイフンではない)
     */
    public static boolean isTellNumberCheck(String input) {
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= '0' && c <= '9' || c == '-') {
                if (c == '.') {
                    count++;
                }
            } else {
                return false;
            }
        }
        if (count > 1) {
            return false;
        }
        return true;
    }

    /**
     * 小数点以下の桁数をチェックする。<br>
     * (小数点以下がなければtrueを返す)<br>
     *
     * @param String input - チェック対象の文字列
     * @param int length - 小数点以下の桁数
     * @return boolean - 判定(true:小数点以下の桁数が同じ / false:小数点以下の桁数が同じではない)
     */
    public static boolean isDigitPointLengthCheck(String input, int length) {
        int ii = 0;
        if (!isDigitPointCheck(input)) {
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (c == '.') {
                    ii = 1;
                } else {
                    if (0 < ii) {
                        ii = ii + 1;
                    }
                }
            }
        }
        if (ii == 0)
            return true;
        if (ii - 1 > length) {
            return false;
        }
        return true;
    }

    /**
     * 整数の桁数をチェックする。<br>
     * (文字列の長さが0ならばtrueを返す)<br>
     *
     * @param String input - チェック対象の文字列
     * @param int length - 整数の桁数
     * @return boolean - 判定(true:整数の桁数が指定桁数以内 / false:整数の桁数が指定桁数以内ではない)
     */
    public static boolean isIntegerLengthCheck(String input, int length) {
        int i = 0;
        // 半角数字、ハイフォン、小数点のみで構成されていなかったらfalseを返す
        if (!isDigitNumericCheckModify(input))
            return false;

        if (input.charAt(0) == '-') {
            i = i + 1;
        }
        while (i < input.length()) {
            char c = input.charAt(i);
            if (c == '.') {
                break;
            }
            i = i + 1;
        }
        if (i == 0)
            return true;
        if (i > length) {
            return false;
        }
        return true;
    }

    /**
     * 数値(浮動小数)チェック<br>
     *
     * @param String input - チェック対象の文字列
     * @param int length - 整数の桁数
     * @return boolean - 判定(true:数値 / false:数値ではない)
     */
    public static boolean isDoubleCheck(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * コードの長さをチェックする。<br>
     * (文字の数を比べる)<br>
     *
     * @param String str - チェック対象の文字列
     * @param int length - 桁数
     * @return boolean - 判定(true / false)
     */
    public static boolean isMaxStrLength(String str, int length) {
        if (isNullOrEmpty(str)) {
            return true;
        }

        if (str.length() <= length) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * コードの長さをチェックする.
     *
     * <pre>
     * バイト数を比べる
     * </pre>
     *
     * @param str チェック対象の文字列
     * @param maxLength 桁数
     * @return TRUE(maxLength桁数以下)/FALSE(maxLength桁数より以上)
     */
    public static boolean isMaxStrByteLength(String str, int maxLength) {
        int num = 0;
        try {
            num = byteLength(str);
        } catch (Exception e) {
        }

        return num <= maxLength;
    }

    /**
     * コードの長さをチェックする.
     *
     * <pre>
     * バイト数を比べる
     * </pre>
     *
     * @param str チェック対象の文字列
     * @param minLength 桁数
     * @return TRUE(minLength桁数以上)/FALSE(minLength桁数より以下)
     */
    public static boolean isMinStrByteLength(String str, int minLength) {
        int num = 0;
        try {
            num = byteLength(str);
        } catch (Exception e) {
        }

        return num >= minLength;
    }

    /**
     * コードの長さをチェックする.<br>
     * (バイト数を比べる)<br>
     *
     * @param str チェック対象の文字列
     * @param length 桁数
     * @return boolean - 判定(true / false)
     */
    public static boolean isStrByteLength(String str, int length) {
        int num = 0;
        try {
            num = byteLength(str);
        } catch (Exception e) {
        }

        if (num == length) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 文字列をUNICODEとしてではなく、Shift_JISとして変換した場合の文字のByte数を求める関数.<br>
     * aアｱ123 → 7<br>
     *
     * @param argStr チェック対象の文字列
     * @return 文字列のShift_JISとした場合にバイト数
     */
    public static int byteLength(String argStr) throws Exception {
        int ret = 0;
        int i = 0;
        int len = 0;

        len = argStr.length();
        for (i = 0; i < len; i++) {
            if ((argStr.charAt(i) > 0x0019 && argStr.charAt(i) < 0x0080)
                    || (argStr.charAt(i) >= 0xFF61 && argStr.charAt(i) <= 0xFF9F)) {
                ret++;
            } else {
                ret += 2;
            }
        }
        return ret;
    }

    /**
     * 指定したバイト数で文字列を切り出す.
     *
     * @param target 対象文字列
     * @param maxBytes 指定バイト数
     * @return カット後文字列
     */
    public static String truncateBytes(String target, int maxBytes) {
        return truncateBytes(target, Charset.defaultCharset(), maxBytes);
    }

    /**
     * 指定したバイト数で文字列を切り出す.
     *
     * @param target 対象文字列
     * @param charset 文字コード
     * @param maxBytes 指定バイト数
     * @return カット後文字列
     */
    public static String truncateBytes(String target, Charset charset, int maxBytes) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(maxBytes);
        CharBuffer charBuffer = CharBuffer.wrap(target);
        CharsetEncoder encoder = charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE)
                .onUnmappableCharacter(CodingErrorAction.REPLACE).reset();
        CoderResult coderResult = encoder.encode(charBuffer, byteBuffer, true);
        if (!coderResult.isOverflow()) {
            return target;
        }
        encoder.flush(byteBuffer);
        return charBuffer.flip().toString();
    }

    /**
     * Stringを切り分ける
     *
     * @param str 文字列データ
     * @param beginIndex スタートインデックス
     * @param endIndex エンドインデックス
     * @return 切り分けられた文字列
     */
    public static String subString(String str, int beginIndex, int endIndex) {
        try {
            return str.substring(beginIndex, endIndex);
        } catch (Exception e) {
            return EMPTY;
        }
    }

    /**
     * メッセージを置換する.
     *
     * <pre>
     * 置換文字列を{0}, {1}..にreplaceする
     * </pre>
     *
     * @param pattern パターン
     * @param arguments 置換文字列(文字になりうるもの)
     * @return 置換後メッセージ
     */
    public static String format(String pattern, Object... arguments) {
        return MessageFormat.format(pattern, arguments);
    }

    /**
     *
     * 最初のいらない「0」文字を外す.
     *
     * <pre>
     * ("001" -> 1, "01234" -> 1234, "0" -> 0)
     * </pre>
     *
     * @param intTypeValue IntegerタイプのString value
     * @return Integer value
     */
    public static String removeLeadingZeroForInteger(String intTypeValue) {
        return Integer.valueOf(intTypeValue).toString();
    }

    /**
     * 先頭をゼロ埋めした文字列を取得する.
     *
     * @param str_int 対象数字
     * @param len 桁数
     * @return ゼロ埋めした文字列
     */
    public static String changeTopZeroString(int str_int, int len) {
        // 文字に変換
        String str = new Integer(str_int).toString();
        // 文字数分０を付加
        for (int i = 1; i < len; i++) {
            str = "0" + str;
        }
        // 文字列の右からlen文字分を切り出す
        return str.substring(str.length() - len, str.length());
    }

    /**
     * 数値を文字列に変換する(toString).
     *
     * @param value 値
     * @return 文字列
     */
    public static String toStringIfNullToZero(Number value) {
        if (value == null) {
            return "0";
        }
        return value.toString();
    }

    /**
     * 英数混在チェック.
     *
     * @param target チェック対象値
     * @return True : 英数両方保持 / False : 左記以外
     */
    public static boolean hasNumberAndChar(String target) {
        boolean hasNumber = false;
        boolean hasChar = false;
        // Check have number
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            if (c < '0' || c > '9') {
                hasNumber = true;
            }
        }
        // Check have char
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')) {
                hasChar = true;
            }
        }
        return hasNumber && hasChar;
    }
}
