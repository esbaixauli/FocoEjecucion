/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ubu.logic.FileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Rubén
 */
public class DocumentHandler {

    private DocumentHandler() {
    }
    // String appName, int fps,String videoOutput

    public static List readConfig(String readPath) throws FileNotFoundException,
            Exception{
        List<String> results = new ArrayList<String>();
        DocumentBuilderFactory docBuilderFactory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(new File(readPath));
        doc.getDocumentElement().normalize();
        //When file has been opened, get the parameters 
        // and put them in a list.
        NodeList l = doc.getElementsByTagName("appName");
        Element el = (Element) l.item(0);
        results.add(el.getFirstChild().getNodeValue());
        l = doc.getElementsByTagName("fps");
        el = (Element) l.item(0);
        results.add(el.getFirstChild().getNodeValue());
        l = doc.getElementsByTagName("output");
        el = (Element) l.item(0);
        results.add(el.getFirstChild().getNodeValue());

        if (results.size() == 3) {
            return results;
        } else {
            return null;
        }
    }

    public static void writeConfig(String appName, String fps, String output,
            String writePath) throws Exception {
        DocumentBuilderFactory docFac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFac.newDocumentBuilder();
        //root
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("config");
        doc.appendChild(rootElement);
        //appending the application name
        Element appNameEl = doc.createElement("appName");
        appNameEl.appendChild(doc.createTextNode(appName));
        rootElement.appendChild(appNameEl);
        
        Element fpsEl = doc.createElement("fps");
        fpsEl.appendChild(doc.createTextNode(fps));
        rootElement.appendChild(fpsEl);
        
        Element outputEl = doc.createElement("output");
        outputEl.appendChild(doc.createTextNode(output));
        rootElement.appendChild(outputEl);
        //write the file through the transformer DOM classes
        TransformerFactory tFactory = TransformerFactory.newInstance();
	Transformer transformer = tFactory.newTransformer();
	DOMSource source = new DOMSource(doc);
	StreamResult result = new StreamResult(new File(writePath)); 
	transformer.transform(source, result);
    }
}
