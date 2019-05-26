
package webservices_lab2.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createPerson complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createPerson">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://Server.webservices_lab2/}person" minOccurs="0"/>
 *         &lt;element name="arg1" type="{http://Server.webservices_lab2/}postgreSQLDAO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createPerson", propOrder = {
    "arg0",
    "arg1"
})
public class CreatePerson {

    protected Person arg0;
    protected PostgreSQLDAO arg1;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link Person }
     *     
     */
    public Person getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Person }
     *     
     */
    public void setArg0(Person value) {
        this.arg0 = value;
    }

    /**
     * Gets the value of the arg1 property.
     * 
     * @return
     *     possible object is
     *     {@link PostgreSQLDAO }
     *     
     */
    public PostgreSQLDAO getArg1() {
        return arg1;
    }

    /**
     * Sets the value of the arg1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link PostgreSQLDAO }
     *     
     */
    public void setArg1(PostgreSQLDAO value) {
        this.arg1 = value;
    }

}
