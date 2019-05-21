package example.HelloWorldService;

import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;


@XmlRootElement
public class TestObject {

    @XmlAttribute(required = true)
    public String s;

    @XmlAttribute(required = true)
    List<String> swag;
}
