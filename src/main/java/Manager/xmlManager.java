package Manager;

import Queries.xmlDDL;
import Exception.IncorrectSchemaException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class xmlManager {
    private final ArrayList<String> columnsArray ;
    private final ArrayList<String> columnsArrayValues ;
    private String queryType;
    private String constraint;
    xmlDDL xmlDDL = new xmlDDL();

    public xmlManager(){
        columnsArray = new ArrayList<>();
        columnsArrayValues = new ArrayList<>();


    }

    public String ParserXML(String file){
        try{
            File inputFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.parse(inputFile);
            queryType = doc.getDocumentElement().getNodeName();
            String tableName = doc.getDocumentElement().getChildNodes().item(1).getNodeName();
            //System.out.println(queryType + " query on table " + tableName);
            //System.out.println(tableName);
            NodeList nodeList = doc.getElementsByTagName(tableName);
            for (int i = 0; i < nodeList.getLength(); i++) {
                NodeList columnList = nodeList.item(i).getChildNodes();
                for (int j = 0; j < columnList.getLength(); j++) {
                    Node nNode = nodeList.item(i);
                    String column = columnList.item(j).getNodeName();
                    if (!column.equals("#text")) {
                        Element  element = (Element) nNode;
                        //String ColumnName = columnList.item(j).getNodeName();
                        String ColumnValue = element.getElementsByTagName(columnList.item(j).getNodeName())
                                .item(0).getTextContent();
                        columnsArray.add(column);
                        columnsArrayValues.add(ColumnValue);
                    }
                }

            }
            doc.getDocumentElement().normalize();
            return tableName;

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }


    public String createParserXML(String file){
        try{
            File inputFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.parse(inputFile);
            queryType = doc.getDocumentElement().getNodeName();
            String tableName = doc.getDocumentElement().getChildNodes().item(1).getNodeName();
            System.out.println(queryType.toUpperCase() + " query on table " + tableName.toUpperCase());
            //System.out.println(tableName);
            NodeList nodeList = doc.getElementsByTagName(tableName);
            for (int i = 0; i < nodeList.getLength(); i++) {
                NodeList columnList = nodeList.item(i).getChildNodes();
//                String attribute = nodeList.item(i).getAttributes().getNamedItem("name").getNodeValue();
//                System.out.println(attribute);
                for (int j = 0; j < columnList.getLength(); j++) {
                    Node nNode = nodeList.item(i);
                    String column = columnList.item(j).getNodeName();
                    String idCheck = "id";
                    if (!column.equals("#text")) {
                        Element element = (Element) nNode;
                        if(column.toLowerCase().indexOf(idCheck.toLowerCase()) != 1 && columnList.item(j).hasAttributes() ){
                            System.out.print("Has id with ");
                            constraint = columnList.item(j).getAttributes().getNamedItem("name").getNodeValue();
                            System.out.println(constraint + " constraint");
                        }
                        String ColumnValue = element.getElementsByTagName(columnList.item(j).getNodeName())
                                .item(0).getTextContent();
                        columnsArray.add(column);
                        columnsArrayValues.add(ColumnValue);
                    }
                }


            }


            doc.getDocumentElement().normalize();
            //dao(columnsArray, columnsArrayValues, tableName, constraint);
            return tableName;

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }


    public boolean create(String file){
        String tableName = createParserXML(file);

        try {
            System.out.println("   \n\n");
            System.out.println(tableName);
            if (queryType.equals("create")) {
                for (int i = 0; i < columnsArray.size(); i++) {
                    System.out.print(columnsArray.get(i) + "   ");
                    System.out.println(columnsArrayValues.get(i));
                }
            } else {
                //System.out.println("Not an add file schema");
                throw new IncorrectSchemaException();
            }
            //return true;
            return xmlDDL.createTable(tableName, columnsArray, columnsArrayValues, constraint);
        }catch(IncorrectSchemaException e){
            System.out.println("Check1");
            e.printStackTrace();
            return false;
        }
    }

    public boolean drop(String input) {
        String tableName = ParserXML(input);;

        try {
            System.out.println("   \n\n");
            if (input.indexOf('\\') != -1) {
                //System.out.println("String is a file " + input);
                //tableName = ParserXML(input);
                if (queryType.equals("drop")) {
                    for (int i = 0; i < columnsArray.size(); i++) {
                        System.out.print(columnsArray.get(i) + "   ");
                        System.out.println(columnsArrayValues.get(i));
                        return xmlDDL.dropTable(tableName);
                    }
                } else {
                    //System.out.println("Not a drop file schema");
                    throw new IncorrectSchemaException();

                }
            } else {
                //System.out.println("String is a table name " + input);
                return xmlDDL.dropTable(input);
            }
            return false;
        }catch(IncorrectSchemaException e){
            //System.out.println("Check1");
            e.printStackTrace();
            return false;
        }
    }
}
