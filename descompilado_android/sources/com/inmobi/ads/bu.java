package com.inmobi.ads;

import android.os.SystemClock;
import android.webkit.URLUtil;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.tonyodev.fetch.FetchConst;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* JADX INFO: compiled from: VastHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public class bu {
    private static final String a = "bu";
    private static final Map<String, NativeTracker.TrackerEventType> d;
    private c.j b;
    private final String c = "Progressive";
    private int e = 0;
    private bx f;

    static {
        HashMap map = new HashMap();
        d = map;
        map.put("Error", NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_ERROR);
        d.put(com.my.target.bj.fX, NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_RENDER);
        d.put(com.my.target.bj.gi, NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_CLICK);
        d.put("creativeView", NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_CREATIVE_VIEW);
        d.put("start", NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_PLAY);
        d.put(com.my.target.bj.gq, NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_Q1);
        d.put(com.my.target.bj.gr, NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_Q2);
        d.put(com.my.target.bj.gs, NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_Q3);
        d.put(com.my.target.bj.gt, NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_Q4);
        d.put(com.my.target.bj.gv, NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_MUTE);
        d.put(com.my.target.bj.gw, NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_UNMUTE);
        d.put("pause", NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_PAUSE);
        d.put("resume", NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_RESUME);
        d.put("fullscreen", NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_FULLSCREEN);
        d.put(com.my.target.bj.gA, NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_EXIT_FULLSCREEN);
    }

    public bu(c.j jVar) {
        this.b = jVar;
        this.f = new bx(this.b);
    }

    private static com.inmobi.commons.core.network.d b(String str) {
        com.inmobi.commons.core.network.c cVar = new com.inmobi.commons.core.network.c(HttpRequest.METHOD_GET, str);
        cVar.u = false;
        cVar.t = true;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        com.inmobi.commons.core.network.d dVarA = new com.inmobi.commons.core.network.e(cVar).a();
        try {
            com.inmobi.a.n.a().a(cVar.g());
            com.inmobi.a.n.a().b(dVarA.c());
            com.inmobi.a.n.a().c(SystemClock.elapsedRealtime() - jElapsedRealtime);
        } catch (Exception e) {
            new StringBuilder("Error in setting request-response data size. ").append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
        return dVarA;
    }

    private static int c(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return 0;
        }
    }

    static Node a(Node node, String str) {
        List<Node> listB = b(node, str);
        if (listB.size() > 0) {
            return listB.get(0);
        }
        return null;
    }

    private static List<Node> b(Node node, String str) {
        ArrayList arrayList = new ArrayList();
        if (node != null && str != null) {
            NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node nodeItem = childNodes.item(i);
                if (nodeItem.getNodeType() == 1 && str.equals(nodeItem.getNodeName())) {
                    arrayList.add(nodeItem);
                }
            }
        }
        return arrayList;
    }

    private static String c(Node node, String str) {
        return a(a(node, str));
    }

    private static Node a(List<Node> list, String str) {
        Node nodeA = null;
        if (list == null) {
            return null;
        }
        for (int i = 0; i < list.size() && (nodeA = a(list.get(i), str)) == null; i++) {
        }
        return nodeA;
    }

    static List<Node> a(Document document, String str) {
        if (document == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        NodeList elementsByTagName = document.getElementsByTagName(str);
        for (int i = 0; i < elementsByTagName.getLength(); i++) {
            arrayList.add(elementsByTagName.item(i));
        }
        if (elementsByTagName.getLength() == 0) {
            return null;
        }
        return arrayList;
    }

    static String a(Node node) {
        String textContent;
        if (node == null) {
            return null;
        }
        try {
            textContent = node.getTextContent();
        } catch (DOMException e) {
            new StringBuilder("Error getting node value; ").append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            textContent = null;
        }
        if (textContent != null) {
            return textContent.trim();
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:164:0x035d  */
    public final bx a(String str) {
        String str2;
        String str3;
        String str4;
        if (str == null || str.isEmpty()) {
            a(303);
            return this.f;
        }
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(str)));
            Node nodeA = a((Node) document, com.my.target.bj.fS);
            if (nodeA == null) {
                a(101);
                return this.f;
            }
            Node nodeA2 = a(nodeA, com.my.target.bj.fU);
            if (nodeA2 == null) {
                a(303);
                return this.f;
            }
            Node nodeA3 = a(nodeA2, com.my.target.bj.fW);
            if (nodeA3 != null) {
                this.e++;
                if (this.e > this.b.a) {
                    a(IronSourceConstants.OFFERWALL_AVAILABLE);
                    return this.f;
                }
                if (!b(nodeA3)) {
                    a(101);
                    return this.f;
                }
                List<Node> listA = a(document, com.my.target.bj.gc);
                if (listA != null) {
                    a(b(listA.get(0), com.my.target.bj.gd));
                }
                x.a(document, this.f);
                ag.a(document, this.f);
                a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_CLICK, a(document, com.my.target.bj.gi));
                String strC = c(nodeA3, com.my.target.bj.fT);
                if (strC == null) {
                    a(101);
                    return this.f;
                }
                if (strC == null || strC.isEmpty()) {
                    a(300);
                } else {
                    com.inmobi.commons.core.network.d dVarB = b(strC);
                    if (dVarB.a()) {
                        a(301);
                    } else {
                        a(dVarB.b());
                    }
                }
            } else {
                Node nodeA4 = a(nodeA2, com.my.target.bj.fV);
                if (nodeA4 == null) {
                    a(101);
                    return this.f;
                }
                if (!b(nodeA4)) {
                    a(101);
                    return this.f;
                }
                Node nodeA5 = a(nodeA4, com.my.target.bj.fY);
                if (nodeA5 == null) {
                    a(101);
                    return this.f;
                }
                List<Node> listB = b(nodeA5, com.my.target.bj.fZ);
                if (listB.isEmpty()) {
                    a(101);
                    return this.f;
                }
                Node nodeA6 = a(listB, com.my.target.bj.ga);
                if (nodeA6 == null) {
                    a(FetchConst.NETWORK_WIFI);
                    return this.f;
                }
                List<Node> listB2 = b(nodeA6, com.my.target.bj.gb);
                if (listB2.isEmpty()) {
                    a(101);
                    return this.f;
                }
                String strA = a(listB2.get(0));
                if (strA == null || strA.isEmpty() || !strA.matches("\\d*:[0-5][0-9]:[0-5][0-9](:[0-9][0-9][0-9])?")) {
                    a(101);
                    return this.f;
                }
                this.f.b = strA;
                Node nodeA7 = a(nodeA6, com.my.target.bj.ge);
                if (nodeA7 == null) {
                    a(101);
                    return this.f;
                }
                Node nodeA8 = a(nodeA6, com.my.target.bj.gg);
                this.f.c = c(nodeA8, com.my.target.bj.gh);
                a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_CLICK, b(nodeA8, com.my.target.bj.gi));
                a(b(a(nodeA6, com.my.target.bj.gc), com.my.target.bj.gd));
                x.a(document, this.f);
                ag.a(document, this.f);
                List<Node> listB3 = b(nodeA7, com.my.target.bj.gf);
                if (listB3.isEmpty()) {
                    a(401);
                    return this.f;
                }
                c.C0072c c0072c = this.b.d;
                for (int i = 0; i < listB3.size(); i++) {
                    Element element = (Element) listB3.get(i);
                    String strA2 = a(element);
                    if (strA2 != null && !strA2.trim().isEmpty()) {
                        String attribute = element.getAttribute(com.my.target.bj.gV);
                        String attribute2 = element.getAttribute("type");
                        int iC = c(element.getAttribute("bitrate"));
                        if ((!c0072c.a || iC > 0) && attribute != null && attribute.trim().equalsIgnoreCase("Progressive")) {
                            ArrayList<String> arrayList = this.b.e;
                            if (attribute2 != null) {
                                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                    if (attribute2.equalsIgnoreCase(arrayList.get(i2))) {
                                        this.f.a.add(new bv(strA2, attribute, attribute2, iC));
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                if (this.f.a.isEmpty()) {
                    a(403);
                }
                List<Node> listB4 = b(nodeA5, com.my.target.bj.fZ);
                if (listB4.isEmpty()) {
                    a(101);
                    return this.f;
                }
                Node nodeA9 = a(listB4, com.my.target.bj.gL);
                if (nodeA9 == null) {
                    return this.f;
                }
                List<Node> listA2 = a(document, "CompanionAdTracking");
                HashMap map = new HashMap();
                String str5 = "; Discarding this tracker";
                String str6 = "Malformed URL: ";
                String str7 = "id";
                String attribute3 = null;
                if (listA2 != null) {
                    Iterator<Node> it = listA2.iterator();
                    while (it.hasNext()) {
                        Node nodeA10 = a(it.next(), com.my.target.bj.gc);
                        if (nodeA10 != null) {
                            NamedNodeMap attributes = nodeA10.getAttributes();
                            Node namedItem = (attributes == null || attributes.getLength() <= 0) ? null : attributes.getNamedItem("id");
                            if (namedItem != null) {
                                String nodeValue = namedItem.getNodeValue();
                                for (Node node : b(nodeA10, com.my.target.bj.gd)) {
                                    if ("closeEndCard".equals(((Element) node).getAttribute("event"))) {
                                        String strA3 = a(node);
                                        if (!URLUtil.isValidUrl(strA3)) {
                                            StringBuilder sb = new StringBuilder("Malformed URL: ");
                                            sb.append(strA3);
                                            sb.append("; Discarding this tracker");
                                        } else {
                                            map.put(nodeValue, new NativeTracker(strA3, 0, NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_END_CARD_CLOSE, null));
                                        }
                                    }
                                    it = it;
                                }
                            }
                        }
                        it = it;
                    }
                }
                List<Node> listB5 = b(nodeA9, com.my.target.bj.gM);
                int i3 = 0;
                boolean z = false;
                while (i3 < listB5.size()) {
                    Element element2 = (Element) listB5.get(i3);
                    int iC2 = c(element2.getAttribute("width"));
                    int iC3 = c(element2.getAttribute("height"));
                    if (iC2 == 0 || iC3 == 0) {
                        str2 = str5;
                        str3 = str6;
                        str7 = str7;
                        str4 = attribute3;
                    } else {
                        String strC2 = c(element2, com.my.target.bj.gj);
                        if (!URLUtil.isValidUrl(strC2)) {
                            strC2 = attribute3;
                        }
                        if (element2.hasAttribute(str7)) {
                            attribute3 = element2.getAttribute(str7);
                        }
                        bt btVar = new bt(iC2, iC3, strC2, attribute3);
                        Node nodeA11 = a(element2, com.my.target.bj.gR);
                        if (nodeA11 != null) {
                            String strA4 = a(nodeA11);
                            String attribute4 = ((Element) nodeA11).getAttribute("creativeType");
                            if (attribute4 != null && !attribute4.trim().isEmpty()) {
                                if (d(attribute4)) {
                                    btVar.a(new bt.a(1, strA4));
                                } else {
                                    z = true;
                                }
                            }
                        }
                        Node nodeA12 = a(element2, com.my.target.bj.gT);
                        if (nodeA12 != null) {
                            btVar.a(new bt.a(2, a(nodeA12)));
                        }
                        Node nodeA13 = a(element2, com.my.target.bj.gS);
                        if (nodeA13 != null) {
                            btVar.a(new bt.a(3, a(nodeA13)));
                        }
                        List<bt.a> list = btVar.c;
                        if (list == null || list.size() == 0) {
                            str2 = str5;
                            str3 = str6;
                            str4 = null;
                        } else {
                            a(b(element2, com.my.target.bj.gk), btVar);
                            for (Node node2 : b(a(element2, com.my.target.bj.gc), com.my.target.bj.gd)) {
                                String attribute5 = ((Element) node2).getAttribute("event");
                                if (d.containsKey(attribute5)) {
                                    String strA5 = a(node2);
                                    if (!URLUtil.isValidUrl(strA5)) {
                                        StringBuilder sb2 = new StringBuilder(str6);
                                        sb2.append(strA5);
                                        sb2.append(str5);
                                    } else {
                                        btVar.a(new NativeTracker(strA5, 0, d.get(attribute5), null));
                                    }
                                }
                                str6 = str6;
                                str5 = str5;
                            }
                            str2 = str5;
                            str3 = str6;
                            str4 = null;
                            if (attribute3 != null && map.containsKey(attribute3)) {
                                btVar.a((NativeTracker) map.get(attribute3));
                            }
                            this.f.e.add(btVar);
                        }
                    }
                    i3++;
                    attribute3 = str4;
                    str6 = str3;
                    str7 = str7;
                    str5 = str2;
                }
                int size = this.f.e.size();
                if (size == 0 && z) {
                    b(IronSourceError.ERROR_BN_LOAD_PLACEMENT_CAPPED);
                } else if (listB5.size() > 0 && size == 0) {
                    b(600);
                }
            }
            return this.f;
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            a(100);
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return this.f;
        }
    }

    private static boolean d(String str) {
        int size = bt.f.size();
        for (int i = 0; i < size; i++) {
            if (str.equalsIgnoreCase(bt.f.get(i))) {
                return true;
            }
        }
        return false;
    }

    private static void a(List<Node> list, bt btVar) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Node node = list.get(i);
                if (1 == node.getNodeType()) {
                    String strA = a(node);
                    if (!URLUtil.isValidUrl(strA)) {
                        StringBuilder sb = new StringBuilder("Malformed URL: ");
                        sb.append(strA);
                        sb.append("; Discarding this tracker");
                    } else {
                        btVar.a(new NativeTracker(strA, 0, NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_CLICK, null));
                    }
                }
            }
        }
    }

    private boolean b(Node node) {
        if (node == null) {
            return false;
        }
        a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_ERROR, b(node, "Error"));
        List<Node> listB = b(node, com.my.target.bj.fX);
        if (listB.isEmpty()) {
            return false;
        }
        return a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_RENDER, listB);
    }

    private void a(List<Node> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (Node node : list) {
            String attribute = ((Element) node).getAttribute("event");
            if (d.containsKey(attribute)) {
                a(d.get(attribute), node);
            }
        }
    }

    private boolean a(NativeTracker.TrackerEventType trackerEventType, List<Node> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Node node = list.get(i);
                if (node.getNodeType() == 1 && !a(trackerEventType, node)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean a(NativeTracker.TrackerEventType trackerEventType, Node node) {
        String strA = a(node);
        if (!URLUtil.isValidUrl(strA)) {
            StringBuilder sb = new StringBuilder("Malformed URL ");
            sb.append(strA);
            sb.append(" Discarding this tracker");
            return trackerEventType != NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_RENDER;
        }
        this.f.a(new NativeTracker(strA, 0, trackerEventType, null));
        return true;
    }

    private void a(int i) {
        this.f.f = i;
        b(i);
    }

    private void b(int i) {
        com.inmobi.rendering.a.c cVarA = com.inmobi.rendering.a.c.a();
        HashMap map = new HashMap();
        map.put("[ERRORCODE]", String.valueOf(i));
        for (NativeTracker nativeTracker : this.f.d) {
            if (nativeTracker.b == NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_ERROR) {
                cVarA.a(com.inmobi.commons.core.utilities.d.a(nativeTracker.a, map), nativeTracker.c);
            }
        }
    }
}
