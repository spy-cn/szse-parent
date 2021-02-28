package com.spy.szse.common.enums;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 错误枚举类
 *
 * @Author: spy
 * @Date: 2021/2/28 15:58
 */

public enum ErrorEnum {
    ERROR_NOT_EMPTY(400101, "Bad Request. %s  must not be null or empty."),
    ERROR_ILLEGAL_DATA(400102, "Illegal value for %s ."),
    ERROR_ILLEGAL_MAPPING_DATA(400103, "Illegal mappings field, already exist the following mappings for %s ."),
    ERROR_INVALID_REQUEST(400104, "Bad Request."),
    ERROR_REPEAT_NAME(400106, "Exists repeat productName in the same level"),
    ERROR_BATCH_CREATE_PRODUCTS(400107, "Exists repeat productCode or relation is wrong"),
    ERROR_BATCH_MOVE_PRODUCTS(400108, "ProductCode relation is wrong"),
    ERROR_CHILDREN_CONTAIN_PARENT(400109, "Children contain parentCode"),
    ERROR_UNKNOWN_ERROR(500100, "Unknown error, please contact system support"),
    ERROR_UNEXPECTED_ERROR(500101, "Operation failed for unexpected error, please contact system for support"),
    ERROR_INSERT_STREAM(500102, "Failed to insert usCnt/dsCnt stream product chain between %s and %s unexpected error!"),
    ERROR_UPDATE_STREAM(500103, "Failed to update usCnt/dsCnt stream product chain between %s and %s unexpected error!"),
    ERROR_BATCH_UPDATE_PRODUCT(500104, "Failed to updateProduct the product node parent for unexpected error!"),
    ERROR_INSERT_PRODUCT_MAPPING(500105, "Failed to insert the product mapping for unexpected error!"),
    ERROR_INSERT_PRODUCT_MAPPING2(500106, "Failed to insert dict_3rd_csf_product_mapping mappings from %s to %s."),
    ERROR_CREATE_PRODUCT(500107, "Failed to createProduct product node with data %s."),
    ERROR_DELETE_PRODUCT_MAPPING(500108, "Failed to Delete Product Mapping."),
    ERROR_DELETE_PRODUCT(500109, "Failed to Delete Dict_industry_product."),
    ERROR_DELETE_PRODUCT_CHAIN(500110, "Failed to Delete product_chain_stream."),
    ERROR_REDIS_INSERT_KEY(500111, "Failed to insert product node %s of exists others operate."),
    ERROR_INSERT_PRODUCT(500112, "Failed to insert the product!"),
    ERROR_DELETE_PRODUCTS_CHILDREN_EXIST(500113, "Can not delete Products while children exists."),
    ERROR_CSF_PRODUCT_NOT_EXIST(500114, "CsfProductCode %s not exist"),
    ERROR_INSERT_STREAM_EXIST(500115, "Exists stream product chain between %s and %s"),
    ERROR_UPDATE_STREAM_NOT_EXIST(500116, "Not exists stream product chain between %s and %s"),
    ERROR_UPDATE_STREAM_FAIL(500117, "Failed to update product_chain_stream."),
    ERROR_INSERT_STREAM_FAIL(500118, "Failed to insert product_chain_stream."),
    ERROR_PRODUCT_INIT_NOT_FINISH(500119, "Please Connect with CSF to finish the init process."),
    ERROR_ILLEGAL_VALUE(500120, "Illegal Value for %s."),
    ERROR_EXIST_TREE(500121, "Exists product tree %s!"),
    ERROR_INSERT_TREE(500122, "Failed to insert product tree %s!"),
    ERROR_UPDATE_TREE(500123, "Failed to update product tree %s!"),
    ERROR_DELETE_TREE(500124, "Failed to delete product tree %s!"),
    ERROR_NOT_EXIST_TREE(500125, "Not exists product tree %s!"),
    ERROR_INSERT_RELATION(500126, "Failed to insert product relation %s!"),
    ERROR_EXIST_MAPPING(500150, "Failed to insert product node for exists mapping relations!."),
    ERROR_PRODUCT_VERSION(500151, "Not the latest version, please refresh page"),
    ERROR_GENERATE_PRODUCT_CODE(500152, "Generate product node process fail, please try again later"),
    ERROR_UPDATE_PRODUCT_WITHOUT_CHANGES(500153, "Product node updated without changes"),
    ERROR_UPDATE_PRODUCT_TREE_WITHOUT_CHANGES(500154, "Product tree updated without changes");

    private final int errorId;
    private String errorMessage;

    ErrorEnum(int errorId, String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorId = errorId;
    }

    public String format(Object... params) {
        if (ArrayUtils.isNotEmpty(params)) {
            this.errorMessage = String.format(this.errorMessage, params);
        }
        return this.errorMessage;
    }

    public int getErrorId() {
        return errorId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "ErrorEnum{" +
                "errorId=" + errorId +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
