
package org.dayup.avatar.base.refs.page;

import java.io.Serializable;

public class PageInfo implements Serializable {

    private int num = 1;
    private int size = 10;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
