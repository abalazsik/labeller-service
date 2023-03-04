
package com.mycompany.labeller.web.data;

/**
 *
 * @author ador
 */
public class WebLabellerException {

    private String msg;

    public WebLabellerException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
