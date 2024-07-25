package com.cyanrocks.wms.constants;

public enum InventoryFieldsEnum {

    owner("货主", "owner"),
    wareHouse("仓库", "ware_house"),
    specNo("商家编码", "spec_no"),
    goodsName("货品名称", "goods_name"),
    goodsShortName("货品简称", "goods_short_name"),
    goodsNo("货品编号", "goods_no"),
    specId("规格码", "spec_id"),
    spacName("规格名称", "spec_name"),
    barcode("条形码", "barcode"),
    brandName("品牌", "brand_name"),
    goodsType("分类名称", "goods_type"),
    unitName("基本单位", "unit_name"),
    caseSpecification("箱规", "case_specification");

    public String getFieldsCn() {
        return fieldsCn;
    }

    public String getFieldsEn() {
        return fieldsEn;
    }

    private final String fieldsCn;

    private final String fieldsEn;

    InventoryFieldsEnum(String fieldsCn, String fieldsEn) {
        this.fieldsCn = fieldsCn;
        this.fieldsEn = fieldsEn;
    }
}
