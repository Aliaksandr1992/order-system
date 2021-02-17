package com.epam.orderingsystem.parser;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.io.Reader;

public class StaxXmlProcessor implements AutoCloseable{

    private static XMLInputFactory FACTORY = XMLInputFactory.newInstance();
    private XMLStreamReader reader;

    public StaxXmlProcessor(Reader reader1) throws XMLStreamException
    {
        reader = FACTORY.createXMLStreamReader(reader1);
    }

    public XMLStreamReader getReader()
    {
        return reader;
    }

    @Override
    public void close()
    {
        if (reader != null)
        {
            try {
                reader.close();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
    }
}
