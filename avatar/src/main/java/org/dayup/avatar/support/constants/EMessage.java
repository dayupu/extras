package org.dayup.avatar.support.constants;

public enum EMessage {

    // common message
    SUCCESS("200", "common.success"),
    SAVE_SUCCESS("200", "common.save.success"),
    DROP_SUCCESS("200", "common.drop.success"),
    MOVE_SUCCESS("200", "common.move.success"),
    SYSTEM_ERROR("503", "common.system.error"),
    // business message
    DOC01001("500", "docs.error.library.name.exists"),
    DOC01002("500", "docs.error.library.not.exists"),
    DOC01003("500", "docs.error.document.not.exists");

    private String code;
    private String msgKey;

    EMessage(String code, String msgKey) {
        this.code = code;
        this.msgKey = msgKey;
    }

    public String getCode() {
        return code;
    }

    public String getMsgKey() {
        return msgKey;
    }
}
