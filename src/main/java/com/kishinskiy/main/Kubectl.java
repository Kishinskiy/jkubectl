package com.kishinskiy.main;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.util.Config;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Kubectl {

    List<String> getPods(String... namespaces) throws IOException, ApiException {
        String n = null;
        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);
        CoreV1Api api = new CoreV1Api();

        for ( String namespace : namespaces) {
            if (namespace == null) {
               n = "default";
            } else {
                n = namespaces[0];
            }
        }
       V1PodList list =api.listNamespacedPod (
                 n,
                true,
                null,
                null,
                null,
                null,
                null,
                null,
                5,
                null
        );

        return list.getItems()
                .stream()
                .map(__ -> __.getMetadata().getName())
                .collect(Collectors.toCollection(ArrayList::new));
    }
}