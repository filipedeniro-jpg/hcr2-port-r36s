package com.applovin.impl.sdk.utils;

import android.util.Xml;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes.dex */
public class p {
    private final com.applovin.impl.sdk.n a;
    private final boolean b;
    private Stack<a> c;
    private StringBuilder d;
    private long e;
    private a f;

    private static class a extends o {
        a(String str, Map<String, String> map, o oVar) {
            super(str, map, oVar);
        }

        void a(o oVar) {
            if (oVar == null) {
                throw new IllegalArgumentException("None specified.");
            }
            this.c.add(oVar);
        }

        void d(String str) {
            this.b = str;
        }
    }

    p(com.applovin.impl.sdk.h hVar) {
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        this.a = hVar.w();
        this.b = ((Boolean) hVar.a(com.applovin.impl.sdk.b.b.eU)).booleanValue();
    }

    public static o a(String str, com.applovin.impl.sdk.h hVar) throws SAXException {
        return new p(hVar).a(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> a(Attributes attributes) {
        if (attributes == null) {
            return Collections.emptyMap();
        }
        int length = attributes.getLength();
        HashMap map = new HashMap(length);
        for (int i = 0; i < length; i++) {
            map.put(attributes.getQName(i), attributes.getValue(i));
        }
        return map;
    }

    public o a(String str) throws SAXException {
        if (str == null) {
            throw new IllegalArgumentException("Unable to parse. No XML specified.");
        }
        this.d = new StringBuilder();
        this.c = new Stack<>();
        this.f = null;
        Xml.parse(str, new ContentHandler() { // from class: com.applovin.impl.sdk.utils.p.1
            @Override // org.xml.sax.ContentHandler
            public void characters(char[] cArr, int i, int i2) throws SAXException {
                if (!p.this.b) {
                    i = 0;
                }
                String strTrim = new String(Arrays.copyOfRange(cArr, i, i2)).trim();
                if (k.b(strTrim)) {
                    p.this.d.append(strTrim);
                }
            }

            @Override // org.xml.sax.ContentHandler
            public void endDocument() throws SAXException {
                long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - p.this.e;
                p.this.a.a("XmlParser", "Finished parsing in " + seconds + " seconds");
            }

            @Override // org.xml.sax.ContentHandler
            public void endElement(String str2, String str3, String str4) throws SAXException {
                p pVar = p.this;
                pVar.f = (a) pVar.c.pop();
                p.this.f.d(p.this.d.toString().trim());
                p.this.d.setLength(0);
            }

            @Override // org.xml.sax.ContentHandler
            public void endPrefixMapping(String str2) throws SAXException {
            }

            @Override // org.xml.sax.ContentHandler
            public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
            }

            @Override // org.xml.sax.ContentHandler
            public void processingInstruction(String str2, String str3) throws SAXException {
            }

            @Override // org.xml.sax.ContentHandler
            public void setDocumentLocator(Locator locator) {
            }

            @Override // org.xml.sax.ContentHandler
            public void skippedEntity(String str2) throws SAXException {
            }

            @Override // org.xml.sax.ContentHandler
            public void startDocument() throws SAXException {
                p.this.a.a("XmlParser", "Begin parsing...");
                p.this.e = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
            }

            @Override // org.xml.sax.ContentHandler
            public void startElement(String str2, String str3, String str4, Attributes attributes) throws SAXException {
                try {
                    a aVar = p.this.c.isEmpty() ? null : (a) p.this.c.peek();
                    a aVar2 = new a(str3, p.this.a(attributes), aVar);
                    if (aVar != null) {
                        aVar.a(aVar2);
                    }
                    p.this.c.push(aVar2);
                } catch (Exception e) {
                    p.this.a.b("XmlParser", "Unable to process element <" + str3 + ">", e);
                    throw new SAXException("Failed to start element", e);
                }
            }

            @Override // org.xml.sax.ContentHandler
            public void startPrefixMapping(String str2, String str3) throws SAXException {
            }
        });
        a aVar = this.f;
        if (aVar != null) {
            return aVar;
        }
        throw new SAXException("Unable to parse XML into node");
    }
}
