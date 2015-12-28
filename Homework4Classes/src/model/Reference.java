/**
 * 
 */
package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * @author pzoli
 * @param <T>
 *
 */
@XmlType
public class Reference<T> extends ExpOrValue<T> {

    public final static String regexp = "/[a-zA-Z]{1}[!\\w\\.-_]*/";
    
    private Workspace objSpace;
    private ExpOrValue<T> value;

    private String refName;

    public Reference() {
    }

    public Reference(String refName) {
        this.refName = refName;
    }

    @XmlElement
    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
    }

    @XmlTransient
    public Workspace getObjSpace() {
        return objSpace;
    }

    public void setObjSpace(Workspace objSpace) {
        this.objSpace = objSpace;
        if ((refName != null) && (objSpace != null)) {
            String[] objNameSpace = refName.split("\\.");
            if ((value == null) && (objNameSpace != null) && (objNameSpace.length > 1)) {
                value = objSpace.getObjectByName(objNameSpace[1]);
            }
        }
    }

    @Override
    public T getValue() {
        return (value != null ? value.getValue() : null);
    }

    public ExpOrValue<T> getObject() {
        return value;
    }

    @Override
    public String toString() {
        String result = " " + refName + ":" + (value != null ? value.toString() : "null") + " ";
        return result;
    }
}
