package com.hung.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 【TIME-3】配列ユーティリティ.
 *
 * <pre>
 * 配列操作に関する共通的な処理
 * </pre>
 *
 * @author Mitsui Zosen Systems Research Inc.
 * @version X.X
 * @since TIME-3 X.X
 */
public class CommonArraysUtils {

    /**
     * [不可視] デフォルトコンストラクタ.
     */
    protected CommonArraysUtils() {
    }

    /**
     * 対象がNullかどうか判定する.
     *
     * @param args 評価対象
     * @return True: Null / False: 左記以外
     */
    public static Boolean isNull(Object[] args) {
        return args == null;
    }

    /**
     * 対象が非Nullかどうか判定する.
     *
     * @param args 評価対象
     * @return True: 非Null / False: 左記以外
     */
    public static Boolean isNotNull(Object[] args) {
        return !isNull(args);
    }

    /**
     * 対象がNullまたは0件かどうか判定する.
     *
     * @param args 評価対象
     * @return True: Nullまたは0件 / False: 左記以外
     */
    public static Boolean isNullOrEmpty(Object[] args) {
        return isNull(args) || args.length == 0;
    }

    /**
     * 対象が非Nullかつ非0件かどうか判定する.
     *
     * @param args 評価対象
     * @return True: 非Nullかつ非0件 / False: 左記以外
     */
    public static Boolean isNotNullOrEmpty(Object[] args) {
        return !isNullOrEmpty(args);
    }

    /**
     * 配列をリストに変換する.
     *
     * @param <T> 変換対象タイプ
     * @param arrays 変換対象配列
     * @return 変換後リスト
     */
    public static <T> List<T> toList(T[] arrays) {
        if (isNullOrEmpty(arrays)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(arrays));
    }

    /**
     * 配列に要素を追加する.
     *
     * <pre>
     * 追加対象がNullまたは空文字の場合、追加しない
     * </pre>
     *
     * @param arrays 対象配列
     * @param target 追加対象
     * @return 追加後配列
     */
    public static Object[] add(Object[] arrays, Object target) {
        // なければ何もしない
        if (CommonObjectUtils.isNullOrEmpty(target)) {
            return arrays;
        }
        // 一時的にリストに変換し、要素を追加
        List<Object> ret = toList(arrays);
        ret.add(target);
        // 再配列化
        return CommonListUtils.toArrays(ret);
    }

    /**
     * 配列に要素を追加する.
     *
     * <pre>
     * 追加対象がNullまたは0件の場合、追加しない
     * 実質的にJoin
     * </pre>
     *
     * @param arrays 対象配列
     * @param targets 追加対象配列
     * @return 追加後配列
     */
    public static Object[] addAll(Object[] arrays, Object[] targets) {
        // なければ何もしない
        if (CommonArraysUtils.isNullOrEmpty(targets)) {
            return arrays;
        }
        // 一時的にリストに変換し、要素を追加
        List<Object> ret = toList(arrays);
        ret.addAll(toList(targets));
        // 再配列化
        return CommonListUtils.toArrays(ret);
    }

    /**
     * 配列が指定長かどうか判定する.
     *
     * @param arrays 評価対象
     * @param length 長さ
     * @return True : 指定長 / False : 左記以外
     */
    public static boolean isNotNullAndMatchSize(Object[] arrays, int length) {
        if (arrays == null) {
            return false;
        }
        return arrays.length == length;
    }

    /**
     * テストコード.
     *
     * <pre>
     * あとでうつします
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {
        Object[] list = add(new String[] {"apple"}, null);
        for (Object object : list) {
            System.out.println(object);
        }
        System.out.println("-----");
        list = add(new String[] {"fuga"}, "hoge");
        for (Object object : list) {
            System.out.println(object);
        }
        System.out.println("-----");
        list = add(new String[] {"fuga"}, null);
        for (Object object : list) {
            System.out.println(object);
        }
    }

}
