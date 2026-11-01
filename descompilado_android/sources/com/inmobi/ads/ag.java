package com.inmobi.ads;

import android.text.TextUtils;
import com.inmobi.commons.core.utilities.Logger;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: compiled from: MoatVastHelper.java */
/* JADX INFO: loaded from: classes2.dex */
class ag {
    private static final String a = "ag";

    ag() {
    }

    static void a(Document document, bx bxVar) {
        List<Node> listA = bu.a(document, "Verification");
        if (listA != null) {
            StringBuilder sb = new StringBuilder();
            Iterator<Node> it = listA.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Element element = (Element) it.next();
                NodeList childNodes = element.getChildNodes();
                if ("Moat".equals(element.getAttribute("vendor")) && childNodes.getLength() > 0) {
                    for (int i = 0; i < childNodes.getLength(); i++) {
                        Node nodeItem = childNodes.item(i);
                        if ("ViewableImpression".equals(nodeItem.getNodeName())) {
                            String strA = a(nodeItem);
                            if (!TextUtils.isEmpty(strA)) {
                                sb.append(strA);
                                sb.append(";");
                                Logger.a(Logger.InternalLogLevel.INTERNAL, a, "Found Moat Verification tag in VAST with value : " + strA);
                            }
                        }
                    }
                }
            }
            if (sb.length() != 0) {
                Logger.a(Logger.InternalLogLevel.INTERNAL, a, "Moat VastIDs in VAST : " + sb.toString());
                bxVar.a(new NativeTracker(sb.toString(), 0, NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_MOAT, null));
            }
        }
    }

    private static String a(Node node) {
        StringWriter stringWriter = new StringWriter();
        try {
            Transformer transformerNewTransformer = TransformerFactory.newInstance().newTransformer();
            transformerNewTransformer.setOutputProperty("omit-xml-declaration", "yes");
            transformerNewTransformer.transform(new DOMSource(node), new StreamResult(stringWriter));
        } catch (TransformerException e) {
            Logger.a(Logger.InternalLogLevel.INTERNAL, a, "Exception while converting Moat node to string : " + e.getMessage());
        }
        return stringWriter.toString();
    }
}
