
package soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UpdateSpotFree_QNAME = new QName("http://soap/", "updateSpotFree");
    private final static QName _UpdateSpotOccupied_QNAME = new QName("http://soap/", "updateSpotOccupied");
    private final static QName _UpdateSpotFreeResponse_QNAME = new QName("http://soap/", "updateSpotFreeResponse");
    private final static QName _UpdateSpotOccupiedResponse_QNAME = new QName("http://soap/", "updateSpotOccupiedResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdateSpotFreeResponse }
     * 
     */
    public UpdateSpotFreeResponse createUpdateSpotFreeResponse() {
        return new UpdateSpotFreeResponse();
    }

    /**
     * Create an instance of {@link UpdateSpotOccupiedResponse }
     * 
     */
    public UpdateSpotOccupiedResponse createUpdateSpotOccupiedResponse() {
        return new UpdateSpotOccupiedResponse();
    }

    /**
     * Create an instance of {@link UpdateSpotOccupied }
     * 
     */
    public UpdateSpotOccupied createUpdateSpotOccupied() {
        return new UpdateSpotOccupied();
    }

    /**
     * Create an instance of {@link UpdateSpotFree }
     * 
     */
    public UpdateSpotFree createUpdateSpotFree() {
        return new UpdateSpotFree();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateSpotFree }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "updateSpotFree")
    public JAXBElement<UpdateSpotFree> createUpdateSpotFree(UpdateSpotFree value) {
        return new JAXBElement<UpdateSpotFree>(_UpdateSpotFree_QNAME, UpdateSpotFree.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateSpotOccupied }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "updateSpotOccupied")
    public JAXBElement<UpdateSpotOccupied> createUpdateSpotOccupied(UpdateSpotOccupied value) {
        return new JAXBElement<UpdateSpotOccupied>(_UpdateSpotOccupied_QNAME, UpdateSpotOccupied.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateSpotFreeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "updateSpotFreeResponse")
    public JAXBElement<UpdateSpotFreeResponse> createUpdateSpotFreeResponse(UpdateSpotFreeResponse value) {
        return new JAXBElement<UpdateSpotFreeResponse>(_UpdateSpotFreeResponse_QNAME, UpdateSpotFreeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateSpotOccupiedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "updateSpotOccupiedResponse")
    public JAXBElement<UpdateSpotOccupiedResponse> createUpdateSpotOccupiedResponse(UpdateSpotOccupiedResponse value) {
        return new JAXBElement<UpdateSpotOccupiedResponse>(_UpdateSpotOccupiedResponse_QNAME, UpdateSpotOccupiedResponse.class, null, value);
    }

}
