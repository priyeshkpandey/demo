package com.vida.test;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.MapConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.FileLocator;
import org.apache.commons.configuration2.plist.XMLPropertyListConfiguration;
import org.apache.commons.configuration2.tree.ImmutableNode;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class XMLPropertyListConfigurationExt  extends XMLPropertyListConfiguration {

    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private FileLocator locator;

    public void initFileLocator(FileLocator locator) {
        this.locator = locator;
    }

    public void write(Writer out) throws ConfigurationException {
        PrintWriter writer = new PrintWriter(out);
        if (this.locator.getEncoding() != null) {
            writer.println("<?xml version=\"1.0\" encoding=\"" + this.locator.getEncoding() + "\"?>");
        } else {
            writer.println("<?xml version=\"1.0\"?>");
        }

        writer.println("<!DOCTYPE plist SYSTEM \"file://localhost/System/Library/DTDs/PropertyList.dtd\">");
        writer.println("<plist version=\"1.0\">");
        this.printNode(writer, 1, (ImmutableNode)this.getNodeModel().getNodeHandler().getRootNode());
        writer.println("</plist>");
        writer.flush();
    }

    private void printNode(PrintWriter out, int indentLevel, ImmutableNode node) {
        String padding = StringUtils.repeat(" ", indentLevel * 4);
        if (node.getNodeName() != null) {
            out.println(padding + "<key>" + StringEscapeUtils.escapeXml10(node.getNodeName()) + "</key>");
        }

        List<ImmutableNode> children = node.getChildren();
        if (!children.isEmpty()) {
            out.println(padding + "<dict>");
            Iterator it = children.iterator();

            while(it.hasNext()) {
                ImmutableNode child = (ImmutableNode)it.next();
                this.printNode(out, indentLevel + 1, child);
                if (it.hasNext()) {
                    out.println();
                }
            }

            out.println(padding + "</dict>");
        } else if (node.getValue() == null) {
            out.println(padding + "<dict/>");
        } else {
            Object value = node.getValue();
            this.printValue(out, indentLevel, value);
        }

    }

    private void printValue(PrintWriter out, int indentLevel, Object value) {
        String padding = StringUtils.repeat(" ", indentLevel * 4);
        if (value instanceof Date) {
            synchronized(format) {
                out.println(padding + "<date>" + format.format((Date)value) + "</date>");
            }
        } else if (value instanceof Calendar) {
            this.printValue(out, indentLevel, ((Calendar)value).getTime());
        } else if (value instanceof Number) {
            if (!(value instanceof Double) && !(value instanceof Float) && !(value instanceof BigDecimal)) {
                out.println(padding + "<integer>" + value.toString() + "</integer>");
            } else {
                out.println(padding + "<real>" + value.toString() + "</real>");
            }
        } else if (value instanceof Boolean) {
            if ((Boolean)value) {
                out.println(padding + "<true/>");
            } else {
                out.println(padding + "<false/>");
            }
        } else if (value instanceof List) {
            out.println(padding + "<array>");
            Iterator var5 = ((List)value).iterator();

            while(var5.hasNext()) {
                Object o = var5.next();
                this.printValue(out, indentLevel + 1, o);
            }

            out.println(padding + "</array>");
        } else if (value instanceof HierarchicalConfiguration) {
            HierarchicalConfiguration<ImmutableNode> config = (HierarchicalConfiguration)value;
            this.printNode(out, indentLevel, (ImmutableNode)config.getNodeModel().getNodeHandler().getRootNode());
        } else if (value instanceof Configuration) {
            out.println(padding + "<dict>");
            Configuration config = (Configuration)value;
            Iterator it = config.getKeys();

            while(it.hasNext()) {
                String key = (String)it.next();
                ImmutableNode node = (new ImmutableNode.Builder()).name(key).value(config.getProperty(key)).create();
                this.printNode(out, indentLevel + 1, node);
                if (it.hasNext()) {
                    out.println();
                }
            }

            out.println(padding + "</dict>");
        } else if (value instanceof Map) {
            Map<String, Object> map = transformMap((Map)value);
            this.printValue(out, indentLevel, new MapConfiguration(map));
        } else if (value instanceof byte[]) {
            String base64;
            try {
                base64 = new String(Base64.encodeBase64((byte[])((byte[])value)), "UTF-8");
            } catch (UnsupportedEncodingException var9) {
                throw new AssertionError(var9);
            }

            out.println(padding + "<data>" + StringEscapeUtils.escapeXml10(base64) + "</data>");
        } else if (value != null) {
            out.println(padding + "<string>" + StringEscapeUtils.escapeXml10(String.valueOf(value)) + "</string>");
        } else {
            out.println(padding + "<string/>");
        }

    }

    private static Map<String, Object> transformMap(Map<?, ?> src) {
        Map<String, Object> dest = new HashMap();
        Iterator var2 = src.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<?, ?> e = (Map.Entry)var2.next();
            if (e.getKey() instanceof String) {
                dest.put((String)e.getKey(), e.getValue());
            }
        }

        return dest;
    }

}
