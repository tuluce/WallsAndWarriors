package com.oops.wallsandwarriors.util;

import com.oops.wallsandwarriors.game.model.ChallengeData;

import java.io.*;
import java.util.Base64;

/**
 * Created by caglasozen on 10/29/18.
 */
public class EncodeUtil {

    private static String base64;
    private static ChallengeData data;
    private static int challengeNo = 0;

    public static String encode(ChallengeData toEncode) throws FileNotFoundException, IOException
    {
        challengeNo++;
        String tag = "chl"+challengeNo+".txt";
        PrintWriter out = new PrintWriter(tag);

        data = toEncode;
        byte[] inBytes = getbyteStream(data);

        base64 = Base64.getEncoder().encodeToString(inBytes);
        out.println(base64);
        return base64;
    }



    private static byte[] getbyteStream(ChallengeData toByte) throws IOException
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
                    System.out.println("BYTE STREAMING EXCEPTION");
                }
            }
        }
    }
}