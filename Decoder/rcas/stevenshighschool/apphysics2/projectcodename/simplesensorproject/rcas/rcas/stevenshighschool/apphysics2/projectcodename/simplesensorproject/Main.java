package rcas.stevenshighschool.apphysics2.projectcodename.simplesensorproject;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import rcas.stevenshighschool.apphysics2.projectcodename.simplesensorproject.*;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
    ArrayList<DataPoint> dataPoint;
	try {
            FileInputStream fileIn = new FileInputStream(args[0]);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object e = in.readObject();
            in.close();
            fileIn.close();
            System.out.println(e.getClass());
            System.out.println(e.toString());
            dataPoint = (ArrayList<DataPoint>) e;
        }catch (Exception c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }
        for(DataPoint pnt: dataPoint){
            System.out.println(pnt.time);
            System.out.println("A_X: "+pnt.a_x+", A_Y: "+pnt.a_y+", A_Z:"+pnt.a_z +", P: "+pnt.p);
            System.out.println("lat: "+pnt.lat+", lon: "+pnt.lon+", alt:"+pnt.alt);
            System.out.println("====");
        }
    }
}
