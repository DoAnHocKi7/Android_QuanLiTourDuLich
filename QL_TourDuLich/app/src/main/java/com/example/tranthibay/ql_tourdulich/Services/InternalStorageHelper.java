package com.example.tranthibay.ql_tourdulich.Services;

import android.content.Context;
import android.util.Xml;

import com.example.tranthibay.ql_tourdulich.Model.DangNhap.LoginModel;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class InternalStorageHelper {
    public static void luuDulieuMacDinh(String fileName, String content, Context context) {
        FileOutputStream stream = null;
        try {
            stream = context.openFileOutput( fileName, Context.MODE_PRIVATE );
            stream.write( content.getBytes() );
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String docDuLieuMacDinh(String fileName, Context context) {
        StringBuffer buffer = new StringBuffer();
        try {
            FileInputStream inputStream = context.getApplicationContext().openFileInput( fileName );
            BufferedReader reader = new BufferedReader( new InputStreamReader( inputStream ) );
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append( line );
            }
        } catch (Exception e) {

        }
        return buffer.toString();
    }

    public static boolean kiemTraFileTonTai(String fileName, Context context) {
        File file = context.getApplicationContext().getFileStreamPath( fileName );
        return file.exists();
    }

    public static void taoFileXML(Context context) {
        String filename = "file.xml";

        FileOutputStream fos;

        try {
            fos = context.getApplicationContext().openFileOutput( filename, Context.MODE_PRIVATE );
            XmlSerializer serializer = Xml.newSerializer();
            serializer.setOutput( fos, "UTF-8" );
            serializer.startDocument( null, Boolean.valueOf( true ) );
            serializer.setFeature( "http://xmlpull.org/v1/doc/features.html#indent-output", true );
            serializer.startTag( null, "root" );
            for (int j = 0; j < 3; j++) {
                serializer.startTag( null, "record" );

                serializer.text( "TAMSON" );

                serializer.endTag( null, "record" );
            }
            serializer.endDocument();

            serializer.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> docFileXML(Context context, String filename) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        String data;
        try {
            fis = context.openFileInput( filename );
            isr = new InputStreamReader( fis );
            char[] inputBuffer = new char[fis.available()];
            isr.read( inputBuffer );
            data = new String( inputBuffer );
            isr.close();
            fis.close();

            Document doc = InternalStorageHelper.loadXMLFromString( data );
            Node n = doc.getFirstChild();
            NodeList nl = n.getChildNodes();
            Node an,an2;
            for (int i = 0; i <nl.getLength() ; i++) {
                an=nl.item( i );
                String val=an.getNodeValue();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Document loadXMLFromString(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource( new StringReader( xml ) );
        return builder.parse( is );
    }
}
