package com.oops.wallsandwarriors.util;

import com.oops.wallsandwarriors.game.model.ChallengeData;

import java.io.*;
import java.util.Base64;

public class EncodeUtils {

    private static String base64;
    private static ChallengeData data;

    public static String encode(ChallengeData toEncode) throws FileNotFoundException, IOException
    {
        String tag = toEncode.getName() +".txt";
        PrintWriter out = new PrintWriter(tag);


        data = toEncode;
        byte[] inBytes = getByteStream(data);

        base64 = Base64.getEncoder().encodeToString(inBytes);
        out.println(base64);
        out.close();
        return base64;
    }

    public static byte[] getByteStream(ChallengeData toByte) throws IOException
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
                    System.out.println("TO BYTE STREAMING EXCEPTION");
                }
            }
        }
    }

    public static ChallengeData decode(String toDecode) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        byte[] byteArray = Base64.getDecoder().decode(toDecode);
        data = fromByteStream(byteArray);

        String tag = data.getName() +".txt";
        PrintWriter out = new PrintWriter(tag);
        out.println(toDecode);
        out.close();

        return data;

    }

    private static ChallengeData fromByteStream(byte[] toObj) throws IOException,ClassNotFoundException
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
                System.out.println("FROM BYTE STREAMING EXCEPTION");
            }
        }
    }

}
