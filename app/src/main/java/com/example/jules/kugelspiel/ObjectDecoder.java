package com.example.jules.kugelspiel;

import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by jules on 05.01.2017.
 */

public class ObjectDecoder {
    /*
    public static void main( String [] args )  throws IOException,

            ClassNotFoundException {
        String string = toString( new SomeClass() );
        System.out.println(" Encoded serialized version " );
        System.out.println( string );
        SomeClass some = ( SomeClass ) fromString( string );
        System.out.println( "\n\nReconstituted object");
        System.out.println( some );
    }
    */

    /** Read the object from Base64 string. */
    public Object fromString( String s ) throws IOException ,
            ClassNotFoundException {
        byte [] data = Base64.decode( s, Base64.DEFAULT );
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(  data ) );
        Object o  = ois.readObject();
        ois.close();
        return o;
    }

    /** Write the object to a Base64 string. */
    public String toString( Serializable o ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( o );
        oos.close();
        return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
    }
}
