package org.example.kuber.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author renc
 */
public class TokenGenTest {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/renc/iCoder/IdeaProjects/kuber/kuber-controller/src/test/resources/credentials.txt");
        Files.readAllLines(path).forEach(row -> {
            String[] strings = row.split(",");
            Algorithm algorithm = Algorithm.HMAC256(strings[2]);
            String token = JWT.create()
                    .withIssuer(strings[1])
                    .sign(algorithm);
            System.out.printf(strings[0] + "," + token + "\n");
        });
    }
}
