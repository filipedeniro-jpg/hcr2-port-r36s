package com.inmobi.ads;

import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: compiled from: IasVastHelper.java */
/* JADX INFO: loaded from: classes2.dex */
class x {
    private static final String a = "x";

    x() {
    }

    static void a(Document document, bx bxVar) {
        List<Node> listA = bu.a(document, "AVID");
        if (listA != null) {
            Iterator<Node> it = listA.iterator();
            while (it.hasNext()) {
                a(it.next(), bxVar);
            }
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static void a(Node node, bx bxVar) {
        String strA;
        while (node.hasChildNodes()) {
            String nodeName = node.getNodeName();
            byte b = -1;
            switch (nodeName.hashCode()) {
                case -2077435339:
                    if (nodeName.equals("AdVerifications")) {
                        b = 1;
                    }
                    break;
                case -1320080837:
                    if (nodeName.equals("Verification")) {
                        b = 2;
                    }
                    break;
                case 2021392:
                    if (nodeName.equals("AVID")) {
                        b = 0;
                    }
                    break;
                case 1561251035:
                    if (nodeName.equals("JavaScriptResource")) {
                        b = 3;
                    }
                    break;
            }
            if (b == 0) {
                node = bu.a(node, "AdVerifications");
                if (node == null) {
                    return;
                }
            } else {
                if (b == 1) {
                    if (node.hasChildNodes()) {
                        NodeList childNodes = node.getChildNodes();
                        int length = childNodes.getLength();
                        for (int i = 0; i < length; i++) {
                            a(childNodes.item(i), bxVar);
                        }
                        return;
                    }
                    return;
                }
                if (b != 2) {
                    if (b == 3 && (strA = bu.a(node)) != null) {
                        bxVar.a(new NativeTracker(strA, 0, NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_IAS, null));
                        return;
                    }
                    return;
                }
                node = bu.a(node, "JavaScriptResource");
                if (node == null) {
                    return;
                }
            }
        }
    }
}
