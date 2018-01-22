package com.hung.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections4.comparators.ComparatorChain;
import org.apache.commons.collections4.comparators.NullComparator;

import com.hung.dto.common.SortCondition;

/**
 * 【TIME-3】リストユーティリティ.
 *
 * <pre>
 * リスト操作に関する共通的な処理
 * </pre>
 *
 * @author Mitsui Zosen Systems Research Inc.
 * @version X.X
 * @since TIME-3 X.X
 */
public class CommonListUtils {

    /**
     * [不可視] デフォルトコンストラクタ.
     */
    protected CommonListUtils() {
    }

    /**
     * 対象がNullかどうか判定する.
     *
     * @param list 評価対象
     * @return True: Null / False: 左記以外
     */
    public static boolean isNull(List<?> list) {
        return list == null;
    }

    /**
     * 対象が非Nullかどうか判定する.
     *
     * @param list 評価対象
     * @return True: 非Null / False: 左記以外
     */
    public static boolean isNotNull(List<?> list) {
        return !isNull(list);
    }

    /**
     * 対象がNullまたは0件かどうか判定する.
     *
     * @param list 評価対象
     * @return True: Nullまたは0件 / False: 左記以外
     */
    public static boolean isNullOrEmpty(List<?> list) {
        return isNull(list) || list.isEmpty();
    }

    /**
     * 対象が非Nullかつ非0件かどうか判定する.
     *
     * @param list 評価対象
     * @return True: 非Nullかつ非0件 / False: 左記以外
     */
    public static boolean isNotNullOrEmpty(List<?> list) {
        return !isNullOrEmpty(list);
    }

    /**
     * 件数を取得する.
     *
     * @param list 対象リスト
     * @return 件数(Nullの場合でも0を返却)
     */
    public static int getCount(List<?> list) {
        return isNotNullOrEmpty(list) ? list.size() : 0;
    }

    /**
     * 対象がNullまたは0件の場合、Nullとして返却する.
     *
     * @param <T> 扱うタイプ
     * @param list 対象リスト
     * @return Nullまたは0件 : Null / 左記以外 : そのまま
     */
    public static <T> List<T> emptyToNull(List<T> list) {
        if (isNullOrEmpty(list)) {
            return null;
        }
        return list;
    }

    /**
     * 対象がNullまたは0件かどうか判定する.
     *
     * @param map 評価対象
     * @return True: Nullまたは0件 / False: 左記以外
     */
    public static boolean isNullOrEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 対象が非Nullかつ非0件かどうか判定する.
     *
     * @param map 評価対象
     * @return True: 非Nullかつ非0件 / False: 左記以外
     */
    public static boolean isNotNullOrEmpty(Map<?, ?> map) {
        return !isNullOrEmpty(map);
    }

    /**
     * 対象がNullまたは0件かどうか判定する.
     *
     * @param set 評価対象
     * @return True: Nullまたは0件 / False: 左記以外
     */
    public static boolean isNullOrEmpty(Set<?> set) {
        return set == null || set.isEmpty();
    }

    /**
     * 対象が非Nullかつ非0件かどうか判定する.
     *
     * @param set 評価対象
     * @return True: 非Nullかつ非0件 / False: 左記以外
     */
    public static boolean isNotNullOrEmpty(Set<?> set) {
        return !isNullOrEmpty(set);
    }

    /**
     * リストを配列に変換する.
     *
     * <pre>
     * 総称型(Generics)が配列では使えないため、Objectで返却する
     * </pre>
     *
     * @param list 変換対象リスト
     * @param <T> 変換対象タイプ
     * @return 配列
     */
    public static <T> Object[] toArrays(List<T> list) {
        // なければ新規配列のみ返却
        if (isNullOrEmpty(list)) {
            return new Object[] {};
        }
        // 初期化
        Object[] ret = new Object[list.size()];
        // 古典的だが最速？
        for (int idx = 0; idx < ret.length; idx++) {
            ret[idx] = list.get(idx);
        }
        return ret;
    }

    /**
     * ソートする.
     *
     * @see CommonListUtils#sort(List, List)
     * @param <T> ソート対象タイプ
     * @param list ソート対象リスト
     * @param condition ソート条件
     * @return ソート後リスト
     */
    public static <T> List<T> sort(List<T> list, SortCondition condition) {
        if (CommonObjectUtils.isNullOrEmpty(condition)) {
            return list;
        }
        List<SortCondition> sc = Arrays.asList(condition);
        return sort(list, sc);
    }

    /**
     * ソートする.
     *
     * @see CommonListUtils#sort(List, List)
     * @param <T> ソート対象タイプ
     * @param list ソート対象リスト
     * @param conditions ソート条件配列
     * @return ソート後リスト
     */
    public static <T> List<T> sort(List<T> list, SortCondition... conditions) {
        if (CommonArraysUtils.isNullOrEmpty(conditions)) {
            return list;
        }
        List<SortCondition> conditionList = new ArrayList<>();
        for (SortCondition sortCondition : conditions) {
            conditionList.add(sortCondition);
        }
        return sort(list, conditionList);
    }

