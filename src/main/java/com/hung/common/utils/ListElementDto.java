package com.hung.common.utils;

/**
 * 
 * Element show screen.
 *
 *
 * @author Mitsui Zosen Systems Research Inc.
 * @version X.X
 * @since TIME-3 X.X
 */
public class ListElementDto<T> {

    /** 要素. */
    private T element;
    /** 行番号(連番). */
    private int rowNo;

    /**
     * コンストラクタ.
     */
    public ListElementDto() {
        this.element = null;
        this.rowNo = -1;
    }

    /**
     * コンストラクタ.
     *
     * @param element 要素
     * @param rowNo 行番号(連番)
     */
    public ListElementDto(T element, int rowNo) {
        this.element = element;
        this.rowNo = rowNo;
    }

    /**
     * 要素を取得する.
     *
     * @return 要素
     */
    public T getElement() {
        return element;
    }

    /**
     * 要素を設定する.
     *
     * @param element 要素
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * 行番号(連番)を取得する.
     *
     * @return 行番号(連番)
     */
    public int getRowNo() {
        return rowNo;
    }

    /**
     * 行番号(連番)を設定する.
     *
     * @param rowNo 行番号(連番)
     */
    public void setRowNo(int rowNo) {
        this.rowNo = rowNo;
    }

}
