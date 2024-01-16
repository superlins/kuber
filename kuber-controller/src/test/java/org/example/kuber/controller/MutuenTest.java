package org.example.kuber.controller;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

/**
 * @author renc
 */
public class MutuenTest {

    static HashFunction murmur3 = Hashing.murmur3_32();

    public static void main(String[] args) {
        String userStr = "e7ed2a65-77c5-474c-8332-f23aea2020d8#72";

        HashCode hashCode = murmur3.hashString(userStr, StandardCharsets.UTF_8);
        String num = String.valueOf(String.format("%1$04d", (Math.abs(hashCode.padToLong()) % 8)));
        int total = Integer.parseInt(num);
        int reminder = total % 256;
        String suffix = String.valueOf(total / 256);
        String hex = Integer.toHexString(reminder);
        String rowKey;
        if (reminder < 16) {
            rowKey = "0" + hex + suffix + ":" + userStr;
        } else {
            rowKey = hex + suffix + ":" + userStr;
        }
        System.out.println(rowKey);
    }
}
