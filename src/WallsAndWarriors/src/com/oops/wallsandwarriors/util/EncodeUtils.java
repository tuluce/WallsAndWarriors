package com.oops.wallsandwarriors.util;

import com.oops.wallsandwarriors.model.ChallengeData;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import java.util.Base64;

/**
 * A class to convert ChallengeData into Base64 code and vice-versa to provide mobility 
 * and easy storage for challenges of all types.
 * @author OOPs
 */
public class EncodeUtils {

    private static String base64;
    private static ChallengeData data;

    /**
     * Method to encode Challenge game object into Base64 String.
     * @param  toEncode  ChallengeData of the object to be encoded into Base64 String
     * @return Encoded String version of the object.
     * @throws IOException
     */
    public static String encode(ChallengeData toEncode) throws IOException
    {
        data = toEncode;
        byte[] inBytes = getByteStream(data);
        base64 = Base64.getEncoder().encodeToString(inBytes);
        
        return base64;
    }

    /**
     * Method to get byte stream of a ChallengeData object
     * @param toByte, Challenge data object
     * @return objInBytes, bytes of ChallengeData object
     * @throws IOException
     */
    private static byte[] getByteStream(ChallengeData toByte) throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(toByte);
            out.flush();
            byte[] objInBytes = bos.toByteArray();
            return objInBytes;
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Method to decode Base64 String into Challenge game object.
     * @param  toDecode  Base64 String encoding of the object.
     * @return ChallengeData version of the object.
     * @throws ClassNotFoundException if a class exception occurs during conversion.
     * @throws FileNotFoundException if a file exception occurs during reading from file.
     * @throws IOException if an input or output exception occurs during conversion.
     */
    public static ChallengeData decode(String toDecode)
            throws FileNotFoundException, IOException, ClassNotFoundException
    {
        byte[] byteArray = Base64.getDecoder().decode(toDecode);
        data = fromByteStream(byteArray);

        return data;

    }

    private static ChallengeData fromByteStream(byte[] toObj)
            throws IOException,ClassNotFoundException
    {
        ByteArrayInputStream bis = new ByteArrayInputStream(toObj);
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            Object o = in.readObject();
            return (ChallengeData) o;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
