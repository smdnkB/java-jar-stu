package com.liu.minio;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Test {

    public static void main(String[] args) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        String bucketName = "my-files01";
        // 初始化客户端
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://192.168.0.8:9000")
                .credentials("root", "12345678")
                .build();

        BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder()
                .bucket(bucketName).build();

        boolean b = minioClient.bucketExists(bucketExistsArgs);
        System.out.println(b);
        if (!b){
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }


    }
}