    /**
     * ソートする.
     *
     * <pre>
     * 追加したソート条件の順でソートする
     * ソート項目がNullの場合、その要素は最後尾になる(降順ソートの場合は先頭)
     * Reflectionを使用しているため、最速ではない
     * 数値(int, integer)の場合は使用しないこと
     * </pre>
     *
     * @param <T> ソート対象タイプ
     * @param list ソート対象リスト
     * @param conditions ソート条件リスト
     * @return ソート後リスト
     */
    public static <T> List<T> sort(List<T> list, List<SortCondition> conditions) {
        if (isNullOrEmpty(list) || isNullOrEmpty(conditions)) {
            return list;
        }

        ComparatorChain<T> comparator = new ComparatorChain<>();
        for (SortCondition sortCondition : conditions) {
            BeanComparator<T> beanComparator = null;
            if (sortCondition.isCompareInteger()) {
                beanComparator = new BeanComparator<>(sortCondition.getKey(), new Comparator<T>() {

                    @Override
                    public int compare(Object obj1, Object obj2) {
                        return Integer.valueOf((String) obj1) - Integer.valueOf((String) obj2);
                    }
                });
            } else {
                beanComparator = new BeanComparator<>(sortCondition.getKey(), new NullComparator<>());
            }
            comparator.addComparator(beanComparator, sortCondition.isReverse());
            /*
             * comparator.addComparator(new BeanComparator<>(sortCondition.getKey(), new NullComparator<>()),
             * sortCondition.isReverse());
             */
        }
        Collections.sort(list, comparator);
        return list;
    }

    /**
     * Listを指定したサイズ毎に分割する.
     *
     * @param <T> 対象タイプ
     * @param list 対象リスト
     * @param size リストの分割単位
     * @return サイズ毎に分割されたList。但し、Listがnullまたは空の場合、もしくはsizeが0以下の場合はnullを返す
     */
    public static <T> List<List<T>> devide(List<T> list, int size) {
        if (isNullOrEmpty(list) || size <= 0) {
            return null;
        }

        int block = list.size() / size + (list.size() % size > 0 ? 1 : 0);

        List<List<T>> devidedList = new ArrayList<>(block);
        for (int i = 0; i < block; i++) {
            int start = i * size;
            int end = Math.min(start + size, list.size());
            devidedList.add(new ArrayList<>(list.subList(start, end)));
        }
        return devidedList;
    }

    /**
     * 空リストをNullリストに変換する.
     *
     * @param <T> 対象の型
     * @param list 判定対象
     * @return リスト
     */
    public static <T> List<T> toNullIfEmpty(List<T> list) {
        if (CommonListUtils.isNullOrEmpty(list)) {
            return null;
        }
        return list;
    }

    /**
     * Nullリストを空リストに変換する.
     *
     * @param <T> 対象の型
     * @param list 判定対象
     * @return リスト
     */
    public static <T> List<T> toEmptyIfNull(List<T> list) {
        if (CommonListUtils.isNull(list)) {
            return new ArrayList<>();
        }
        return list;
    }

    /**
     * リストを結合する.
     *
     * @param <T> 対象リストの型
     * @param targetLists 対象リスト
     * @return 結合後リスト
     */
    @SafeVarargs
    public static <T> List<T> join(final List<T>... targetLists) {
        List<T> joined = new ArrayList<>();
        for (List<T> list : targetLists) {
            if (isNotNullOrEmpty(list)) {
                joined.addAll(list);
            }
        }
        return joined;
    }

    /**
     * 先頭要素を取得する.
     *
     * @param <T> 対象リストの型
     * @param targetLists 対象リスト
     * @return 先頭要素
     */
    public static <T> T getFirst(List<T> targetLists) {
        if (CommonListUtils.isNotNullOrEmpty(targetLists)) {
            return targetLists.get(0);
        }
        return null;
    }

    /**
     * 末尾要素を取得する.
     *
     * @param <T> 対象リストの型
     * @param targetLists 対象リスト
     * @return 末尾要素
     */
    public static <T> T getLast(List<T> targetLists) {
        if (CommonListUtils.isNotNullOrEmpty(targetLists)) {
            return targetLists.get(targetLists.size() - 1);
        }
        return null;
    }

    /**
     * 対象Index要素を取得する.
     *
     * @param <T> 対象リストの型
     * @param targetLists 対象リスト
     * @param index 要素Index
     * @return 対象Index要素
     */
    public static <T> T getElement(List<T> targetLists, int index) {
        if (CommonListUtils.getCount(targetLists) > index) {
            return targetLists.get(index);
        }
        return null;
    }

}
