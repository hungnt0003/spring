package com.hung.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javassist.NotFoundException;

/**
 * 
 * Common list show data.
 *
 * <pre>
 * 内容, 使用例など
 * </pre>
 *
 * @author Mitsui Zosen Systems Research Inc.
 * @version X.X
 * @since TIME-3 X.X
 */
public class ListDto<T> {

    /** 要素. */
    private List<ListElementDto<T>> elements = new ArrayList<>();
    // private List<ListElementDto<T>> elements = new ArrayList<>();

    /**
     * 要素を取得する.
     *
     * @return 要素
     */
    public List<ListElementDto<T>> getElements() {
        return this.elements;
    }

    /**
     * 要素を設定する.
     *
     * @param elements 要素
     */
    public void setElements(List<ListElementDto<T>> elements) {
        this.elements = elements;
    }

    /**
     * コンストラクタ.
     *
     * @param data 設定内容
     */
    public ListDto(List<T> data) {
        initialize(data);
    }

    /**
     * 初期化処理.
     *
     * @param data 設定内容
     */
    protected void initialize(List<T> data) {
        if (CommonListUtils.isNullOrEmpty(data)) {
            return;
        }
        // 行番号付与
        AtomicInteger i = new AtomicInteger(0);
        data.forEach(d -> this.elements.add(new ListElementDto<>(d, i.getAndIncrement())));
    }

    /**
     * 指定された行番号が範囲内にあるかどうかを判定する.
     *
     * @param rowNo 行番号
     * @return True : 選択可能 / False : 左記以外
     */
    public boolean isRowAvailable(int rowNo) {
        if (CommonListUtils.isNullOrEmpty(this.elements)) {
            return false;
        }
        if ((rowNo >= 0) && (rowNo < elements.size())) {
            return true;
        }
        return false;
    }

    /**
     * 選択された要素を取得する.
     *
     * @param element 行番号情報
     * @return 選択された要素
     * @throws Exception
     */
    public T getElement(ListElementDto<T> element) throws NotFoundException {
        if (element == null) {
            // TODO INODAI
            throw new NotFoundException("");
        }
        return getElement(element.getRowNo());
    }

    /**
     * [TODO 近々privateにします]選択された要素を取得する.
     *
     * @param rowNo 行番号
     * @return 選択された要素
     * @throws Exception
     */
    public T getElement(int rowNo) throws NotFoundException {
        if (isRowAvailable(rowNo)) {
            return elements.get(rowNo).getElement();
        } else {
            // TODO INODAI
            throw new NotFoundException("TODO");
        }
    }

}
