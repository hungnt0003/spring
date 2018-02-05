package com.hung.dto;

import com.hung.common.AbstractDto;

/**
 * クラスタイトル(ピリオド削除厳禁).
 *
 * <pre>
 * 内容, 使用例など
 * </pre>
 *
 * @author Mitsui Zosen Systems Research Inc.
 * @version X.X
 * @since TIME-3 X.X
 */
public class RoleDto extends AbstractDto {

    private int id;
    private String name;

    /**
     * (デフォルト)コンストラクタ(ピリオド削除厳禁).
     * 
     * <pre>
     * 初期化内容, 使用例など(不要の場合は削除)
     * </pre>
     */
    public RoleDto() {
        // TODO Auto-Generated Constructor Stub
        super();
    }

    /**
     * (デフォルト)コンストラクタ(ピリオド削除厳禁).
     * 
     * <pre>
     * 初期化内容, 使用例など(不要の場合は削除)
     * </pre>
     * 
     * @param id
     * @param name
     */
    public RoleDto(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    /**
     * idを取得する.
     * 
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * idを設定する.
     * 
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * nameを取得する.
     * 
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * nameを設定する.
     * 
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void init() {
        setName(null);
    }

}
