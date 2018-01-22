package com.hung.common.utils;

import java.lang.reflect.Field;

/**
 * 【TIME-3】オブジェクトユーティリティ.
 *
 * <pre>
 * オブジェクト操作に関する共通的な処理
 * </pre>
 *
 * @author Mitsui Zosen Systems Research Inc.
 * @version X.X
 * @since TIME-3 X.X
 */
public class CommonObjectUtils {

    /**
     * [不可視]デフォルトコンストラクタ.
     */
    protected CommonObjectUtils() {
    }

    /**
     * オブジェクト同士が同じものか比較する.
     *
     * <pre>
     * 以下のルールに従う
     * ・両方とも Null or Empty -> 同等
     * ・片方だけ Null or Empty -> 異なる
     * </pre>
     *
     * @param validate1 オブジェクト1
     * @param validate2 オブジェクト2
     * @return True : 同等 / False : 左記以外
     */
    public static boolean isEqual(Object validate1, Object validate2) {

        // 両方Null or Empty -> 同等
        if (isNullOrEmpty(validate1) && isNullOrEmpty(validate2)) {
            return true;
        }

        // 片方Null or Empty -> 異なる
        if ((isNullOrEmpty(validate1) && isNotNullOrEmpty(validate2))
                || (isNotNullOrEmpty(validate1) && isNullOrEmpty(validate2))) {
            return false;
        }

        return validate1.equals(validate2);
    }

    /**
     * 対象がNullかどうか判定する.
     *
     * @param target 評価対象
     * @return True : Nullまたは空 / False : 左記以外
     */
    public static boolean isNull(Object target) {
        if (target == null) {
            return true;
        }

        return false;
    }

    /**
     * 対象リストがすべてNullかどうか判定する.
     *
     * @param targets 評価対象リスト
     * @return True : Nullまたは空 / False : 左記以外
     */
    public static boolean isNull(Object... targets) {
        if (targets == null || targets.length == 0) {
            return true;
        } else {
            for (Object target : targets) {
                if (target instanceof Boolean) {
                    // booleanタイプの場合、チェックがいらない
                    continue;
                }
                if (isNotNull(target)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 対象がNullではないかどうか判定する.
     *
     * @param target 評価対象
     * @return True : Not Nullまたは空 / False : 左記以外
     */
    public static boolean isNotNull(Object target) {
        return !isNull(target);
    }

    /**
     * 対象がNullまたは空かどうか判定する.
     *
     * @param target 評価対象
     * @return True : Nullまたは空 / False : 左記以外
     */
    public static boolean isNullOrEmpty(Object target) {
        if (target == null) {
            return true;
        }
        if (target.toString() == null || target.toString().isEmpty()) {
            return true;
        }
        return false;
        // return target == null || target.toString().isEmpty();
    }

    /**
     * 対象がNullまたは空かどうか判定する.
     *
     * @param target 評価対象
     * @return True : Nullまたは空 / False : 左記以外
     */
    public static boolean isNotNullOrEmpty(Object target) {
        return !isNullOrEmpty(target);
    }

    /**
     * 対象オブジェクトの指定したフィールドの値を取得する.
     *
     * <pre>
     * 処理内容, 使用例など(不要の場合は削除)
     * </pre>
     *
     * @param target 対象オブジェクト
     * @param fields フィールドリスト
     * @param fieldName フィールド名
     * @return フィールドの値
     */
    public static Object getFieldValue(Object target, Field[] fields, String fieldName) {
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals(fieldName)) {
                try {
                    return field.get(target);
                } catch (IllegalArgumentException e) {
                    // TODO INODAI ハンドリングすること
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO INODAI ハンドリングすること
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
