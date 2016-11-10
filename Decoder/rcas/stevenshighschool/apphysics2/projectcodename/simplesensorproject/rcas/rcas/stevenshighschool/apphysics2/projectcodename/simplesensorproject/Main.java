package rcas.stevenshighschool.apphysics2.projectcodename.simplesensorproject;

import java.io.*;
import java.util.ArrayList;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class Main {

    //this is the singular function
    public static void main(String[] args) {
        //
        ArrayList<DataPoint> dataPoint;
        try {
            //deserializes data from file provided in args[0]
            FileInputStream fileIn = new FileInputStream(args[0]);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            //reads object, converts to our array
            Object e = in.readObject();
            in.close();
            fileIn.close();
            System.out.println(e.getClass());
            System.out.println(e.toString());
            dataPoint = (ArrayList<DataPoint>) e;
        }catch (Exception c) {
            //generic exception catch
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }
        try {
            //prints to csv--don't worry about this stuff
            PrintWriter pw = new PrintWriter(new File("data.csv"));
            StringBuilder sb = new StringBuilder();
            sb.append("n");
            sb.append(',');
            sb.append("a_x");
            sb.append(',');
            sb.append("a_y");
            sb.append(',');
            sb.append("a_z");
            sb.append(',');
            sb.append("p");
            sb.append(',');
            sb.append("time");
            sb.append('\n');

            Namespace ns = Namespace.getNamespace("http://www.opengis.net/kml/2.2");
            Document kml = new Document();
            Element root = new Element("kml", ns);
            kml.setRootElement(root);
            root.addNamespaceDeclaration(ns);
            Element doc = new Element("Document");
            int iter = 0;
            for (DataPoint pnt : dataPoint) {
                //also prints it to command line, not necessary in production
                System.out.println(pnt.time);
                System.out.println("A_X: " + pnt.a_x + ", A_Y: " + pnt.a_y + ", A_Z:" + pnt.a_z + ", P: " + pnt.p);
                System.out.println("lat: " + pnt.lat + ", lon: " + pnt.lon + ", alt:" + pnt.alt);
                System.out.println("====");
                sb.append(iter);
                iter++;
                sb.append(',');
                sb.append(pnt.a_x);
                sb.append(',');
                sb.append(pnt.a_y);
                sb.append(',');
                sb.append(pnt.a_z);
                sb.append(',');
                sb.append(pnt.p);
                sb.append(',');
                sb.append(pnt.time);
                sb.append('\n');
                Element child = new Element("Placemark");
                child.addContent(new Element("name").addContent(dataPoint.indexOf(pnt)+""));
                child.addContent(new Element("description").addContent(pnt.time+""));
                Element point = new Element("Point");
                point.addContent(new Element("coordinates").addContent(pnt.lon+","+pnt.lat+","+pnt.alt));
                point.addContent(new Element("altitudeMode").addContent("absolute"));
                child.addContent(point);
                doc.addContent(child);
            }
            pw.write(sb.toString());
            pw.close();
            root.addContent(doc);
            XMLOutputter outter = new XMLOutputter();
            outter.setFormat(Format.getPrettyFormat());
            outter.output(kml, new FileWriter(new File("data.kml")));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
