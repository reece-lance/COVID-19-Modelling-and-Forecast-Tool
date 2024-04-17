import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/*
 * SOURCRE: tutorial https://www.javaguides.net/2020/01/java-dom-tutorial.html
 *
 * We will also need to use iText to create the pdf
 * https://kb.itextpdf.com/home/it5kb/examples/parsing-more-than-one-html-files-to-a-single-pdf
 * */
public class ReadXMLFileInJava {
  public static void main(String[] args) {
    //        String filePath = "C:\\Users\\Filip
    // Vlcek\\source\\repos\\XmlTestJava\\src\\main\\java\\users.xml";
    var filePath = ReadXMLFileInJava.class.getClassLoader().getResource("HtmlTemplates/users.xml");
    File xmlFile = null;
    try {
      xmlFile = new File(filePath.toURI());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = null;

    List<User> userList = null;
    Document doc = null;
    try {
      dBuilder = dbFactory.newDocumentBuilder();
      doc = dBuilder.parse(xmlFile);
      doc.getDocumentElement().normalize();

      logUserDocInfo(doc);

    } catch (Exception e1) {
      e1.printStackTrace();
    }

    /* UPDATING  */
    updateElementValue(doc);
    logUserDocInfo(doc);

    deleteElement(doc);
    logUserDocInfo(doc);

    /* CREATING  */

    var newUser = new User(666, "Thomas", "Dingledong", 12, "PrefferNotToSay");

    Element rootElement = doc.getDocumentElement();
    // append root element to document
//    doc.appendChild(rootElement);

    // append first child element to root element
    rootElement.appendChild(createUserElement(doc, newUser));
    logUserDocInfo(doc);

    /* OUTPUT  */
    try {
      writeXMLFile(doc);
    } catch (TransformerException e) {
      e.printStackTrace();
    }
  }

  private static void logUserDocInfo(Document doc) {
    System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
    NodeList nodeList = doc.getElementsByTagName("User");
    // now XML is loaded as Document in memory, lets convert it to Object List
    var userList = new ArrayList<User>();

    for (int i = 0; i < nodeList.getLength(); i++) {
      userList.add(getUser(nodeList.item(i)));
    }
    // lets print User list information
    for (User emp : userList) {
      System.out.println(emp.toString());
    }
  }

  private static User getUser(Node node) {
    // XMLReaderDOM domReader = new XMLReaderDOM();
    User user = new User();
    if (node.getNodeType() == Node.ELEMENT_NODE) {
      Element element = (Element) node;
      user.setId(Integer.parseInt(getTagValue("id", element)));
      user.setFirstName(getTagValue("firstName", element));
      user.setLastName(getTagValue("lastName", element));

      var gender = getTagValue("gender", element);

      user.setGender(gender == null ? "" : gender);
      user.setAge(Integer.parseInt(getTagValue("age", element)));
    }
    return user;
  }

  private static String getTagValue(String tag, Element element) {

    var valueNode = element.getElementsByTagName(tag).item(0);

    if (valueNode == null){
      return null;
    }
    NodeList nodeList = valueNode.getChildNodes();
    Node node = nodeList.item(0);
    return node.getNodeValue();
  }

  private static void updateElementValue(Document doc) {
    NodeList users = doc.getElementsByTagName("User");
    Element user = null;
    // loop for each user
    for (int i = 0; i < users.getLength(); i++) {
      user = (Element) users.item(i);
      Node name = user.getElementsByTagName("firstName").item(0).getFirstChild();
      name.setNodeValue(name.getNodeValue().toUpperCase());
    }
  }

  private static void deleteElement(Document doc) {
    NodeList users = doc.getElementsByTagName("User");
    Element user = null;
    // loop for each user
    for (int i = 0; i < users.getLength(); i++) {
      user = (Element) users.item(i);
      Node genderNode = user.getElementsByTagName("gender").item(0);
      user.removeChild(genderNode);
    }
  }

  private static Node createUserElement(Document doc, User userData) {
    Element user = doc.createElement("User");

    // set id attribute
//    user.setAttribute("id", String.valueOf(userData.getId()));
    user.appendChild(createUserElements(doc, user, "id", String.valueOf(userData.getId())));


    // create firstName element
    user.appendChild(createUserElements(doc, user, "firstName", userData.getFirstName()));

    // create lastName element
    user.appendChild(createUserElements(doc, user, "lastName", userData.getLastName()));

    // create age element
    user.appendChild(createUserElements(doc, user, "age", String.valueOf(userData.getAge())));

    // create gender element
    user.appendChild(createUserElements(doc, user, "gender", userData.getGender()));

    return user;
  }

  // utility method to create text node
  private static Node createUserElements(Document doc, Element element, String name, String value) {
    Element node = doc.createElement(name);
    node.appendChild(doc.createTextNode(value));
    return node;
  }

  private static void writeXMLFile(Document doc)
      throws TransformerFactoryConfigurationError, TransformerConfigurationException,
          TransformerException {
    doc.getDocumentElement().normalize();
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(new File("users_updated.xml"));
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.transform(source, result);
    System.out.println("XML file updated successfully");
  }
}
